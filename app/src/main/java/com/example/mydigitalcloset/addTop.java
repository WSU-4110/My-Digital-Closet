package com.example.mydigitalcloset;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.io.IOException;

public abstract class addTop extends OutfitCreationActivity implements addPhoto{


    @Override
    public void addPhoto(){
        //get top image from firebase:
        binding.getTop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                progressDialog = new ProgressDialog(addTop.this);
                progressDialog.setMessage("Fetching top image...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                //use top name entered by user to get top from database
                String topID = binding.getTopName.getText().toString();
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
                            Toast.makeText(addTop.this, "Failed to retrieve top image", Toast.LENGTH_SHORT);
                        }
                    });
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
        //end top section
    }
}
