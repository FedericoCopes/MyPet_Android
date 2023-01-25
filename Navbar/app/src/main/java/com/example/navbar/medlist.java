package com.example.navbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class medlist extends AppCompatActivity {

    String nomeUtente;
    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter1 myAdapter;
    ArrayList<Medicinale> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medlist);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nomeUtente = extras.getString("nomeutente");
        }

        recyclerView = findViewById(R.id.medlist);
        database = FirebaseDatabase.getInstance().getReference("users").child(nomeUtente).child("medicinali");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MyAdapter1(this, list);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Medicinale med = dataSnapshot.getValue(Medicinale.class);
                    list.add(med);


                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ImageView imageView = findViewById(R.id.imagenewmed);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(medlist.this, new_med.class);
                intent.putExtra("nomeutente", nomeUtente);
                startActivity(intent);
            }
        });
    }
}