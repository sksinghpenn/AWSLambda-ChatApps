package com.lifotech.awslambda.chat.conversations;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.lifotech.awslambda.chat.conversation.ChatConversation;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ChatConversationPostRequestHandler implements RequestHandler<ChatConversationPostRequestModel, ChatConversationPostResponseModel> {

    @Override
    public ChatConversationPostResponseModel handleRequest(ChatConversationPostRequestModel chatConversationPostRequestModel, Context context) {


        LambdaLogger logger = context.getLogger();

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

        DynamoDBMapper mapper = new DynamoDBMapper(client);

        String[] users =chatConversationPostRequestModel.getUsers();

        List<String> usersList = Arrays.asList(users);

        usersList.add(chatConversationPostRequestModel.getCognitoUsername());


        for(String user: usersList) {
            ChatConversation chatConversation = new ChatConversation();
            chatConversation.setConversationId(UUID.randomUUID().toString());
            chatConversation.setUserName(user);


            mapper.save(chatConversation);
        }

        return null;
    }
}
