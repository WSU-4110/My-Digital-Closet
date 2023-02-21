package com.example.mydigitalcloset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ContactForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);

        Button btn = findViewById(R.id.btnsubmit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openSuccessPage();

                Log.i("MyDigitalCloset", "Your form has been successfully summited");
                //Shows summited on the bottom of the screen
                Toast.makeText(getApplicationContext(), "Summited!", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
    public void openSuccessPage(){
        Intent intent = new Intent(this, SuccessPage.class);
        startActivity(intent);
    }
}