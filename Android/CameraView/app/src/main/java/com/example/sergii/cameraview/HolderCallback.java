package com.example.sergii.cameraview;

import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.IOException;

/**
 * Created by sergii on 13.11.16.
 */
class HolderCallback implements SurfaceHolder.Callback {

    private static final String TAG = HolderCallback.class.getSimpleName();
    private final int CAMERA_ID = 0;
    private Camera mCamera;
    private IRotation mRotation;

    public IRotation getRotation() {
        return mRotation;
    }

    public void setRotation(IRotation mRotation) {
        this.mRotation = mRotation;
    }

    public interface IRotation {
        int getRotation();
    }

    public void setCamera( Camera aCamera ){
        this.mCamera = aCamera;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        Log.d(TAG, "surfaceCreated: ");
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        mCamera.stopPreview();
        setCameraDisplayOrientation(CAMERA_ID);
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    void setCameraDisplayOrientation(int cameraId) {
        // определяем насколько повернут экран от нормального положения
        int degrees = 0;
        if ( getRotation() != null ){
            degrees = getRotation().getRotation();
            Log.d(TAG, "setCameraDisplayOrientation: " + degrees);
        }
        int result = 0;

        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);

        // задняя камера
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
            result = ((360 - degrees) + info.orientation);
        } else
            // передняя камера
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                result = ((360 - degrees) - info.orientation);
                result += 360;
            }
        result = result % 360;
        mCamera.setDisplayOrientation(result);
    }

}
