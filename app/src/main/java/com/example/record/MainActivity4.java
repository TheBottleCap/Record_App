package com.example.record;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class MainActivity4 extends AppCompatActivity {
    TextView username;
    FirebaseAuth fAuth;
    CardView card;
    EditText seller,description;
    Button save;
    String _UID;
    public static final String s="com.example.record.extra.NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        seller = findViewById(R.id.seller);
        description = findViewById(R.id.description);
        save = findViewById(R.id.saveBtn);
        card = findViewById(R.id.card);
        username = findViewById(R.id.userName);

        card.setVisibility(View.INVISIBLE);


        fAuth = FirebaseAuth.getInstance();
        final FirebaseUser theUser = fAuth.getCurrentUser();
        if (theUser !=null)
            _UID = theUser.getEmail();
        username.setText(_UID);

        findViewById(R.id.popUpBtn).setOnClickListener(v -> {
            card.setVisibility(View.VISIBLE);
        });

        String a = _UID.substring(0,_UID.indexOf("@"));

        save.setOnClickListener(v -> {
            HashMap<String,Object> map = new HashMap<>();
            map.put("order",description.getText().toString());
            map.put("topic",seller.getText().toString());

//                now to send data to firebase database
            FirebaseDatabase.getInstance().getReference().child(a)
                    .push()
                    .setValue(map)
                    .addOnCompleteListener(task -> Toast.makeText(MainActivity4.this, "Data Saved", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(MainActivity4.this,e.toString(), Toast.LENGTH_SHORT).show());


            card.setVisibility(View.INVISIBLE);
        });

        Button logOutBtn = findViewById(R.id.logOutBtn);
        logOutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),MainActivity3.class));
            finish();
        });

        findViewById(R.id.showBtn).setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(),MainActivity5.class);
            intent.putExtra(s,a);
            startActivity(intent);
        });

    }
}