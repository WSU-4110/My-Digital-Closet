package com.example.mydigitalcloset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private TextInputEditText textInputEditTextPassword, textInputEditTextUsername;
    private Button buttonLogin;
    private TextView textViewSignUp;
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("MyDigitalClosetPrefs", Context.MODE_PRIVATE);

        // Check if the user is already logged in and if they just logged in
        boolean userJustLoggedIn = sharedPreferences.getBoolean("userJustLoggedIn", false);
        if (mAuth.getCurrentUser() != null && userJustLoggedIn) {
            // If the user is logged in and just logged in, navigate to the OutfitCreationActivity
            Intent intent = new Intent(getApplicationContext(), OutfitCreationActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_login);

        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewSignUp = findViewById(R.id.signUpText);

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, password;
                username = String.valueOf(textInputEditTextUsername.getText());
                password = String.valueOf(textInputEditTextPassword.getText());

                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    mAuth.signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Login.this, "Login successful!", Toast.LENGTH_SHORT).show();
                                        sharedPreferences.edit().putBoolean("userJustLoggedIn", true).apply();
                                        Intent intent = new Intent(getApplicationContext(), OutfitCreationActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        sharedPreferences.edit().putBoolean("userJustLoggedIn", false).apply();
    }
}
