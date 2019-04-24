package com.ignacio.lorenz.prrcmobile;

public class URLMaker {
    private String url = "http://192.168.1.9/prrc/public/api/";

    public URLMaker(String added_url){
        url = url.concat(added_url);
    }

    public String getUrl() {
        return url;
    }
}
