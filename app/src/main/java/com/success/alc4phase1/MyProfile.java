package com.success.alc4phase1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MyProfile extends AppCompatActivity {
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        profileImage = findViewById(R.id.profileImage);
        Picasso.get().load(R.drawable.profile_pics).into(profileImage);
    }
}
