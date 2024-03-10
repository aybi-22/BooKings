package com.example.bookings;

public class Booking {
    private long id;
    private String name;
    private String email;
    // Weitere Felder, falls benötigt...

    public Booking(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        // Initialisierung weiterer Felder...
    }

    // Getter und Setter Methoden
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter und Setter für weitere Felder...
}
