package opencv.nilesh.navigation_demo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

   private static final int SELECT_PICTURE = 1;
    private File imageFile = Environment.getExternalStorageDirectory();
    private String selectedImagePath;
    private ImageView img;
    private int GALLERY_PICK = 100;
    private Uri tempUri;
    private String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    private String imageFileName = timeStamp + ".jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        img = (ImageView)findViewById(R.id.imageView2);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "/"+imageFileName);
          //  sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,Uri.fromFile(imageFile)));
            tempUri = Uri.fromFile(imageFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
            //intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(intent,SELECT_PICTURE);
            Toast.makeText(this,tempUri.getPath(), Toast.LENGTH_LONG).show();



        } else if (id == R.id.nav_gallery) {

            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            //i.setType("image/*");
            startActivityForResult(i, GALLERY_PICK);

        } else if (id == R.id.nav_slideshow) {

            NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_menu_manage).setContentTitle("Intent").setContentText("Open Activity");
            Intent intent = new Intent(MainActivity.this,navigation.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addParentStack(navigation.class);
            stackBuilder.addNextIntent(intent);
            PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
            nBuilder.setContentIntent(pendingIntent);
            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//            nBuilder.getNotification().flags |= Notification.FLAG_AUTO_CANCEL;
              nBuilder.setAutoCancel(true);
            notificationManager.notify(1, nBuilder.build());
            Toast.makeText(this,"Notification arrive",Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_manage) {

            Intent intent = new Intent(MainActivity.this,navigation.class);
            startActivity(intent);

        } else if (id == R.id.nav_send) {

            View content = findViewById(R.id.imageView2);
            content.setDrawingCacheEnabled(true);

            Bitmap bitmap = content.getDrawingCache();
            File root = Environment.getExternalStorageDirectory();
            File cachePath = new File(root.getAbsolutePath() + "/DCIM/Camera/" + timeStamp + ".jpg");
            try {
                cachePath.createNewFile();
                FileOutputStream ostream = new FileOutputStream(cachePath);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                ostream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/*");
            share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(cachePath));
            startActivity(Intent.createChooser(share,"Share via"));



        /* //   Uri uri = Uri.parse(Environment.DIRECTORY_PICTURES);
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            //shareIntent.setData(uri);
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(imageFile.getAbsoluteFile() + "/Pictures/nil.jpg")));
            //Uri.fromFile(new File(getApplication().getFileStreamPath(imageFileName).getAbsolutePath()))
            shareIntent.setType("images/jpg");
            startActivityForResult(shareIntent, 0);
*/
        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode != 1) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                //System.out.println("Image Path : " + selectedImagePath);
                Toast.makeText(this,"Image Path :" + selectedImagePath,Toast.LENGTH_LONG).show();
                img.setImageURI(selectedImageUri);
            }
            else {
                galleryAddPic(imageFile);
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

    private void galleryAddPic(File imgfile) {
      /*  Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        //imageFile = MediaScannerConnection.scanFile(this, new String[] {imageFile.getPath()}, new String[] {"images/jpg"}, null);
        this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
*/
        try {
            MediaStore.Images.Media.insertImage(getContentResolver(),imgfile.getAbsolutePath(),"","");
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }

    }

}
