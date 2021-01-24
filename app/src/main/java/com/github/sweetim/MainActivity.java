package com.github.sweetim;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ALL_SMS_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] requiredPermissions = {
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_SMS
        };

        checkAndEnablePermission(requiredPermissions);
    }

    private void checkAndEnablePermission(String[] requiredPermissions) {
        if (!hasPermission(this, requiredPermissions)) {
            ActivityCompat.requestPermissions(this, requiredPermissions, REQUEST_ALL_SMS_PERMISSIONS);
        }
    }

    private boolean hasPermission(Context context, String... permissions) {
        for (String permission : permissions) {
            boolean isNotOk = ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED;

            if (isNotOk) {
                return false;
            }
        }

        return true;
    }
}