package com.example.bookings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class BookingAdapter extends ArrayAdapter<Booking> {
    public BookingAdapter(Context context, List<Booking> bookings) {
        super(context, 0, bookings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_booking, parent, false);
        }

        Booking booking = getItem(position);

        TextView tvBookingInfo = convertView.findViewById(R.id.tvBookingInfo);
        tvBookingInfo.setText(booking != null ? booking.getName() + ", " + booking.getEmail() : "No Info");

        return convertView;
    }

}