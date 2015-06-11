package com.java.clientserver.serverside;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectThread extends Thread {

    private Socket mSocket;
    private String mClintName;
    
    private String msgToSend = "";
    private String msgLog = "";
    private DataInputStream dataInputStream = null;
    private DataOutputStream dataOutputStream = null;
    
    public interface IConnectedThreadListener{
 
    	void onAddItem( final ConnectThread aConnectThread );
    	void onRemoveItem( final ConnectThread aCconnectThread );
    	
    	void onBroadcastMsg( final String aMsg );
    	void onPrintLog( final String aMsg );
    }
    
    private IConnectedThreadListener mListener;

    public ConnectThread( Socket socket) {
        this.mSocket = socket;
    }

    @Override
    public void run() {
    	
    	if ( mListener == null ){
    		throw new IllegalArgumentException( " mListener == null " );
    	}

        try {
            dataInputStream = new DataInputStream(mSocket.getInputStream());
            dataOutputStream = new DataOutputStream(mSocket.getOutputStream());

            mClintName = dataInputStream.readUTF();

            dataOutputStream.writeUTF("Welcome " + mClintName + "\n");
            dataOutputStream.flush();

            mListener.onPrintLog( getClientInfo() );
            mListener.onBroadcastMsg( mClintName + " join our chat.\n");

            while (true) {
                if (dataInputStream.available() > 0) {
                	//TODO made by string builder
                	msgLog = mClintName + ": " + dataInputStream.readUTF();;
                    
                    mListener.onPrintLog( msgLog );
                    mListener.onBroadcastMsg( msgLog );                	
                }

                //TODO implement it
//                try {
//					wait(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
                
                if (!msgToSend.isEmpty()) {
                	System.out.print( "msgToSend" + msgToSend ); 
                    dataOutputStream.writeUTF(msgToSend);
                    dataOutputStream.flush();
                    msgToSend = "";
                }
                
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeDataInput();
            closeDataOutput();
          
            mListener.onRemoveItem( this );
        }
    }

    private void closeDataOutput() {
        if (dataOutputStream != null) {
            try {
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
	}

	private void closeDataInput() {
    	if (dataInputStream != null) {
            try {
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}

	private String getClientInfo() {
		return mClintName 
				+ " connected@"
                + mSocket.getInetAddress()
                + ":"
                + mSocket.getPort() + "\n";
	}

	public void sendMsg(String msg) {
        msgToSend = msg;
        System.out.print( "sendMsg::" + msgToSend );
    }
	
	//TODO probably set as thread name
	public String getClientName(){
		return mClintName;
	}

	public IConnectedThreadListener getConnectedThreadListener() {
		return mListener;
	}

	public void setConnectedThreadListener(IConnectedThreadListener mListener) {
		this.mListener = mListener;
	}

}

