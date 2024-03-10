package com.example.bookings;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        ListView faqListView = findViewById(R.id.faqListView);
        String[] faqs = getResources().getStringArray(R.array.faq_questions);
        ArrayAdapter<String> faqAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, faqs);
        faqListView.setAdapter(faqAdapter);
    }

}