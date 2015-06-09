package com.example.serverside.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.example.serverside.SocketServerListener;

public class ConnectThread extends Thread {
	private Socket socket;
	private ChatClient connectClient;
	private String msgToSend = "";
	
	private SocketServerListener mListener;
	private ConnectedThreadListener mConnectedListener;
	
	ConnectThread(	ChatClient client, Socket socket, 
				  	SocketServerListener aListener, 
				  	ConnectedThreadListener aConnectedListener){
		connectClient = client;
		this.socket= socket;
		client.socket = socket;
		client.chatThread = this;
		mListener = aListener;
		mConnectedListener = aConnectedListener;
	}

	@Override
	public void run() {
		DataInputStream dataInputStream = null;
		DataOutputStream dataOutputStream = null;

		String msgLog = "";
		try {
			dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());

			String name = dataInputStream.readUTF();

			connectClient.name = name;

			msgLog = connectClient.name + " connected@" 
					+ connectClient.socket.getInetAddress() + ":" 
					+ connectClient.socket.getPort() + "\n";
			mListener.updateMassege(msgLog);

			dataOutputStream.writeUTF("Welcome " + name + "\n");
			dataOutputStream.flush();

			mConnectedListener.broadcastMsg(name + " join our chat.\n");

			while (true) {
				if (dataInputStream.available() > 0) {
					String newMsg = dataInputStream.readUTF();
					msgLog = name + ": " + newMsg;
					mListener.updateMassege(msgLog);

					mConnectedListener.broadcastMsg(name + ": " + newMsg);
				}

				if(!msgToSend.equals("")){
					dataOutputStream.writeUTF(msgToSend);
					dataOutputStream.flush();
					msgToSend = "";
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dataInputStream != null) {
				try {
					dataInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (dataOutputStream != null) {
				try {
					dataOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		mConnectedListener.onRemove(connectClient);
		
		msgLog += "-- " + connectClient.name + " leaved\n";
		mListener.updateMassege(msgLog);
		mConnectedListener.broadcastMsg("-- " + connectClient.name + " leaved\n");
		
	}

	public void sendMsg(String msg) {
		msgToSend = msg;
	}

}

