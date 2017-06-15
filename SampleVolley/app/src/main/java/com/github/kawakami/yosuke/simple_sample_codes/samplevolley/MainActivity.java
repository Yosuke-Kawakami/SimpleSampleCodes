package com.github.kawakami.yosuke.simple_sample_codes.samplevolley;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    RequestQueue mRequestQueue;
    TextView     mTextView;

    List<String> mTags;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        mTextView     = (TextView)findViewById(R.id.tv);
        mTags         = new ArrayList<String>();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int method = Request.Method.GET;
                String url = new Uri.Builder()
                    .scheme("https")
                    .authority("www.googleapis.com")
                    .path("books/v1/volumes")
                    .appendQueryParameter("q", "android")
                    .build()
                    .toString();

                StringRequest sr  = new StringRequest(method, url, rl, rel);
                String        tag = String.valueOf(System.currentTimeMillis());

                sr.setTag(tag);
                mTags.add(tag);

                mRequestQueue.add(sr);
            }
        });
    }


    @Override
    protected void onStop()
    {
        super.onStop();
        if(mRequestQueue != null)
        {
            for(String tag : mTags)
            {
                mRequestQueue.cancelAll(tag);
            }
        }
    }



    // 読みやすさ優先として大局変数にしただけ。局所で生成するべき。
    Response.Listener<String> rl = new Response.Listener<String>()
    {
        @Override
        public void onResponse(String response)
        {
            mTextView.setText(response);
        }
    };

    Response.ErrorListener rel = new Response.ErrorListener()
    {
        @Override
        public void onErrorResponse(VolleyError error) {
            mTextView.setText(error.toString());
        }
    };
}
