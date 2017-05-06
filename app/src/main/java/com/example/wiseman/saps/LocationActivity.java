package com.example.wiseman.saps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LocationActivity extends AppCompatActivity {

    private Button button;
    private EditText phone;

    public static final String FILE_NAME = "Users";
    SharedPreferences DATA_ACCESS;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        button = (Button)findViewById(R.id.btnJoin);
        phone = (EditText)findViewById(R.id.etPhone);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Phone = phone.getText().toString().trim();
                DATA_ACCESS = getSharedPreferences(FILE_NAME,getApplicationContext().MODE_PRIVATE);
                editor = DATA_ACCESS.edit();

                if(Phone.isEmpty() || Phone.length()!=10){
                    Toast.makeText(getApplicationContext(),"Invalid Phone",Toast.LENGTH_LONG).show();
                    return;
                }else {

                    editor.putString("Phone",Phone);
                    editor.commit();

                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }
                }

        });
    }

}