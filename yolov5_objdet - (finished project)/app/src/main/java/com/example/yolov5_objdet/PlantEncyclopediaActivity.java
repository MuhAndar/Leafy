package com.example.yolov5_objdet;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PlantEncyclopediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_encyclopedia);

        Button sintrongButton = findViewById(R.id.sintrongButton);
        Button cassavaButton = findViewById(R.id.cassavaButton);
        Button celosiaButton = findViewById(R.id.celosiaButton);
        Button ginsengliarButton = findViewById(R.id.ginsengliarButton);

        sintrongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlantEncyclopediaActivity.this, SintrongActivity.class);
                startActivity(intent);
            }
        });

        cassavaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlantEncyclopediaActivity.this, CassavaActivity.class);
                startActivity(intent);
            }
        });

        celosiaButton.setOnClickListener(new View.OnClickListener() { // Add this block
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlantEncyclopediaActivity.this, CelosiaActivity.class);
                startActivity(intent);
            }
        });

        ginsengliarButton.setOnClickListener(new View.OnClickListener() { // Add this block
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlantEncyclopediaActivity.this, GinsengliarActivity.class);
                startActivity(intent);
            }
        });

        // Add more button listeners for other plants as needed
    }

    public void goBack(View view) {
        finish(); // Close the current activity and return to the previous one
    }
}
