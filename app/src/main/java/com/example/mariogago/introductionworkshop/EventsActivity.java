package com.example.mariogago.introductionworkshop;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class EventsActivity extends AppCompatActivity implements LocationGetter.Callback {

    private static int REQUEST_PERMISSION_CODE = 11;

    private TextView nameText;
    private TextView locationText;
    private Preferences preferences;
    private LocationGetter locationGetter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        locationGetter = new LocationGetter(this);
        preferences = new Preferences(this);
        nameText = (TextView) findViewById(R.id.events_name);
        locationText = (TextView) findViewById(R.id.events_location);

        String name = preferences.getName();

        if (name == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            nameText.setText(getResources().getString(R.string.events_name, name));

            tryAndGetLocation();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        locationGetter.stop();
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            }
        }
    }
    
    private void tryAndGetLocation() {
        String permission = Manifest.permission.ACCESS_FINE_LOCATION;
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { permission }, 11);
            return;
        }

        getLocation();
    }

    public void getLocation() {
        locationGetter.start(this);
    }

    @Override
    public void onNewLocation(Location location) {
        locationText.setText(getResources().getString(R.string.events_location, location.getLatitude(), location.getLongitude()));
    }
}
