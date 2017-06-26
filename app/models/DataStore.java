package models;

import javax.inject.Singleton;
import java.util.*;

/**
 * Created by jerem on 6/23/2017.
 */
@Singleton
public class DataStore {

    private int id = 1;
    private HashMap<Integer, HashMap<String, Integer>> subscriptions = new HashMap<>();
    private HashMap<String, HashSet<Integer>> messages = new HashMap<>();

    /**
     * Method that adds a new supscription with all accepted message
     * types to the supscriptions map. Then Adds all new message types
     * to the messages with a set of all supscriptions that are subscribed
     * to the message
     * @param newSubscription supscription object
     * @return new supscription id
     */
    public int addSubscription(NewSubscription newSubscription){
        if (!subscriptions.containsKey(id)) {
            HashMap<String, Integer> subscriptionMessages = new HashMap<>();
            newSubscription.getSubscription().stream().forEach(message-> {
                if (!messages.containsKey(message.getMessageType())) {
                    messages.put(message.getMessageType(), new HashSet<>(Arrays.asList(id)));
                } else{
                    messages.get(message.getMessageType()).add(id);
                }
                subscriptionMessages.put(message.getMessageType(), 0);
            });

            subscriptions.put(id, subscriptionMessages);
            return id++;
        }
        return 0;
    }

    /**
     * Method that accepts a message, looks up all subscribed subscriptions
     * then notifies those subscriptions of the message
     * @param message
     * @return Message
     */
    public Message postMessage(Message message){
        if(messages.containsKey(message.getMessageType())){
            messages.get(message.getMessageType()).stream().forEach(subscription -> {
                subscriptions.get(subscription).put(message.getMessageType(),
                        subscriptions.get(subscription).get(message.getMessageType()) + 1);
            });
            return new Message("added messages to supscriptions");
        }
        return new Message("failed to add messages to supscription");
    }

    /**
     * Method that finds all subscriptions by the subscription Id
     * @param subscriptonId
     * @return Subscription object
     */
    public Subscription getSubscription(int subscriptonId){
        return new Subscription(subscriptions.get(subscriptonId));
    }

    /**
     * Method to add a message to a subscription. If the message doesn't exist,
     * add it to the message map with related subscription.
     * @param subscriptionMessage
     * @return
     */
    public Message addSubscriptionMessages(SubscriptionMessage subscriptionMessage){
        if(subscriptions.containsKey(subscriptionMessage.getId())){
            HashMap<String, Integer> map = subscriptions.get(subscriptionMessage.getId());
            if(!messages.containsKey(subscriptionMessage.getMessage().getMessageType())) {
                messages.put(subscriptionMessage.getMessage().getMessageType(),
                        new HashSet<>(Arrays.asList(subscriptionMessage.getId())));
            } else {
                messages.get(subscriptionMessage.getMessage().getMessageType()).add(subscriptionMessage.getId());
            }
            if(!map.containsKey(subscriptionMessage.getMessage().getMessageType())){
                map.put(subscriptionMessage.getMessage().getMessageType(), 0);
                return new Message("message:[" + subscriptionMessage.getMessage().getMessageType()
                        + "] added to supscription " + subscriptionMessage.getId());
            }
            return new Message("message already exists under supscription");
        }
        return new Message("supscription does not exist");
    }

    /**
     * Method to remove a message that is subscribed to, also removes the subscription
     * from the message map.
     * @param subscriptionMessage
     * @return
     */
    public Message removeSubscriptionMessages(SubscriptionMessage subscriptionMessage){
        if(subscriptions.containsKey(subscriptionMessage.getId())){
            HashMap<String, Integer> map = subscriptions.get(subscriptionMessage.getId());
            if(map.containsKey(subscriptionMessage.getMessage().getMessageType())){
                map.remove(subscriptionMessage.getMessage().getMessageType());
                messages.get(subscriptionMessage.getMessage().getMessageType()).remove(subscriptionMessage.getId());
                return new Message("message:[" + subscriptionMessage.getMessage().getMessageType()
                        + "] removed from supscription " + subscriptionMessage.getId());
            }
            return new Message("supscription " + subscriptionMessage.getId() +
                    " does not contain " + subscriptionMessage.getMessage().getMessageType());
        }
        return new Message("supscription does not exist");
    }
}
