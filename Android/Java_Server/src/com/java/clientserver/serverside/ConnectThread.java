package com.java.clientserver.serverside;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectThread extends Thread { 

	private static final long WAIT_TIME = 1000;
	private Socket mSocket;
	private String mClintName;

	private String msgToSend = "";
	private DataInputStream dataInputStream = null;
	private DataOutputStream dataOutputStream = null;

	public interface IConnectedThreadListener{

		void onAddItem( final ConnectThread aConnectThread );
		void onRemoveItem( final ConnectThread aCconnectThread );

		void onBroadcastMsg( final String aMsg );
	}

	private IConnectedThreadListener mListener;

	public ConnectThread( Socket socket) {
		this.mSocket = socket;
	}

	@Override
	public void run() {

		if ( mListener == null ){
			throw new IllegalArgumentException( " mListener == null " );
		}

		try {
			dataInputStream = new DataInputStream(mSocket.getInputStream());
			dataOutputStream = new DataOutputStream(mSocket.getOutputStream());

//			mClintName = dataInputStream.readUTF();
			mClintName = "was";

//			dataOutputStream.writeUTF("Welcome " + mClintName + "\n");
//			dataOutputStream.writeUTF("HTTP/1.1 200 OK");
//			dataOutputStream.writeUTF("Content-Type: text/html");
//		    dataOutputStream.writeUTF("\r\n");
//		    dataOutputStream.writeUTF("<p> Hello world </p>");
//			dataOutputStream.flush();
			
			PrintWriter out = new PrintWriter(mSocket.getOutputStream());
		    out.println("HTTP/1.1 200 OK");
		    out.println("Content-Type: text/html");
		    out.println("\r\n");
		    out.println("<p> Hello world </p>");
		    out.flush();

			mListener.onAddItem(this);

			while (true) {
//				if ( haveIncomeMsg()) {
//					//TODO made by string builder
//					mListener.onBroadcastMsg( mClintName + ": " + dataInputStream.readUTF() );                	
//				}               
//				if ( hasSendMsg() ) {
//					dataOutputStream.writeUTF(msgToSend);
//					dataOutputStream.flush();
//					msgToSend = "";
//				}
				waitConnected();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeDataInput();
			closeDataOutput();

			mListener.onRemoveItem( this );
		}
	}

	private boolean hasSendMsg() {
		return !msgToSend.isEmpty();
	}

	private boolean haveIncomeMsg() {
		try {
			return dataInputStream.available() > 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private void waitConnected() {
		while ( !isAnyNotification() ){
			System.out.println( "tik inside internal thread" );
			synchronized (this) {
				try {
					wait(WAIT_TIME);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private boolean isAnyNotification() {
		return haveIncomeMsg() || hasSendMsg();
	}

	private void closeDataOutput() {
		if (dataOutputStream != null) {
			try {
				dataOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void closeDataInput() {
		if (dataInputStream != null) {
			try {
				dataInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getConnectedInfo() {
		return mClintName 
				+ " connected@"
				+ mSocket.getInetAddress()
				+ ":"
				+ mSocket.getPort() + "\n";
	}

	public void sendMsg(String msg) {
		msgToSend = msg;
	}

	//TODO probably set as thread name
	public String getClientName(){
		return mClintName;
	}

	public IConnectedThreadListener getConnectedThreadListener() {
		return mListener;
	}

	public void setConnectedThreadListener(IConnectedThreadListener mListener) {
		this.mListener = mListener;
	}

}

