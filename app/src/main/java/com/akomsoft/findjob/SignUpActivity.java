package com.akomsoft.findjob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    public static final String REGISTER_URL = "http://www.akomsoft.com/WebServer/volleyRegister.php";

    public static final String KEY_NAME = "name";
    public static final String KEY_LASTNAME = "lastName";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "pasword";

    private EditText editTextName;
    private EditText ediTextLasName;
    private EditText editTextEmail;
    private EditText ediTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextName = (EditText) findViewById(R.id.et_name);
        ediTextLasName = (EditText) findViewById(R.id.et_last_name);
        editTextEmail = (EditText) findViewById(R.id.et_new_email);
        ediTextPassword = (EditText) findViewById(R.id.et_new_password);

    }

    public void JobClickHandler(View view) {

        registerUser();

        Intent JobListIntent = new Intent(this, JobListActivity.class);
        startActivity(JobListIntent);
    }

    private void registerUser() {
        final String name = editTextName.getText().toString().trim();
        final String lastName = ediTextLasName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = ediTextPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(SignUpActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUpActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put(KEY_NAME,name);
                params.put(KEY_LASTNAME,lastName);
                params.put(KEY_PASSWORD,password);
                params.put(KEY_EMAIL, email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
