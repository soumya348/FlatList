package com.soumya.flatlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FlatListAdapter flatListAdapter;
    ArrayList<FlatList> lists=new ArrayList<FlatList>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.nameRecycler);
        recyclerView.setHasFixedSize(true);

        flatListAdapter=new FlatListAdapter(lists, MainActivity.this);
        recyclerView.setAdapter(flatListAdapter);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

//        for (int i=0; i<10; i++ ) {
//            FlatList list=new FlatList(String.valueOf(i),"Mr","soumya","ranjan");
//            lists.add(list);
//        }
        ProgressDialog loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);

        String url = " https://randomuser.me/api/?results=100&inc=name";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
               // Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showJSON(String response) {

        // Toast.makeText(MainActivity.this,""+response,Toast.LENGTH_LONG).show();
        if (response.contains("results")) {

            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray result = jsonObject.getJSONArray("results");
                for (int i = 0; i < result.length(); i++) {
                    JSONObject ob = result.getJSONObject(i);
                    String pageName = ob.getJSONObject("name").getString("title");
                    FlatList list = new FlatList(String.valueOf(i+1),  ob.getJSONObject("name").getString("title"),  ob.getJSONObject("name").getString("first"),  ob.getJSONObject("name").getString("last"));
                    lists.add(list);
                }
               // Toast.makeText(MainActivity.this,""+result.getJSONObject(1).getJSONObject("name").getString("title"),Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            recyclerView.setAdapter(flatListAdapter);
            flatListAdapter.notifyDataSetChanged();
        }
        else {
            Toast.makeText(MainActivity.this,""+response,Toast.LENGTH_LONG).show();
        }
    }
}