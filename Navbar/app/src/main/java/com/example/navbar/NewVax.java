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

public class NewVax extends AppCompatActivity {

    private String nomeUtente;
    private EditText tipoV, dataV, fineV;
    private Button salvaV;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_vax);

        database = FirebaseDatabase.getInstance();

        tipoV = findViewById(R.id.tipo_vax);
        dataV = findViewById(R.id.data_vax);
        fineV = findViewById(R.id.fine_vax);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nomeUtente = extras.getString("nomeutente");
        }

        salvaV = findViewById(R.id.salva_vax);
        salvaV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipo = tipoV.getText().toString();
                String data = dataV.getText().toString();
                String fine = fineV.getText().toString();

                if (tipoV.getText().toString().isEmpty() || dataV.getText().toString().isEmpty() || fineV.getText().toString().isEmpty() || !isValidDate(data) || !isValidDate(fine)) {
                    Toast.makeText(NewVax.this, "Please fill all fields with valid date format (dd/MM/yyyy)", Toast.LENGTH_SHORT).show();
                } else {
                    reference = database.getReference("users").child(nomeUtente).child("vaccini").child(tipo);
                    Vaccino vaccino = new Vaccino(tipo, data, fine);
                    reference.setValue(vaccino);
                    Intent intent = new Intent(NewVax.this, vaxlist.class);
                    intent.putExtra("nomeutente", nomeUtente);
                    startActivity(intent);
                    finish();
                }
            }
        });
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


