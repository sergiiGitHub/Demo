package com.java.example.multithread;


public class InternalThread extends Thread { 

	private final CallBack mCallBAck;

	public InternalThread(  CallBack aCallBack ) {
		super( "InternalThread" );
		this.mCallBAck = aCallBack;
		System.out.println("InternalThread"); 
	}

	private void sleepInternal() {
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		System.out.println( "InternalThread::run" );
		
		int i = 0;
		while( i < 2 ){
			System.out.println( "InternalThread::while" );
			sleepInternal();
			i++;
		}
		mCallBAck.notifyMain();
	}

}
