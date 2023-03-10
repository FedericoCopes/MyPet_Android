package com.example.navbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Properties;
public class Register extends AppCompatActivity {

    // create object of DatabaseReference class to access firebase's Realtime Database
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mypet-756fb-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText fullname = findViewById(R.id.fullname);
        final EditText email = findViewById(R.id.email);
        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        final EditText conPassword = findViewById(R.id.conPassword);

        final Button registerBtn = findViewById(R.id.registerBtn);
        final TextView loginNowBtn = findViewById(R.id.loginNow);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get data from EditTexts into String variables
                final String fullnameTxt = fullname.getText().toString();
                final String emailTxt = email.getText().toString();
                final String usernameTxt = username.getText().toString();
                final String passwordTxt = password.getText().toString();
                final String conPasswordTxt = conPassword.getText().toString();

                // check if user fill all the fields before sending data to firebase
                if(fullnameTxt.isEmpty() || emailTxt.isEmpty() || usernameTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(Register.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }

                // check if passwords are matching with each other
                // if not matching with each other then show a toast message
                else if(!passwordTxt.equals(conPasswordTxt)){
                    Toast.makeText(Register.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                }

                // check if Username contains ".", "#", "$", "[" or "]"
                else if(usernameTxt.matches(".*[.#\\$\\[\\]].*")){
                    Toast.makeText(Register.this, "Invalid Username", Toast.LENGTH_SHORT).show();
                }else if(passwordTxt.length() < 8){
                    Toast.makeText(Register.this, "Invalid password", Toast.LENGTH_SHORT).show();
                }else if(passwordTxt.matches(".*[A-Z].*") == false){
                    Toast.makeText(Register.this, "Invalid password", Toast.LENGTH_SHORT).show();
                }

                else{

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            // check if username is not registered before

                            if(snapshot.hasChild(usernameTxt)){
                                Toast.makeText(Register.this, "Username is already registered", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                // sending data to firebase Realtime Database.
                                // we are using numeric username as unique identity of every user
                                // so all the other details of users comes under username
                                databaseReference.child("users").child(usernameTxt).child("fullname").setValue(fullnameTxt);
                                databaseReference.child("users").child(usernameTxt).child("email").setValue(emailTxt);
                                databaseReference.child("users").child(usernameTxt).child("password").setValue(passwordTxt);
                                Toast.makeText(Register.this, "User registered successfully.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent (Register.this, NewPet.class);
                                intent.putExtra("nomeutente",usernameTxt);
                                startActivity(intent);
                                finish();
                                // show a success message the finish the activity
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        loginNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}