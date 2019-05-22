package com.lifotech.awslambda.chat.usermanagement;


import java.io.Serializable;

public class CognitoResposeModel implements Serializable {


    private String[] userNames;


    public String[] getUserNames() {
        return userNames;
    }

    public void setUserNames(String[] userNames) {
        this.userNames = userNames;
    }


}
