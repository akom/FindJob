package com.akomsoft.findjob;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class
        JobActivity extends AppCompatActivity {

    private ProgressDialog loading;
    private String id;
    private TextView tv_job_tittle;
    private TextView tv_job_description;
    private TextView tv_job_location;
    private TextView tv_job_company;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        tv_job_tittle = (TextView) findViewById(R.id.job_tittle);
        tv_job_description = (TextView) findViewById(R.id.job_description);
        tv_job_location = (TextView) findViewById(R.id.job_location);
        tv_job_company = (TextView) findViewById(R.id.job_company);

        id = String.valueOf(getIntent().getIntExtra(JobListActivity.JOB_ID, 0));
        getData();
    }

    private void getData() {

        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);

        String url = Config.DATA_URL+id;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(JobActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){
        String tittle="";
        String description="";
        String location = "";
        String company ="";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            tittle = collegeData.getString(Config.KEY_TITTLE);
            description = collegeData.getString(Config.KEY_DESCRIPTION);
            location = collegeData.getString(Config.KEY_LOCATION);
            company = collegeData.getString(Config.KEY_COMPANY);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        tv_job_tittle.setText(tittle);
        tv_job_company.setText(company);
        tv_job_location.setText(location);
        tv_job_description.setText(description);
    }
}
