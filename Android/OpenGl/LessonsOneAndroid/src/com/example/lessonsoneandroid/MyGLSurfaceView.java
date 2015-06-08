package com.example.lessonsoneandroid;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MyGLSurfaceView extends GLSurfaceView {
	
    public MyGLSurfaceView(Context context){
        super(context);
        
        setEGLContextClientVersion(2);
        
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        
        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(new MyRenderer());
    }
}
