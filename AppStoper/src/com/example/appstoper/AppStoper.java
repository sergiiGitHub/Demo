package com.example.appstoper;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AppStoper extends Activity implements OnClickListener {

	private static final String TAG = AppStoper.class.getSimpleName();
	private static final String THIS_PACKAGE = AppStoper.class.getPackage().getName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button button = (Button) findViewById(R.id.button_stop);
		button.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Log.d( TAG, "onClick");
		if ( v.getId() == R.id.button_stop ){
			Log.d( TAG, "button_stop");
			forceStopAllApp();
		}
	}

	@Override
	protected void onPause() {
		Log.d( TAG, "onPause");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.d( TAG, "onStop");
		super.onStop();
		deleteCache( getApplicationContext() );
	}
	
	public static void deleteCache(Context context) {
	    try {
	        File dir = context.getCacheDir();
	        if (dir != null && dir.isDirectory()) {
	            deleteDir(dir);
	        }
	    } catch (Exception e) {}
	}

	public static boolean deleteDir(File dir) {
	    if (dir != null && dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i = 0; i < children.length; i++) {
	            boolean success = deleteDir(new File(dir, children[i]));
	            if (!success) {
	                return false;
	            }
	        }
	    }
	    return dir.delete();
	}
	
	@Override
	protected void onDestroy() {
		Log.d( TAG, "onDestroy");

		super.onDestroy();
		//kill myself
		killProcess(android.os.Process.myPid());
	}

	private void forceStopAllApp() {
		final ActivityManager am = (ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
		final List<ActivityManager.RunningAppProcessInfo> pids = am.getRunningAppProcesses();
		for(ActivityManager.RunningAppProcessInfo info : pids) {

			Log.i(TAG, info.processName);

			//TODO get name  
			if( !info.processName.contains(THIS_PACKAGE) ){
				killProcess( info.pid );
			}
		}
		finish();
	}

	private void killProcess(int pid) {
		//TODO check
//		android.os.Process.killProcess(pid);
		android.os.Process.sendSignal(pid, android.os.Process.SIGNAL_QUIT);
		android.os.Process.sendSignal(pid, android.os.Process.SIGNAL_KILL);
	}

}
