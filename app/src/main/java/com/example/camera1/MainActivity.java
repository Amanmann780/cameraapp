package com.example.camera1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity  extends AppCompatActivity {
    public static final int REQUEST_CAPTURE =1;
    ImageView result_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button click =(Button)findViewById(R.id.btnclickphoto);
        result_photo =(ImageView)findViewById(R.id.imgview);
        if (!hascamera())
        {
            click.setEnabled(false);
        }
    }
    public boolean hascamera()
    {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);

    }
    public void launchCamera(View v)
    {
        Intent i =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i , REQUEST_CAPTURE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CAPTURE && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap)extras.get("data");
            result_photo.setImageBitmap(photo);
        }

    }
}
