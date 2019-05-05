package com.ignacio.lorenz.prrcmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ignacio.lorenz.prrcmobile.Adapter.ViewpagerAdapter;
import com.ignacio.lorenz.prrcmobile.QRScanner.Scanner;

public class Home extends AppCompatActivity {

    private ViewpagerAdapter adapt;
    private ViewPager vp;
    private TabLayout tabs;
    Bundle error;

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("PRRC");

        adapt = new ViewpagerAdapter(getSupportFragmentManager());

        vp = (ViewPager) findViewById(R.id.viewpager);
        tabs = (TabLayout) findViewById(R.id.tabs);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        error = getIntent().getExtras();
        if(error != null){
            Toast.makeText(getApplicationContext(), "Error: " + error.getString("errorMsg"), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewFragment();
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
                Intent intent = new Intent(Home.this, Scanner.class);
                startActivity(intent);
                return true;
            case R.id.logout:
                session.logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    protected void viewFragment(){
        vp.setAdapter(adapt);
        tabs.setupWithViewPager(vp);
        tabs.getTabAt(0).setIcon(R.drawable.ic_home);
        View tab1 = tabs.getTabAt(0).view;
        tab1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "All Documents", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        tabs.getTabAt(1).setIcon(R.drawable.ic_all);
        View tab2 = tabs.getTabAt(1).view;
        tab2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "My Documents", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        tabs.getTabAt(2).setIcon(R.drawable.ic_accept);
        View tab3 = tabs.getTabAt(2).view;
        tab3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Accepted Documents", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        tabs.getTabAt(3).setIcon(R.drawable.ic_receive);
        View tab4 = tabs.getTabAt(3).view;
        tab4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Received Documents", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        tabs.getTabAt(4).setIcon(R.drawable.ic_inactive);
        View tab5 = tabs.getTabAt(4).view;
        tab5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Inactive Documents", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        tabs.getTabAt(5).setIcon(R.drawable.ic_archive);
        View tab6 = tabs.getTabAt(5).view;
        tab6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Archived Documents", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
