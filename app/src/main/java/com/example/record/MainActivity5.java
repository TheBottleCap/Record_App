package com.example.record;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Objects;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;


public class MainActivity5 extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference db;
    MyAdapter adapter;
    ArrayList<Model> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Intent intent= getIntent();
        String a = intent.getStringExtra(MainActivity4.s);


        db = FirebaseDatabase.getInstance().getReference().child(a);


        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        list = new ArrayList<>();
        adapter = new MyAdapter(this,list);
        recyclerView.setAdapter(adapter);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Model model = ds.getValue(Model.class);
                    Log.i(TAG, "onDataChange: value of topic is: "+ Objects.requireNonNull(model).getTopic());
                    Log.i(TAG, "onDataChange: value of order is: "+Objects.requireNonNull(model).getOrder());
                    list.add(model);
                    Log.i(TAG, "onDataChange: list value is: "+ list);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

    }

}
