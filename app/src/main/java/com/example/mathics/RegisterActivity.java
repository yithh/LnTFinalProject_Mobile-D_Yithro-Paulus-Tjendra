package com.example.mathics;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    TextView loginLink;
    EditText emailField, passwordField, confPasswordField;
    Button registerButton;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailField = findViewById(R.id.et_emailRegister);
        passwordField = findViewById(R.id.et_passwordRegister);
        confPasswordField = findViewById(R.id.et_confPasswordRegister);
        registerButton = findViewById(R.id.btn_register);
        loginLink = findViewById(R.id.tv_loginLink);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");  // Firebase Realtime Database reference

        loginLink.setOnClickListener(v -> {
            Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(loginIntent);
        });

        registerButton.setOnClickListener(v -> {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();
            String confPassword = confPasswordField.getText().toString();

            if(!email.contains("@") || !email.endsWith(".com")) {
                Toast.makeText(this, "Email harus ada '@' dan diakhiri dengan '.com'", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 5) {
                Toast.makeText(this, "Password harus lebih dari 5 karakter", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confPassword)) {
                Toast.makeText(this, "Password dan Konfirm Password tidak sama", Toast.LENGTH_SHORT).show();
            } else {
                registerUser(email, password);
            }
        });
    }

    private void registerUser(String email, String password) {
        ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle("Registering");
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            // Save the username and email to the Firebase Database
                            databaseReference.child(user.getUid()).setValue(new User());

                            Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                            Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(loginIntent);
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    // User class to store in Firebase Database
    public static class User {
        public String username;
        public String email;

        public User() {
            // Default constructor required for Firebase
        }

        public User(String username, String email) {
            this.username = username;
            this.email = email;
        }
    }
}
