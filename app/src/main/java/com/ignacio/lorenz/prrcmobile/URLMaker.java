package com.ignacio.lorenz.prrcmobile;

public class URLMaker {
    private String url = "http://10.1.53.15/PRRC-Dtracking/public/api/";

    public URLMaker(String added_url){
        url = url.concat(added_url);
    }

    public String getUrl() {
        return url;
    }
}
