package com.example.mydigitalcloset;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydigitalcloset.databinding.ActivityClothingSeeAllBinding;
import com.example.mydigitalcloset.databinding.ActivityClothingUploadBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;


public class clothingSeeAll extends AppCompatActivity {


    private DatabaseReference mDatabase;
    ProgressDialog progressDialog;
    ActivityClothingSeeAllBinding binding;
    FirebaseStorage storage;
    StorageReference stoRef;
    ImageView imgView;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_see_all);
        storage = FirebaseStorage.getInstance();
        stoRef = storage.getReference();
        // Inflate the layout
        binding = ActivityClothingSeeAllBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        //buttons!

        //Back to clothing front page
        Button b2c = findViewById(R.id.backToClothUp);
        b2c.setOnClickListener(view -> {
            Intent intent=new Intent(clothingSeeAll.this, clothingFront.class);
            startActivity(intent);
        });
        

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imgView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


}
