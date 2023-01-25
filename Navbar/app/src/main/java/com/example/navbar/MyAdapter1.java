package com.example.navbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder> {

    Context context;

    ArrayList<Medicinale> list;


    public MyAdapter1(Context context, ArrayList<Medicinale> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_med,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Medicinale med = list.get(position);
        holder.nomeM.setText(med.getNome());
        holder.dataM.setText(med.getDatascadenza());
        holder.giorniA.setText(med.getGiorniassunzione());
        holder.orariA.setText(med.getOrarioassunzione());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nomeM, dataM, giorniA, orariA;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeM = itemView.findViewById(R.id.tvnome);
            dataM = itemView.findViewById(R.id.tvdatascadenza);
            giorniA = itemView.findViewById(R.id.tvgiorniassunzione);
            orariA = itemView.findViewById(R.id.tvgiorniassunzione);

        }
    }

}
