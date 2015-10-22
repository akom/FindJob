package com.akomsoft.findjob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logInClickHandler(View view) {
        Intent LoginIntent = new Intent(this,LoginActivity.class);
        startActivity(LoginIntent);
    }

    public void singUpClickHandler(View view) {
        Intent SignUpIntent = new Intent(this,SignUpActivity.class);
        startActivity(SignUpIntent);
    }
}
