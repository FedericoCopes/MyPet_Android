package com.example.navbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class new_med extends AppCompatActivity {

    String nomeUtente;
    EditText nome, datascadenza, giorniassunzione, orarioassunzione;
    Button salvaM;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_med);

        database = FirebaseDatabase.getInstance();

        nome = findViewById(R.id.nome_med);
        datascadenza = findViewById(R.id.data_scadenza_med);
        giorniassunzione = findViewById(R.id.giorno_assunzione_med);
        orarioassunzione = findViewById(R.id.orario_assunzione_med);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nomeUtente = extras.getString("nomeutente");
        }

        salvaM = findViewById(R.id.salva_med);
        salvaM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeM = nome.getText().toString();
                String dataS = datascadenza.getText().toString();
                String giornoA = giorniassunzione.getText().toString();
                String orarioA = orarioassunzione.getText().toString();
                reference = database.getReference("users").child(nomeUtente).child("medicinali").child(nomeM);
                Medicinale med = new Medicinale(nomeM, dataS, giornoA, orarioA);
                reference.setValue(med);
                Intent intent = new Intent(new_med.this, medlist.class);
                intent.putExtra("nomeutente", nomeUtente);
                startActivity(intent);
                finish();
            }
        });
    }
}
