package models;

/**
 * Created by jerem on 6/23/2017.
 */
public class Message {

    private String messageType;

    private String method;

    /**
     * Default constructor for json serialization
     */
    public Message(){}

    public Message(String messageType){
        this.messageType = messageType;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }



}
