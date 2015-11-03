package com.example.animationtest;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class AnimaionListenerImpl implements AnimationListener {

	private static final String TAG = AnimaionListenerImpl.class.getSimpleName(); 

	@Override
	public void onAnimationEnd(Animation animation) {
		Log.d( TAG, "onAnimationEnd" );
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		Log.d( TAG, "onAnimationRepeat" );		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		Log.d( TAG, "onAnimationStart" );
	}

}
