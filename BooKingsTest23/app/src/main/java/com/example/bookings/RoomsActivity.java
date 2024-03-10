package com.example.bookings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class RoomsActivity extends AppCompatActivity {

    private Button BedroomButton;
    private Button EventroomButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        BedroomButton = findViewById(R.id.bedroomButton);
        EventroomButton = findViewById(R.id.eventRoomButton);
        backButton = findViewById(R.id.backButton);

        BedroomButton.setOnClickListener(view -> {
            Intent intent = new Intent(RoomsActivity.this, Bedroom.class);
            startActivity(intent);
        });

        EventroomButton.setOnClickListener(view -> {
            Intent intent = new Intent(RoomsActivity.this,Eventroom.class);
            startActivity(intent);
        });

        backButton.setOnClickListener(view -> finish());
    }
}
