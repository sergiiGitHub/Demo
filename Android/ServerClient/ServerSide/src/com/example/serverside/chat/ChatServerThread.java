package com.example.serverside.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.example.serverside.SocketServerListener;

public class ChatServerThread extends Thread implements ConnectedThreadListener {

	private static final int SocketServerPORT = 8080;

	private final List<ChatClient> userList;
	private final SocketServerListener mSocketServerListener;
	
	private ServerSocket serverSocket;
	private Socket socket = null;

	public ChatServerThread( SocketServerListener aSocketServerListener ) {
		mSocketServerListener = aSocketServerListener;
		
		userList = new ArrayList<ChatClient>();
	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(SocketServerPORT);
			mSocketServerListener.displayPortInfo(serverSocket.getLocalPort());
			while (true) {
				socket = serverSocket.accept();

				ChatClient client = new ChatClient();
				userList.add(client);
				ConnectThread connectThread = new ConnectThread(client, socket, 
						mSocketServerListener,
						this);
				connectThread.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeSocket();
		}

	}

	private void closeSocket() {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onRemove(ChatClient aClient) {
		userList.remove(aClient);
	}

	@Override
	public void broadcastMsg(String  msg) {
		String msgLog = "";
		for(int i=0; i<userList.size(); i++){
			userList.get(i).chatThread.sendMsg(msg);
			msgLog  += "- send to " + userList.get(i).name + "\n";
		}
		
		mSocketServerListener.updateMassege(msgLog);

	}

	public void turnLightOn() {
		if ( !userList.isEmpty() ){
			userList.get(0).chatThread.sendMsg("Turn light");
		}
	}
}
