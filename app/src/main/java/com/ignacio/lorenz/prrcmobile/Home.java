package com.ignacio.lorenz.prrcmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ignacio.lorenz.prrcmobile.Adapter.ViewpagerAdapter;
import com.ignacio.lorenz.prrcmobile.QRScanner.Scanner;

public class Home extends AppCompatActivity {

    private ViewpagerAdapter adapt;
    private ViewPager vp;
    private TabLayout tabs;
    Bundle error;

    SessionManager session;

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("PRRC");

        adapt = new ViewpagerAdapter(getSupportFragmentManager());

        vp = findViewById(R.id.viewpager);
        tabs = findViewById(R.id.tabs);
        title = findViewById(R.id.home_title);

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
        title.setText("All Documents");
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

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch(i){
                    case 0 :
                        title.setText("All Documents");
                        break;
                    case 1:
                        title.setText("My Documents");
                        break;
                    case 2:
                        title.setText("Accepted Documents");
                        break;
                    case 3:
                        title.setText("Received Documents");
                        break;
                    case 4:
                        title.setText("Inactive Documents");
                        break;
                    case 5:
                        title.setText("Archived Documents");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        View tab1 = tabs.getTabAt(0).view;
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("All Documents");
            }
        });

        tabs.getTabAt(1).setIcon(R.drawable.ic_all);
        View tab2 = tabs.getTabAt(1).view;
        tab2.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                title.setText("My Documents");
                return false;
            }
        });

        tabs.getTabAt(2).setIcon(R.drawable.ic_accept);
        View tab3 = tabs.getTabAt(2).view;
        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("Accepted Documents");
            }
        });

        tabs.getTabAt(3).setIcon(R.drawable.ic_receive);
        View tab4 = tabs.getTabAt(3).view;
        tab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("Received Documents");
            }
        });

        tabs.getTabAt(4).setIcon(R.drawable.ic_inactive);
        View tab5 = tabs.getTabAt(4).view;
        tab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("Inactive Documents");
            }
        });

        tabs.getTabAt(5).setIcon(R.drawable.ic_archive);
        View tab6 = tabs.getTabAt(5).view;
        tab6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("Archived Documents");
            }
        });

    }
}
