package com.example.clientside;

import android.os.Bundle;
import android.os.AsyncTask;
import android.app.Activity;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ClientSide extends Activity implements OnClickListener {

	public static final String TAG = ClientSide.class.getSimpleName();
	TextView textResponse;
	EditText editTextAddress, editTextPort; 
	Button buttonConnect, buttonClear;

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

	}

	public class MyClientTask extends AsyncTask<Void, Void, Void> {

		String dstAddress;
		int dstPort;
		String response = "";

		MyClientTask(String addr, int port){
			dstAddress = addr;
			dstPort = port;
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			Log.d( TAG, " doInBackground " );

			Socket socket = null;

			try {
				socket = new Socket(dstAddress, dstPort);

				ByteArrayOutputStream byteArrayOutputStream = 
						new ByteArrayOutputStream(1024);
				byte[] buffer = new byte[1024];

				int bytesRead;
				InputStream inputStream = socket.getInputStream();

				/*
				 * notice:
				 * inputStream.read() will block if no data return
				 */
					while ((bytesRead = inputStream.read(buffer)) != -1){
						byteArrayOutputStream.write(buffer, 0, bytesRead);
						response += byteArrayOutputStream.toString("UTF-8");
					}
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
				if(socket != null){
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			textResponse.setText(response);
			super.onPostExecute(result);
		}

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
		String internetAdreess = editTextAddress.getText().toString();
		int port = Integer.parseInt(editTextPort.getText().toString());
		MyClientTask myClientTask = new MyClientTask( internetAdreess, port );
		myClientTask.execute();
	}



}
