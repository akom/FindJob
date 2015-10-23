package com.akomsoft.findjob;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseJSON {
    public static String[] ids;
    public static String[] tittles;
    public static String[] locations;
    public static String[] companies;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_ID = "id";
    public static final String KEY_TITTLE = "tittle";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_COMPANY = "company";

    private JSONArray jobs = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(json);
            jobs = jsonObject.getJSONArray(JSON_ARRAY);

            ids = new String[jobs.length()];
            tittles = new String[jobs.length()];
            locations = new String[jobs.length()];
            companies = new String[jobs.length()];

            for(int i=0;i<jobs.length();i++){
                JSONObject jo = jobs.getJSONObject(i);
                ids[i] = jo.getString(KEY_ID);
                tittles[i] = jo.getString(KEY_TITTLE);
                locations[i] = jo.getString(KEY_LOCATION);
                companies[i] =jo.getString(KEY_COMPANY);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
