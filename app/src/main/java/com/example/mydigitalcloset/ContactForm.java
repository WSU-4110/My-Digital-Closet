package com.example.mydigitalcloset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mydigitalcloset.closetPage.AllSavedOufitsPage;
import com.example.mydigitalcloset.closetPage.AllSavedOutfits;
import com.example.mydigitalcloset.databinding.ActivityClothingFrontBinding;
import com.example.mydigitalcloset.databinding.ActivityContactFormBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContactForm extends AppCompatActivity {

    ActivityContactFormBinding binding;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);

        //NAV BAR STUFF:
        binding = ActivityContactFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setContentView(R.layout.activity_contact_form);


            setContentView(R.layout.activity_contact_form);

            Button b1=(Button)findViewById(R.id.btnsubmit);

            b1.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    openSuccessPage();


                    Log.i("MyDigitalCloset", "Your form has been successfully submitted");
                    //Shows summited on the bottom of the screen
                    Toast.makeText(getApplicationContext(), "Submitted!", Toast.LENGTH_SHORT)
                            .show();
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
                Intent intent = new Intent(getApplicationContext(), AllSavedOutfits.class);
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

        //end nav buttons

    }//end oncreate

    public void openSuccessPage(){
        Intent intent = new Intent(this, SuccessPage.class);
        startActivity(intent);
    }

}