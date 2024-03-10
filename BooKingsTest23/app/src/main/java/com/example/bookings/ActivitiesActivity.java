package com.example.bookings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ActivitiesActivity extends AppCompatActivity {

    private Button safariTourButton;
    private Button wellnessButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        safariTourButton = findViewById(R.id.safariTourButton);
        wellnessButton = findViewById(R.id.wellnessButton);
        backButton = findViewById(R.id.backButton);

        safariTourButton.setOnClickListener(view -> {
            Intent intent = new Intent(ActivitiesActivity.this, SafariActivity.class);
            startActivity(intent);
        });

        wellnessButton.setOnClickListener(view -> {
            Intent intent = new Intent(ActivitiesActivity.this, WellnessActivity.class);
            startActivity(intent);
        });

        backButton.setOnClickListener(view -> finish());
    }
}
