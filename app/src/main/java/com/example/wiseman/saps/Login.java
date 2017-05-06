package com.example.wiseman.saps;

import android.app.ProgressDialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    Button btnLogin ;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Animation upAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate);
        upAnim.reset();

        RelativeLayout loginlayout = (RelativeLayout)findViewById(R.id.loginlayout);
        loginlayout.clearAnimation();
        loginlayout.setAnimation(upAnim);


        btnLogin = (Button)findViewById(R.id.btLogin);
        username =(EditText) findViewById(R.id.LogUsername);
        password =(EditText) findViewById(R.id.LogPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equalsIgnoreCase("police") && username.getText().toString().equalsIgnoreCase("police")){

                    startActivity(new Intent(Login.this,messageActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext(),"Incorrect Details",Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
    }
}
