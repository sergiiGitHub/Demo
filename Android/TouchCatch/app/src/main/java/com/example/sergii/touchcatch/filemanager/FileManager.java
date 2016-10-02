package com.example.sergii.touchcatch.filemanager;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by sergii on 02.10.16.
 */
public class FileManager {

    private static final String TAG = FileManager.class.getSimpleName();
    private FileLineReader mFileReader;
    private MediaScannerConnection mediaScannerConnection;

    public FileLineReader getFileReader() {
        return mFileReader;
    }

    public void setFileReader(FileLineReader mFileReader) {
        this.mFileReader = mFileReader;
    }

    public void readFile(File file, FileLineReader.OnLineReadListener onLineRead){
        if ( getFileReader() != null ) {
            getFileReader().read(file, onLineRead);
        }
    }

    public boolean createDirectory( File dir ) {

        Log.d(TAG, "Can not find dir, create it: " + dir);
        if (!dir.mkdirs()) {
            Log.e(TAG, "!!! Can not create directory: " + dir);
            return false;
        }
        return true;
    }

    public File createFile(Context context, File file ) {
        if ( !file.exists()){
            Log.e(TAG, "File not exist: create" + file);
            try {
                file.createNewFile();
                scanFile( context, file );
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "File can not be created " + file, e);
                return null;
            }
        }
        return file;
    }

    private void scanFile( Context context, final File file) {
        final MediaScannerConnection.MediaScannerConnectionClient mediaScannerConnectionClient =
                new MediaScannerConnection.MediaScannerConnectionClient() {
                    @Override
                    public void onMediaScannerConnected() {
                        Log.d(TAG, "onMediaScannerConnected: " + file.getAbsolutePath());
                        if( mediaScannerConnection != null ) {
                            mediaScannerConnection.scanFile(file.getAbsolutePath(), null);
                        }
                    }

                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                        mediaScannerConnection.disconnect();
                    }
                };
        mediaScannerConnection = new MediaScannerConnection(context, mediaScannerConnectionClient);
        mediaScannerConnection.connect();
    }

    public void writeToFile(File file, List<String> values) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);  // create an actual file & a FileWriter obj
            for( String value : values ){
                if (value != null ) {
                    fw.write( value ); // write characters to
                }
            }
            fw.flush();	            // flush before closing
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "File can not be write" + file, e);
            if ( fw != null ){
                try {
                    fw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
