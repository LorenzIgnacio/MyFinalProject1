package com.ignacio.lorenz.prrcmobile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity_Encoder extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer_encoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.side_bar_encoder);

        Toolbar toolbar = findViewById(R.id.toolbar_encoder);
        setSupportActionBar(toolbar);

        drawer_encoder = findViewById(R.id.drawer_layout_encoder);
        NavigationView navigationView = findViewById(R.id.nav_view_encoder);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_encoder, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_encoder.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_encoder,
                    new AllDocument()).commit();
            navigationView.setCheckedItem(R.id.nav_AllDocu_Encoder);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_AllDocu_Encoder:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_encoder,
                        new AllDocument()).commit();
                break;

            case R.id.nav_MyDocu_Encoder:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_encoder,
                        new MyDocument()).commit();
                break;

            case R.id.nav_InTran_Encoder:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_encoder,
                        new InActive()).commit();
                break;

            case R.id.nav_Archive_Encoder:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_encoder,
                        new Archive()).commit();
                break;

            case R.id.nav_About_Encoder:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_encoder,
                        new About()).commit();
                break;

            case R.id.nav_Logout_Encoder:
                Toast.makeText(this, "Logged out!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, Login.class);
                startActivity(i);
                break;
        }

        drawer_encoder.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer_encoder.isDrawerOpen(GravityCompat.START)) {
            drawer_encoder.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}






