package com.example.contactstest;

import static android.Manifest.permission.*;
import static android.content.pm.PackageManager.*;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.Manifest.permission;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.contactstest.data.ContactManager;
import com.example.contactstest.data.FileManager;

import java.security.Permission;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "FF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Result API
        ActivityResultLauncher<String[]> launcher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(), callback -> {
                    if (Boolean.TRUE.equals(callback.get(WRITE_EXTERNAL_STORAGE))) {
                        FileManager.Write(this, "Test text");
                    }
                    if (Boolean.TRUE.equals(callback.get(READ_CONTACTS))) {
                        ContactManager.contacts(this);
                    }
                }
        );

        //

        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            launcher.launch(new String[]{WRITE_EXTERNAL_STORAGE});
        } else {
            Toast.makeText(this,"EPTA",Toast.LENGTH_LONG).show();
        }

        if (ContextCompat.checkSelfPermission(this, READ_CONTACTS) != PERMISSION_GRANTED) {
            launcher.launch(new String[]{READ_CONTACTS});
        } else {
            Toast.makeText(this,"CONTACTOS",Toast.LENGTH_LONG).show();
        }
    }
}