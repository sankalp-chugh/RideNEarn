package com.example.ridenearn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends ActionBarActivity{
	//preferences
	public static final String PREFS_NAME = "registered";
	public static final String PREF_EMAIL = "email";
	
	private String EmailValue;
	
	EditText email;
	Button register;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		//set views
		email = (EditText)findViewById(R.id.email);
		register = (Button)findViewById(R.id.register);
	}
	
	public void doRegistration(View v){
		String getEmail = email.getText().toString();
		if(checkMail(getEmail)){
			Intent i = new Intent(Register.this, ProfileEdit.class);
			startActivity(i);
		}else
			Toast.makeText(getApplicationContext(), "Invalid email Id", Toast.LENGTH_LONG).show();
	}
	
	/**
	 * Function to check valid college email id
	 * @param str
	 * @return
	 */
	private Boolean checkMail(String str){
		return str.substring(str.indexOf("@")+1).equals("stu.upes.ac.in");
	}
	
	private void savePreferences() {
	    SharedPreferences settings = getSharedPreferences(PREFS_NAME,
	            Context.MODE_PRIVATE);
	    SharedPreferences.Editor editor = settings.edit();

	    // Edit and commit
	    EmailValue = email.getText().toString();
	    editor.putString(PREF_EMAIL, EmailValue);
	    editor.commit();
	}
	
	@Override
	public void onPause() {
	    super.onPause();
	    savePreferences();
	    
	}
	
}
