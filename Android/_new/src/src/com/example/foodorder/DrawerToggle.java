package com.example.foodorder;

import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

public class DrawerToggle extends ActionBarDrawerToggle {

	private Activity mActivity;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;

	public DrawerToggle(Activity activity, DrawerLayout drawerLayout,
			int drawerImageRes, 
			int openDrawerContentDescRes,
			int closeDrawerContentDescRes, CharSequence aTitle) {		
		
		super(activity, drawerLayout,
				drawerImageRes, openDrawerContentDescRes, closeDrawerContentDescRes);
		
		mActivity = activity;
		mActivity.getActionBar().setDisplayHomeAsUpEnabled(true);
		mActivity.getActionBar().setHomeButtonEnabled(true);
		
		mTitle = mDrawerTitle = aTitle;
	}

    /** Called when a drawer has settled in a completely closed state. */
    public void onDrawerClosed(View view) {
        super.onDrawerClosed(view);
        mActivity.getActionBar().setTitle(mTitle);
        mActivity.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
    }

    /** Called when a drawer has settled in a completely open state. */
    public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        mActivity.getActionBar().setTitle(mDrawerTitle);
        mActivity.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
    }
   
    public void setTitle( CharSequence aTitle ){
    	mTitle = aTitle;
    }
}
