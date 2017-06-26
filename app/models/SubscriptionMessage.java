package models;

/**
 * Created by jerem on 6/24/2017.
 */
public class SubscriptionMessage {

    private int id;

    private Message message;

    private String method;

    /**
     * Default constructor for json serialization
     */
    public SubscriptionMessage(){}

    public SubscriptionMessage(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public Message getMessage(){
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
