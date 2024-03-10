package com.example.bookings;

import android.content.Intent; // Anfragen an das System
import android.os.Bundle; //Key-Value-Speicher für Datenübergabe
import android.view.View; // UI Komponennte
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast; //Benachrichtigungen
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText; //Benutzername
    private EditText passwordEditText; //Passwort
    private EditText confirmPasswordEditText; //erneute Passworteingabe - Bestätigung
    private Button registerButton;     // Button löst Registrierung aus
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //Ruft nötige sachen von onCreate auf - automatisch von Android
        setContentView(R.layout.activity_register); // festlegung Layout
        //Initialisierung BatabaseHelper + UI


        databaseHelper = new DatabaseHelper(this);

        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        confirmPasswordEditText = findViewById(R.id.editTextConfirmPassword);
        registerButton = findViewById(R.id.buttonRegister);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewAccount();
            }
        });
    }

    private void registerNewAccount() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        if (validateInput(username, password, confirmPassword)) {
            if (!databaseHelper.checkUser(username)) {
                databaseHelper.addUser(username, password);
                Toast.makeText(RegisterActivity.this, "Account successfully created!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(RegisterActivity.this, "Username already exists.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validateInput(String username, String password, String confirmPassword) {
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Username and password cannot be empty.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(confirmPassword)) {
            Toast.makeText(RegisterActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
