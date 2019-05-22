package com.lifotech.awslambda.chat.usermanagement;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClient;
import com.amazonaws.services.cognitoidp.model.ListUsersRequest;
import com.amazonaws.services.cognitoidp.model.ListUsersResult;
import com.amazonaws.services.cognitoidp.model.UserType;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;
import java.util.stream.Collectors;


public class CognitoUserManagementRequestHandler implements RequestHandler<CognitoRequestModel, CognitoResposeModel> {

    @Override
    public CognitoResposeModel handleRequest(CognitoRequestModel cognitoRequestModel, Context context) {


        AWSCognitoIdentityProvider awsCognitoIdentityProvider = new AWSCognitoIdentityProviderClient();

        ListUsersRequest listUsersRequest = new ListUsersRequest();
        listUsersRequest.setUserPoolId("us-east-1_lpmDfHlU2");
        listUsersRequest.setFilter("");
        listUsersRequest.setLimit(60);


        ListUsersResult result = awsCognitoIdentityProvider.listUsers(listUsersRequest);

        List<UserType> users = result.getUsers();

        List<String> usersList = users.stream().map(user -> user.getUsername()).collect(Collectors.toList());

        String[] usersArray = new String[usersList.size()];
        usersArray = usersList.toArray(usersArray);

        CognitoResposeModel cognitoResposeModel = new CognitoResposeModel();
        cognitoResposeModel.setUserNames(usersArray);

        return cognitoResposeModel;

    }
}
