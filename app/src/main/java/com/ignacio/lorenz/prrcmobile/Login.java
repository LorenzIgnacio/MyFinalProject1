package com.ignacio.lorenz.prrcmobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    Button btn_login;
    EditText et_username;
    EditText et_password;
    SharedPreferences sp;


    protected void onCreate(Bundle savedInstanceState) {
        final RequestQueue queue = Volley.newRequestQueue(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = (EditText) findViewById(R.id.etEmail);
        et_password = (EditText) findViewById(R.id.etPassword);
        btn_login = (Button) findViewById(R.id.btnLogin);

        sp = PreferenceManager.getDefaultSharedPreferences(this);
        btn_login.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                try{
                    //url depends on ipv4 ip address you have
                    String url = "http://192.168.1.8/prrc/public/api/mobile_login";
                    final JSONObject jsonBody = new JSONObject();

                    jsonBody.put("username", et_username.getText().toString());
                    jsonBody.put("password", et_password.getText().toString());

                    JsonObjectRequest jsonFromUrl = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {



                        public void onResponse(JSONObject response) {

                            if(response != null){
                                Intent intent = new Intent(Login.this,HomeActivity.class);
                                startActivity(intent);
                                Login.this.finish();
                            }else{
                                Toast.makeText(Login.this, "Invalid email or password", Toast.LENGTH_LONG).show();

                            }
                            //Toast.makeText(getApplicationContext(), "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();

                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                    onBackPressed();

                                }
                            }

                    );

                    queue.add(jsonFromUrl);
                }
                catch(JSONException e){
                    e.printStackTrace();
                }

            }
        });
    }
}