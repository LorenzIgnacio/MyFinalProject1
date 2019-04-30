package com.ignacio.lorenz.prrcmobile.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ignacio.lorenz.prrcmobile.R;

public class RecyclerViewTransactions extends RecyclerView.ViewHolder {
    public TextView transaction_deadline;
    public TextView transaction_remarks;
    public TextView FromLoc;
    public TextView ToLoc;
    public TextView From;
    public TextView To;
    public TextView created_date;
    public TextView receive_details;
    public TextView complied_details;


    public RecyclerViewTransactions(@NonNull View itemView) {
        super(itemView);
    }

    public RecyclerViewTransactions(LayoutInflater inflater, ViewGroup container){
        super(inflater.inflate(R.layout.transaction_details, container, false));

        transaction_remarks = itemView.findViewById(R.id.transaction_remarks);
        transaction_deadline = itemView.findViewById(R.id.transaction_deadline);
        FromLoc = itemView.findViewById(R.id.FromLoc);
        ToLoc = itemView.findViewById(R.id.ToLoc);
        From = itemView.findViewById(R.id.From);
        To = itemView.findViewById(R.id.To);
        created_date = itemView.findViewById(R.id.created_date);
        receive_details = itemView.findViewById(R.id.receive_details);
        complied_details = itemView.findViewById(R.id.complied_details);

    }
}
