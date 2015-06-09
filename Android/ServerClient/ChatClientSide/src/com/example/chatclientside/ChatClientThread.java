package com.example.chatclientside;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.util.Log;

public class ChatClientThread extends Thread {

	private static final String TAG = ChatClientThread.class.getSimpleName();
	private String name;
	private String dstAddress;
	private int dstPort;

	private String msgToSend = "";
	private boolean goOut = false;
	private ChatClientListener mChatClientListener;
	private String msgLog = "";

	private Socket socket = null;
	private DataOutputStream dataOutputStream = null;
	private DataInputStream dataInputStream = null;
	
	ChatClientThread(String name, 
			String address,
			int port, 
			ChatClientListener aChatClientListener) {
		this.name = name;
		dstAddress = address;
		dstPort = port;
		this.mChatClientListener = aChatClientListener;
	}

	@Override
	public void run() {
		try {
			socket = new Socket(dstAddress, dstPort);
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream.writeUTF(name);
			dataOutputStream.flush();

			while (!goOut) {
				if (dataInputStream.available() > 0) {
					msgLog  += dataInputStream.readUTF();
					Log.d(TAG , "Input stream = " + msgLog  );
					mChatClientListener.onUpdateChatMsg(msgLog);
				}

				if(!msgToSend.equals("")){
					dataOutputStream.writeUTF(msgToSend);
					dataOutputStream.flush();
					msgToSend = "";
				}
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
			mChatClientListener.onToastMsg( e.toString() );
		} catch (IOException e) {
			e.printStackTrace();
			mChatClientListener.onToastMsg( e.toString() );
		} finally {
			closeSocket();
			closeDataIputStream();
			closeDataOutputStream();

			mChatClientListener.onDisconnected();
		}
	}
	
	private void closeSocket() {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
				mChatClientListener.onToastMsg( e.toString() );
			}
		}
	}

	private void closeDataIputStream() {
		if (dataInputStream != null) {
			try {
				dataInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				mChatClientListener.onToastMsg( e.toString() );
			}
		}
	}

	private void closeDataOutputStream() {
		if (dataOutputStream != null) {
			try {
				dataOutputStream.close();
			} catch (IOException e) {
				mChatClientListener.onToastMsg( e.toString() );
				e.printStackTrace();
			}
		}
	}

	public void sendMsg(String msg){
		msgToSend = msg;
	}

	public void disconnect(){
		goOut = true;
	}
}
