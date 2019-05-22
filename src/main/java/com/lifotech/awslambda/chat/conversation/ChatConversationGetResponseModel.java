package com.lifotech.awslambda.chat.conversation;

import java.io.Serializable;

public class ChatConversationGetResponseModel implements Serializable {


    private ChatConversationModel[] chatConversationModels;


    public ChatConversationModel[] getChatConversationModels() {
        return chatConversationModels;
    }

    public void setChatConversationModels(ChatConversationModel[] chatConversationModels) {
        this.chatConversationModels = chatConversationModels;
    }
}
