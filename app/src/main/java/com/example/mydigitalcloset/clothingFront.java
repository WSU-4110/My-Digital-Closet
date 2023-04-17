package com.example.mydigitalcloset;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mydigitalcloset.closetPage.AllSavedOufitsPage;

//import com.example.mydigitalcloset.databinding.ActivityOutfitCreationBinding;

import com.example.mydigitalcloset.closetPage.AllSavedOutfits;

import com.example.mydigitalcloset.databinding.ActivityClothingFrontBinding;


public class clothingFront extends AppCompatActivity {

    ActivityClothingFrontBinding binding;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //NAV BAR STUFF:
        binding = ActivityClothingFrontBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setContentView(R.layout.activity_clothing_front);
        //this moves to the page that adds clothing items
        Button addPage = findViewById(R.id.addItem);
        addPage.setOnClickListener(view -> {
            Intent intent=new Intent(clothingFront.this,clothingUpload.class);
            startActivity(intent);
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

        //buttons!


        //go to all clothing items page
        Button seeAll = findViewById(R.id.seeAll);
        seeAll.setOnClickListener(view -> {
            Intent intent=new Intent(clothingFront.this, clothingSeeAll.class);
            startActivity(intent);
        });



    }//end of on create

}