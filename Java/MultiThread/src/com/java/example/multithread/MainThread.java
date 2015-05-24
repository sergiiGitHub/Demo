package com.java.example.multithread;



/** Class for training develop multi thread skills
 * Useful URL
 * http://habrahabr.ru/post/164487/ 
 */


public class MainThread implements CallBack, Runnable {

	public void waitMain(){
		synchronized (this) {
			System.out.println( "Main::waitMain" );
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println( "Main::continue" );
		}
	}

	@Override
	public void notifyMain() {
		System.out.println( "Main::notifyMain" );
		synchronized (this) {
			notifyAll();
		}
	}

	private void startProcess() {
		
	
		int i = 0;
		while( i < 2 ){
			
			//TODO provide another thread without creation new instance
//			new InternalThread( this ).start();
//			waitMain();
			
			InternalThread it = new InternalThread( this );
			it.start();
			addForJoin(it);	

			i++;
			System.out.println( "Main::while" );
		}
	}

	private void addForJoin(Thread it) {
		try {
			it.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		startProcess();
	}
	
	public static void main ( String [] args ){
		
		MainThread main = new MainThread();
		main.run();
	}

}

