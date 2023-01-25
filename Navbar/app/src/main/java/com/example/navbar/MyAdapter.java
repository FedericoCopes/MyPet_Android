package com.example.navbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Vaccino> list;

    public MyAdapter(Context context, ArrayList<Vaccino> list) {
        this.context = context;
        this.list = list;
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private int compareDates(String expirationDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date expDate = sdf.parse(expirationDate);
            Date currentDate = new Date();
            long diffInMillies = expDate.getTime() - currentDate.getTime();
            return (int) diffInMillies / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return 1;
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tipo, data, fine;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tipo = itemView.findViewById(R.id.tvtype);
            data = itemView.findViewById(R.id.tvdata);
            fine = itemView.findViewById(R.id.tvfine);
        }
    }
}

