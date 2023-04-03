package com.example.mydigitalcloset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydigitalcloset.closetPage.AllSavedOufitsPage;
import com.example.mydigitalcloset.databinding.ActivityAboutusPageBinding;
import com.example.mydigitalcloset.databinding.ActivityOutfitCreationBinding;

public class AboutUsPageActivity extends AppCompatActivity {
    ActivityAboutusPageBinding binding;

  @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      // Inflate the layout
      binding = ActivityAboutusPageBinding.inflate(getLayoutInflater());
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
              Intent intent = new Intent(getApplicationContext(), AllSavedOufitsPage.class);
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
        /*binding.settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ?.class);
                startActivity(intent);
                finish();
            }
        });*/
      //end nav buttons
    }//end onCreate

}