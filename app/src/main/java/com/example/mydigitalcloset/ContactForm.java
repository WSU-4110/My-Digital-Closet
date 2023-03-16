package com.example.mydigitalcloset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mydigitalcloset.databinding.ActivityClothingFrontBinding;
import com.example.mydigitalcloset.databinding.ActivityContactFormBinding;

public class ContactForm extends AppCompatActivity {

    ActivityContactFormBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //NAV BAR STUFF:
        binding = ActivityContactFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.home:
                    Intent intentHome = new Intent(getApplicationContext(), HomeFragment.class);
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


        setContentView(R.layout.activity_contact_form);

        Button btn = findViewById(R.id.btnsubmit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openSuccessPage();

                Log.i("MyDigitalCloset", "Your form has been successfully summited");
                //Shows summited on the bottom of the screen
                Toast.makeText(getApplicationContext(), "Summited!", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
    public void openSuccessPage(){
        Intent intent = new Intent(this, SuccessPage.class);
        startActivity(intent);
    }
}