package com.example.clientside;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ClientSide extends Activity implements OnClickListener {

	public static final String TAG = ClientSide.class.getSimpleName();
	private TextView textResponse;
	private EditText editTextAddress, editTextPort; 
	private Button buttonConnect, buttonClear;
	private EditText welcomeMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editTextAddress = (EditText)findViewById(R.id.address) ;
		editTextAddress.setText("192.168.0.102");

		editTextPort = (EditText)findViewById(R.id.port);
		editTextPort.setText("8080");

		buttonConnect = (Button)findViewById(R.id.connect);
		buttonConnect.setOnClickListener( this );

		buttonClear = (Button)findViewById(R.id.clear);
		buttonClear.setOnClickListener( this );

		textResponse = (TextView)findViewById(R.id.response);
		
		welcomeMsg = ( EditText)findViewById(R.id.welcomemsg);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.connect:
			connect();
			break;
		case R.id.clear:
			textResponse.setText("");
			break;

		default:
			break;
		}
	}

	private void connect() {
		final String internetAdreess = editTextAddress.getText().toString();
		final int port = Integer.parseInt(editTextPort.getText().toString());
//		final String msg = "Welcome:)";
		final String msg = welcomeMsg.getText().toString();
		MyClientTask myClientTask = new MyClientTask( internetAdreess, port, msg );
		myClientTask.execute();
	}

	public class MyClientTask extends AsyncTask<Void, Void, Void> {

		private String dstAddress;
		private int dstPort;
		private String response = "";
		private String msgToServer;

		private Socket socket = null;
		private DataOutputStream dataOutputStream = null;
		private DataInputStream dataInputStream = null;

		MyClientTask(String addr, int port, String aMsg){
			dstAddress = addr;
			dstPort = port;
			msgToServer = aMsg;
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			Log.d( TAG, " doInBackground " );



			try {

				socket = new Socket(dstAddress, dstPort);

				dataOutputStream = new DataOutputStream( socket.getOutputStream() );
				dataInputStream = new DataInputStream( socket.getInputStream() );

				if(msgToServer != null){
					dataOutputStream.writeUTF(msgToServer);
				}

				response = dataInputStream.readUTF();

				Log.d( TAG, "responce :: " + response);

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response = "UnknownHostException: " + e.toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response = "IOException: " + e.toString();
			}finally{
				closeSocket();
				closeDataIputStream();
				closeDataOutputStream();

			}
			return null;
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

		@Override
		protected void onPostExecute(Void result) {
			textResponse.setText(response);
			super.onPostExecute(result);
		}
		
		
	}
}
