package com.example.firststep;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	protected static final String EXTRA_MESSAGE = "com.example.firststep.mainactivity";
	protected static final String KEY_TEXT_MSG = "txt_msg_send";
	private EditText editText = null;
	ContentValues mContentTextMsg = new ContentValues();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		editText = (EditText) findViewById(R.id.edit_message);
		
	    if (savedInstanceState != null) {
	        // Restore value of members from saved state
	    	editText.setText(savedInstanceState.getString(KEY_TEXT_MSG)) ;
	    } else {
	        // Probably initialize members with default values for a new instance
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void sendMessage(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();		// Always call the superclass method first
		
		// Save the note's current draft, because the activity is stopping
	    // and we want to be sure the current note progress isn't lost.
		mContentTextMsg.put(KEY_TEXT_MSG, editText.getText().toString());
	}
	
	@Override
	protected void onStart() {
	    super.onStart();  // Always call the superclass method first
	    
	    // The activity is either being restarted or started for the first time
	    // so this is where we should make sure that GPS is enabled
	 
	    
//	    
//	    if (!gpsEnabled) {
//	        // Create a dialog here that requests the user to enable GPS, and use an intent
//	        // with the android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS action
//	        // to take the user to the Settings screen to enable GPS when they click "OK"
//	    }
	}

	@Override
	protected void onRestart() {
	    super.onRestart();  // Always call the superclass method first
	    
	    String mRestoredValue = (String) mContentTextMsg.get(KEY_TEXT_MSG);
	    if ( mRestoredValue != null )
	    	editText.setText(mRestoredValue);
	    
	    // Activity being restarted from stopped state    
	}
	

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	    // Save the user's current game state
	    savedInstanceState.putString(KEY_TEXT_MSG, editText.getText().toString());
	    
	    // Always call the superclass so it can save the view hierarchy state
	    super.onSaveInstanceState(savedInstanceState);
	}
	
}
