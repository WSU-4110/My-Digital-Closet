package com.example.mydigitalcloset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydigitalcloset.databinding.ActivityAboutusPageBinding;
import com.example.mydigitalcloset.databinding.ActivityOutfitCreationBinding;

public class AboutUsPageActivity extends AppCompatActivity {
    ActivityAboutusPageBinding binding;

  @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      // Inflate the layout
      binding = ActivityAboutusPageBinding.inflate(getLayoutInflater());
      setContentView(binding.getRoot());
    }

}