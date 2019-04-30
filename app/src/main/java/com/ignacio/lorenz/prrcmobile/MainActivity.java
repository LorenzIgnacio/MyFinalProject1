package com.ignacio.lorenz.prrcmobile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SplashScreen
        if(!isConnected(MainActivity.this)) buildDialog(MainActivity.this).show();
        else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent loginIntent = new Intent(MainActivity.this, Login.class);
                    startActivity(loginIntent);
                    Toast.makeText(MainActivity.this,"Internet Connection Successful!",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }
    }

   public boolean isConnected(Context context) {
       ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
       NetworkInfo networkInfo = cm.getActiveNetworkInfo();

       if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
           android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
           android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

           if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) {
               return true;}
       else return false;
   }else
       return false;
   }

   public AlertDialog.Builder buildDialog(Context c){

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setTitle("No Internet Connection");
        builder.setMessage("Please check your internet connection and try again . Click OK to exit. ");


        builder.setPositiveButton("OK",(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"Exiting the PRRC Document Tracker Application",Toast.LENGTH_SHORT).show();
                finish();

            }
        }));

        return builder;
   }

}

