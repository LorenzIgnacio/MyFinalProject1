package com.ignacio.lorenz.prrcmobile.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ignacio.lorenz.prrcmobile.Document.ShowActivity;
import com.ignacio.lorenz.prrcmobile.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder{
    public CardView mCardView;
    public TextView status;
    public TextView username;
    public TextView reference_number;
    public TextView final_action_date;
    public TextView subject;

    protected Button show_btn;

    Context ctext;


    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public RecyclerViewHolder(LayoutInflater inflater, ViewGroup container, int layout_resource, final Context context){
        super(inflater.inflate(layout_resource, container, false));
        mCardView = itemView.findViewById(R.id.all_docu_cardview);
        status = itemView.findViewById(R.id.status);
        username = itemView.findViewById(R.id.username);
        reference_number = itemView.findViewById(R.id.reference_number);
        final_action_date = itemView.findViewById(R.id.final_action_date);
        subject = itemView.findViewById(R.id.subject);
        show_btn = itemView.findViewById(R.id.show_on_card);

        show_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowActivity.class);
                intent.putExtra("reference_number", reference_number.getText().toString());
                context.startActivity(intent);
            }
        });
    }

}
