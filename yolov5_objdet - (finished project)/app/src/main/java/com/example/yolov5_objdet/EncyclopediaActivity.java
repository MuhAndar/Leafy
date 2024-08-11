package com.example.yolov5_objdet;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class EncyclopediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedia);

        Button foragingTipsButton = findViewById(R.id.foragingTipsButton);
        Button poisonTestButton = findViewById(R.id.poisonTestButton);
        Button plantEncyclopediaButton = findViewById(R.id.plantEncyclopediaButton);

        foragingTipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EncyclopediaActivity.this, ForagingTipsActivity.class);
                startActivity(intent);
            }
        });

        poisonTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EncyclopediaActivity.this, PoisonTestActivity.class);
                startActivity(intent);
            }
        });

        plantEncyclopediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EncyclopediaActivity.this, PlantEncyclopediaActivity.class);
                startActivity(intent);
            }
        });
    }

    public void goBack(View view) {
        finish(); // Close the current activity and return to the previous one
    }
}
