package com.lifotech.awslambda.chat.messages;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.lifotech.awslambda.chat.conversation.ChatMessage;

import java.util.Date;

public class ChatMessagePostRequestHandler implements RequestHandler<ChatMessagePostRequestModel, ChatMessagePostResponseModel> {

    @Override
    public ChatMessagePostResponseModel handleRequest(ChatMessagePostRequestModel chatMessagePostRequestModel, Context context) {

        LambdaLogger logger = context.getLogger();

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

        DynamoDBMapper mapper = new DynamoDBMapper(client);


        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setConversationId(chatMessagePostRequestModel.getId());
        chatMessage.setSender(chatMessagePostRequestModel.getCognitoUsername());
        Date date = new Date();
        chatMessage.setTimestamp(date.getTime());
        chatMessage.setMessage(chatMessagePostRequestModel.getMessage());


        mapper.save(chatMessage);

        return null;
    }
}




    /*'use strict';

    var AWS = require('aws-sdk');

    var dynamo = new AWS.DynamoDB();

    exports.handler = function (event, context, callback) {
        dynamo.putItem({
                TableName: 'Chat-Messages',
                Item: {
            ConversationId: {S: event.id},
            Timestamp: {
                N: "" + new Date().getTime()
            },
            Message: {S: event.message},
            Sender: {S: event.cognitoUsername}
        }
    }, function(err, data) {
            if(err !== null) {
                callback(err);
            } else {
                callback(null, null);
            }
        });
    };*/