package com.ignacio.lorenz.prrcmobile.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ignacio.lorenz.prrcmobile.Adapter.RecyclerViewAdapterTransactions;
import com.ignacio.lorenz.prrcmobile.R;
import com.ignacio.lorenz.prrcmobile.Singleton_Volley_Request;
import com.ignacio.lorenz.prrcmobile.URLMaker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Transactions extends Fragment {

    private String url = new URLMaker("show").getUrl();

    private static final String KEY_FROM = "from";
    private static final String KEY_TO = "to";
    private static final String KEY_DATE_DEADLINE = "date_deadline";
    private static final String KEY_HAS_RECEIVED = "has_received";

    private List<HashMap<String, String>> transaction_lists = new ArrayList<>();

    RecyclerView rv;

    String ref_num;
    Bundle info;
    JSONObject post_details;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transactions, container, false);

        rv = view.findViewById(R.id.recycle_view_transaction_details);

        getTransactionDetails();

        return view;
    }

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

    public void getTransactionDetails(){
        JsonObjectRequest transaction_details = new JsonObjectRequest(Request.Method.POST, url, post_details, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray transactions = response.getJSONArray("transaction");
                    transactions(transactions);
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

        Singleton_Volley_Request.getInstance(getContext()).addToRequestQueue(transaction_details);
    }

    public void transactions(JSONArray data){
        for(int i=0; i < data.length(); i++){
            HashMap<String, String> map = new HashMap<>();
            try {
                map.put(KEY_FROM, data.getJSONObject(i).getString("in_charge"));
                map.put(KEY_TO, data.getJSONObject(i).getString("recipient"));
                map.put(KEY_DATE_DEADLINE, data.getJSONObject(i).getString("date_deadline"));
                if(data.getJSONObject(i).getInt("is_received") == 1){
                    map.put(KEY_HAS_RECEIVED, "Yes");
                }
                else{
                    map.put(KEY_HAS_RECEIVED, "No");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            transaction_lists.add(map);
        }

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(new RecyclerViewAdapterTransactions(getContext(), transaction_lists));
    }
}
