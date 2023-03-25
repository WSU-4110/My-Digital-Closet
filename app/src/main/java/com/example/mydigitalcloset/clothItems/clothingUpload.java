package com.example.mydigitalcloset.clothItems;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydigitalcloset.R;
import com.example.mydigitalcloset.databinding.ActivityClothingUploadBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class clothingUpload extends AppCompatActivity {

    private static volatile clothingUpload INSTANCE = null;//Start of singleton design pattern

    private clothingUpload(){}

    public DatabaseReference mDatabase;

    public static clothingUpload getInstance() {
        if (INSTANCE == null) {
            synchronized (clothingUpload.class) {
                if (INSTANCE == null) {
                    INSTANCE = new clothingUpload();
                }
            }
        }
        return INSTANCE;
    }                                                   //End of singleton design pattern

    ProgressDialog progressDialog;
    ActivityClothingUploadBinding binding;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_upload);

        // Get a reference to the "clothing" node in your Firebase Realtime Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("clothing");
        //buttons!

        //Back to clothing front page
        Button b2c = findViewById(R.id.backToClothUp);
        b2c.setOnClickListener(view -> {
            Intent intent=new Intent(clothingUpload.this,clothingFront.class);
            startActivity(intent);
        });

        Button topPage = findViewById(R.id.addTops);
        topPage.setOnClickListener(view -> {
            Intent intent=new Intent(clothingUpload.this,clothingFront.class);
            startActivity(intent);
        });

        /*binding.addTops.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                progressDialog = new ProgressDialog(clothingUpload.this);
                progressDialog.setMessage("Test...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                //String imageID = binding.etimageId.getText().toString();

                //storageReference = FirebaseStorage.getInstance().getReference
            }
        });*/

    }


}
