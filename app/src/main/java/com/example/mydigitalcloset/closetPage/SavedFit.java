package com.example.mydigitalcloset.closetPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mydigitalcloset.ContactForm;
import com.example.mydigitalcloset.OutfitCreationActivity;
import com.example.mydigitalcloset.R;
import com.example.mydigitalcloset.clothingFront;
import com.example.mydigitalcloset.databinding.ActivitySavedFitBinding;

public class SavedFit extends AppCompatActivity {



    ActivitySavedFitBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreated = true;
////NAV BAR STUFF:
//        binding = ActivitySavedFitBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
//            switch(item.getItemId()){
//                case R.id.home:
//                    Intent intentHome = new Intent(getApplicationContext(), OutfitCreationActivity.class);
//                    startActivity(intentHome);
//                    finish();
//                    break;
//                /*case R.id.wardrobe:
//                    Intent intentWardrobe = new Intent(getApplicationContext(), OutfitCreationActivity.class);
//                    startActivity(intentWardrobe);
//                    finish();
//                    break;*/
//                case R.id.addItem:
//                    Intent intentAdd = new Intent(getApplicationContext(), clothingFront.class);
//                    startActivity(intentAdd);
//                    finish();
//                    break;
//                case R.id.contactSupport:
//                    Intent intentContact = new Intent(getApplicationContext(), ContactForm.class);
//                    startActivity(intentContact);
//                    finish();
//                    break;
//            }
//
//            return true;
//        });
//        //end nav bar

        setContentView(R.layout.activity_saved_fit);
    }

    static Boolean isCreated = true;
    public static int getContentView() {
        return R.id.SavedOutfitLayout;
    }
    public static Boolean isCreatedSuccessfully()
    {
        return isCreated;
    }

}