package com.ignacio.lorenz.prrcmobile.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ignacio.lorenz.prrcmobile.R;

public class RecyclerViewTransactions extends RecyclerView.ViewHolder {
//    public TextView from;
//    public TextView to;
//    public TextView date_deadline;
//    public TextView has_receive;


    public RecyclerViewTransactions(@NonNull View itemView) {
        super(itemView);
    }

    public RecyclerViewTransactions(LayoutInflater inflater, ViewGroup container){
        super(inflater.inflate(R.layout.transaction_details, container, false));

//        from = itemView.findViewById(R.id.from);
//        to = itemView.findViewById(R.id.to);
//        date_deadline = itemView.findViewById(R.id.date_deadline);
//        has_receive = itemView.findViewById(R.id.has_received);
    }
}
