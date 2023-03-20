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
        binding.bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent intentHome = new Intent(getApplicationContext(), OutfitCreationActivity.class);
                        startActivity(intentHome);
                        finish();
                    case R.id.addItem:
                        Intent intentAdd = new Intent(getApplicationContext(), clothingFront.class);
                        startActivity(intentAdd);
                        finish();
                        break;
                    case R.id.contactSupport:
                        return true;
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


        setContentView(R.layout.activity_contact_form);

        Button btn = findViewById(R.id.btnsubmit);

        Button btn1 = findViewById(R.id.btn1AboutUs);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openSuccessPage();


                Log.i("MyDigitalCloset", "Your form has been successfully submitted");
                //Shows summited on the bottom of the screen
                Toast.makeText(getApplicationContext(), "Submitted!", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }


    public void openSuccessPage(){
        Intent intent = new Intent(this, SuccessPage.class);
        startActivity(intent);
    }
    public void openAboutUsPageActivity(){
        Intent intent = new Intent(this, AboutUsPageActivity.class);
        startActivity(intent);
    }
}