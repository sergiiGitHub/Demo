package com.example.serverside;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import com.example.serverside.chat.ChatServerThread;
import com.example.serverside.socket.SocketServerThread;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ServerSide extends Activity implements SocketServerListener, OnClickListener {

	private static final String TAG = ServerSide.class.getSimpleName();
	private String mMassege = "";

	private SocketServerThread mSocketServerThread;
	private Button mButtonTurnOn;

	private TextView infoIp, infoPort, chatMsg;
	private ChatServerThread chatServerThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		infoIp = (TextView) findViewById(R.id.infoip);
		infoPort = (TextView) findViewById(R.id.infoport);
		chatMsg = (TextView) findViewById(R.id.chatmsg);

		infoIp.setText(getIpAddress());

		mButtonTurnOn = (Button) findViewById(R.id.button_turn_on);
		mButtonTurnOn.setOnClickListener(this);

		// Socket-----------------------------------------------------------
		//		mSocketServerThread = new SocketServerThread( this );
		//		Thread socketServerThread = new Thread(mSocketServerThread);
		//		socketServerThread.start();

		//-----------------------------------------------------------
		// chat
		chatServerThread = new ChatServerThread( this );
		chatServerThread.start();


	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (mSocketServerThread.getSocket() != null) {
			try {
				mSocketServerThread.getSocket().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void displayPortInfo( final int aPort ){
		ServerSide.this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				infoPort.setText("I'm waiting here: " + aPort );
			}
		});
	}

	public void updateMassege( final String aMessage ){
		ServerSide.this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mMassege += aMessage;
				chatMsg.setText(mMassege);
			}
		});
	}


	private String getIpAddress() {
		String ip = "";
		try {
			Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface.getNetworkInterfaces();
			while (enumNetworkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = enumNetworkInterfaces.nextElement();
				Enumeration<InetAddress> enumInetAddress = networkInterface.getInetAddresses();
				while (enumInetAddress.hasMoreElements()) {
					InetAddress inetAddress = enumInetAddress.nextElement();

					if (inetAddress.isSiteLocalAddress()) {
						ip += "SiteLocalAddress: " 
								+ inetAddress.getHostAddress() + "\n";
					}

				}

			}

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ip += "Something Wrong! " + e.toString() + "\n";
		}

		return ip;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_turn_on:
			Log.d(TAG, "Turn the light");
			//TODO turn the light
			chatServerThread.turnLightOn();
			break;

		default:
			break;
		}
	}
}
