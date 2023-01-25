package com.example.navbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;


public class MenuFragment extends Fragment {

    String nomeUtente;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Menu");
        View view = inflater.inflate(R.layout.menu_fragment,null);
        nomeUtente = this.getArguments().getString("nomeutente");

        Button buttonVaccini = (Button) view.findViewById(R.id.buttonVaccini);
        buttonVaccini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), vaxlist.class);
                intent.putExtra("nomeutente", nomeUtente);
                startActivity(intent);
            }
        });

        Button buttonMedicinali = (Button) view.findViewById(R.id.buttonMedicinali);
        buttonMedicinali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), medlist.class);
                intent1.putExtra("nomeutente", nomeUtente);
                startActivity(intent1);
            }
        });
        return view;
    }
}

