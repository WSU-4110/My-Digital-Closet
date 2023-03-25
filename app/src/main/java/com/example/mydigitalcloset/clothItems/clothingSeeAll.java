package com.example.mydigitalcloset.clothItems;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydigitalcloset.R;
import com.example.mydigitalcloset.databinding.ActivityClothingSeeAllBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class clothingSeeAll extends AppCompatActivity {
    private DatabaseReference mDatabase;
    ProgressDialog progressDialog;
    ActivityClothingSeeAllBinding binding;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_see_all);

        // Get a reference to the "clothing" node in your Firebase Realtime Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("clothing");

        Button topsButton = findViewById(R.id.seeTops);
        topsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new clothing item under the "clothing/tops" node in your database
                String clothingId = mDatabase.child("tops").push().getKey();
                mDatabase.child("tops").child(clothingId).setValue("new item");
            }
        });

        Button bottomsButton = findViewById(R.id.seeBottoms);
        bottomsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new clothing item under the "clothing/bottoms" node in your database
                String clothingId = mDatabase.child("bottoms").push().getKey();
                mDatabase.child("bottoms").child(clothingId).setValue("new item");
            }
        });

        Button shoesButton = findViewById(R.id.seeShoes);
        shoesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new clothing item under the "clothing/shoes" node in your database
                String clothingId = mDatabase.child("shoes").push().getKey();
                mDatabase.child("shoes").child(clothingId).setValue("new item");
            }
        });

        Button socksButton = findViewById(R.id.seeSocks);
        socksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new clothing item under the "clothing/socks" node in your database
                String clothingId = mDatabase.child("socks").push().getKey();
                mDatabase.child("socks").child(clothingId).setValue("new item");
            }
        });

        Button headwearButton = findViewById(R.id.seeHeadwear);
        headwearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new clothing item under the "clothing/headwear" node in your database
                String clothingId = mDatabase.child("headwear").push().getKey();
                mDatabase.child("headwear").child(clothingId).setValue("new item");
            }
        });

        Button otherButton = findViewById(R.id.seeOther);
        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new clothing item under the "clothing/other" node in your database
                String clothingId = mDatabase.child("other").push().getKey();
                mDatabase.child("other").child(clothingId).setValue("new item");
            }
        });
        //buttons!

        //Back to clothing front page
        Button b2c = findViewById(R.id.backToClothUp);
        b2c.setOnClickListener(view -> {
            Intent intent=new Intent(clothingSeeAll.this,clothingFront.class);
            startActivity(intent);
        });
        

    }


}
