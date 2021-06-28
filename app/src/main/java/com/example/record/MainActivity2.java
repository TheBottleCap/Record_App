package com.example.record;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;


public class MainActivity2 extends AppCompatActivity {
    TextView username;
    FirebaseAuth fAuth;
    EditText seller,description;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        seller = findViewById(R.id.seller);
        description = findViewById(R.id.description);
        save = findViewById(R.id.saveBtn);
        username = findViewById(R.id.userName);
//        username.setText(Objects.requireNonNull(fAuth.getCurrentUser()).getEmail());

        save.setOnClickListener(v -> {
            HashMap<String,Object> map = new HashMap<>();
            map.put("Seller",seller.getText().toString());
            map.put("Description",description.getText().toString());

//                now to send data to firebase database
            FirebaseDatabase.getInstance().getReference().child("User")
                    .setValue(map)
                    .addOnCompleteListener(task -> Toast.makeText(MainActivity2.this, "Data Saved", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(MainActivity2.this, "Error Coming"+ e.toString(), Toast.LENGTH_SHORT).show());

        });


        Button logOutBtn = findViewById(R.id.logOutBtn);
        logOutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),MainActivity3.class));
            finish();
        });

    }
}