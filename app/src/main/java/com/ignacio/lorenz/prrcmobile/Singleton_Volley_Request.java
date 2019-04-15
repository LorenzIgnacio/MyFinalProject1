package com.ignacio.lorenz.prrcmobile;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Singleton_Volley_Request {
    private static Singleton_Volley_Request instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private Singleton_Volley_Request(Context context){
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized Singleton_Volley_Request getInstance(Context context) {
        if (instance == null) {
            instance = new Singleton_Volley_Request(context);
        }
        return instance;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
