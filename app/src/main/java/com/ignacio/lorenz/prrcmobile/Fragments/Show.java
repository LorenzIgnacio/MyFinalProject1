package com.ignacio.lorenz.prrcmobile.Fragments;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.ignacio.lorenz.prrcmobile.R;
import com.ignacio.lorenz.prrcmobile.URLMaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Show extends AllDocument{
    private String url = new URLMaker("show").getUrl();

    private static final String KEY_RUSH = "is_rush";
    private static final String KEY_TYPE = "type_of_docu_id";
    private static final String KEY_REF = "reference_number";
    private static final String KEY_DATE = "final_action_date";
    private static final String KEY_SUBJECT = "subject";

    private List<HashMap<String, String>> show = new ArrayList<>();
}
