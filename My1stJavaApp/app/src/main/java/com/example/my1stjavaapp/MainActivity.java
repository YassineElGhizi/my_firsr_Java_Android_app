package com.example.my1stjavaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView name = (TextView)findViewById(R.id.name);
        TextView age = (TextView)findViewById(R.id.age);
        Button addBtn = (Button)findViewById(R.id.add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                String a = age.getText().toString();

                System.out.println("=====");
                System.out.println(name.getText().toString());
                System.out.println(age.getText().toString());
                System.out.println("=====");
                postData(n , a);
            }
        });
    }

    public void postData(String name , String age){
        System.out.println("sending an http requests");
//        String url = "http://127.0.0.1:5000/user";
//        String url = "http://192.168.1.122:5000/user";
//        String url = "http://localhost:5000/user";
        String url = "http://4134-196-64-146-96.ngrok.io/save";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(MainActivity.this,response.trim(),Toast.LENGTH_LONG).show();
                        System.out.println("response is " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        System.out.println("EROOOOOOOOOOOOR " + error);
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("name",name);
                params.put("age",age);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        System.out.println("DONE!");
        requestQueue.add(stringRequest);
        System.out.println("DONE 2!");
    }

}
