package com.example.abs.seamless;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class add_res extends AppCompatActivity {

    EditText spl, name;
    Button add;
    String t,n,ln;
    TextView splt;
    Button logoi;
    ImageView logod;
    Button sbt;
    Bitmap logo;
    Uri filePath;

    FirebaseDatabase database;
    DatabaseReference myRef;

    FirebaseStorage storage;
    StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_res);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Restaurants");

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        name = (EditText) findViewById(R.id.name);
        spl = (EditText) findViewById(R.id.spcl);
        add = (Button) findViewById(R.id.adds);
        splt = (TextView) findViewById(R.id.splt);
        logod = (ImageView) findViewById(R.id.logov);
        logoi = (Button) findViewById(R.id.logoi);
        sbt = (Button) findViewById(R.id.sbmt);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spl.getText().toString().length() > 20)
                    Toast.makeText(getApplicationContext(), "Length >20", Toast.LENGTH_SHORT).show();
                else if(spl.getText().toString().length()==0)
                    Toast.makeText(getApplicationContext(), "Empty Input!", Toast.LENGTH_SHORT).show();
                else {
                    if (t == null) {
                        t = spl.getText().toString();
                        t = t.replace(" ", "");
                    } else
                        t += ", " + spl.getText().toString();
                    splt.setText(t);
                    spl.setText("");
                }
            }
        });

        logoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2);
            }
        });

        sbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n = name.getText().toString();
                if (valid() == true) {
                    ln = n.replaceAll("\\W+", "");
                    card_data data = new card_data(n,t,ln);
                    myRef.push().setValue(data);
                    uploadFile();
                }
            }
        });
    }

    public boolean valid() {
        if (name.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Enter name!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (name.getText().toString().length() > 30) {
            Toast.makeText(getApplicationContext(), "Name size >30", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(t==null){
            Toast.makeText(getApplicationContext(), "No speciality entered!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(logo==null)
        {
            Toast.makeText(getApplicationContext(), "No logo selected!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            filePath = data.getData();
            try {
                logo = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                logo = resizeBitmapFitXY(200,200,logo);
                logod.setImageBitmap(logo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        }

    public Bitmap resizeBitmapFitXY(int width, int height, Bitmap bitmap){
        Bitmap background = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        float originalWidth = bitmap.getWidth(), originalHeight = bitmap.getHeight();
        Canvas canvas = new Canvas(background);
        float scale, xTranslation = 0.0f, yTranslation = 0.0f;
        if (originalWidth > originalHeight) {
            scale = height/originalHeight;
            xTranslation = (width - originalWidth * scale)/2.0f;
        }
        else {
            scale = width / originalWidth;
            yTranslation = (height - originalHeight * scale)/2.0f;
        }
        Matrix transformation = new Matrix();
        transformation.postTranslate(xTranslation, yTranslation);
        transformation.preScale(scale, scale);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, transformation, paint);
        return background;
    }

    void uploadFile() {
        //if there is a file to upload
        if (filePath != null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            StorageReference riversRef = storageRef.child("logos/"+ln+".jpg");
            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //if the upload is successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying a success toast
                            Toast.makeText(getApplicationContext(), "Restaurant Uploaded", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //if the upload is not successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying error message
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        }
        //if there is not any file
        else {
            //you can display an error toast
        }
    }
}

