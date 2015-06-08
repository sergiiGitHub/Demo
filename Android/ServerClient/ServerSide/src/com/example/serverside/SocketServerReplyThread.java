package com.example.serverside;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import com.example.serverside.SocketServerThread.SocketServerListener;

class SocketServerReplyThread extends Thread {

	private final Socket hostThreadSocket;
	private final SocketServerListener mSocketServerListener;
	private final String mMsgReply; 

	SocketServerReplyThread(Socket socket, String aMsgReply, SocketServerListener aSocketServerListener) {
		hostThreadSocket = socket;
		mMsgReply = aMsgReply;
		mSocketServerListener = aSocketServerListener;
	}

	@Override
	public void run() {
		OutputStream outputStream;

		String message = null;
		try {
			outputStream = hostThreadSocket.getOutputStream();
			PrintStream printStream = new PrintStream(outputStream);
			printStream.print(mMsgReply);
			printStream.close();

			message = "replayed: " + mMsgReply + "\n";

			mSocketServerListener.updateMassege(message);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message += "Something wrong! " + e.toString() + "\n";
			mSocketServerListener.updateMassege(message);
		}

	}

}
