package com.example.mydigitalcloset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.mydigitalcloset.closetPage.AllSavedOutfits;

import com.example.mydigitalcloset.databinding.ActivitySuccessPageBinding;

public class SuccessPage extends AppCompatActivity {
    ActivitySuccessPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySuccessPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
                Intent intent = new Intent(getApplicationContext(), AboutUsPageActivity.class);
                startActivity(intent);
                Log.i("MyDigitalCloset", "You have successfully opened the About Us Page");
                //Shows summited on the bottom of the screen
                Toast.makeText(getApplicationContext(), "Welcome to the About Us Page!", Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        });
        binding.settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingsPage.class);
                startActivity(intent);
                Log.i("MyDigitalCloset", "You have successfully opened settings Page");
                Toast.makeText(getApplicationContext(), "Welcome to the Settings Page!", Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        });
        //end nav buttons
    }//end oncreate
}