package com.example.mydigitalcloset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mydigitalcloset.closetPage.AllSavedOufitsPage;
import com.example.mydigitalcloset.databinding.ActivityOutfitCreationBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

public class OutfitCreationActivity extends AppCompatActivity {

    ActivityOutfitCreationBinding binding;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Inflate the layout
        binding = ActivityOutfitCreationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //nav bar:
        binding.bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        // stay in the same activity
                        return true;
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

        //get top image from firebase:
        binding.getTop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                progressDialog = new ProgressDialog(OutfitCreationActivity.this);
                progressDialog.setMessage("Fetching top image...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                //use top name entered by user to get top from database
                String topID = binding.getTopName.getText().toString();
                storageReference = FirebaseStorage.getInstance().getReference("images/tops/"+topID+".png");

                //create local file for top image
                try{
                    File topfile = File.createTempFile("tempfile_top", ".png");
                } catch(IOException e){
                    e.printStackTrace();
                }


            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            // If user is not signed in, go back to LoginActivity
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
    }
}
