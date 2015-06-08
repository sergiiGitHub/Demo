package com.example.serverside;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import android.util.Log;

public class SocketServerTurnOnLighter extends Thread {

	private final String TAG = SocketServerTurnOnLighter.this.getName();
	private final Boolean isTurnOn;
	private final Socket hostThreadSocket;

	public SocketServerTurnOnLighter(Socket socket, Boolean isTurnOn) {
		this.hostThreadSocket = socket;
		this.isTurnOn = isTurnOn;
	}

	@Override
	public void run() {
		OutputStream outputStream;

		try {
			outputStream = hostThreadSocket.getOutputStream();
			PrintStream printStream = new PrintStream(outputStream);
			printStream.print(isTurnOn.toString());
			printStream.close();
			Log.d( TAG, "Light is"+ isTurnOn );

		} catch (IOException e) {
			Log.d( TAG, "error" );
			e.printStackTrace();
		}
	}	
}
