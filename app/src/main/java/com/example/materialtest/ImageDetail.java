package com.example.materialtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;

public class ImageDetail extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String imageUrl = getIntent().getStringExtra("imageUrl");
        ImageView imageView = new ImageView(this);
        setContentView(imageView);
        BitmapUtils bitmapUtils = new BitmapUtils(this);
        bitmapUtils.display(imageView, imageUrl);
    }
}
