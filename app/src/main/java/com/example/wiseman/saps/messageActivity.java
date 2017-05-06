package com.example.wiseman.saps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class messageActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    String insertUrlMessage = "https://www.bodycorporate.payghost.co.za/InsertMessage.php";
    EditText message;
    ImageButton Messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        message = (EditText)findViewById(R.id.messageEdit);
        Messages = (ImageButton)findViewById(R.id.chatSendButton);

       Messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, insertUrlMessage, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response.toString());
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> parameters = new HashMap<String, String>();

                        parameters.put("message",message.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);
                Toast.makeText(messageActivity.this, "Broadcast message sent...", Toast.LENGTH_LONG).show();
            }
        });

    }
}
