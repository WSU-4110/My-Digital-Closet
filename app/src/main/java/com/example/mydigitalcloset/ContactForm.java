package com.example.mydigitalcloset;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.mydigitalcloset.closetPage.AllSavedOutfits;

import com.example.mydigitalcloset.databinding.ActivityContactFormBinding;


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


                    String name = binding.editTextTextPersonName.getText().toString();
                    String email = binding.editTextTextPersonName2.getText().toString();
                    String message = binding.editTextTextPersonName4.getText().toString();


                    String to = "MyDigitalClosetCS@gmail.com";
                    String subject = "Contact Form Submission";
                    String body = "Name: " + name + "\nEmail: " + email + "\nMessage: " + message;


                    Intent emailIn = new Intent(Intent.ACTION_SEND);
                    emailIn.setType("plain/text");
                    emailIn.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                    emailIn.putExtra(Intent.EXTRA_SUBJECT, subject);
                    emailIn.putExtra(Intent.EXTRA_TEXT, body);

                    openSuccessPage();

                    try {
                        startActivity(Intent.createChooser(emailIn, "Send email using one of the following apps..."));
                        Log.i("MyDigitalCloset", "Your form has been successfully submitted");
                        //Shows summited on the bottom of the screen
                        Toast.makeText(getApplicationContext(), "Submitted!", Toast.LENGTH_SHORT)
                                .show();
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(ContactForm.this,
                                "There is no email client installed.", Toast.LENGTH_SHORT).show();
                    }
                }

            });

            /*
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
                Intent intent = new Intent(getApplicationContext(), AboutUsPageActivity.class);
                startActivity(intent);
                Log.i("MyDigitalCloset", "You have successfully opened the About Us Page");
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

    public void openSuccessPage(){
        Intent intent = new Intent(this, SuccessPage.class);
        startActivity(intent);
    }

}