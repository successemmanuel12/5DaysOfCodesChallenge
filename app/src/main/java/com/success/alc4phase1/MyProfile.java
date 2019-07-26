package com.success.alc4phase1;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;

public class MyProfile extends AppCompatActivity {
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        profileImage = findViewById(R.id.profile_image);
        //   Picasso.get().load(R.drawable.success).into(profileImage);

        Glide.with(this)
                .load(R.drawable.success)
                .into(profileImage);
    }
}
