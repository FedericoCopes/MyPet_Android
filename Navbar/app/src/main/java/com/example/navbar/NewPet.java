package com.example.navbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewPet extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    String nomeUtente, tipologia;
    EditText nome, razza, colore, sesso, dataNascita, peso, tipologia_animale;
    TextView loginRedirectText;
    Button salvaAnimale;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pet);

        database = FirebaseDatabase.getInstance();

        nome = findViewById(R.id.nome_animale);
        razza = findViewById(R.id.razza_animale);
        colore = findViewById(R.id.mantello_animale);
        sesso = findViewById(R.id.sesso_animale);
        dataNascita = findViewById(R.id.nascita_animale);
        peso = findViewById(R.id.peso_animale);
        tipologia_animale = findViewById(R.id.tipologia_animale);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nomeUtente = extras.getString("nomeutente");
        }
        //Toast.makeText(this, nomeUtente, Toast.LENGTH_SHORT).show();



        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipology, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipologia_animale.setAdapter(adapter);
        tipologia_animale.setOnItemSelectedListener(this);*/

        salvaAnimale = findViewById(R.id.registra_animale);
        salvaAnimale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = database.getReference("users").child(nomeUtente);

                String name = nome.getText().toString();
                String razzaCane = razza.getText().toString();
                String coloreCane = colore.getText().toString();
                String sessoCane = sesso.getText().toString();
                String dataNascitaAnimale = dataNascita.getText().toString();
                String tipologia = tipologia_animale.getText().toString();
                Double pesoAnimale = Double.parseDouble(peso.getText().toString());

                if(sessoCane.equals("Maschio") || sessoCane.equals("Femmina") ){
                    if(checkdate(dataNascitaAnimale) == false || dataNascitaAnimale.length() != 10){
                        Toast.makeText(NewPet.this, "Invalid date, the right model is dd/mm/yyyy", Toast.LENGTH_SHORT).show();
                    }else{
                        Animale animale = new Animale(tipologia, name, razzaCane, coloreCane, sessoCane, pesoAnimale, dataNascitaAnimale);
                        reference.child("animale").setValue(animale);
                        Intent intent = new Intent(NewPet.this, MainActivity.class);
                        intent.putExtra("nomeutente", nomeUtente);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(NewPet.this, "Gender not accepted", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        tipologia = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), tipologia, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static boolean checkdate(String strDate)
    {
        /* Check if date is 'null' */
        if (strDate.trim().equals(""))
        {
            return true;
        }
        else
        {
            /*
             * Set preferred date format,
             * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
            SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
            sdfrmt.setLenient(false);
            /* Create Date object
             * parse the string into date
             */
            try
            {
                Date javaDate = sdfrmt.parse(strDate);
                System.out.println(strDate+" is valid date format");
            }
            /* Date format is invalid */
            catch (ParseException e)
            {
                System.out.println(strDate+" is Invalid Date format");
                return false;
            }
            /* Return true if date format is valid */
            return true;
        }
    }

}