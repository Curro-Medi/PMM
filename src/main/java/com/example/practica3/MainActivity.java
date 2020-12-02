package com.example.practica3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int REQUEST_CODE_ASK_PERMISSIONS = 123;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btnHTTP);
        btn.setOnClickListener(this);

        Button btn2 = (Button) findViewById(R.id.btnXML);
        btn2.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.permisos);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnHTTP){

            Intent intent = new Intent(v.getContext(), DescargaImagen.class);
            startActivity(intent);

        }else if(v.getId() == R.id.btnXML){

            Intent intent = new Intent(v.getContext(), DescargaXML.class);
            startActivity(intent);

        }

    }



}