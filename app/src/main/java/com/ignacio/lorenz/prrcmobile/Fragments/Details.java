package com.ignacio.lorenz.prrcmobile.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ignacio.lorenz.prrcmobile.R;
import com.ignacio.lorenz.prrcmobile.Singleton_Volley_Request;
import com.ignacio.lorenz.prrcmobile.URLMaker;

import org.json.JSONException;
import org.json.JSONObject;

public class Details extends Fragment {

    private String url = new URLMaker("show").getUrl();

    TextView docu_type;
    TextView rush;
    TextView source;
    TextView creator;
    TextView confidentiality;
    TextView complexity;
    TextView sender_details;
    TextView final_action_date;
    TextView iso;

    String ref_num;
    Bundle info;
    JSONObject post_details;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        info = getActivity().getIntent().getExtras();
        ref_num = info.getString("reference_number");
        post_details = new JSONObject();
        try {
            post_details.put("reference_number", ref_num);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details, container, false);
        docu_type = view.findViewById(R.id.docu_type);
        rush = view.findViewById(R.id.rush);
        source = view.findViewById(R.id.source);
        creator = view.findViewById(R.id.creator);
        confidentiality = view.findViewById(R.id.confidentiality);
        complexity = view.findViewById(R.id.complexity);
        sender_details = view.findViewById(R.id.sender_details);
        final_action_date = view.findViewById(R.id.final_action_date);
        iso = view.findViewById(R.id.iso);

        details();

        return view;
    }

    public void details(){
        JsonObjectRequest details = new JsonObjectRequest(Request.Method.POST, url, post_details, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    docu_type.setText(response.getString("docuType"));
                    if(response.getInt("is_rush") == 1){
                        rush.setText("Yes");
                    }
                    else{
                        rush.setText("No");
                    }
                    source.setText(response.getString("source"));
                    creator.setText(response.getString("creator"));
                    if(response.getInt("confidentiality") == 1){
                        confidentiality.setText("Admin Level");
                    }
                    else{
                        confidentiality.setText("All Levels");
                    }
                    complexity.setText(response.getString("complexity"));
                    sender_details.setText(response.getString("creator") + " from "
                    + response.getString("sender_address"));
                    final_action_date.setText(response.getString("final_action_date"));
                    iso.setText(response.getString("iso_code"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error fetching record details...", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });

        Singleton_Volley_Request.getInstance(getContext()).addToRequestQueue(details);
    }
}
