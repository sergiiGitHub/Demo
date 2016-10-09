package com.example.sergii.touchcatch;

import android.os.Environment;

import java.io.File;

/**
 * Created by sergii on 09.10.16.
 */
public class ConfigFile {

    public static final int DEFAULT_POS_X = 492;
    public static final int DEFAULT_POS_Y = 527;
    public static final int DEFAULT_SIZE_X = 160;
    public static final int DEFAULT_SIZE_Y = 300;

    public static final String DEFAULT_CONFIG_DATA
            = "position_x=" + ConfigFile.DEFAULT_POS_X
            + "position_y=" + ConfigFile.DEFAULT_POS_Y
            + "size_x=" + ConfigFile.DEFAULT_SIZE_X
            + "size_y=" + ConfigFile.DEFAULT_SIZE_Y;

    public static final String FILE_NAME = "config.txt";
    public static final String DIR_MANE = "touchCatch";
    public static final File ROOT_DIR = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
    private static File mHomeDir;
    private static File mConfigFile;


    public static File getHomeDir(){
        if ( mHomeDir == null ){
            mHomeDir = new File(ROOT_DIR, DIR_MANE);
        }
        return mHomeDir;
    }

    public static File getConfigFile() {
        if ( mConfigFile == null ){
            mConfigFile = new File(getHomeDir(), FILE_NAME);
        }
        return mConfigFile;
    }


}
