package com.example.mydigitalcloset;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mydigitalcloset.closetPage.AllSavedOufitsPage;
import com.example.mydigitalcloset.databinding.ActivityClothingFrontBinding;
//import com.example.mydigitalcloset.databinding.ActivityOutfitCreationBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class clothingFront extends AppCompatActivity {

    ActivityClothingFrontBinding binding;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);

        //NAV BAR STUFF:
        binding = ActivityClothingFrontBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        /*binding.bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent intentHome = new Intent(getApplicationContext(), OutfitCreationActivity.class);
                        startActivity(intentHome);
                        finish();
                        break;
                    case R.id.addItem:
                        return true;
                    case R.id.contactSupport:
                        Intent intentContact = new Intent(getApplicationContext(), ContactForm.class);
                        startActivity(intentContact);
                        finish();
                        break;
                    case R.id.wardrobe:
                        Intent intentWardrobe = new Intent(getApplicationContext(), AllSavedOufitsPage.class);
                        startActivity(intentWardrobe);
                        finish();
                        break;
                    case R.id.settings:
                        startActivity(new Intent(OutfitCreationActivity.this, SETTINGS.class));
                        break;
                }
                return true;
            }
        });
        //end nav bar*/

        setContentView(R.layout.activity_clothing_front);
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
                Intent intent = new Intent(getApplicationContext(), AllSavedOufitsPage.class);
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