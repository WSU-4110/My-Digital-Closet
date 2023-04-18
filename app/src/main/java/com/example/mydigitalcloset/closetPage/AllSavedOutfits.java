package com.example.mydigitalcloset.closetPage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mydigitalcloset.AboutUsPageActivity;
import com.example.mydigitalcloset.ContactForm;
import com.example.mydigitalcloset.Module;
import com.example.mydigitalcloset.Outfit;
import com.example.mydigitalcloset.OutfitCreationActivity;
import com.example.mydigitalcloset.R;
import com.example.mydigitalcloset.SettingsPage;
import com.example.mydigitalcloset.clothingFront;
import com.example.mydigitalcloset.databinding.ActivityAllSavedOutfitsBinding;
import com.example.mydigitalcloset.databinding.ActivityOutfitCreationBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class AllSavedOutfits extends AppCompatActivity {
    //private ImageView rectangle_1;
    ActivityAllSavedOutfitsBinding binding;

    Context context;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    DatabaseReference ref = database.getReference();
    DatabaseReference outfitsRef = database.getReference("outfits");
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    Module module;
    Button btnDelete, btnUpdate, btnView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Inflate the layout
        binding = ActivityAllSavedOutfitsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //INITIALIZE VARS:
        btnDelete = (Button) findViewById(R.id.deleteButton);
        btnUpdate = (Button) findViewById(R.id.updateButton);
        btnView = (Button) findViewById(R.id.viewButton);
        module = new Module();
        //list stuff
        listView = (ListView) findViewById(R.id.outfitList);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        outfitsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value = snapshot.getValue(Outfit.class).toString();
                arrayList.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
                Intent intent = new Intent(getApplicationContext(), AboutUsPageActivity.class); //change depending on romans pages
                startActivity(intent);
                finish();
            }
        });
        binding.settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingsPage.class);
                startActivity(intent);
                finish();
            }
        });

        //get name of outfit clicked in list & assign it to module
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                module.setGvalue_Name(arrayList.get(position));
                module.setGvalue_id(arrayList.get(position));
                Toast.makeText(AllSavedOutfits.this, "Outfit '" + arrayList.get(position) + "' selected!", Toast.LENGTH_LONG).show();
            }
        });

        //delete:
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String str = module.getGvalue_Name();
                if (str==""){
                    Toast.makeText(AllSavedOutfits.this, "Please select an outfit to delete", Toast.LENGTH_LONG).show();
                }
                else {
                    ref.child("outfits").child(str).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ref.child("outfits").child(str).removeValue();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    Toast.makeText(AllSavedOutfits.this, "Outfit " + str + " has been deleted", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), AllSavedOutfits.class);
                    startActivity(intent);
                }
            }
        });

        //update:
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //view:
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fit = module.getGvalue_Name();

                DatabaseReference fitRef = outfitsRef.child(fit);
                DatabaseReference topRef = fitRef.child("top");
                DatabaseReference bottomsRef = fitRef.child("bottoms");
                DatabaseReference shoesRef = fitRef.child("shoes");
                DatabaseReference headwearRef = fitRef.child("headwear");
                DatabaseReference otherRef = fitRef.child("other");
                DatabaseReference socksRef = fitRef.child("socks");
                topRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String topID = snapshot.getValue(String.class);
                        if (topID != " "){showTop(topID);}
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }//end oncreate

    public void openOutfit(){
        Intent intent = new Intent(this, SavedFit.class);
        startActivity(intent);
    }

    public void showTop(String top){
        String topID = top;
        Toast.makeText(AllSavedOutfits.this, topID, Toast.LENGTH_SHORT).show();
        progressDialog = new ProgressDialog(AllSavedOutfits.this);
        progressDialog.setMessage("Fetching top image...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //use top name entered by user to get top from database
        //topID = binding.getTopName.getText().toString();
        storageReference = FirebaseStorage.getInstance().getReference("images/tops/"+topID+".png");
        //create local file for top image
        try{
            File topfile = File.createTempFile("tempfile_top", ".png");
            storageReference.getFile(topfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                //ON SUCCESS: image fetched
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    //dismiss progress dialog if showing
                    if (progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }

                    //top image will be stored in bitmap var
                    Bitmap topbitmap = BitmapFactory.decodeFile(topfile.getAbsolutePath());
                    binding.topImage.setImageBitmap(topbitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                //ON FAILURE
                @Override
                public void onFailure(@NonNull Exception e) {
                    //dismiss progress dialog if showing
                    if (progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }

                    //failure toast
                    Toast.makeText(AllSavedOutfits.this, "Failed to retrieve top image", Toast.LENGTH_SHORT).show();
                }
            });
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}