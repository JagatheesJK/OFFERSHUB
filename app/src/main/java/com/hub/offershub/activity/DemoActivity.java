package com.hub.offershub.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.hub.offershub.databinding.ActivityDemoBinding;

import java.io.File;

public class DemoActivity extends AppCompatActivity {

    private ActivityDemoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDemoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Uri uri =  Uri.parse( "https://img.freepik.com/free-vector/realistic-neon-lights-background_23-2148907367.jpg");
        File file = new File(uri.getPath());
        Glide.with(getApplicationContext()).load(file).into(binding.demoImg);

//        Bitmap bmImg = BitmapFactory.decodeFile("https://img.freepik.com/free-vector/realistic-neon-lights-background_23-2148907367.jpg");
//        binding.demoImg.setImageBitmap(bmImg);

//        testMethod();
    }

    private void testMethod() {
        File imgFile = new File("https://img.freepik.com/free-vector/realistic-neon-lights-background_23-2148907367.jpg");
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            binding.demoImg.setImageBitmap(myBitmap);
        }
    }
}