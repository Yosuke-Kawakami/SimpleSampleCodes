package com.github.kawakami.yosuke.simple_sample_codes.listfragmentwithvolley;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
{
    MyListFragment mFragment;
    RequestQueue   mRequestQueue;
    ImageLoader    mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        mImageLoader = new ImageLoader(
                mRequestQueue,
                new ImageLoader.ImageCache()
                {
                    @Override
                    public Bitmap getBitmap(String url) {
                        return null;
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                    }
                }
        );


        //
        boolean hasFragment = (getSupportFragmentManager().findFragmentByTag("TAG") != null);
        if(hasFragment)
        {
            mFragment = (MyListFragment) getSupportFragmentManager().findFragmentByTag("TAG");
        }
        else
        {
            getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment, mFragment = new MyListFragment(), "TAG")
                .commit();
        }


        //
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(mFragment != null && mRequestQueue != null)
                {
                /*
                    MyItem item = new MyItem("TITLE", "Author", "http://image.url");
                    mFragment.addRow(item);
                */

                    Uri.Builder b = new Uri.Builder();
                    b.scheme("https");
                    b.authority("www.googleapis.com");
                    b.path("books/v1/volumes");
                    b.appendQueryParameter("q", "android");

                    JsonObjectRequest jor = new JsonObjectRequest(
                        Request.Method.GET,
                        b.build().toString(),
                        (JSONObject) null,  // A JSONObject to post with the request.
                        rl,
                        rel
                    );

                    mRequestQueue.add(jor);
                }
            }
        });
    }

    Response.Listener rl = new Response.Listener<JSONObject>()
    {
        @Override
        public void onResponse(JSONObject response)
        {
            String url = "http://books.google.com/books/content?id=OfN_7hj2t5wC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api";
            MyItem item = new MyItem("TITLE", "AUTHOR", url);
            mFragment.addRow(item);

            //    System.out.println(response.toString());
        }
    };

    Response.ErrorListener rel = new Response.ErrorListener()
    {
        @Override
        public void onErrorResponse(VolleyError error)
        {
            Log.d("Log", error.toString());
        }
    };

    // ----------------------------------------------------

    @Override
    protected void onResume()
    {
        super.onResume();
        if(mRequestQueue != null) mRequestQueue.start();
    }


    @Override
    protected void onPause()
    {
        super.onPause();
        if(mRequestQueue != null) mRequestQueue.stop();
    }

    // --------------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menu_clear_list:
                mFragment.clearRows();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
