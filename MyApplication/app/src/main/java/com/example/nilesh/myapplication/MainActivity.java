package com.example.nilesh.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;
    private ImageView img;
    private Button button,aButton;
    private int GALLERY_PICK = 100;
    private File imageFile;
    private GoogleMap googleMap;

    String timeStamp = "DCIM" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = timeStamp + ".jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            initializeMap();
        }catch(Exception e){
            e.printStackTrace();
        }


        img = (ImageView)findViewById(R.id.imageView);
        button = (Button)findViewById(R.id.button2);
        aButton = (Button)findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //i.setType("image/*");
                startActivityForResult(i, GALLERY_PICK);

            }
        });

        aButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                View cView = findViewById(R.id.imageView);
                cView.setDrawingCacheEnabled(true);

                Bitmap bm = cView.getDrawingCache();
                File file = Environment.getExternalStorageDirectory();
                File cachefilepath = new File(file.getAbsolutePath() + "DCIM/camera/image.jpg");
                try {
                    cachefilepath.createNewFile();
                    FileOutputStream outputStream = new FileOutputStream(cachefilepath);
                    bm.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                    outputStream.close();

                }catch (Exception e){
                    e.printStackTrace();
                    //Toast.makeText(this,"Errrrrrorr",Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(cachefilepath));
                startActivityForResult(intent,1);
            }
        });


    }

    private void initializeMap() {
        if(googleMap==null){

        }
    }

    public void process(View view){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), imageFileName);
        Uri tempUri = Uri.fromFile(imageFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(intent, SELECT_PICTURE);

      /*  intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,tempUri);
        this.sendBroadcast(intent);
        */
        Toast.makeText(this,tempUri.getPath(),Toast.LENGTH_LONG).show();

        galleryAddPic(imageFile.toString());

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_PICK) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                //System.out.println("Image Path : " + selectedImagePath);
                Toast.makeText(this,"Image Path :" + selectedImagePath,Toast.LENGTH_LONG).show();
                img.setImageURI(selectedImageUri);
           }

        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);

    }

    private void galleryAddPic(String mCurrentPhotoPath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }





}
