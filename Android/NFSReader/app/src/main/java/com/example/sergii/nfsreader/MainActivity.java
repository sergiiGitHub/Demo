package com.example.sergii.nfsreader;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private NfcAdapter nfcAdapter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        textView = (TextView) findViewById(R.id.txtButton);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: ");
        Bundle bundle = intent.getExtras();
        for (String key : bundle.keySet())
        {
            Log.d(TAG, key + " = \"" + bundle.get(key) + "\"");
        }
        if ( intent.hasExtra(NfcAdapter.EXTRA_TAG) ){
            Log.d(TAG, "onNewIntent: hasExtra");
            Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if ( parcelables != null && parcelables.length > 0 ){
                readTextFromMessage( (NdefMessage) parcelables[0] );
            } else {
                Log.d(TAG, "onNewIntent: parcelables null ");
            }
        }
    }

    private void readTextFromMessage(NdefMessage ndefMessage) {
        NdefRecord[] ndeRecords = ndefMessage.getRecords();
        if ( ndeRecords != null && ndeRecords.length > 0 ){
            textView.setText(getText(ndeRecords[0]));
        } else {
            Log.d(TAG, "readTextFromMessage: ");
        }
    }

    public void tglRead(){
        textView.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        enableNfc();
    }

    private void enableNfc() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        IntentFilter[] filter = new IntentFilter[]{};
        nfcAdapter.enableForegroundDispatch(
                this, pendingIntent, filter, null
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        disableNfc();
    }

    private void disableNfc() {
        nfcAdapter.disableForegroundDispatch(this);
    }

    public String getText( NdefRecord record ){
        String text = null;
        byte payload[] = record.getPayload();
        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
        int languageSize = payload[0] & 0063;
        try {
            text = new String(payload, languageSize + 1, payload.length - languageSize - 1, textEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }

}
