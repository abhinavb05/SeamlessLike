package com.example.abs.seamless;

import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class splash extends AppCompatActivity {

    Animation animation;
    ImageView l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        l=(ImageView)findViewById(R.id.imageView2);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anm);
        l.startAnimation(animation);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(splash.this,MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 1100);
    }
}
