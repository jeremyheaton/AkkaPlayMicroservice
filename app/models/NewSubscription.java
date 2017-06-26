package models;

import java.util.HashMap;
import java.util.List;

/**
 * Created by jerem on 6/24/2017.
 */
public class NewSubscription {

    private List<Message> subscription;

    /**
     * Default constructor for json serialization
     */
    public NewSubscription(){}

    public List<Message> getSubscription() {
        return subscription;
    }

    public void setSubscription(List<Message> subscription) {
        this.subscription = subscription;
    }

}
