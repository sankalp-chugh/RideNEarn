package com.example.ridenearn;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;

public class UserFunctions {
    
    private JSONParser jsonParser;
     
    // Testing in localhost using wamp or xampp 
    // use http://10.0.2.2/ to connect to your localhost ie http://localhost/
    private static String loginURL = "http://10.0.2.2/ridenearns/";
    private static String registerURL = "http://10.0.2.2/ridenearns/";
     
    private static String login_tag = "login";
    private static String register_tag = "register";
     
    // constructor
    public UserFunctions(){
        jsonParser = new JSONParser();
    }
    
    /**
     * function check Email Id validity
     * @param email
     * returns boolean
     */
    public Boolean checkEmail(String email){
    	String r_mail = email.substring(email.indexOf("@")+1);
    	if(r_mail.equals("stu.upes.ac.in"))
    		return true;
    	return false;
    }
    
    public String[] getName(String email){
	   	 
	   	//Triming Email 
	   	 String[] strArray = email.split("\\@");
	   	 
	   	//FullName
	   	 String [] fullName = new String[1];
	   	 fullName[0] = strArray[0];
	   	  
	   	fullName=fullName[0].split("\\.",2);
	   	fullName[1]= fullName[1].replaceAll("\\d*$", "");
	   	return fullName;
    }
     
    /**
     * function make Login Request
     * @param email
     * @param gender
     * */
    public JSONObject loginUser(String email, String gender){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", login_tag));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", gender));
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        // return json
        // Log.e("JSON", json.toString());
        return json;
    }
     
    /**
     * function make Login Request
     * @param email
     * @param password
     * */
    public JSONObject registerUser(String email, String gender){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", register_tag));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("gender", gender));
         
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
        // return json
        return json;
    }
     
}