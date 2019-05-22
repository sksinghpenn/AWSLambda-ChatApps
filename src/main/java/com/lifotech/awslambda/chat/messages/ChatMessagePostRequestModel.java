package com.lifotech.awslambda.chat.messages;

import java.io.Serializable;

public class ChatMessagePostRequestModel implements Serializable {

    private String id;
    private String cognitoUsername;
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
