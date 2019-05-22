package com.lifotech.awslambda.chat.messages;

import java.io.Serializable;

public class ChatMessageGetRequestModel implements Serializable {

    private String id;

    private String cognitoUsername;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCognitoUsername() {
        return cognitoUsername;
    }

    public void setCognitoUsername(String cognitoUsername) {
        this.cognitoUsername = cognitoUsername;
    }
}
