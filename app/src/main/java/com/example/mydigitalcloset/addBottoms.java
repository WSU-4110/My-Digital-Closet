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

public abstract class addBottoms extends OutfitCreationActivity implements addPhoto{
    @Override
    public void addPhoto(){
        binding.getBottoms.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                progressDialog = new ProgressDialog(addBottoms.this);
                progressDialog.setMessage("Fetching bottoms image...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                //use bottoms name entered by user to get top from database
                String bottomsID = binding.getBottomsName.getText().toString();
                storageReference = FirebaseStorage.getInstance().getReference("images/bottoms/"+bottomsID+".png");
                //create local file for bottom image
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
                            //top image will be stored in bitmap var
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
                            Toast.makeText(addBottoms.this, "Failed to retrieve bottoms image", Toast.LENGTH_SHORT);
                        }
                    });
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

}
