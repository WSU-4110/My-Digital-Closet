package com.example.mydigitalcloset;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydigitalcloset.databinding.ActivityClothingFrontBinding;
import com.example.mydigitalcloset.databinding.ActivityOutfitCreationBinding;

public class clothingFront extends AppCompatActivity {

    ActivityClothingFrontBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //NAV BAR STUFF:
        binding = ActivityClothingFrontBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.home:
                    Intent intentHome = new Intent(getApplicationContext(), OutfitCreationActivity.class);
                    startActivity(intentHome);
                    finish();
                    break;
                /*case R.id.wardrobe:
                    Intent intentWardrobe = new Intent(getApplicationContext(), OutfitCreationActivity.class);
                    startActivity(intentWardrobe);
                    finish();
                    break;*/
                case R.id.addItem:
                    Intent intentAdd = new Intent(getApplicationContext(), clothingFront.class);
                    startActivity(intentAdd);
                    finish();
                    break;
                case R.id.contactSupport:
                    Intent intentContact = new Intent(getApplicationContext(), ContactForm.class);
                    startActivity(intentContact);
                    finish();
                    break;
            }

            return true;
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