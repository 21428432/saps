package com.example.wiseman.saps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FirstScreen extends AppCompatActivity {
    SharedPreferences data_stored;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        data_stored = getApplicationContext().getSharedPreferences("Users", getApplicationContext().MODE_PRIVATE);
        String name = data_stored.getString("Phone","");

        if(name.isEmpty()){

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(FirstScreen.this,LocationActivity.class));
                }
            },4000);
        }
        else {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(FirstScreen.this,MainActivity.class));
                }
            },4000);

        }


    }
}
