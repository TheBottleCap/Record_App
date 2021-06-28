package com.example.record;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity3 extends AppCompatActivity {

    EditText email,password;
    TextView signInText;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        email = findViewById(R.id.lemail);
        password = findViewById(R.id.lpassword);
        fAuth = FirebaseAuth.getInstance();
        Button loginBtn = findViewById(R.id.logInBtn);
        signInText = findViewById(R.id.signInTxt);

        signInText.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        });

        loginBtn.setOnClickListener(v -> {
            String emailId = email.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (emailId.isEmpty()){
                email.setError("Email is required");
                email.requestFocus();
                return;
            }
            if (pass.isEmpty()){
                password.setError("Password is required");
                password.requestFocus();
                return;
            }

//                authenticate user
            fAuth.signInWithEmailAndPassword(emailId,pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity3.this, "Logged in successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                }else {
                    Toast.makeText(MainActivity3.this, "Error! "+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });


    }
}