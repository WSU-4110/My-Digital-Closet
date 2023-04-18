package com.example.mydigitalcloset.closetPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mydigitalcloset.AboutUsPageActivity;
import com.example.mydigitalcloset.ContactForm;
import com.example.mydigitalcloset.OutfitCreationActivity;
import com.example.mydigitalcloset.R;
import com.example.mydigitalcloset.SettingsPage;
import com.example.mydigitalcloset.clothingFront;
import com.example.mydigitalcloset.databinding.ActivityAllSavedOutfitsBinding;
import com.example.mydigitalcloset.databinding.ActivityOutfitCreationBinding;

public class AllSavedOutfits extends AppCompatActivity {
    private ImageView rectangle_1;
    ActivityAllSavedOutfitsBinding binding;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout
        binding = ActivityAllSavedOutfitsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        rectangle_1 = (ImageView) findViewById(R.id.rectangle_1);
        rectangle_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openOutfit();
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
        binding.settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingsPage.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void openOutfit(){
        Intent intent = new Intent(this, SavedFit.class);
        startActivity(intent);
    }

}