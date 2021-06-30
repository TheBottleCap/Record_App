package com.example.record;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
   public static final String s="com.example.record.extra.NAME";
    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Button signInBtn = findViewById(R.id.signInBtn);
        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity3.class));
            finish();
        }

        signInBtn.setOnClickListener(v -> {
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
            fAuth.createUserWithEmailAndPassword(emailId,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "User added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity4.class);
                        intent.putExtra(s, emailId.toString());
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "user not added" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        });

        findViewById(R.id.loginTxt).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),MainActivity3.class)));


    }

}