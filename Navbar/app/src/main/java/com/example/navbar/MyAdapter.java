package com.example.navbar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import android.widget.Button;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Vaccino> list;
    private String nomeUtente;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    public MyAdapter(Context context, ArrayList<Vaccino> list, String nomeUtente) {
            this.context = context;
            this.list = list;
            this.nomeUtente = nomeUtente;
            }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.item_vax, parent, false);
            return new MyViewHolder(v);
            }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Vaccino vax = list.get(position);
            holder.tipo.setText(vax.getTipo());
            holder.data.setText(vax.getData());
            holder.fine.setText(vax.getFine());

            int comparisonResult = compareDates(vax.getFine());
            if (comparisonResult < 0) {
                holder.itemView.setBackgroundTintList(context.getResources().getColorStateList(R.color.red));
            } else if (comparisonResult < 5) {
                holder.itemView.setBackgroundTintList(context.getResources().getColorStateList(R.color.yellow));
            } else {
                holder.itemView.setBackgroundTintList(context.getResources().getColorStateList(R.color.green));
            }


            holder.elimina.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(vax);
                    notifyDataSetChanged(); // Notifica l'adattatore della modifica della lista
                    reference = database.getReference("users").child(nomeUtente).child("vaccini").child(vax.getTipo());
                    reference.removeValue();
                }
            });
    }

    @Override
    public int getItemCount() {
            return list.size();
            }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tipo, data, fine;
        Button elimina;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tipo = itemView.findViewById(R.id.tvtype);
            data = itemView.findViewById(R.id.tvdata);
            fine = itemView.findViewById(R.id.tvfine);
            elimina = itemView.findViewById(R.id.tvdelate);
            database = FirebaseDatabase.getInstance();
        }
    }

    private int compareDates(String expirationDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date expDate = sdf.parse(expirationDate);
            Date currentDate = new Date();
            long diffInMillies = expDate.getTime() - currentDate.getTime();
            return (int) diffInMillies / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            Log.e("MyAdapter", "Error while parsing expiration date", e);
            // Gestione dell'errore
            return Integer.MAX_VALUE;
        }
    }



}








