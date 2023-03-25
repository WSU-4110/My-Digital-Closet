package com.example.mydigitalcloset.clothItems;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mydigitalcloset.ContactForm;
import com.example.mydigitalcloset.OutfitCreationActivity;
import com.example.mydigitalcloset.R;
import com.example.mydigitalcloset.closetPage.AllSavedOufitsPage;
import com.example.mydigitalcloset.databinding.ActivityClothingFrontBinding;
//import com.example.mydigitalcloset.databinding.ActivityOutfitCreationBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.storage.StorageReference;

public class clothingFront extends AppCompatActivity {

    ActivityClothingFrontBinding binding;
    Context context;
    ProgressDialog progressDialog;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_front);
        //NAV BAR STUFF:
        binding = ActivityClothingFrontBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
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
                    /*case R.id.settings:
                        startActivity(new Intent(OutfitCreationActivity.this, SETTINGS.class));
                        break;*/
                }
                return true;
            }
        });
        //end nav bar

        //buttons!

        //go to clothing upload page
        Button addPage = findViewById(R.id.addItem);
        addPage.setOnClickListener(view -> {     //from THIS page to      THIS page
            Intent intent=new Intent(clothingFront.this,clothingUpload.class);
            startActivity(intent);
        });

        //go to all clothing items page
        Button seeAll = findViewById(R.id.seeAll);
        seeAll.setOnClickListener(view -> {
            Intent intent=new Intent(clothingFront.this,clothingSeeAll.class);
            startActivity(intent);
        });



    }//end of on create
}