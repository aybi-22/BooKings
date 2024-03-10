package com.example.bookings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PersonalData extends AppCompatActivity {

    public EditText bookingEditText;
    private EditText nameEditText;
    private EditText emailEditText;
    private Button submitButton;
    private DatabaseHelper databaseHelper;
    private Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        databaseHelper = new DatabaseHelper(this);
        nameEditText = findViewById(R.id.firstNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        submitButton = findViewById(R.id.continueButton);
        backButton = findViewById(R.id.back);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitBooking();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitBooking();
            }
        });

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private void submitBooking() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        if (!name.isEmpty() && !email.isEmpty()) {
            long bookingId = databaseHelper.addBooking(name, email);
            Toast.makeText(PersonalData.this, "Booking submitted. ID: " + bookingId, Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(PersonalData.this, BookingManagerActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(PersonalData.this, "Bitte füllen Sie alle Felder aus", Toast.LENGTH_SHORT).show();
        }
    }
}