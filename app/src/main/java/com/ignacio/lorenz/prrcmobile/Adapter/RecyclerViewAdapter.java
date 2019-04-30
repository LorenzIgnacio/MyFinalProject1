package com.ignacio.lorenz.prrcmobile.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ignacio.lorenz.prrcmobile.RecyclerView.RecyclerViewHolder;

import java.util.HashMap;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    Context ctext;
    int layout_resource;
    List<HashMap<String, String>> docu_data;

    public RecyclerViewAdapter(Context context, int resource, List<HashMap<String, String>> docu_info){
        ctext = context;
        layout_resource = resource;
        docu_data = docu_info;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctext);
        return new RecyclerViewHolder(inflater, viewGroup, layout_resource, ctext);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        recyclerViewHolder.status.setText(docu_data.get(i).get("status"));
        recyclerViewHolder.username.setText(docu_data.get(i).get("username"));
        recyclerViewHolder.reference_number.setText(docu_data.get(i).get("reference_number"));
        recyclerViewHolder.final_action_date.setText(docu_data.get(i).get("final_action_date"));
        recyclerViewHolder.subject.setText(docu_data.get(i).get("subject"));

    }

    @Override
    public int getItemCount() {
        return docu_data.size();
    }
}
