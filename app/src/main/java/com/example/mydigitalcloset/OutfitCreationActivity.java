package com.example.mydigitalcloset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.mydigitalcloset.databinding.ActivityOutfitCreationBinding;

public class OutfitCreationActivity extends AppCompatActivity {

    ActivityOutfitCreationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //NAV BAR STUFF:
        binding = ActivityOutfitCreationBinding.inflate(getLayoutInflater());
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

    }

    /*private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentTransaction.replace(R.id.frame_layout, fragment);
        FragmentTransaction.commit();
    }*/
}