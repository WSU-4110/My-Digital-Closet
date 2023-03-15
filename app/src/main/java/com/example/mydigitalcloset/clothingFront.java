package com.example.mydigitalcloset;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mydigitalcloset.closetPage.AllSavedOufitsPage;
import com.example.mydigitalcloset.databinding.ActivityClothingFrontBinding;
import com.example.mydigitalcloset.databinding.ActivityOutfitCreationBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class clothingFront extends AppCompatActivity {

    ActivityClothingFrontBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //NAV BAR STUFF:
        binding = ActivityClothingFrontBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(clothingFront.this, OutfitCreationActivity.class));
                        break;
                    case R.id.addItem:
                        /*Intent intentAdd = new Intent(getApplicationContext(), clothingFront.class);
                        startActivity(intentAdd);*/
                        //startActivity(new Intent(clothingFront.this, clothingFront.class));
                        //finish();
                        return true;
                        //break;
                    case R.id.contactSupport:
                        /*Intent intentContact = new Intent(getApplicationContext(), ContactForm.class);
                        startActivity(intentContact);*/
                        startActivity(new Intent(clothingFront.this, ContactForm.class));
                        //finish();
                        break;
                    case R.id.wardrobe:
                        startActivity(new Intent(clothingFront.this, AllSavedOufitsPage.class));
                        break;
                    /*case R.id.settings:
                        startActivity(new Intent(OutfitCreationActivity.this, SETTINGS.class));
                        break;*/
                }
                return true;
            }
        });
        //end nav bar

        setContentView(R.layout.activity_clothing_front);
        //this moves to the page that adds clothing items
        Button addPage = findViewById(R.id.addItem);
        addPage.setOnClickListener(view -> {
            Intent intent=new Intent(clothingFront.this,clothingUpload.class);
            startActivity(intent);
        });
    }
}