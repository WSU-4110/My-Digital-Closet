package com.example.mydigitalcloset;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import com.example.mydigitalcloset.closetPage.AllSavedOufitsPage;
import com.example.mydigitalcloset.databinding.ActivityClothingUploadBinding;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class clothingUpload extends AppCompatActivity {
    private DatabaseReference mDatabase;

    ProgressDialog progressDialog;
    ActivityClothingUploadBinding binding;
    //Initialize storage
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference stoRef = storage.getReference();

    int SELECT_PICTURE = 200;
    ImageView IVimg;
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                // Callback is invoked after the user selects a media item or closes the
                // photo picker.
                if (uri != null) {
                    Log.d("PhotoPicker", "Selected URI: " + uri);
                } else {
                    Log.d("PhotoPicker", "No media selected");
                }
            });
private final int gallery_r_code = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout
        binding = ActivityClothingUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Get a reference to the "clothing" node in your Firebase Realtime Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("clothing");

        IVimg = findViewById(R.id.IVimg);
        Button topsButton = findViewById(R.id.addTops);
        topsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery = new Intent(Intent.ACTION_PICK);
                gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery, gallery_r_code );
                // Create a new clothing item under the "clothing/tops" node in your database
                //String clothingId = mDatabase.child("tops").push().getKey();
                //uploadImage();
                //mDatabase.child("tops").child("Blue T").setValue(pickMedia);
            }

        });

        Button bottomsButton = findViewById(R.id.addBottoms);
        bottomsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new clothing item under the "clothing/bottoms" node in your database
                String clothingId = mDatabase.child("bottoms").push().getKey();
                mDatabase.child("bottoms").child(clothingId).setValue("new item");
            }
        });

        Button shoesButton = findViewById(R.id.addShoes);
        shoesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new clothing item under the "clothing/shoes" node in your database
                String clothingId = mDatabase.child("shoes").push().getKey();
                mDatabase.child("shoes").child(clothingId).setValue("new item");
            }
        });

        Button socksButton = findViewById(R.id.addSocks);
        socksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new clothing item under the "clothing/socks" node in your database
                String clothingId = mDatabase.child("socks").push().getKey();
                mDatabase.child("socks").child(clothingId).setValue("new item");
            }
        });

        Button headwearButton = findViewById(R.id.addHeadwear);
        headwearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new clothing item under the "clothing/headwear" node in your database
                String clothingId = mDatabase.child("headwear").push().getKey();
                mDatabase.child("headwear").child(clothingId).setValue("new item");
            }
        });

        Button otherButton = findViewById(R.id.addOther);
        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new clothing item under the "clothing/other" node in your database
                String clothingId = mDatabase.child("other").push().getKey();
                mDatabase.child("other").child(clothingId).setValue("new item");
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
        //end nav buttons

        //Back to clothing front page
        Button b2c = findViewById(R.id.backToClothUp);
        b2c.setOnClickListener(view -> {
            Intent intent = new Intent(clothingUpload.this, clothingFront.class);
            startActivity(intent);
        });
        /*binding.settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ?.class);
                startActivity(intent);
                finish();
            }
        });*/

    }//end oncreate

protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode==gallery_r_code){
                IVimg.setImageURI(data.getData());
            }
        }

    }

} //end of class


