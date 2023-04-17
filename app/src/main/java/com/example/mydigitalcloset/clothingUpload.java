package com.example.mydigitalcloset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.mydigitalcloset.closetPage.AllSavedOufitsPage;
import com.example.mydigitalcloset.databinding.ActivityClothingUploadBinding;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;


public class clothingUpload extends AppCompatActivity {
    private DatabaseReference mDatabase;

    ProgressDialog progressDialog;
    ActivityClothingUploadBinding binding;
    
    //Initialize storage
    FirebaseStorage storage;
    StorageReference stoRef;
    
    ImageView imgView;

    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Firebase
        storage = FirebaseStorage.getInstance();
        stoRef = storage.getReference();
        // Inflate the layout
        binding = ActivityClothingUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Get a reference to the "clothing" node in your Firebase Realtime Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("clothing");

        imgView = findViewById(R.id.imgView); //Preview picture

        TextView addItem = findViewById(R.id.headerAddItem); //going to see the add item text to the user text
        Button galleryButton = findViewById(R.id.openGallery); //Access gallery
        Button catButton = findViewById(R.id.itemCat); //Choose the category
        TextView catDisplay = findViewById(R.id.confirmCat);
        EditText itemText = findViewById(R.id.itemName); //Type in the name of the item
        Button acceptButton = findViewById(R.id.accept); //submit to firebase

        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();

                // Create a new clothing item under the "clothing/tops" node in your database
                //String clothingId = mDatabase.child("tops").push().getKey();

                //mDatabase.child("tops").child("Blue T").setValue(pickMedia);
            }

        });

        //Choose the category of the item

        catButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseCat();
                //String catPicked = catDisplay.getText().toString();
                //String catID = mDatabase.child(catPicked).push().getKey();
            }

        });

        //Type in the name of the item
        itemText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = itemText.getText().toString();
            }
        //submit everything to the database
        });
        itemText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    //do what you want on the press of 'done'
                    closeKeyboard();
                }
                return false;
            }
        });
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String catPicked = catDisplay.getText().toString();
                String namePicked = itemText.getText().toString();
                //testing default names
                if (catPicked.equals("Category"))
                    catPicked = "other";

                if (namePicked.equals(""))
                    namePicked = "item";

                String result = namePicked + ", " + catPicked;
                addItem.setText(result); //Replaces the header of the page with the item name + category
                //String clothingId = mDatabase.child(catPicked).push().getKey();

                //mDatabase.child(catPicked).child(clothingId).setValue(namePicked);
                uploadImage(namePicked,catPicked);
                
                /*            String clothingId = mDatabase.child("tops").push().getKey();
            mDatabase.child("tops").child(clothingId).setValue("new item");*/

            }

        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        /*binding.settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ?.class);
                startActivity(intent);
                finish();
            }
        });*/
        //end nav buttons

        //Back to clothing front page - commented out in order to test the accept button
        /*Button b2c = findViewById(R.id.backToClothUp);
        b2c.setOnClickListener(view -> {
            Intent intent = new Intent(clothingUpload.this, clothingFront.class);
            startActivity(intent);
        });*/

    }//end oncreate

/*protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode==gallery_r_code){
                imgView.setImageURI(data.getData());
            }
        }

    }*/
    //Grab image from gallery
private void chooseImage() {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
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

    private void uploadImage(String name, String cat) {
        if(filePath != null)
        {
            //String clothingID = UUID.randomUUID().toString();
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            StorageReference ref = stoRef.child("images/"+ cat + "/"+name);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(clothingUpload.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(clothingUpload.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    //Choose the category
    public String chooseCat() {
        String[] category = {"tops","bottoms","headwear","shoes","socks","other"};
        TextView catDisplay = findViewById(R.id.confirmCat);                    //shows the category name next to the button
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a category");
        builder.setItems(category, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the user clicked on category[which]
                catDisplay.setText(category[which]);                            //stores the category name is the nearby textboxString catPick =
            }
        });
        builder.show();
        return Arrays.toString(category);
    }

    public String chooseName(){
        return null;
    }

    private void closeKeyboard()
    {
        View view = this.getCurrentFocus();
        if (view != null) {

            InputMethodManager manager
                    = (InputMethodManager)
                    getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            manager
                    .hideSoftInputFromWindow(
                            view.getWindowToken(), 0);
        }
    }


} //end of class


