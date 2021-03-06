package com.example.mariogago.introductionworkshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button nextButton;
    private EditText nameText;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = new Preferences(this);
        nextButton = (Button) findViewById(R.id.main_next);
        nameText = (EditText) findViewById(R.id.main_name);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                openNextActivity(name);
            }
        });
    }

    private void openNextActivity(String name) {
        if (name.trim().isEmpty()) {
            Toast.makeText(this, R.string.name_missing, Toast.LENGTH_LONG).show();
        } else {
            preferences.saveName(name);

            Intent intent = new Intent(this, EventsActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
