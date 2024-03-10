package com.example.bookings;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class BookingManagerActivity extends AppCompatActivity {

    private Button updateButton;
    private Button deleteButton;
    private ListView listView;
    private DatabaseHelper databaseHelper;
    private BookingAdapter adapter;
    private Booking currentBooking;
    private EditText nameEditText;
    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_manager);

        databaseHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listView);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);


        adapter = new BookingAdapter(this, new ArrayList<>());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentBooking = adapter.getItem(position);
                if (currentBooking != null) {
                }
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentBooking != null) {
                    updateBooking(currentBooking.getId());
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentBooking != null) {
                    deleteBooking(currentBooking.getId());
                }
            }
        });

        loadBookings();
    }

    private void loadBookings() {
        List<Booking> bookings = databaseHelper.getAllBookings();
        adapter.clear();
        adapter.addAll(bookings);
        adapter.notifyDataSetChanged();
    }

    private void updateBooking(long bookingId) {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        if (databaseHelper.updateBooking(bookingId) > 0) {
            Toast.makeText(this, "Booking updated successfully.", Toast.LENGTH_LONG).show();
            loadBookings();
        } else {
            Toast.makeText(this, "Failed to update booking.", Toast.LENGTH_LONG).show();
        }
    }

    private void deleteBooking(long bookingId) {
        databaseHelper.deleteBooking(bookingId);
        Toast.makeText(this, "Booking deleted successfully.", Toast.LENGTH_LONG).show();
        loadBookings();
    }

}