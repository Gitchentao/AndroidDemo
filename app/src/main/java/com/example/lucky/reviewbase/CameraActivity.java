package com.example.lucky.reviewbase;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends AppCompatActivity {

    private static String TAG = "CameraActivity";

    private Uri imageUri;
    private ImageView picture;

    private final int REQUEST_IMAGE_CAPTURE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Button startcamera = findViewById(R.id.startcamera);
        picture = findViewById(R.id.picture);

        startcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                File file = new File(getExternalCacheDir(), "test_image.jpg");
//                try {
//                    if (file.exists()) {
//                        file.delete();
//                    }
//                    file.createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(CameraActivity.this, "com.example.lucky.reviewbase.fileprovider1", getImageFile());
                    Log.i(TAG, "dispatchTakePictureIntent: " + imageUri.toString());

                } else {
                    imageUri = Uri.fromFile(getImageFile());
                }
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

            }
        });
    }


    // 获取文件对象，保存图片
    public File getImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMDD_HHmmss").format(new Date());
        String imageFileName = "test_" + timeStamp + ".jpg";
        //File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File file = new File(storageDir, imageFileName);
        if (file.exists()) {
            file.delete();
        }

        return file;
    }

    private File mTmpFile;

    private void dispatchTakePictureIntent(Intent intent) {
        if (intent.resolveActivity(getPackageManager()) == null) {
            return;
        }
        File imageFile = null;
        imageFile = getImageFile();
        mTmpFile = imageFile;
        if (imageFile != null) {
            if (Build.VERSION.SDK_INT >= 24) {
                imageUri = FileProvider.getUriForFile(CameraActivity.this, "com.example.lucky.reviewbase.fileprovider1", getImageFile());
                Log.i(TAG, "dispatchTakePictureIntent: " + imageUri.toString());

            } else {
                imageUri = Uri.fromFile(getImageFile());
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_IMAGE_CAPTURE:
                try {
                    if (requestCode != RESULT_OK) {

                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));

                        picture.setImageBitmap(bitmap);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }


    }
}
