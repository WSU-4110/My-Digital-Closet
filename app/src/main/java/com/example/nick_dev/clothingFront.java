package com.example.nick_dev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class clothingFront extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clothing);
        //this moves to the page that adds clothing items
        Button addPage = findViewById(R.id.addItem);
        addPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(clothingFront.this,clothingUpload.class);
                startActivity(intent);
            }
        });
    }
}