package com.example.bookings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialisieren der DatabaseHelper-Instanz
        databaseHelper = new DatabaseHelper(this);

        // Referenzieren der Views
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        // Setzen des OnClickListener für den Login-Button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Versuch, sich anzumelden
                login();
            }
        });
    }

    private void login() {
        // Extrahieren von Benutzername und Passwort aus den Eingabefeldern
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Überprüfen der Anmeldeinformationen gegen die Datenbank
        boolean isSuccess = authenticateUser(username, password);

        if (isSuccess) {
            // Bei erfolgreicher Authentifizierung zur HomeActivity wechseln
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Bei fehlgeschlagener Authentifizierung Fehlermeldung anzeigen
            Toast.makeText(LoginActivity.this, "Login failed. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean authenticateUser(String username, String password) {
        // Verwendung der checkUser-Methode der DatabaseHelper-Klasse zur Überprüfung der Anmeldeinformationen
        return databaseHelper.checkUser(username, password);
    }
}
