package com.apptech.yohannes.paymentassistant.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.apptech.yohannes.paymentassistant.R;
import com.apptech.yohannes.paymentassistant.helpers.CameraPreview;
import com.apptech.yohannes.paymentassistant.services.OCRService;

import java.io.File;

public class CameraActivity extends Activity implements View.OnClickListener, Camera.PictureCallback {
    private FrameLayout previewHolder;
    private Camera camera;
    private CameraPreview preview;
    private ImageButton btnCapture;
    private ImageView imgCardNumber;

    int x, y, width, height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

       // AlertDialog.Builder dialogBuilder = new AlertDialog.Builder( getBaseContext());
        //dialogBuilder.setMessage(R.string.ocr_notice);
        //dialogBuilder.show();

        previewHolder = (FrameLayout)findViewById(R.id.previewHolder);
        btnCapture = (ImageButton)findViewById(R.id.btnCapture);
        imgCardNumber = (ImageView)findViewById(R.id.imgCardNumber);

        if(!this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
        {
            Toast.makeText(getApplicationContext(), "No camera, sorry", Toast.LENGTH_LONG);
            finish();
        }
        else
        {
            camera = Camera.open(GetBackCameraID());
            preview = new CameraPreview(getApplicationContext(), camera);
            previewHolder.addView(preview);
            btnCapture.bringToFront();
            imgCardNumber.bringToFront();

            //set event listeners
            previewHolder.setOnClickListener(this);
            btnCapture.setOnClickListener(this);
        }
    }

    private int GetBackCameraID()// throws Exception
    {
      for(int i = 0; i <  Camera.getNumberOfCameras();  i++)
      {
          Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
          Camera.getCameraInfo(i, cameraInfo);
          if(cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK)
              return i;
      }
        return -1;
        //throw new Exception("Camera not found");
    }

    @Override
    public void onClick(View view) {

        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_AUTOFOCUS))
            return;

        if(view == previewHolder)
            camera.autoFocus(null);
        else if(view == btnCapture)
            camera.takePicture(null, null, this);
    }

    @Override
    public void onPictureTaken(byte[] bytes, Camera camera) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        x = (int)(imgCardNumber.getX() + bitmap.getWidth() * .525);
        y = (int)(imgCardNumber.getY() + bitmap.getWidth() * .26);
        width = (int)(imgCardNumber.getWidth() -  imgCardNumber.getWidth() * 0.30);
        height = (int)(imgCardNumber.getHeight() * .60);

        bitmap = Bitmap.createBitmap(bitmap, x, y, width, height);
        imgCardNumber.setImageBitmap(bitmap);

        OCRService ocrService = new OCRService();
        String detectedText = ocrService.Read(bitmap);
        Intent intent = new Intent();
        intent.putExtra("detectedText", detectedText);
        setResult(Activity.RESULT_OK, intent);
       finish();
    }
}
