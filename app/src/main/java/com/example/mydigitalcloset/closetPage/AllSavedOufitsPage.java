package com.example.mydigitalcloset.closetPage;

import androidx.appcompat.app.AppCompatActivity;


//package exportkit.figma;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mydigitalcloset.ContactForm;
import com.example.mydigitalcloset.OutfitCreationActivity;
import com.example.mydigitalcloset.R;
import com.example.mydigitalcloset.clothingFront;
import com.example.mydigitalcloset.databinding.ActivityAllSavedOufitsPageBinding;
import com.example.mydigitalcloset.databinding.ActivityClothingFrontBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AllSavedOufitsPage extends AppCompatActivity {

    private View _bg__iphone_14___1_ek2;
    private View bg_light;
    private ImageView vector;
    private ImageView vector_ek1;
    private TextView caption;
    private ImageView image_1;
    private ImageView vector_ek2;
    private ImageView vector_ek3;
    private ImageView image_7;
    private ImageView image_2;
    private ImageView image_3;
    private ImageView image_4;
    private ImageView image_5;
    private TextView saved_outfits;
    private ImageView rectangle_1;
    private TextView campus_green_sweater_and_jeans_skirt;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_saved_oufits_page);


        // _bg__iphone_14___1_ek2 = (View) findViewById(R.id._bg__iphone_14___1_ek2);
        bg_light = (View) findViewById(R.id.bg_light);
        vector = (ImageView) findViewById(R.id.vector);
        vector_ek1 = (ImageView) findViewById(R.id.vector_ek1);
        caption = (TextView) findViewById(R.id.caption);
        image_1 = (ImageView) findViewById(R.id.image_1);
        vector_ek2 = (ImageView) findViewById(R.id.vector_ek2);
        vector_ek3 = (ImageView) findViewById(R.id.vector_ek3);
        image_7 = (ImageView) findViewById(R.id.image_7);
        image_2 = (ImageView) findViewById(R.id.image_2);
        image_3 = (ImageView) findViewById(R.id.image_3);
        image_4 = (ImageView) findViewById(R.id.image_4);
        image_5 = (ImageView) findViewById(R.id.image_5);
        saved_outfits = (TextView) findViewById(R.id.saved_outfits);
        rectangle_1 = (ImageView) findViewById(R.id.rectangle_1);
        campus_green_sweater_and_jeans_skirt = (TextView) findViewById(R.id.campus_green_sweater_and_jeans_skirt);


        //custom code goes here

        rectangle_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOutfit();
            }
        });
    }

    public void openOutfit() {
        Intent intent = new Intent(this, SavedOutfit.class);
        startActivity(intent);
    }
}

/* main conflict
public class AllSavedOufitsPage extends Activity {

    ActivityAllSavedOufitsPageBinding binding; //navbar
    Context context;

    private View _bg__iphone_14___1_ek2;
    private View bg_light;
    private ImageView vector;
    private ImageView vector_ek1;
    private TextView caption;
    private ImageView image_1;
    private ImageView vector_ek2;
    private ImageView vector_ek3;
    private ImageView image_7;
    private ImageView image_2;
    private ImageView image_3;
    private ImageView image_4;
    private ImageView image_5;
    private TextView saved_outfits;
    private ImageView rectangle_1;
    private TextView campus_green_sweater_and_jeans_skirt;

    private Button nextButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_saved_oufits_page);


        //NAV BAR STUFF:
        binding = ActivityAllSavedOufitsPageBinding.inflate(getLayoutInflater());
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
                        Intent intentAdd = new Intent(getApplicationContext(), clothingFront.class);
                        startActivity(intentAdd);
                        finish();
                        break;
                    case R.id.contactSupport:
                        Intent intentContact = new Intent(getApplicationContext(), ContactForm.class);
                        startActivity(intentContact);
                        finish();
                        break;
                    case R.id.wardrobe:
                        return true;
                    /*case R.id.settings:
                        startActivity(new Intent(OutfitCreationActivity.this, SETTINGS.class));
                        break;
                }
                return true;
            }
        });
        //end nav bar

        nextButton = (Button) findViewById(R.id.nextbutton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOutfit();
            }
        });

        _bg__iphone_14___1_ek2 = (View) findViewById(R.id._bg__iphone_14___1_ek2);
        bg_light = (View) findViewById(R.id.bg_light);
        vector = (ImageView) findViewById(R.id.vector);
        vector_ek1 = (ImageView) findViewById(R.id.vector_ek1);
        caption = (TextView) findViewById(R.id.caption);
        image_1 = (ImageView) findViewById(R.id.image_1);
        vector_ek2 = (ImageView) findViewById(R.id.vector_ek2);
        vector_ek3 = (ImageView) findViewById(R.id.vector_ek3);
        image_7 = (ImageView) findViewById(R.id.image_7);
        image_2 = (ImageView) findViewById(R.id.image_2);
        image_3 = (ImageView) findViewById(R.id.image_3);
        image_4 = (ImageView) findViewById(R.id.image_4);
        image_5 = (ImageView) findViewById(R.id.image_5);
        saved_outfits = (TextView) findViewById(R.id.saved_outfits);
        rectangle_1 = (ImageView) findViewById(R.id.rectangle_1);
        campus_green_sweater_and_jeans_skirt = (TextView) findViewById(R.id.campus_green_sweater_and_jeans_skirt);


        //custom code goes here

    }

    public void openOutfit(){
        Intent intent = new Intent(this, AllSavedOufitsPage.class);
        startActivity(intent);
 */


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test);
//    }
//}