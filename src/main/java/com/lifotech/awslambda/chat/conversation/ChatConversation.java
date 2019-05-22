package com.lifotech.awslambda.chat.conversation;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Chat-Conversations")
public class ChatConversation {

    private String conversationId;
    private String userName;


    @DynamoDBHashKey(attributeName = "ConversationId")
    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    @DynamoDBAttribute(attributeName = "Username")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "ChatConversation{" +
                "conversationId='" + conversationId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
