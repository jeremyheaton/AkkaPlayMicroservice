package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jerem on 6/23/2017.
 */
public class Subscription {

    private HashMap<String, Integer> subscribedMessages;

    /**
     * Default constructor for json serialization
     */
    public Subscription(){}

    public Subscription(HashMap<String, Integer> subscription){
        this.subscribedMessages = subscription;
    }

    public HashMap<String, Integer> getSubscribedMessages() {
        return subscribedMessages;
    }

    public void setSubscribedMessages(HashMap<String, Integer> subscribedMessages) {
        this.subscribedMessages = subscribedMessages;
    }

}
