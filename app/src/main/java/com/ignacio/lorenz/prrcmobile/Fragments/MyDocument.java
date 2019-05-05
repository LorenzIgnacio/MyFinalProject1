package com.ignacio.lorenz.prrcmobile.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.ignacio.lorenz.prrcmobile.SessionManager;
import com.ignacio.lorenz.prrcmobile.Singleton_Volley_Request;
import com.ignacio.lorenz.prrcmobile.URLMaker;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MyDocument extends Fragment {

    private String url = new URLMaker("my_docus/").getUrl();

    private static final String KEY_STATUS = "status";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_REF = "reference_number";
    private static final String KEY_DATE = "final_action_date";
    private static final String KEY_SUBJECT = "subject";
    private static final String KEY_RUSH = "is_rush";

    private List<HashMap<String, String>> my_docus = new ArrayList<>();

    private HashMap<String, String> user;

    SessionManager session;

    public MyDocument() {

    }

    SwipeRefreshLayout swiper;

    RecyclerView rv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getContext());

        user = session.getUserDetails();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recycler_view_docu, container, false);
        rv = view.findViewById(R.id.recycle_view_all_docu);
        my_docus.clear();

        swiper = view.findViewById(R.id.refresher);
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                my_docus.clear();
                getJSONData();
                swiper.setRefreshing(false);
            }
        });

        getJSONData();

        return view;
    }

    public void getJSONData(){
        JsonArrayRequest mydocu = new JsonArrayRequest(Request.Method.GET, url + user.get(SessionManager.KEY_ID),
                null, new Response.Listener<JSONArray>() {
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
                        map.put(KEY_RUSH, response.getJSONObject(i).getString(KEY_RUSH));

                        my_docus.add(map);
                    }
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setAdapter(new RecyclerViewAdapter(getContext(), R.layout.card_view_docu, my_docus));
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

        Singleton_Volley_Request.getInstance(getContext()).addToRequestQueue(mydocu);
    }

}
