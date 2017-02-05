package com.example.mariogago.introductionworkshop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class EventDetails extends AppCompatActivity {

    private ImageButton imageButton;
    private EditText messageText;
    private Button shareButton;
    private Bitmap imageBitmap;
    private Uri photoURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        imageButton = (ImageButton) findViewById(R.id.photo);
        messageText= (EditText) findViewById(R.id.details_comment);
        shareButton = (Button) findViewById(R.id.share_data);

        String title = getIntent().getStringExtra("name");;
        setTitle(title);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCameraPhoto();
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {;
            imageButton.setImageURI(photoURI);
            imageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    private void getCameraPhoto() {
        File folder = new File(getFilesDir(), "images");
        folder.mkdirs();

        File file = new File(folder, "image.jpg");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        photoURI = FileProvider.getUriForFile(this, "com.example.mariogago" +
                ".introductionworkshop.fileprovider", file);

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 101);
        }
    }

    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, messageText.getText().toString());
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_STREAM, photoURI);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(photoURI, getContentResolver().getType(photoURI));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
