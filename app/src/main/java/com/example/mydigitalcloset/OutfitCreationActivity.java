package com.example.mydigitalcloset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mydigitalcloset.databinding.ActivityOutfitCreationBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class OutfitCreationActivity extends AppCompatActivity {

    ActivityOutfitCreationBinding binding;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Inflate the layout and set up the bottom navigation view
        binding = ActivityOutfitCreationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
                }
                return true;
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
