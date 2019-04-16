package com.ignacio.lorenz.prrcmobile;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ignacio.lorenz.prrcmobile.Adapter.ViewpagerAdapter;

public class Home extends AppCompatActivity {

    private ViewpagerAdapter adapt;
    private ViewPager vp;
    private TabLayout tabs;

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
                Toast.makeText(getApplicationContext(), "QR", Toast.LENGTH_LONG).show();
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
        tabs.getTabAt(1).setIcon(R.drawable.ic_all);
        tabs.getTabAt(2).setIcon(R.drawable.ic_accept);
        tabs.getTabAt(3).setIcon(R.drawable.ic_receive);
        tabs.getTabAt(4).setIcon(R.drawable.ic_inactive);
        tabs.getTabAt(5).setIcon(R.drawable.ic_archive);

        tabs.getTabAt(0).setText("All");
        tabs.getTabAt(1).setText("My");
        tabs.getTabAt(2).setText("Accept");
        tabs.getTabAt(3).setText("Receive");
        tabs.getTabAt(4).setText("Inactive");
        tabs.getTabAt(5).setText("Archive");

    }
}
