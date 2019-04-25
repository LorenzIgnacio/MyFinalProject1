package com.ignacio.lorenz.prrcmobile.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.ignacio.lorenz.prrcmobile.Adapter.RecyclerViewAdapter;
import com.ignacio.lorenz.prrcmobile.R;
import com.ignacio.lorenz.prrcmobile.Singleton_Volley_Request;
import com.ignacio.lorenz.prrcmobile.URLMaker;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Show extends AllDocument{
    private String url = new URLMaker("show").getUrl();

    private static final String KEY_REF = "reference_number";
    private static final String KEY_TYPE = "type_of_docu_id";
    private static final String KEY_RUSH = "is_rush";
    private static final String KEY_SOURCE = "department_id";
    private static final String KEY_CREATOR = "username";
    private static final String KEY_CONF = "confidentiality";
    private static final String KEY_COMP = "complexity";
    private static final String KEY_SENDER = "sender_name";
    private static final String KEY_SUBJECT = "subject";
    private static final String KEY_FINDATE = "final_action_date";
    private static final String KEY_ISO = "iso_code";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_INCHARGE = "in_charge";
    private static final String KEY_ROUTE = "route";
    private static final String KEY_TO = "to";
    private static final String KEY_REMARKS = "remarks";
    private static final String KEY_DEADLINE = "date_deadline";
    private static final String KEY_RECEIVED = "is_received";

    private List<HashMap<String, String>> show = new ArrayList<>();

    public Show() {

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.show, container, false);
        show.clear();

        JsonArrayRequest allDocu = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for(int i = 0; i < response.length(); i++){
                        HashMap<String, String> map = new HashMap<>();
                        map.put(KEY_REF, response.getJSONObject(i).getString(KEY_REF));
                        map.put(KEY_SUBJECT, response.getJSONObject(i).getString(KEY_SUBJECT));
                        map.put(KEY_TYPE, response.getJSONObject(i).getString(KEY_TYPE));
                        map.put(KEY_RUSH, response.getJSONObject(i).getString(KEY_RUSH));
                        map.put(KEY_SOURCE, response.getJSONObject(i).getString(KEY_SOURCE));
                        map.put(KEY_CREATOR, response.getJSONObject(i).getString(KEY_CREATOR));
                        map.put(KEY_CONF, response.getJSONObject(i).getString(KEY_CONF));
                        map.put(KEY_COMP, response.getJSONObject(i).getString(KEY_COMP));
                        map.put(KEY_SENDER, response.getJSONObject(i).getString(KEY_SENDER));
                        map.put(KEY_FINDATE, response.getJSONObject(i).getString(KEY_FINDATE));
                        map.put(KEY_ISO, response.getJSONObject(i).getString(KEY_ISO));
                        map.put(KEY_LOCATION, response.getJSONObject(i).getString(KEY_LOCATION));
                        map.put(KEY_INCHARGE, response.getJSONObject(i).getString(KEY_INCHARGE));
                        map.put(KEY_ROUTE, response.getJSONObject(i).getString(KEY_ROUTE));
                        map.put(KEY_TO, response.getJSONObject(i).getString(KEY_TO));
                        map.put(KEY_REMARKS, response.getJSONObject(i).getString(KEY_REMARKS));
                        map.put(KEY_DEADLINE, response.getJSONObject(i).getString(KEY_DEADLINE));
                        map.put(KEY_RECEIVED, response.getJSONObject(i).getString(KEY_RECEIVED));


                        show.add(map);
                    }

                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setAdapter(new RecyclerViewAdapter(getContext(), R.layout.card_view_docu, show));

                }
                catch(JSONException e){
                    Toast.makeText(getContext(), "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error : " + error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        Singleton_Volley_Request.getInstance(getContext()).addToRequestQueue(allDocu);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
