package com.ignacio.lorenz.prrcmobile.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ignacio.lorenz.prrcmobile.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public CardView mCardView;
    public TextView status;
    public TextView username;
    public TextView reference_number;
    public TextView final_action_date;
    public TextView subject;


    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public RecyclerViewHolder(LayoutInflater inflater, ViewGroup container, int layout_resource){
        super(inflater.inflate(layout_resource, container, false));
        mCardView = itemView.findViewById(R.id.all_docu_cardview);
        status = itemView.findViewById(R.id.status);
        username = itemView.findViewById(R.id.username);
        reference_number = itemView.findViewById(R.id.reference_number);
        final_action_date = itemView.findViewById(R.id.final_action_date);
        subject = itemView.findViewById(R.id.subject);
    }
}
