package com.example.bookings;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button activitiesButton;
    private Button roomsButton;
    private Button myBookingsButton;
    private Button logoutButton;
    private Button contactUsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        activitiesButton = findViewById(R.id.activitiesButton);
        roomsButton = findViewById(R.id.roomsButton);
        myBookingsButton = findViewById(R.id.myBookingsButton);
        logoutButton = findViewById(R.id.logoutButton);
        contactUsButton = findViewById(R.id.contactUsButton);

        activitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ActivitiesActivity.class);
                startActivity(intent);
            }
        });

        roomsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RoomsActivity.class);
                startActivity(intent);
            }
        });

        myBookingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BookingManagerActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        contactUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ContactUs.class);
                startActivity(intent);
            }
        });
    }
}