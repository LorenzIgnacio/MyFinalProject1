package com.ignacio.lorenz.prrcmobile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AllDocument extends Fragment {

    Button btn_login;
    TextView status;
    TextView username;
    TextView final_action_date;
    TextView subject;

    SharedPreferences sp;


    public AllDocument() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all_docu, container, false);
        final RequestQueue queue = Volley.newRequestQueue(getActivity());
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.activity_login);

        subject = (TextView) getView().findViewById(R.id.textSubject);
        status = (TextView) getView().findViewById(R.id.textStatus);
        final_action_date = (TextView) getView().findViewById(R.id.textDate);
        username = (TextView) getView().findViewById(R.id.textCreator);
        //btn_login = (Button) getView().findViewById(R.id.btnLogin);

        sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    //url depends on ipv4 ip address you have
                    String url = "http://192.168.1.8/prrc/public/api/mobile_login";
                    final JSONObject jsonBody = new JSONObject();

                    jsonBody.put("subject", subject.getText().toString());
                    jsonBody.put("status", status.getText().toString());
                    jsonBody.put("date", final_action_date.getText().toString());
                    jsonBody.put("username", username.getText().toString());

                    JsonObjectRequest jsonFromUrl = new JsonObjectRequest(Request.Method.GET, url, jsonBody, new Response.Listener<JSONObject>() {


                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("all_docus");

                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject all_docus = jsonArray.getJSONObject(i);

                                    String username = all_docus.getString("username");
                                    String subject = all_docus.getString("status");
                                    int date = all_docus.getInt("final_action_date");
                                    int status = all_docus.getInt("status");


                                    Toast.makeText(getActivity().getApplicationContext(), "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();



                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            //Toast.makeText(getActivity().getApplicationContext(), "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();

                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                    getActivity().onBackPressed();

                                }
                            }

                    );

                    queue.add(jsonFromUrl);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        return view;
    }
}



    

