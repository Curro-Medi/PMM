package com.example.practica3;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
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
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DescargaXML extends AppCompatActivity {


    ProgressDialog progressDoalog;
    private ImageView imageView;
    private Bitmap loadedImage;
    private String HttpAddress = "https://stackoverflow.com/feeds/tag?tagnames=android&sort=newest";
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    private ActivityResultLauncher<String> requestPermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descarga_x_m_l);

        Button btn = (Button) findViewById(R.id.btndescargaXML);

        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                ConnectivityManager connectivityManager = getSystemService(ConnectivityManager.class);
                Network currentNetwork = connectivityManager.getActiveNetwork();
                NetworkCapabilities caps = connectivityManager.getNetworkCapabilities(currentNetwork);

                if (caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                    Toast toad = Toast.makeText(DescargaXML.this, "Muy buena, tienes internet", Toast.LENGTH_SHORT);
                    toad.show();
                    if (ContextCompat.checkSelfPermission(
                            DescargaXML.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_GRANTED) {

                        progressDoalog = new ProgressDialog(DescargaXML.this);
                        progressDoalog.setMessage("Descarga en proceso");
                        progressDoalog.setTitle("Descargando");
                        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDoalog.show();
                        doSomeTaskAsync();


                    } else {
                        requestPermissionLauncher.launch(
                                Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    }
                } else {
                    Toast toad = Toast.makeText(DescargaXML.this, "No hay conexion a internet ", Toast.LENGTH_SHORT);
                    toad.show();
                }

            }
        });
    }

    public void doSomeTaskAsync(){
        Runnable runnable = new Runnable(){
            @Override
            public void run(){

                    saveXML();
                    AdaptadorXML adp = new AdaptadorXML();

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressDoalog.dismiss();

            }
        };
        executorService.submit(runnable);
    }
        public void saveXML() {

                try {

                    URL url = new URL("https://stackoverflow.com/feeds/tag?tagnames=android&sort=newest");

                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    urlConnection.setRequestMethod("GET");
                    urlConnection.setDoOutput(true);

                    urlConnection.connect();

                    File SDCardRoot = Environment.getExternalStorageDirectory();

                    File file = new File(SDCardRoot,"porfaquevaya.xml");

                    FileOutputStream fileOutput = new FileOutputStream(file);

                    InputStream inputStream = urlConnection.getInputStream();

                    int totalSize = urlConnection.getContentLength();
                    int downloadedSize = 0;

                    byte[] buffer = new byte[1024];
                    int bufferLength = 0;

                    while ( (bufferLength = inputStream.read(buffer)) > 0 ) {

                        fileOutput.write(buffer, 0, bufferLength);
                        downloadedSize += bufferLength;

                    }

                    fileOutput.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }












}