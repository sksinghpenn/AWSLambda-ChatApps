package com.lifotech.awslambda.chat.conversations;

import java.io.Serializable;

public class ChatConversationPostRequestModel implements Serializable {

    private String[] users;

    private String cognitoUsername;

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }

    public String getCognitoUsername() {
        return cognitoUsername;
    }

    public void setCognitoUsername(String cognitoUsername) {
        this.cognitoUsername = cognitoUsername;
    }
}
