package com.example.serverside;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerThread extends Thread {

	static final int SocketServerPORT = 8080;

	private int count = 0;
	private ServerSocket serverSocket;
//	private DataInputStream dataInputStream = null;
//	private DataOutputStream dataOutputStream = null;

	public interface SocketServerListener{
		void displayInfo( );
		void updateMassege( final String aMessage );
	}

	private SocketServerListener mSocketServerListener;

	private String sendMsg;

	private Socket socket;

	public SocketServerThread( SocketServerListener aSocketServerListener ) {
		mSocketServerListener = aSocketServerListener;
	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(SocketServerPORT);

			mSocketServerListener.displayInfo();

			while (true) {
				socket = serverSocket.accept();

//				dataInputStream = new DataInputStream( socket.getInputStream() );
//				dataOutputStream = new DataOutputStream( socket.getOutputStream() );
//
//				String messageFromClient = messageFromClient = dataInputStream.readUTF();

				count++;
				String message = "#" + count + " from " + socket.getInetAddress() 
						+ ":" + socket.getPort() + "\n";
				mSocketServerListener.updateMassege(message);
				sendMsg = "Hello from Android, you are #" + count;

				SocketServerReplyThread socketServerReplyThread = new SocketServerReplyThread(
						socket, 
						sendMsg,
						mSocketServerListener);
				socketServerReplyThread.run();

//				String msgReply = "Hello from Android, you are #" + count;
//				dataOutputStream.writeUTF(msgReply);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

