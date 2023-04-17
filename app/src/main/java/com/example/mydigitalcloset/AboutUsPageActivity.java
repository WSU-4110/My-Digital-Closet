package com.example.mydigitalcloset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;

import android.widget.Toast;


import com.example.mydigitalcloset.closetPage.AllSavedOutfits;
import com.example.mydigitalcloset.databinding.ActivityAboutusPageBinding;


public class AboutUsPageActivity extends AppCompatActivity {
    ActivityAboutusPageBinding binding;

  @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      // Inflate the layout
      binding = ActivityAboutusPageBinding.inflate(getLayoutInflater());
      setContentView(binding.getRoot());


            Button b1=(Button)findViewById(R.id.btnContactForm);

            b1.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    openContactForm();

                    Log.i("MyDigitalCloset", "You have successfully opened the Contact Form");
                    //Shows summited on the bottom of the screen
                    Toast.makeText(getApplicationContext(), "Welcome to the Contact Form!", Toast.LENGTH_SHORT)
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
                finish();
            }
        });

      //end nav buttons

    }//end onCreate

    public void openContactForm(){
        Intent intent = new Intent(this, ContactForm.class);
        startActivity(intent);
    }

}