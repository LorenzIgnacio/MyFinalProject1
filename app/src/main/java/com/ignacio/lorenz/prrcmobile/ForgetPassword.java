package com.ignacio.lorenz.prrcmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class ForgetPassword extends AppCompatActivity {

    private String url = new URLMaker("change").getUrl();

    Button send_req;
    EditText username;
    String username_param;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        send_req = findViewById(R.id.btnReq);
        send_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = findViewById(R.id.etUsername);
                username_param = username.getText().toString();
                change_request(username_param);
            }
        });
    }

    public void change_request(String username){
        send_req.setAlpha(.5f);
        send_req.setClickable(false);
        JSONObject post_details = new JSONObject();

        try {
            post_details.put("username", username);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest output = new JsonObjectRequest(Request.Method.POST, url, post_details, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Intent intent = new Intent(ForgetPassword.this, Login.class);
                try {
                    intent.putExtra("req_message", response.getString("message"));
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    send_req.setAlpha(1f);
                    send_req.setClickable(true);
                    JSONObject message = new JSONObject(new String(error.networkResponse.data, StandardCharsets.UTF_8));
                    Toast.makeText(getApplicationContext(), "Error: " + message.getString("message"), Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Singleton_Volley_Request.getInstance(getApplicationContext()).addToRequestQueue(output);
    }
}
