package com.java.clientserver.serverside;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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

			BufferedReader in = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
			PrintWriter out = new PrintWriter(mSocket.getOutputStream(),true);
			out.println("HTTP/1.1 200 OK");
			out.println("Content-Type: text/html");
			out.println("\r\n");
			out.println("<html>\r\n");
			out.println("<head>\r\n");
			out.println("<title>Socket.IO chat</title>\r\n");
			out.println("</head>\r\n");
			out.println("<body>\r\n");
			out.println("<p><b> Hello world </b></p>");
			out.println("<button>Send</button>");
			out.println("</body>\r\n");
			out.println("</html>\r\n");
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
				String line = in.readLine();
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


	public void actionPerformed(ActionEvent event){
		System.out.println( "OnButton click" );
//		Object source = event.getSource();

		//		   if(source == button){
		//			   //Send data over socket
		//		      String text = textField.getText();
		//		      out.println(text);
		//		      textField.setText(new String(""));
		//		      out.println(text);
		//		   }
		//		//Receive text from server
		//		   try{
		//		     String line = in.readLine();
		//		     System.out.println("Text received: " + line);
		//		   } catch (IOException e){
		//		     System.out.println("Read failed");
		//		     System.exit(1);
		//		   }
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

