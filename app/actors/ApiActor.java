package actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import models.*;

import javax.inject.Inject;

/**
 * Created by jerem on 6/24/2017.
 */
public class ApiActor extends AbstractActor{

    DataStore ds;

    @Inject
    public ApiActor(DataStore dataStore) {
        this.ds = dataStore;
        receive(ReceiveBuilder.
                match(NewSubscription.class, s -> sender().tell(ds.addSubscription((NewSubscription) s), self())).
                match(Message.class, s -> sender().tell(ds.postMessage( (Message) s), self())).
                match(SubscriptionMessage.class, s -> {
                    if (s.getMethod() == null) {
                        sender().tell(ds.getSubscription(s.getId()), self());
                    } else {
                        switch (s.getMethod()) {
                            case "add":
                                sender().tell(ds.addSubscriptionMessages(s), self());
                                break;
                            case "remove":
                                sender().tell(ds.removeSubscriptionMessages(s), self());
                                break;
                            default:
                                sender().tell(new Message("method not supported"), self());
                        }
                    }
                }).
                matchAny(o -> sender().tell(new Message("Unable to process reqeust"), self())).build()
        );
    }
}
