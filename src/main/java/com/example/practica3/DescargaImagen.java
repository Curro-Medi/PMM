package com.example.practica3;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DescargaImagen extends AppCompatActivity{

    ProgressDialog progressDoalog;
    private ImageView imageView;
    private Bitmap loadedImage;
    private String imageHttpAddress = "https://upload.wikimedia.org/wikipedia/en/b/bd/Doraemon_character.png";
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    private ActivityResultLauncher<String> requestPermissionLauncher;
    NotificationCompat.Builder noti ;
    private static final int idNoti=51625;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descarga_imagen);

        noti = new NotificationCompat.Builder(this);
        noti.setAutoCancel(true);
        Button btn = (Button) findViewById(R.id.btnImage);

        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new
                ActivityResultCallback<Boolean>(){
                    @Override
                    public void onActivityResult(Boolean isGranted){
                        if (isGranted) {
                            Toast toad = Toast.makeText(DescargaImagen.this, "Muy buena"  , Toast.LENGTH_SHORT);
                            toad.show();
                        } else {

                            Toast toad = Toast.makeText(DescargaImagen.this, "No "  , Toast.LENGTH_SHORT);
                            toad.show();
                        }
                    }
                });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connectivityManager = getSystemService(ConnectivityManager.class);
                Network currentNetwork = connectivityManager.getActiveNetwork();
                NetworkCapabilities caps = connectivityManager.getNetworkCapabilities(currentNetwork);

                if(caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)){
                    Toast toad = Toast.makeText(DescargaImagen.this, "Muy buena, tienes internet"  , Toast.LENGTH_SHORT);
                    toad.show();
                    if (ContextCompat.checkSelfPermission(
                            DescargaImagen.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_GRANTED) {

                        progressDoalog = new ProgressDialog(DescargaImagen.this);
                        progressDoalog.setMessage("Descarga en proceso");
                        progressDoalog.setTitle("Descargando");
                        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDoalog.show();
                        doSomeTaskAsync();


                    } else {
                        requestPermissionLauncher.launch(
                                Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    }
                }else{
                    Toast toad = Toast.makeText(DescargaImagen.this, "No hay conexion a internet "  , Toast.LENGTH_SHORT);
                    toad.show();
                }

           }
        });
    }

    public void doSomeTaskAsync(){
        Runnable runnable = new Runnable(){
            @Override
            public void run(){
                try{

                    URL imageUrl = new URL(imageHttpAddress);
                    HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                    loadedImage = BitmapFactory.decodeStream(conn.getInputStream());
                    doSomethingOnUI();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressDoalog.dismiss();

                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        executorService.submit(runnable);
    }


    public void doSomethingOnUI(){
        Handler uiThread = new Handler(Looper.getMainLooper());
        uiThread.post(new Runnable(){
            @Override
            public void run() {
                imageView = (ImageView) findViewById(R.id.viewimage);
                onPostExecute(loadedImage);
                imageView.setImageBitmap(loadedImage);

            }
        });
    }

    protected void onPostExecute(Bitmap result) {
        String date = (DateFormat.format("yyyyMMdd_hhmmss", new java.util.Date()).toString());
        saveImage(this, loadedImage, imageHttpAddress);
    }

    public void saveImage(Context context, Bitmap loadedImage, String imageName) {

        try {

            String ExternalStorageDirectory = Environment.getExternalStorageDirectory() + File.separator;

            File dir = new File(ExternalStorageDirectory + "/imagenes");

            dir.mkdirs();

            File dorae = new File(dir, "doraemon.png");

            OutputStream NOVITA = new FileOutputStream(dorae);

            loadedImage.compress(Bitmap.CompressFormat.PNG, 100, NOVITA);

            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "Doraemon");
            values.put(MediaStore.Images.Media.DESCRIPTION, "Fotito guapa del Doraemon");
            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis ());
            values.put(MediaStore.Images.ImageColumns.BUCKET_ID, dorae.toString().toLowerCase(Locale.getDefault()).hashCode());
            values.put(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, dorae.getName().toLowerCase(Locale.getDefault()));
            values.put("_data", dorae.getAbsolutePath());
            ContentResolver cr = getContentResolver();
            cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            noti.setSmallIcon(R.drawable.notificacion);
            noti.setTicker("Illoo la notificacion");
            noti.setWhen(System.currentTimeMillis());
            noti.setContentTitle("Olee el doraemon");
            noti.setContentText("Esa descarga buena del doraemon");

            Intent IntentNoti = new Intent(DescargaImagen.this, DescargaImagen.class);

            PendingIntent irintent = PendingIntent.getActivity(DescargaImagen.this, 0, IntentNoti, PendingIntent.FLAG_UPDATE_CURRENT);

            noti.setContentIntent(irintent);

            NotificationManager notificacion = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            notificacion.notify(idNoti, noti.build());

            NOVITA.flush();
            NOVITA.close();


        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }


    }





}