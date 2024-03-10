package com.example.bookings;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class WellnessActivity extends AppCompatActivity {

    private TextView dateTextView;
    private TextView timeTextView;
    private NumberPicker numberPicker;
    private Button bookSlotButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellness);

        bookSlotButton = findViewById(R.id.bookSlotButton);
        backButton = findViewById(R.id.backButton);
        dateTextView = findViewById(R.id.dateTextView);
        timeTextView = findViewById(R.id.timeTextView);
        numberPicker = findViewById(R.id.numberPicker);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        dateTextView.setOnClickListener(v -> showDatePicker());
        timeTextView.setOnClickListener(v -> showTimePicker());

        bookSlotButton.setOnClickListener(v -> bookSlot());
        backButton.setOnClickListener(v -> goBack());
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, dayOfMonth) ->
                dateTextView.setText(String.format("%d.%d.%d", dayOfMonth, month + 1, year)),
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(this, (view, hourOfDay, minute) ->
                timeTextView.setText(String.format("%02d:%02d", hourOfDay, minute)),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
    }

    private void bookSlot() {
        Intent intent = new Intent(this, PersonalData.class);
        intent.putExtra("DATE", dateTextView.getText().toString());
        intent.putExtra("TIME", timeTextView.getText().toString());
        intent.putExtra("PEOPLE", numberPicker.getValue());
        startActivity(intent);
    }

    private void goBack() {
        finish();
    }
}