package com.example.mydigitalcloset;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydigitalcloset.closetPage.AllSavedOufitsPage;
import com.example.mydigitalcloset.closetPage.AllSavedOutfits;
import com.example.mydigitalcloset.databinding.ActivityClothingUploadBinding;
import com.example.mydigitalcloset.databinding.ActivityOutfitCreationBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class clothingUpload extends AppCompatActivity {
    private DatabaseReference mDatabase;
    ActivityClothingUploadBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout
        binding = ActivityClothingUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get a reference to the "clothing" node in your Firebase Realtime Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("clothing");

        Button topsButton = findViewById(R.id.button);
        topsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new clothing item under the "clothing/tops" node in your database
                String clothingId = mDatabase.child("tops").push().getKey();
                mDatabase.child("tops").child(clothingId).setValue("new item");
            }
        });

        Button bottomsButton = findViewById(R.id.button3);
        bottomsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new clothing item under the "clothing/bottoms" node in your database
                String clothingId = mDatabase.child("bottoms").push().getKey();
                mDatabase.child("bottoms").child(clothingId).setValue("new item");
            }
        });

        Button shoesButton = findViewById(R.id.button4);
        shoesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new clothing item under the "clothing/shoes" node in your database
                String clothingId = mDatabase.child("shoes").push().getKey();
                mDatabase.child("shoes").child(clothingId).setValue("new item");
            }
        });

        Button socksButton = findViewById(R.id.button6);
        socksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new clothing item under the "clothing/socks" node in your database
                String clothingId = mDatabase.child("socks").push().getKey();
                mDatabase.child("socks").child(clothingId).setValue("new item");
            }
        });

        Button headwearButton = findViewById(R.id.button5);
        headwearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new clothing item under the "clothing/headwear" node in your database
                String clothingId = mDatabase.child("headwear").push().getKey();
                mDatabase.child("headwear").child(clothingId).setValue("new item");
            }
        });

        Button otherButton = findViewById(R.id.button7);
        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new clothing item under the "clothing/other" node in your database
                String clothingId = mDatabase.child("other").push().getKey();
                mDatabase.child("other").child(clothingId).setValue("new item");
            }
        });

        //navigation buttons
        binding.homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OutfitCreationActivity.class);
                startActivity(intent);
                finish();
            }
        });
        binding.wardrobeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),  AllSavedOutfits.class);
                startActivity(intent);
                finish();
            }
        });
        binding.addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), clothingFront.class);
                startActivity(intent);
                finish();
            }
        });
        binding.contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AboutUsPageActivity.class); //change depending on romans pages
                startActivity(intent);
                finish();
            }
        });
        /*binding.settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ?.class);
                startActivity(intent);
                finish();
            }
        });*/
        //end nav buttons
    }//end oncreate
}
