package com.ignacio.lorenz.prrcmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.ClientError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    Button btn_login;
    EditText et_username;
    EditText et_password;

    protected void onCreate(Bundle savedInstanceState) {
        SessionManager session = new SessionManager(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = (EditText) findViewById(R.id.etUsername);
        et_password = (EditText) findViewById(R.id.etPassword);
        btn_login = (Button) findViewById(R.id.btnLogin);

//        sp = PreferenceManager.getDefaultSharedPreferences(this);
        btn_login.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                try{
                    //url depends on ipv4 ip address you have
                    String url = "http://192.168.1.16/PRRC-Dtracking/public/api/mobile_login";
                    final JSONObject jsonBody = new JSONObject();

                    jsonBody.put("username", et_username.getText().toString());
                    jsonBody.put("password", et_password.getText().toString());

                    JsonObjectRequest jsonFromUrl = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {



                        public void onResponse(JSONObject response) {

                            if(response != null){
                                try{
                                    if(response.getInt("is_disabled") == 0){
//                                        Toast.makeText(getApplicationContext(), "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();
//                                        int id = response.getInt("id");
//                                        String name = response.getString("name");
//                                        String username = response.getString("username");
//                                        int department = response.getInt("department_id");
//                                        int role = response.getInt("role_id");
//
//                                        session.createLoginSession(id, name, username, department, role);
                                        Intent intent = new Intent(Login.this,Home.class);
                                        startActivity(intent);
                                        Login.this.finish();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "Response: Disabled User", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch(JSONException e){
                                    Toast.makeText(getApplicationContext(), "Error:  " + e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                    if(error instanceof ClientError){
                                        Toast.makeText(getApplicationContext(), "Error:  Invalid username/password", Toast.LENGTH_SHORT).show();
                                    }
                                    else if(error instanceof TimeoutError){
                                        Toast.makeText(getApplicationContext(), "Error:  Server is down", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }

                    );
                    Singleton_Volley_Request.getInstance(getApplicationContext()).addToRequestQueue(jsonFromUrl);
                }
                catch(JSONException e){
                    e.printStackTrace();
                }

            }
        });
    }
}