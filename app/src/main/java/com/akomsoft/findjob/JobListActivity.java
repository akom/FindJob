package com.akomsoft.findjob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class JobListActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String JSON_URL = "http://akomsoft.com/WebServer/getData.php";
    public static final String JOB_ID = "id";

    private Button buttonGet;

    private ListView listViewJobs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);
        buttonGet = (Button) findViewById(R.id.buttonGet);
        buttonGet.setOnClickListener(this);
        listViewJobs = (ListView) findViewById(R.id.listViewJobs);

        sendRequest();

    }
    private void sendRequest(){
        Log.i("click", "entrou");
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        showJSON(response);
                        Log.i("click", response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(JobListActivity.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });



        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
//        try {
//            json = URLEncoder.encode(json, "UTF8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();

        CustomList cl = new CustomList(this, ParseJSON.ids,ParseJSON.tittles,ParseJSON.locations,ParseJSON.companies);
        listViewJobs.setAdapter(cl);
        listViewJobs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                displayJob(position+1);



            }
        });
    }

    private void displayJob(int position) {

        Intent jobIntent = new Intent(this, JobActivity.class);
        jobIntent.putExtra(JOB_ID, position);
        startActivity(jobIntent);

    }

    @Override
    public void onClick(View v) {
        sendRequest();
        Log.i("click","click");
    }

}
