package com.ignacio.lorenz.prrcmobile;

public class URLMaker {
    private String url = "http://121.58.204.110:1028/document_tracking/public/api/";

    public URLMaker(String added_url){
        url = url.concat(added_url);
    }

    public String getUrl() {
        return url;
    }
}
