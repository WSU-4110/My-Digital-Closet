package com.example.mydigitalcloset.closetPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mydigitalcloset.ContactForm;
import com.example.mydigitalcloset.OutfitCreationActivity;
import com.example.mydigitalcloset.R;
import com.example.mydigitalcloset.clothingFront;
import com.example.mydigitalcloset.databinding.ActivityAllSavedOutfitsBinding;

public class AllSavedOutfits extends AppCompatActivity {
    private ImageView rectangle_1;
    ActivityAllSavedOutfitsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //NAV BAR STUFF:
        binding = ActivityAllSavedOutfitsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.home:
                    Intent intentHome = new Intent(this, OutfitCreationActivity.class);
                    startActivity(intentHome);
                    finish();
                    break;
                /*case R.id.wardrobe:
                    Intent intentWardrobe = new Intent(getApplicationContext(), OutfitCreationActivity.class);
                    startActivity(intentWardrobe);
                    finish();
                    break;*/
                case R.id.addItem:
                    Intent intentAdd = new Intent(this, clothingFront.class);
                    startActivity(intentAdd);
                    finish();
                    break;
                case R.id.contactSupport:
                    Intent intentContact = new Intent(this, ContactForm.class);
                    startActivity(intentContact);
                    finish();
                    break;
            }

            return true;
        });
        //end nav bar

        setContentView(R.layout.activity_all_saved_outfits);

        rectangle_1 = (ImageView) findViewById(R.id.rectangle_1);
        rectangle_1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openOutfit();
        }
    });
}

    public void openOutfit(){
        Intent intent = new Intent(this, SavedFit.class);
        startActivity(intent);
    }

}