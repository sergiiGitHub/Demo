package com.android.customerLayout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

public class CustomizeLayoutActivity extends Activity {
	protected LinearLayout fullLayout;
    protected FrameLayout navContent;
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
		String string;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		ListView list = (ListView) findViewById(R.id.list);
		CreateArray MyStrArr = new CreateArray(  );
		
		for ( int i = 0; i < 3; i++ ){
			string = ( "item" + " "+ String.valueOf(i) );
		    MyStrArr.GenerateArray(string, i);	
		}

		list.setAdapter( new CustomerAdapter(this, MyStrArr.getStrArr( ) ) );
    }

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		
//        fullLayout= (LinearLayout) getLayoutInflater().inflate(R.layout.main, null); // Your base layout here
//        navContent= (FrameLayout) fullLayout.findViewById(R.id.nav_content);
//        getLayoutInflater().inflate(layoutResID, navContent, true); // Setting the content of layout your provided in the nav_content frame
//        setContentView(fullLayout);
	}
	
	
}

class CreateArray{
	private String[] string = new String[3];
	public void GenerateArray( String string, int index ) {
		this.string[index] = string;
	}
	public String[] getStrArr(){
		return this.string;
	}
}