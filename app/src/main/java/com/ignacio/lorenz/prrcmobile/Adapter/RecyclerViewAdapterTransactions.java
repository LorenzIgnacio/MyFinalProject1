package com.ignacio.lorenz.prrcmobile.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ignacio.lorenz.prrcmobile.RecyclerView.RecyclerViewTransactions;

import java.util.HashMap;
import java.util.List;

public class RecyclerViewAdapterTransactions extends RecyclerView.Adapter<RecyclerViewTransactions> {
    Context ctext;
    List<HashMap<String, String>> transaction_details;

    public RecyclerViewAdapterTransactions(Context context, List<HashMap<String, String>> transactions){
        ctext = context;
        transaction_details = transactions;
    }

    @NonNull
    @Override
    public RecyclerViewTransactions onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctext);
        return new RecyclerViewTransactions(inflater, viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewTransactions recyclerViewTransactions, int i) {
//        recyclerViewTransactions.from.setText(transaction_details.get(i).get("from"));
//        recyclerViewTransactions.to.setText(transaction_details.get(i).get("to"));
//        recyclerViewTransactions.date_deadline.setText(transaction_details.get(i).get("date_deadline"));
//        recyclerViewTransactions.has_receive.setText(transaction_details.get(i).get("has_received"));

    }

    @Override
    public int getItemCount() {
        return transaction_details.size();
    }
}
