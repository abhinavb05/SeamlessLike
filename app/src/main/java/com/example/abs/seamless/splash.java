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

    private final int SPLASH_DISPLAY_LENGTH = 200;
    Animation animation;
    ImageView l;

    List<card_data> data_a;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        data_a = new ArrayList<>();

        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        /*mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Restaurants").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    card_data d = noteDataSnapshot.getValue(card_data.class);
                    data_a.add(d);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Toast.makeText(getApplicationContext(), ""+data_a.size(), Toast.LENGTH_SHORT).show();*/

        l=(ImageView)findViewById(R.id.imageView2);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anm);
        l.startAnimation(animation);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                // Create an Intent that will start the Menu-Activity.
                Intent mainIntent = new Intent(splash.this,MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
