package com.example.mariogago.introductionworkshop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EventsActivity extends AppCompatActivity {

    public static final String NAME_EXTRA = "name";

    private TextView nameText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameText = (TextView) findViewById(R.id.events_name);

        String name = getNameFromIntent();
        nameText.setText(name);
    }

    @NonNull
    private String getNameFromIntent() {
        Intent intent = getIntent();
        String name = intent.getStringExtra(NAME_EXTRA);
        if (name == null) {
            throw new RuntimeException("Name missing");
        }
        return name;
    }
}
