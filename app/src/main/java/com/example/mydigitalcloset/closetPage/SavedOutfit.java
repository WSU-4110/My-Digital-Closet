package com.example.mydigitalcloset.closetPage;

/*
 *	This content is generated from the API File Info.
 *	(Alt+Shift+Ctrl+I).
 *
 *	@desc
 *	@file 		iphone_14___1
 *	@date 		Sunday 19th of February 2023 05:33:21 AM
 *	@title 		Page 1
 *	@author
 *	@keywords
 *	@generator 	Export Kit v1.3.figma
 *
 */


//package exportkit.figma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydigitalcloset.R;
public class SavedOutfit extends AppCompatActivity {


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
    private TextView campus_green_sweater_and_jeans_skirt;
    private ImageView vector_ek4;
    private ImageView vector_ek5;

    private Button backButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_outfit);

        backButton = (Button) findViewById(R.id.backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAllSavedOutfits();
            }
        });

        _bg__iphone_14___1_ek2 = (View) findViewById(R.id._bg__iphone_14___1_ek2);
        bg_light = (View) findViewById(R.id.bg_light);
        //vector = (ImageView) findViewById(R.id.vector);
       // vector_ek1 = (ImageView) findViewById(R.id.vector_ek1);
        caption = (TextView) findViewById(R.id.caption);
//        image_1 = (ImageView) findViewById(R.id.image_1);
//        vector_ek2 = (ImageView) findViewById(R.id.vector_ek2);
//        vector_ek3 = (ImageView) findViewById(R.id.vector_ek3);
//        image_7 = (ImageView) findViewById(R.id.image_7);
        image_2 = (ImageView) findViewById(R.id.image_2);
        image_3 = (ImageView) findViewById(R.id.image_3);
        image_4 = (ImageView) findViewById(R.id.image_4);
        image_5 = (ImageView) findViewById(R.id.image_5);
        saved_outfits = (TextView) findViewById(R.id.saved_outfits);
        campus_green_sweater_and_jeans_skirt = (TextView) findViewById(R.id.campus_green_sweater_and_jeans_skirt);
       // vector_ek4 = (ImageView) findViewById(R.id.vector_ek4);
        vector_ek5 = (ImageView) findViewById(R.id.vector_ek5);


        //custom code goes here

    }

    public void openAllSavedOutfits(){
        Intent intent = new Intent(this, AllSavedOufitsPage.class);
        startActivity(intent);
    }
}

