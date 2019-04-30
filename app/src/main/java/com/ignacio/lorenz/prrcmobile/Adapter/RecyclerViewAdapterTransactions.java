package com.ignacio.lorenz.prrcmobile.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ignacio.lorenz.prrcmobile.RecyclerView.RecyclerViewTransactions;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class RecyclerViewAdapterTransactions extends RecyclerView.Adapter<RecyclerViewTransactions> {
    private Context ctext;
    private List<HashMap<String, String>> transaction_details;

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
        recyclerViewTransactions.transaction_remarks.setText(transaction_details.get(i).get("remarks"));
        recyclerViewTransactions.transaction_deadline.setText(transaction_details.get(i).get("date_deadline"));
        recyclerViewTransactions.FromLoc.setText(transaction_details.get(i).get("location"));
        recyclerViewTransactions.From.setText(transaction_details.get(i).get("in_charge"));
        recyclerViewTransactions.ToLoc.setText(transaction_details.get(i).get("route"));
        recyclerViewTransactions.To.setText(transaction_details.get(i).get("recipient"));
        recyclerViewTransactions.created_date.setText(transaction_details.get(i).get("created_at"));
        if(Objects.requireNonNull(transaction_details.get(i).get("has_received")).equals("Yes")){
            recyclerViewTransactions.receive_details.setText(String.format("Yes at %s", transaction_details.get(i).get("received_at")));
        }
        else{
            recyclerViewTransactions.receive_details.setText("No");
        }

        if(Objects.requireNonNull(transaction_details.get(i).get("has_sent")).equals("Yes")){
            recyclerViewTransactions.complied_details.setText(String.format("Yes at %s", transaction_details.get(i).get("sent_at")));
        }
        else{
            recyclerViewTransactions.complied_details.setText("No");
        }

//        recyclerViewTransactions.date_deadline.setText(transaction_details.get(i).get("date_deadline"));
//        recyclerViewTransactions.has_receive.setText(transaction_details.get(i).get("has_received"));

    }

    @Override
    public int getItemCount() {
        return transaction_details.size();
    }
}
