package com.apptech.yohannes.paymentassistant.helpers;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

/**
 * Created by Yohannes on 7/21/2014.
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    SurfaceHolder surfaceHolder;
    Camera _camera;
    Context context;

    public CameraPreview(Context context, Camera camera) {
        super(context);
        this.context = context;
        surfaceHolder = getHolder();
        _camera = camera;
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            _camera.setPreviewDisplay(surfaceHolder);
            SetProperties();
            _camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (surfaceHolder.getSurface() == null) return;

        try {
            _camera.stopPreview();
        } catch (Exception ex) {
          //ignore these errors
         }

        try {
            _camera.setPreviewDisplay(surfaceHolder);
            _camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        try {
            _camera.release();
        }
        catch(Exception ex)
        {
            //ignore the error
        }
    }

    private void SetProperties() {
        Camera.Parameters parameters = _camera.getParameters();
        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_MACRO);
        try {
            _camera.setParameters(parameters);
        }
        catch (Exception ex)
        {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    private Camera.Size GetBiggestSize(List<Camera.Size> sizeList, Camera.Size size)
    {
        for(int i = 0; i < sizeList.toArray().length; i++)
        {
            float _area = sizeList.get(i).height * sizeList.get(i).width;
            if(_area > (size.width * size.height))
                size = sizeList.get(i);
        }
        return  size;
    }
}
