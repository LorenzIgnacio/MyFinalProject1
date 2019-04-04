package com.ignacio.lorenz.prrcmobile;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer_super_admin;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.side_bar);

        sp = PreferenceManager.getDefaultSharedPreferences(this);

        Toolbar toolbar = findViewById(R.id.toolbar_super_admin);
        setSupportActionBar(toolbar);

        drawer_super_admin = findViewById(R.id.drawer_layout_super_admin);
        NavigationView navigationView = findViewById(R.id.nav_view_super_admin);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
        switch(sp.getString("role", "")){
            case "Admin":
                MenuItem ad = menu.findItem(R.id.nav_Accepted);
                ad.setVisible(false);
                break;
            case "Encoder":
                MenuItem ea = menu.findItem(R.id.nav_Accepted);
                ea.setVisible(false);
                break;
            case "Viewer":
                MenuItem v1 = menu.findItem(R.id.nav_MyDocu);
                v1.setVisible(false);
                MenuItem v2 = menu.findItem(R.id.nav_Accepted);
                v2.setVisible(false);
                break;
            case "Approver":
                MenuItem a = menu.findItem(R.id.nav_MyDocu);
                a.setVisible(false);
                break;
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_super_admin, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_super_admin.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new AllDocument()).commit();
            navigationView.setCheckedItem(R.id.nav_AllDocu);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_AllDocu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AllDocument()).commit();
                break;

            case R.id.nav_Accepted:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Accepted()).commit();
                break;

            case R.id.nav_MyDocu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MyDocument()).commit();
                break;
            case R.id.nav_Received:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Received()).commit();
                break;

            case R.id.nav_InActive:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InActive()).commit();
                break;

            case R.id.nav_Archive:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Archive()).commit();
                break;

            case R.id.nav_ScanQR:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ScanQR()).commit();
                break;

            case R.id.nav_About:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new About()).commit();
                break;

            case R.id.nav_Logout:
                Toast.makeText(this, "Logged out!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, Login.class);
                startActivity(i);
                break;
        }

        drawer_super_admin.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer_super_admin.isDrawerOpen(GravityCompat.START)) {
            drawer_super_admin.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}









