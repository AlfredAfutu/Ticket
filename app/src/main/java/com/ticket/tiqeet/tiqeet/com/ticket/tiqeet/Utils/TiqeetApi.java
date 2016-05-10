package com.ticket.tiqeet.tiqeet.com.ticket.tiqeet.Utils;

/**
 * Created by cted on 10/5/15.
 */
public class TiqeetApi {

    static public String tiqeetApiBase = "https://tiqeet-developer.herokuapp.com/api/v2";
    static public String signUpEndpoint = tiqeetApiBase + "/users";
    static public String loginEndpoint = tiqeetApiBase + "/login";


    static public String getSignUpEndpoint(){
        return signUpEndpoint;
    }

    static public String getLoginEndpoint(){return loginEndpoint;}
}
