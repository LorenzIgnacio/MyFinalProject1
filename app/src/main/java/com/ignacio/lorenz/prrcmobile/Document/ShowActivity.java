package com.ignacio.lorenz.prrcmobile.Document;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ignacio.lorenz.prrcmobile.Adapter.ShowPagerAdapter;
import com.ignacio.lorenz.prrcmobile.QRScanner.Scanner;
import com.ignacio.lorenz.prrcmobile.R;
import com.ignacio.lorenz.prrcmobile.SessionManager;
import com.ignacio.lorenz.prrcmobile.Singleton_Volley_Request;
import com.ignacio.lorenz.prrcmobile.URLMaker;

import org.json.JSONException;
import org.json.JSONObject;

public class ShowActivity extends AppCompatActivity {

    private String url = new URLMaker("show").getUrl();

    TextView reference_number;
    TextView subject;
    TextView status;

    private ShowPagerAdapter adapt;
    private ViewPager vp;
    private TabLayout tabs;
    String ref_num;
    Bundle info;
    JSONObject post_details;

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("PRRC");

        adapt = new ShowPagerAdapter(getSupportFragmentManager());

        vp = findViewById(R.id.show_viewpager);
        tabs = findViewById(R.id.show_tabs);
        reference_number = findViewById(R.id.show_ref_number);
        subject = findViewById(R.id.show_subject);
        status = findViewById(R.id.show_status);

        info = getIntent().getExtras();
        ref_num = info.getString("reference_number");

        post_details = new JSONObject();

        try {
            post_details.put("reference_number", ref_num);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        session = new SessionManager(getApplicationContext());
        session.checkLogin();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.qr:
//                Toast.makeText(getApplicationContext(), "QR", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ShowActivity.this, Scanner.class);
                startActivity(intent);
                return true;
            case R.id.logout:
                session.logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        viewFragment();
        getBannerDetails();
    }

    protected void viewFragment(){
        vp.setAdapter(adapt);
        tabs.setupWithViewPager(vp);

        tabs.getTabAt(0).setText("Document Details");
        tabs.getTabAt(1).setText("Document Transaction");

    }

    public void getBannerDetails(){
        JsonObjectRequest banner_details = new JsonObjectRequest(Request.Method.POST, url, post_details, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    reference_number.setText(response.getString("reference_number"));
                    subject.setText(response.getString("subject"));
                    status.setText(response.getString("status"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error fetching record details...", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });

        Singleton_Volley_Request.getInstance(getApplicationContext()).addToRequestQueue(banner_details);
    }
}
