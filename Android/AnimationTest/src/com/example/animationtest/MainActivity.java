package com.example.animationtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends Activity {

	// константы для ID пунктов меню
	final int MENU_ALPHA_ID = 1;
	final int MENU_SCALE_ID = 2;
	final int MENU_TRANSLATE_ID = 3;
	final int MENU_ROTATE_ID = 4;
	
	private static final int[] animArray = { R.anim.myalpha ,R.anim.myrotate, R.anim.myscale, R.anim.mytrans };

	TextView tv;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tv);
		registerForContextMenu(tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		int index = -1;
		switch (item.getItemId()) {
		case R.id.action_alpha: index = 0; 
		break;
		case R.id.action_rotate: index = 1; 
		break;
		case R.id.action_scale: index = 2; 
		break;
		case R.id.action_translate: index = 3; 
		break;			

		default:
			break;
		}
		
		Animation anim = AnimationUtils.loadAnimation(this, animArray[index] );
		anim.setAnimationListener((AnimationListener) new AnimaionListenerImpl());
		tv.startAnimation( anim );
		return super.onMenuItemSelected(featureId, item);
	}

}
