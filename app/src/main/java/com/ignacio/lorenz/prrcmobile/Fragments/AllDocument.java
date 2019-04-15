package com.ignacio.lorenz.prrcmobile.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.ignacio.lorenz.prrcmobile.R;
import com.ignacio.lorenz.prrcmobile.Singleton_Volley_Request;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllDocument extends Fragment {
    private String url = "http://192.168.1.16/PRRC-Dtracking/public/api/all_docus";

    private static final String KEY_STATUS = "status";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_REF = "reference_number";
    private static final String KEY_DATE = "final_action_date";
    private static final String KEY_SUBJECT = "subject";

    private ListAdapter adapter;

    private List<HashMap<String, String>> all_docus = new ArrayList<>();

    public AllDocument() {

    }

    ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all_docu, container, false);
        lv = (ListView) view.findViewById(R.id.listView);
        all_docus.clear();

        JsonArrayRequest allDocu = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for(int i = 0; i < response.length(); i++){
                        HashMap<String, String> map = new HashMap<>();
                        map.put(KEY_REF, response.getJSONObject(i).getString(KEY_REF));
                        map.put(KEY_SUBJECT, response.getJSONObject(i).getString(KEY_SUBJECT));
                        map.put(KEY_USERNAME, response.getJSONObject(i).getString(KEY_USERNAME));
                        map.put(KEY_DATE, response.getJSONObject(i).getString(KEY_DATE));
                        map.put(KEY_STATUS, response.getJSONObject(i).getString(KEY_STATUS));

                        all_docus.add(map);
                    }
                    adapter = new SimpleAdapter(getActivity(), all_docus, R.layout.fragment_all_docu_list,
                            new String[] {KEY_STATUS, KEY_USERNAME, KEY_REF, KEY_DATE, KEY_SUBJECT},
                            new int[] {R.id.status, R.id.username, R.id.reference_number, R.id.final_action_date, R.id.subject});

                    lv.setAdapter(adapter);
//                    Toast.makeText(getContext(), "Loaded new data", Toast.LENGTH_SHORT).show();
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



    

