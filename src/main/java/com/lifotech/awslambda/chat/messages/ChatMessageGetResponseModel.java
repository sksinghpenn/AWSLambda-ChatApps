package com.lifotech.awslambda.chat.messages;

import java.io.Serializable;

public class ChatMessageGetResponseModel implements Serializable {

    private String id;
    private String[] participants;
    private long last;
    private MessageModel[] messages;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getParticipants() {
        return participants;
    }

    public void setParticipants(String[] participants) {
        this.participants = participants;
    }

    public long getLast() {
        return last;
    }

    public void setLast(long last) {
        this.last = last;
    }

    public MessageModel[] getMessages() {
        return messages;
    }

    public void setMessages(MessageModel[] messages) {
        this.messages = messages;
    }
}
