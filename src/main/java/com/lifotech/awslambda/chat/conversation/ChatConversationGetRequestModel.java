package com.lifotech.awslambda.chat.conversation;

import java.io.Serializable;

public class ChatConversationGetRequestModel implements Serializable{

    private String cognitoUsername;

    public String getCognitoUsername() {
        return cognitoUsername;
    }

    public void setCognitoUsername(String cognitoUsername) {
        this.cognitoUsername = cognitoUsername;
    }
}
