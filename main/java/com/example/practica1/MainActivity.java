package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btnform);
        btn.setOnClickListener(this);

        Button btn2 = (Button) findViewById(R.id.btnemail);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnform) {
            Intent intent = new Intent(v.getContext(), LayoutForm.class);
            startActivity(intent);
        }else{

            Intent envemail = new Intent(Intent.ACTION_SENDTO);
            envemail.setData(Uri.parse("mailto:"));
            envemail.putExtra(Intent.EXTRA_EMAIL, new String[] {"francisco.mediavilla.garcia@alumnos.fesac.es"});
            envemail.putExtra(Intent.EXTRA_SUBJECT, "Sugerencias");
            startActivity(envemail);

        }
    }
}