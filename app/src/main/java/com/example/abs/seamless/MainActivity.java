package com.example.abs.seamless;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    List<card_data> data;
    rvadapter adapter;
    FloatingActionButton fab;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<>();
        rv = (RecyclerView)findViewById(R.id.rv);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Restaurants");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data.clear();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();
                    String name = dataSnapshot.child(key).child("name").getValue().toString();
                    String spl = dataSnapshot.child(key).child("spl").getValue().toString();
                    String lo = dataSnapshot.child(key).child("logo").getValue().toString();
                    card_data d = new card_data(name,spl,lo);
                    //Toast.makeText(getApplicationContext(),""+d.name,Toast.LENGTH_SHORT).show();
                    data.add(d);
                }
                adapter = new rvadapter(data);
                rv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error: "+databaseError,Toast.LENGTH_SHORT).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,add_res.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
