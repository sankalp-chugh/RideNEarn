package com.example.ridenearn;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class Sample extends Activity{

	TextView text;
	String email = "sankalp.chugh@yahoo.com";
    String gender = "m";
	
	// JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";
    private static String KEY_ERROR_MSG = "error_msg";
    private static String KEY_UID = "uid";
    private static String KEY_NAME = "name";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);
		
		text = (TextView)findViewById(R.id.sample_text);				
	}
	
	private class GetData extends AsyncTask<String, Integer, String>{
		         
		@Override
		protected String doInBackground(String... params) {
			UserFunctions userFunction = new UserFunctions();
	        JSONObject json = userFunction.registerUser(params[0], params[1]);
	        String output="";
	        
	        try {
				if (json.getString(KEY_SUCCESS) != null) {
					output = json.getString("msg");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return output;
		}

		@Override
		protected void onPostExecute(String output) {
			text.setText(output.toString());
		}
		
		
	}

	@Override
	protected void onResume() {		
		super.onResume();
		new GetData().execute(email,gender);
	}
	
	
	
}
