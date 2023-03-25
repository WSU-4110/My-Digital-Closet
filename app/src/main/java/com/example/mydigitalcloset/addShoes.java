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


public abstract class addShoes extends OutfitCreationActivity implements addPhoto{
    @Override
    public void addPhoto(){
        binding.getShoes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                progressDialog = new ProgressDialog(addShoes.this);
                progressDialog.setMessage("Fetching shoes image...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                //use shoes name entered by user to get shoes from database
                String shoesID = binding.getShoesName.getText().toString();
                storageReference = FirebaseStorage.getInstance().getReference("images/shoes/"+shoesID+".png");
                //create local file for top image
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
                            //top image will be stored in bitmap var
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
                            Toast.makeText(addShoes.this, "Failed to retrieve shoes image", Toast.LENGTH_SHORT);
                        }
                    });
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
