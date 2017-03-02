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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class splash extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;
    Animation animation;
    ImageView l;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Restaurants");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();
                    Toast.makeText(getApplicationContext(),""+dataSnapshot.child(key).getValue(),Toast.LENGTH_SHORT).show();
                }
                Intent mainIntent = new Intent(splash.this,MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        l=(ImageView)findViewById(R.id.imageView2);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anm);
        l.startAnimation(animation);
    }
}
