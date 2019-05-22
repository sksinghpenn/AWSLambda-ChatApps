package com.lifotech.awslambda.chat.conversation;

public class ChatConversationModel {

    private String id;
    private long last;
    private String[] participants;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getLast() {
        return last;
    }

    public void setLast(long last) {
        this.last = last;
    }

    public String[] getParticipants() {
        return participants;
    }

    public void setParticipants(String[] participants) {
        this.participants = participants;
    }
}
