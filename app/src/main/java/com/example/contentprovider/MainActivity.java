package com.example.contentprovider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CONTACTS_ASK_PERMISSIONS = 1001;
    private static final int REQUEST_SMS_ASK_PERMISSIONS = 1002;

    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addControls();
        addEvents();
    }

    private void addEvents() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moManHinhDanhBa();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moManHinhTinNhan();
            }
        });
    }

    private void moManHinhDanhBa() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{"" + "android.permission.READ_CONTACTS"}, REQUEST_SMS_ASK_PERMISSIONS);
        } else{
            Intent intent = new Intent(MainActivity.this, DanhBaActivity.class);
            intent.setClassName("com.example.contentprovider", "com.example.contentprovider.DanhBaActivity");
            startActivity(intent);
        }
    }

    private void moManHinhTinNhan() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{"" + "android.permission.READ_SMS"}, REQUEST_SMS_ASK_PERMISSIONS);
        } else{
            Intent intent = new Intent(MainActivity.this, DocTinNhan.class);
            intent.setClassName("com.example.contentprovider", "com.example.contentprovider.DocTinNhan");
            startActivity(intent);
        }
    }

    private void addControls() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
    }
}