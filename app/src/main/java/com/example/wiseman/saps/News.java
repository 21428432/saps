package com.example.wiseman.saps;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class News extends AppCompatActivity {

    private ListView listView;
    private String JSON_STRING;
    private String URL_GET_ALL_MESSAGES = "https://www.bodycorporate.payghost.co.za/RetrieveMessages.php";
    private String TAG_JSON_ARRAY = "result";
    String message;
    String time;

    LinearLayoutManager linearlayout;
    RecyclerView recyclerView;
    homeViewAdapter homeViewAdapters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        //listView = (ListView)findViewById(R.id.listMessage);
        recyclerView = (RecyclerView)findViewById(R.id.listNews);
        linearlayout = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearlayout);

        getJSON();
    }
    private void showMessages(){

        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        List<homeItems> arrList = new ArrayList<homeItems>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);

                message = jo.getString("message");
                time = jo.getString("time");


                HashMap<String,String> Tenants = new HashMap<>();

                Tenants.put("message",message);
                Tenants.put("time",time);

                list.add(Tenants);

                arrList.add(new homeItems(message,time));
            }
            homeViewAdapters = new homeViewAdapter(getApplicationContext(),arrList);
            recyclerView.setAdapter(homeViewAdapters);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                News.this, list, R.layout.list_row,
                new String[]{"message","time"},
                new int[]{R.id.displayMessage,R.id.messageTime});

//        listView.setAdapter(adapter);
    }


    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(News.this,"Fetching News Feed"," Please Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showMessages();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(URL_GET_ALL_MESSAGES);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

}
