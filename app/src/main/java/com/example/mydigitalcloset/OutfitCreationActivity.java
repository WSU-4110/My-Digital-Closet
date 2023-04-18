package com.example.mydigitalcloset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mydigitalcloset.closetPage.AllSavedOufitsPage;
import com.example.mydigitalcloset.closetPage.AllSavedOutfits;
import com.example.mydigitalcloset.databinding.ActivityOutfitCreationBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OutfitCreationActivity extends AppCompatActivity {

    ActivityOutfitCreationBinding binding;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    DatabaseReference outfitsRef = ref.child("outfits");
    Map<String, Object> outfits = new HashMap<>();


    Context context;

    //clothing IDs
    String topID = " ";
    String bottomsID = " ";
    String shoesID = " ";
    String headwearID = " ";
    String socksID = " ";
    String otherID = " ";
    String outfitName = " ";
    //[0]: name [1]: top [2]: bottoms [3]: shoes [4]: headwear [5]: socks [6]: other
    String outfit[] = {outfitName, topID, bottomsID, shoesID, headwearID, socksID, otherID};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //context = this;
        super.onCreate(savedInstanceState);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Inflate the layout
        binding = ActivityOutfitCreationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //get top image from firebase:
        binding.getTop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showAddTopDialog(OutfitCreationActivity.this);
            }
        });

        //get bottoms image from firebase:
        binding.getBottoms.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showAddBottomsDialog(OutfitCreationActivity.this);
            }
        });

        //get shoes image from firebase:
        binding.getShoes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showAddShoesDialog(OutfitCreationActivity.this);
            }
        });

        //get socks image from firebase:
        binding.getSocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddSocksDialog(OutfitCreationActivity.this);
            }
        });

        //get headwear image from firebase:
        binding.getHeadwear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddHeadwearDialog(OutfitCreationActivity.this);
            }
        });

        //get other image from firebase:
        binding.getOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddOtherDialog(OutfitCreationActivity.this);
            }
        });


        //save outfit button
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //outfit[] - [0]: name [1]: top [2]: bottoms [3]: shoes [4]: headwear [5]: socks [6]: other
                showSaveOutfitDialog(OutfitCreationActivity.this); //get outfit name
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


    } //end on create

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
    //save outfit pop up
    private void showSaveOutfitDialog(Context c) {
        final EditText outfitNameTemp = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Save Outfit")
                .setMessage("Enter a name for your outfit")
                .setView(outfitNameTemp)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        outfitName = String.valueOf(outfitNameTemp.getText());
                        outfit[0] = outfitName;
                        //add check for existing outfit name
                        //save array to firebase
                        Outfit fit = new Outfit(outfit);    //create outfit object from array
                        outfits.put(outfitName, fit);   //save outfit to map
                        //outfitsRef.child(outfitName).setValue(fit);
                        outfitsRef.updateChildren(outfits);

                        Toast.makeText(OutfitCreationActivity.this, "Outfit " + outfitName + " saved!", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }
    //add top pop up
    private void showAddTopDialog(Context c) {
        final EditText topIDtemp = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Add Top")
                .setMessage("Enter name of top:")
                .setView(topIDtemp)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        topID = String.valueOf(topIDtemp.getText());
                        progressDialog = new ProgressDialog(OutfitCreationActivity.this);
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
                                    outfit[1] = topID;
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
                                    Toast.makeText(OutfitCreationActivity.this, "Failed to retrieve top image", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }
    //add bottoms pop up
    private void showAddBottomsDialog(Context c) {
        final EditText bottomsIDtemp = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Add Bottoms")
                .setMessage("Enter name of bottoms:")
                .setView(bottomsIDtemp)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bottomsID = String.valueOf(bottomsIDtemp.getText());
                        progressDialog = new ProgressDialog(OutfitCreationActivity.this);
                        progressDialog.setMessage("Fetching bottoms image...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        storageReference = FirebaseStorage.getInstance().getReference("images/bottoms/"+bottomsID+".png");
                        //create local file for top image
                        try{
                            File bottomsfile = File.createTempFile("tempfile_bottoms", ".png");
                            storageReference.getFile(bottomsfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                //ON SUCCESS: image fetched
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    //dismiss progress dialog if showing
                                    if (progressDialog.isShowing()){
                                        progressDialog.dismiss();
                                    }
                                    outfit[2] = bottomsID;
                                    //bottoms image will be stored in bitmap var
                                    Bitmap bottomsbitmap = BitmapFactory.decodeFile(bottomsfile.getAbsolutePath());
                                    binding.bottomsImage.setImageBitmap(bottomsbitmap);

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
                                    Toast.makeText(OutfitCreationActivity.this, "Failed to retrieve bottoms image", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }
    //add shoes pop up
    private void showAddShoesDialog(Context c) {
        final EditText shoesIDtemp = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Add Shoes")
                .setMessage("Enter name of shoes:")
                .setView(shoesIDtemp)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shoesID = String.valueOf(shoesIDtemp.getText());
                        progressDialog = new ProgressDialog(OutfitCreationActivity.this);
                        progressDialog.setMessage("Fetching shoes image...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        storageReference = FirebaseStorage.getInstance().getReference("images/shoes/"+shoesID+".png");
                        //create local file for shoes image
                        try{
                            File shoesfile = File.createTempFile("tempfile_shoes", ".png");
                            storageReference.getFile(shoesfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                //ON SUCCESS: image fetched
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    //dismiss progress dialog if showing
                                    if (progressDialog.isShowing()){
                                        progressDialog.dismiss();
                                    }
                                    outfit[3] = shoesID;
                                    //shoes image will be stored in bitmap var
                                    Bitmap shoesbitmap = BitmapFactory.decodeFile(shoesfile.getAbsolutePath());
                                    binding.shoesImage.setImageBitmap(shoesbitmap);
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
                                    Toast.makeText(OutfitCreationActivity.this, "Failed to retrieve shoes image", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }
    //add headwear pop up
    private void showAddHeadwearDialog(Context c) {
        final EditText headwearIDtemp = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Add Headwear")
                .setMessage("Enter name of Headwear:")
                .setView(headwearIDtemp)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        headwearID = String.valueOf(headwearIDtemp.getText());
                        progressDialog = new ProgressDialog(OutfitCreationActivity.this);
                        progressDialog.setMessage("Fetching headwear image...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        storageReference = FirebaseStorage.getInstance().getReference("images/headwear/"+headwearID+".png");
                        //create local file for headwear image
                        try{
                            File headwearfile = File.createTempFile("tempfile_headwear", ".png");
                            storageReference.getFile(headwearfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                //ON SUCCESS: image fetched
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    //dismiss progress dialog if showing
                                    if (progressDialog.isShowing()){
                                        progressDialog.dismiss();
                                    }
                                    outfit[4] = headwearID;
                                    //headwear image will be stored in bitmap var
                                    Bitmap headwearbitmap = BitmapFactory.decodeFile(headwearfile.getAbsolutePath());
                                    binding.headwearImage.setImageBitmap(headwearbitmap);
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
                                    Toast.makeText(OutfitCreationActivity.this, "Failed to retrieve headwear image", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }
    //add socks pop up
    private void showAddSocksDialog(Context c) {
        final EditText socksIDtemp = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Add Socks")
                .setMessage("Enter name of socks:")
                .setView(socksIDtemp)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        socksID = String.valueOf(socksIDtemp.getText());
                        progressDialog = new ProgressDialog(OutfitCreationActivity.this);
                        progressDialog.setMessage("Fetching socks image...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        storageReference = FirebaseStorage.getInstance().getReference("images/socks/"+socksID+".png");
                        //create local file for socks image
                        try{
                            File socksfile = File.createTempFile("tempfile_socks", ".png");
                            storageReference.getFile(socksfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                //ON SUCCESS: image fetched
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    //dismiss progress dialog if showing
                                    if (progressDialog.isShowing()){
                                        progressDialog.dismiss();
                                    }
                                    outfit[5] = socksID;
                                    //socks image will be stored in bitmap var
                                    Bitmap socksbitmap = BitmapFactory.decodeFile(socksfile.getAbsolutePath());
                                    binding.socksImage.setImageBitmap(socksbitmap);
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
                                    Toast.makeText(OutfitCreationActivity.this, "Failed to retrieve socks image", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }
    //add other pop up
    private void showAddOtherDialog(Context c) {
        final EditText otherIDtemp = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Add Other Item")
                .setMessage("Enter name of other item:")
                .setView(otherIDtemp)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        otherID = String.valueOf(otherIDtemp.getText());
                        progressDialog = new ProgressDialog(OutfitCreationActivity.this);
                        progressDialog.setMessage("Fetching other image...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        storageReference = FirebaseStorage.getInstance().getReference("images/other/"+otherID+".png");
                        //create local file for other image
                        try{
                            File otherfile = File.createTempFile("tempfile_other", ".png");
                            storageReference.getFile(otherfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                //ON SUCCESS: image fetched
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    //dismiss progress dialog if showing
                                    if (progressDialog.isShowing()){
                                        progressDialog.dismiss();
                                    }
                                    outfit[6] = otherID;
                                    //other image will be stored in bitmap var
                                    Bitmap otherbitmap = BitmapFactory.decodeFile(otherfile.getAbsolutePath());
                                    binding.otherImage.setImageBitmap(otherbitmap);
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
                                    Toast.makeText(OutfitCreationActivity.this, "Failed to retrieve other item image", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

}
