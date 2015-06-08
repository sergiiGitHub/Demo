package com.example.serverside;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import com.example.serverside.SocketServerThread.SocketServerListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ServerSide extends Activity implements SocketServerListener, OnClickListener {

	private static final String TAG = ServerSide.class.getSimpleName();
	private TextView info, infoip, msg;
	private String mMassege = "";

	private SocketServerThread mSocketServerThread;
	private Button mButtonTurnOn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		info = (TextView) findViewById(R.id.info);
		infoip = (TextView) findViewById(R.id.infoip);
		msg = (TextView) findViewById(R.id.msg);
		
		mButtonTurnOn = (Button) findViewById(R.id.button_turn_on);
		
		mButtonTurnOn.setOnClickListener(this);

		infoip.setText(getIpAddress());

		//-----------------------------------------------------------
		mSocketServerThread = new SocketServerThread( this );
		Thread socketServerThread = new Thread(mSocketServerThread);
		socketServerThread.start();
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

	public void displayInfo(  ){
		ServerSide.this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				info.setText("I'm waiting here: " + mSocketServerThread.getSocket().getLocalPort());
			}
		});
	}
	
	public void updateMassege( final String aMessage ){
		ServerSide.this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mMassege += aMessage;
				msg.setText(mMassege);
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
			if (mSocketServerThread.turnOnLight(true)){
				updateMassege(  "Turn the light/n" );
			}
			break;

		default:
			break;
		}
	}
}
