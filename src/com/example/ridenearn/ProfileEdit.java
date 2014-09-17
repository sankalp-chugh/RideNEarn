package com.example.ridenearn;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileEdit extends ActionBarActivity{
	
	// JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";
    private static String KEY_ERROR_MSG = "error_msg";
    private static String KEY_UID = "uid";
    private static String KEY_NAME = "name";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";
	
	private TextView name;
	private String email;
	private String[] fullName;
	private RadioGroup gender,vehicle_type,vehicle_type2;
	private CheckBox second_vehicle,vehicle_owner;
	private Button savebtn;
	private RadioButton genderRadio,vehicleRadio,vehicle2Radio;
	private HashMap<String,String> data = new HashMap();
	private EditText vehicle_num,vehicle_num2;
	
	private LinearLayout extra,vehicle_opt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_edit);
		
		Bundle extras = getIntent().getExtras();
		email = extras.getString("email");
		name = (TextView)findViewById(R.id.name);
		name.setText("");
				
		//gender
		gender = (RadioGroup)findViewById(R.id.gender);			
		//savebtn
		savebtn = (Button)findViewById(R.id.savebtn);
		//vehicle
		vehicle_type = (RadioGroup)findViewById(R.id.vehicle_type);
		vehicle_num = (EditText)findViewById(R.id.vehicle_num);
		vehicle_type2 = (RadioGroup)findViewById(R.id.vehicle_type2);
		vehicle_num2 = (EditText)findViewById(R.id.vehicle_num2);
		//checkBoxes
		addListenerOnSecondVehicle();
		addListenerOnVehicleOwner();
		
		UserFunctions userFunc = new UserFunctions();
		fullName = userFunc.getName(email);
		
		name.setText(fullName[0]+" "+fullName[1]);
	}
	
	public void addListenerOnSecondVehicle() {		 
		second_vehicle = (CheckBox) findViewById(R.id.second_vehicle);	 
		second_vehicle.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
			if (((CheckBox) v).isChecked()) {
				extra = (LinearLayout)findViewById(R.id.extra);
				extra.setVisibility(View.VISIBLE);				
			}
			else
				extra.setVisibility(View.GONE); 
		  }
		});
	
	}
	
	public void addListenerOnVehicleOwner(){
		vehicle_owner = (CheckBox)findViewById(R.id.vehicle_owner);
		vehicle_owner.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					vehicle_opt = (LinearLayout)findViewById(R.id.vehicle_opt);
					vehicle_opt.setVisibility(View.VISIBLE);					
				}
				else
					vehicle_opt.setVisibility(View.GONE); 
			}
			
		});
	}
	
	public void saveDetails(View v){
		//gender
		int genderId = gender.getCheckedRadioButtonId();
		genderRadio = (RadioButton)findViewById(genderId);
		data.put("gender",genderRadio.getText().toString());
		
		//email
		data.put("email", email);
		
		//vehicle type
		
		int vehicleType = vehicle_type.getCheckedRadioButtonId();
		vehicleRadio = (RadioButton)findViewById(vehicleType);
		if(vehicleRadio != null){
		data.put("vehicleType", vehicleRadio.getText().toString());
		data.put("vehicleNo", vehicle_num.getText().toString());
		}
		
		//vehicle type 2
		
		int vehicleType2 = vehicle_type2.getCheckedRadioButtonId();
		vehicle2Radio = (RadioButton)findViewById(vehicleType2);
		if(vehicle2Radio != null){
		data.put("vehicleType2", vehicle2Radio.getText().toString());
		data.put("vehicleNo2", vehicle_num2.getText().toString());
		}
		
		Set set=data.entrySet();  
		  Iterator itr=set.iterator();  
		  
		  while(itr.hasNext()){  
		   Map.Entry m=(Map.Entry)itr.next();  
		   Toast.makeText(getApplicationContext(), m.getKey()+" "+m.getValue(), Toast.LENGTH_LONG).show();  
		  }  
	}
	
	private class SendData extends AsyncTask<String, Integer, String>{
        
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
}
