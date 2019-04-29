package com.ignacio.lorenz.prrcmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.ClientError;
import com.android.volley.DefaultRetryPolicy;
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

    SessionManager session;
    String url;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.etUsername);
        et_password = findViewById(R.id.etPassword);
        btn_login = findViewById(R.id.btnLogin);
        session = new SessionManager(getApplicationContext());
        url = new URLMaker("mobile_login").getUrl();

        btn_login.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                btn_login.setAlpha(.5f);
                btn_login.setClickable(false);
                try{
                    final JSONObject jsonBody = new JSONObject();

                    jsonBody.put("username", et_username.getText().toString());
                    jsonBody.put("password", et_password.getText().toString());

                    JsonObjectRequest jsonFromUrl = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {



                        public void onResponse(JSONObject response) {

                            if(response != null){
                                try{
                                    if(response.getInt("is_disabled") == 0){
//                                        Toast.makeText(getApplicationContext(), "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();
                                        int id = response.getInt("id");
                                        String name = response.getString("name");
                                        String username = response.getString("username");
                                        int department = response.getInt("department_id");
                                        int role = response.getInt("role_id");

                                        session.createLoginSession(id, name, username, department, role);
                                        Intent intent = new Intent(Login.this,Home.class);
                                        startActivity(intent);
                                        Login.this.finish();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "Response: Disabled User", Toast.LENGTH_SHORT).show();
                                        btn_login.setAlpha(1f);
                                        btn_login.setClickable(true);
                                    }
                                }
                                catch(JSONException e){
                                    Toast.makeText(getApplicationContext(), "Error:  " + e.toString(), Toast.LENGTH_SHORT).show();
                                    btn_login.setAlpha(1f);
                                    btn_login.setClickable(true);
                                }
                            }

                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                    if(error instanceof ClientError){
                                        Toast.makeText(getApplicationContext(), "Error:  Invalid username/password", Toast.LENGTH_SHORT).show();
                                        btn_login.setAlpha(1f);
                                        btn_login.setClickable(true);
                                    }
                                    else if(error instanceof TimeoutError){
                                        Toast.makeText(getApplicationContext(), "Error:  Server is down", Toast.LENGTH_SHORT).show();
                                        btn_login.setAlpha(1f);
                                        btn_login.setClickable(true);
                                    }

                                }
                            }

                    );

                    jsonFromUrl.setRetryPolicy(new DefaultRetryPolicy(5000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                    Singleton_Volley_Request.getInstance(getApplicationContext()).addToRequestQueue(jsonFromUrl);
                }
                catch(JSONException e){
                    e.printStackTrace();
                }

            }
        });
    }
}