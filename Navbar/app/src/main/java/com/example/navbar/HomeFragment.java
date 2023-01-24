package com.example.navbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class HomeFragment extends Fragment {


    TextView TxtnomeAnimal, TxtrazzaAnimale, TxtcoloreAnimale, TxtSessoAnimale, TxtdataNasictaAnimale, TxtpesoAnimale, TxtTipologiaAnimale;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Bundle bundle = this.getArguments();
        String nomeUtente;
        nomeUtente = this.getArguments().getString("nomeutente");
        getActivity().setTitle("Home");
        View view = inflater.inflate(R.layout.home_fragment,null);
        TxtnomeAnimal = view.findViewById(R.id.txtNome);
        TxtrazzaAnimale = view.findViewById(R.id.txtrazza);
        TxtcoloreAnimale = view.findViewById(R.id.txtColoreMantello);
        TxtSessoAnimale = view.findViewById(R.id.txtSesso);
        TxtdataNasictaAnimale = view.findViewById(R.id.txtNascita);
        TxtpesoAnimale = view.findViewById(R.id.txtPeso);
        TxtTipologiaAnimale = view.findViewById(R.id.txtTipologia);
        impostaNomeAnimale(nomeUtente);
        return view;
    }
    //LETTURA CARATTERISTICHE DA DATABASE
    private void impostaNomeAnimale(String nomeUtente) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference addnomeAnimale = ref.child("users").child(nomeUtente).child("animale").child("nome");
        DatabaseReference addcoloreAnimale = ref.child("users").child(nomeUtente).child("animale").child("colore");
        DatabaseReference adddataAnimale = ref.child("users").child(nomeUtente).child("animale").child("data");
        DatabaseReference addpesoAnimale = ref.child("users").child(nomeUtente).child("animale").child("peso");
        DatabaseReference addprovenienzaAnimale = ref.child("users").child(nomeUtente).child("animale").child("provenienza");
        DatabaseReference addrazzaAnimale = ref.child("users").child(nomeUtente).child("animale").child("razza");

        addnomeAnimale.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nome = dataSnapshot.getValue(String.class);
                TxtnomeAnimal.setText("Nome: "+nome);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        addcoloreAnimale.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String colore = dataSnapshot.getValue(String.class);
                TxtcoloreAnimale.setText("Colore: "+colore);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adddataAnimale.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                TxtdataNasictaAnimale.setText("Data nascita: "+data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        addpesoAnimale.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double peso = dataSnapshot.getValue(Double.class);
                TxtpesoAnimale.setText("Peso: "+peso+"Kg");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        addprovenienzaAnimale.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String provenienza = dataSnapshot.getValue(String.class);
                TxtSessoAnimale.setText("Sesso: "+provenienza);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        addrazzaAnimale.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String razza = dataSnapshot.getValue(String.class);
                TxtrazzaAnimale.setText("Razza: "+razza);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
