package com.example.serverside.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.example.serverside.SocketServerListener;

public class SocketServerThread extends Thread {

	static final int SocketServerPORT = 8080;

	private int count = 0;
	private ServerSocket serverSocket;
	private DataInputStream dataInputStream = null;
	private DataOutputStream dataOutputStream = null;

	private Socket socket;

	private SocketServerListener mSocketServerListener;

	public SocketServerThread( SocketServerListener aSocketServerListener ) {
		mSocketServerListener = aSocketServerListener;
	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(SocketServerPORT);
			mSocketServerListener.displayPortInfo( serverSocket.getLocalPort() );

			while (true) {
				socket = serverSocket.accept();

				dataInputStream = new DataInputStream( socket.getInputStream() );
				dataOutputStream = new DataOutputStream( socket.getOutputStream() );

				String messageFromClient = "";
				//Check available() before readUTF(),
				//to prevent program blocked if dataInputStream is empty
				if(dataInputStream.available()>0){
					messageFromClient = dataInputStream.readUTF();
				}

				count++;
				String message = "#" + count + " from " + socket.getInetAddress() 
						+ ":" + socket.getPort() + "\n"
						+ "Msg from client: " + messageFromClient + "\n";
				mSocketServerListener.updateMassege(message);

				String msgReply = "Hello from Android, you are #" + count;
				dataOutputStream.writeUTF(msgReply);

			}
		} catch (IOException e) {
			e.printStackTrace();
			final String errMsg = e.toString();
			mSocketServerListener.updateMassege(errMsg);
		} finally {
			closeSocket();
			closeDataIputStream();
			closeDataOutputStream();
		}
	}

	private void closeSocket() {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void closeDataIputStream() {
		if (dataInputStream != null) {
			try {
				dataInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void closeDataOutputStream() {
		if (dataOutputStream != null) {
			try {
				dataOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ServerSocket getSocket() {
		return serverSocket;
	}

	public boolean turnOnLight( boolean isTurnOn ){
		if ( socket != null ){
			SocketServerTurnOnLighter socketServerReplyThread = 
					new SocketServerTurnOnLighter(	socket,	isTurnOn );
			socketServerReplyThread.run();
			return true;
		}
		return false;
	}

}

