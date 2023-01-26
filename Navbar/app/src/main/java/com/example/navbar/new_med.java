package com.example.navbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class new_med extends AppCompatActivity {

    String nomeUtente;
    EditText nome, datascadenza, giorniassunzione, orarioassunzione, qta;
    Button salvaM;
    FirebaseDatabase database;
    DatabaseReference reference;

    String[] giorniValidi = {"L", "MA", "ME", "G", "V", "S", "D", "TUTTI"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_med);

        database = FirebaseDatabase.getInstance();

        nome = findViewById(R.id.nome_med);
        datascadenza = findViewById(R.id.data_scadenza_med);
        giorniassunzione = findViewById(R.id.giorno_assunzione_med);
        orarioassunzione = findViewById(R.id.orario_assunzione_med);
        qta = findViewById(R.id.quantita_med);

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
                String qtaM = qta.getText().toString();

                if (nome.getText().toString().isEmpty() || datascadenza.getText().toString().isEmpty() || giorniassunzione.getText().toString().isEmpty() || orarioassunzione.getText().toString().isEmpty() || qta.getText().toString().isEmpty()|| !isValidDate(dataS)) {
                    Toast.makeText(new_med.this, "Please fill all fields with valid date format (dd/MM/yyyy)", Toast.LENGTH_SHORT).show();
                } if (checkDay(giornoA) == true) {
                    reference = database.getReference("users").child(nomeUtente).child("medicinali").child(nomeM);
                    Medicinale med = new Medicinale(nomeM, dataS, giornoA, orarioA,qtaM);
                    reference.setValue(med);
                    Intent intent = new Intent(new_med.this, medlist.class);
                    intent.putExtra("nomeutente", nomeUtente);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(new_med.this, "Please enter a valid day (L, MA, ME, G, V, S, D, TUTTI)", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkDay(String giornoA){
        for (String giorno : giorniValidi) {
            if (giornoA.equalsIgnoreCase(giorno)) {
                return true;

            }

        }
        return false;
    }

    private boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
