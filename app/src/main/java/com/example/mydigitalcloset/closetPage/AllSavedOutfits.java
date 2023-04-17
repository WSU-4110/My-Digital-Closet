package com.example.mydigitalcloset.closetPage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllSavedOutfits extends AppCompatActivity {
    private ImageView rectangle_1;
    ActivityAllSavedOutfitsBinding binding;

    Context context;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
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

        //list stuff
        listView = (ListView) findViewById(R.id.outfitList);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        //buttons
        btnDelete = findViewById(R.id.deleteButton);
        btnUpdate = findViewById(R.id.viewButton);

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


        /*
        rectangle_1 = (ImageView) findViewById(R.id.rectangle_1);
        rectangle_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openOutfit();
        }
        });*/

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

        //get name of outfit clicked in list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                module.setGvalue_Name(arrayList.get(i));
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
                            ref.child(str).removeValue();
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

    }//end oncreate

    public void openOutfit(){
        Intent intent = new Intent(this, SavedFit.class);
        startActivity(intent);
    }

}