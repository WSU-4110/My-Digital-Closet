package com.example.mydigitalcloset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;

import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mydigitalcloset.closetPage.AllSavedOutfits;
import com.example.mydigitalcloset.databinding.ActivitySettingsPageBinding;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsPage extends AppCompatActivity {

    ActivitySettingsPageBinding binding;

    private FirebaseAuth mAuth;

    private static final int PICK_IMAGE_REQUEST = 1;

    private Uri mImageUri;

    @Override protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivitySettingsPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        Button btnLogOut = findViewById(R.id.btnLogOut);

        Button btnDelete = findViewById(R.id.btnDelete);

        Button btnThemes = findViewById(R.id.btnThemes);

        Button btnPFP = findViewById(R.id.btnPFP);

        //PFP Button
        btnPFP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent();
                in.setType("image/*");
                in.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(in, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        //LogOut Button
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder bui = new AlertDialog.Builder(SettingsPage.this);
                bui.setMessage("Would you like to log out?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                mAuth.signOut();
                Intent intent = new Intent(SettingsPage.this, Login.class);
                startActivity(intent);
                finish();
                Log.i("MyDigitalCloset", "You have successfully Logout of your account");
                Toast.makeText(SettingsPage.this, "You have been Logout Successfully!", Toast.LENGTH_SHORT).show();
            }
        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });
                AlertDialog alert = bui.create();
                alert.show();
            }
        });

        //Delete Account Button
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder bui = new AlertDialog.Builder(SettingsPage.this);
                bui.setMessage("Are you sure you want to delete your account forever and lose all of the amazing outfits you had?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Delete user account
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null) {
                                    user.delete()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Log.i("MyDigitalCloset", "Your account has been deleted successfully!");
                                                        Toast.makeText(SettingsPage.this, "Your account has been successfully deleted!", Toast.LENGTH_SHORT).show();

                                                        Intent intent = new Intent(SettingsPage.this, Login.class);
                                                        startActivity(intent);
                                                        finish();
                                                    } else {
                                                        Log.e("MyDigitalCloset", "Error deleting user account.", task.getException());
                                                        Toast.makeText(SettingsPage.this, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }
                            }
                        })
                        .setNegativeButton("No", null);
                AlertDialog alert = bui.create();
                alert.show();
            }
        });

       //Change themes
        btnThemes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentDark = getResources().getConfiguration().uiMode
                        & Configuration.UI_MODE_NIGHT_MASK;
                switch (currentDark) {
                    case Configuration.UI_MODE_NIGHT_NO:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        recreate();
                        break;
                    case Configuration.UI_MODE_NIGHT_YES:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        recreate();
                        break;
                }
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
                Intent intent = new Intent(getApplicationContext(),  AllSavedOutfits.class);
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
                Intent intent = new Intent(getApplicationContext(), AboutUsPageActivity.class);
                startActivity(intent);
                Log.i("MyDigitalCloset", "You have successfully opened the About Us Page");

                Toast.makeText(getApplicationContext(), "Welcome to the About Us Page!", Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        });
        binding.settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingsPage.class);
                startActivity(intent);
                Log.i("MyDigitalCloset", "You have successfully opened settings Page");

                Toast.makeText(getApplicationContext(), "Welcome to the Settings Page!", Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        });
        //end nav buttons
    }//end onCreate
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();


            Glide.with(this)
                    .load(mImageUri)
                    .into(binding.imgPFP);
        }
    }
    private void displayProfilePicture() {
        ImageView imgPFP = findViewById(R.id.imgPFP);
        Glide.with(this).load(mImageUri).into(imgPFP);
    }
}
