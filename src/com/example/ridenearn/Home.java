package com.example.ridenearn;

import java.io.File;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class Home extends ActionBarActivity {
	//preferences
		private static final String PREFS_NAME = "registered";
		private static final String PREF_EMAIL = "email";
		
		private String EmailValue;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void loadPreferences() {
		File f = new File(
				"/data/data/com.example.ridenearn/shared_prefs/registered.xml");
				if (f.exists())
				    Log.d("TAG", "SharedPreferences registered : exist");
				else{
				    Intent i = new Intent(Home.this, Register.class);
				    startActivity(i);
				}
				
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    loadPreferences();
	     }
}
