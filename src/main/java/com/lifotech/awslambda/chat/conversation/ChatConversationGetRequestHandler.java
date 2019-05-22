package com.lifotech.awslambda.chat.conversation;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.*;

public class ChatConversationGetRequestHandler implements RequestHandler<ChatConversationGetRequestModel, ChatConversationGetResponseModel> {


    @Override
    public ChatConversationGetResponseModel handleRequest(ChatConversationGetRequestModel chatMessagesGetRequestModel, Context context) {

        LambdaLogger logger = context.getLogger();

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);


        DynamoDBQueryExpression<ChatConversation> queryExpression = new DynamoDBQueryExpression<ChatConversation>()
                .withKeyConditionExpression("Username = :username");

        Map<String, AttributeValue> map = new HashMap<>();
        map.put(":username", new AttributeValue(chatMessagesGetRequestModel.getCognitoUsername()));
        queryExpression.setExpressionAttributeValues(map);

        queryExpression.withConsistentRead(false);
        queryExpression.setIndexName("Username-ConversationId-index");

        List<ChatConversation> chatConversationList = mapper.query(ChatConversation.class, queryExpression);


        Map<String, List<String>> mapOfConversationIdUsername = new HashMap<>();

        for (ChatConversation chatConversation : chatConversationList) {
            logger.log("chatConversation " + chatConversation);
            if (mapOfConversationIdUsername.get(chatConversation.getConversationId()) == null) {

                List<String> userNameList = new ArrayList();
                userNameList.add(chatConversation.getUserName());

                mapOfConversationIdUsername.put(chatConversation.getConversationId(), userNameList);
            } else {
                List<String> userNameList = mapOfConversationIdUsername.get(chatConversation.getConversationId());
                userNameList.add(chatConversation.getUserName());

                mapOfConversationIdUsername.put(chatConversation.getConversationId(), userNameList);

            }
        }


        List<ChatConversationModel> list = new ArrayList<>();

        Set<Map.Entry<String, List<String>>> entrySet = mapOfConversationIdUsername.entrySet();

        for (Map.Entry<String, List<String>> entry : entrySet) {
            ChatConversationModel chatConversationModel = new ChatConversationModel();
            String conversationId = entry.getKey();
            chatConversationModel.setId(conversationId);

            //String[] usernames = getUserNames(entry.getKey(), logger);

            chatConversationModel.setParticipants(getUserNames(entry.getKey(), logger));

            ChatMessage chatMessage = getLastMessageOfConversation(entry.getKey(), logger);
            chatConversationModel.setLast(chatMessage.getTimestamp());
            list.add(chatConversationModel);
        }

        ChatConversationModel[] chatConversationModels = new ChatConversationModel[list.size()];

        chatConversationModels = list.toArray(chatConversationModels);

        ChatConversationGetResponseModel chatConversationGetResponseModel = new ChatConversationGetResponseModel();

        chatConversationGetResponseModel.setChatConversationModels(chatConversationModels);

        return chatConversationGetResponseModel;


    }

    private String[] getUserNames(String conversationId, LambdaLogger logger) {


        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);


        ChatConversation partitionKey = new ChatConversation();

        partitionKey.setConversationId(conversationId);
        DynamoDBQueryExpression<ChatConversation> queryExpression = new DynamoDBQueryExpression<ChatConversation>()
                .withHashKeyValues(partitionKey);

        List<ChatConversation> itemList = mapper.query(ChatConversation.class, queryExpression);

        List<String> list = new ArrayList<>();

        for (ChatConversation chatConversation : itemList) {
            list.add(chatConversation.getUserName());
        }

        String[] userNames = new String[itemList.size()];
        userNames = list.toArray(userNames);

        for (String username : userNames) {
            logger.log("username: " + username + "\n");
        }

        return userNames;


    }

    private ChatMessage getLastMessageOfConversation(String conversationId, LambdaLogger logger) {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);


        ChatMessage partitionKey = new ChatMessage();

        partitionKey.setConversationId(conversationId);
        DynamoDBQueryExpression<ChatMessage> queryExpression = new DynamoDBQueryExpression<ChatMessage>()
                .withHashKeyValues(partitionKey);

        List<ChatMessage> itemList = mapper.query(ChatMessage.class, queryExpression);

        logger.log("last message: " + itemList.get(itemList.size() -1));

        return itemList.get(itemList.size() -1);
    }
}
