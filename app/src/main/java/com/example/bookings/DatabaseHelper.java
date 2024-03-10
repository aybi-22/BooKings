package com.example.bookings;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Datenbankversion und -name
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "BookingManager.db";

    // Tabellenname für Benutzer
    private static final String TABLE_USER = "user";

    // Spaltennamen der Benutzertabelle
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    // Tabellenname und Spaltennamen für Buchungen
    private static final String TABLE_BOOKING = "booking";
    private static final String COLUMN_BOOKING_ID = "booking_id";
    private static final String COLUMN_BOOKING_NAME = "booking_name";
    private static final String COLUMN_BOOKING_EMAIL = "booking_email";

    // SQL Statement zum Erstellen der Buchungstabelle
    private static final String CREATE_BOOKING_TABLE = "CREATE TABLE " + TABLE_BOOKING + "("
            + COLUMN_BOOKING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_BOOKING_NAME + " TEXT,"
            + COLUMN_BOOKING_EMAIL + " TEXT" + ")";

    // SQL Statement zum Löschen der Buchungstabelle
    private static final String DROP_BOOKING_TABLE = "DROP TABLE IF EXISTS " + TABLE_BOOKING;

    // SQL Statement zum Erstellen der Benutzertabelle
    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT" + ")";
    private String email;
    private String name;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Erstellen der Buchungstabelle
        db.execSQL(CREATE_BOOKING_TABLE);

        // Erstellen der Benutzertabelle
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Löschen der Buchungstabelle
        db.execSQL(DROP_BOOKING_TABLE);

        // Löschen der Benutzertabelle
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Erneutes Erstellen der Datenbanktabellen
        onCreate(db);
    }

    // Methode zum Hinzufügen einer Buchung
    public long addBooking(String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BOOKING_NAME, name);
        values.put(COLUMN_BOOKING_EMAIL, email);
        long id = db.insert(TABLE_BOOKING, null, values);
        db.close();
        return id;
    }

    // Methode zum Abrufen einer Buchung durch ID
    public Booking getBookingById(long bookingId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_BOOKING,
                new String[]{COLUMN_BOOKING_ID, COLUMN_BOOKING_NAME, COLUMN_BOOKING_EMAIL},
                COLUMN_BOOKING_ID + "=?",
                new String[]{String.valueOf(bookingId)},
                null, null, null);

        Booking booking = null;
        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_BOOKING_ID);
            int nameIndex = cursor.getColumnIndex(COLUMN_BOOKING_NAME);
            int emailIndex = cursor.getColumnIndex(COLUMN_BOOKING_EMAIL);

            if (idIndex != -1 && nameIndex != -1 && emailIndex != -1) {
                long id = cursor.getLong(idIndex);
                String name = cursor.getString(nameIndex);
                String email = cursor.getString(emailIndex);
                booking = new Booking(id, name, email);
            }
            cursor.close();
        }
        db.close();
        return booking;
    }

    // Methode zum Aktualisieren einer Buchung
    public int updateBooking(long bookingId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_BOOKING_NAME, name);
        values.put(COLUMN_BOOKING_EMAIL, email);
        values.put(COLUMN_BOOKING_ID, bookingId);
        values.put(COLUMN_BOOKING_NAME, bookingId);

        int count = db.update(TABLE_BOOKING, values, COLUMN_BOOKING_ID + " = ?", new String[]{String.valueOf(bookingId)});
        db.close();
        return count;
    }

    public void deleteBooking(long bookingId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOKING, COLUMN_BOOKING_ID + " = ?", new String[]{String.valueOf(bookingId)});
        db.close();
    }




    public void addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, username);
        values.put(COLUMN_USER_PASSWORD, password);
        db.insert(TABLE_USER, null, values);
        db.close();
    }


    public boolean checkUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { COLUMN_USER_ID };
        String selection = COLUMN_USER_NAME + " = ?";
        String[] selectionArgs = { username };
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        return cursorCount > 0;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { COLUMN_USER_ID };
        String selection = COLUMN_USER_NAME + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        return cursorCount > 0;
    }


    public List<Booking> getAllBookings() {
        List<Booking> bookingList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_BOOKING,
                new String[]{COLUMN_BOOKING_ID, COLUMN_BOOKING_NAME, COLUMN_BOOKING_EMAIL},
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(COLUMN_BOOKING_ID);
                int nameIndex = cursor.getColumnIndex(COLUMN_BOOKING_NAME);
                int emailIndex = cursor.getColumnIndex(COLUMN_BOOKING_EMAIL);

                if (idIndex != -1 && nameIndex != -1 && emailIndex != -1) {
                    long id = cursor.getLong(idIndex);
                    String name = cursor.getString(nameIndex);
                    String email = cursor.getString(emailIndex);

                    Booking booking = new Booking(id, name, email);
                    bookingList.add(booking);
                }
            } while (cursor.moveToNext());

            cursor.close();
        }
        db.close();
        return bookingList;
    }
}