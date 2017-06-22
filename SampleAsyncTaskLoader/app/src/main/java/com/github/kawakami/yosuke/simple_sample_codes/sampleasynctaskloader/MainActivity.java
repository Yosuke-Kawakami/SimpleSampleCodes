package com.github.kawakami.yosuke.simple_sample_codes.sampleasynctaskloader;

import android.app.LoaderManager;
import android.content.Loader;
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

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>
{
    MyAsyncTaskLoader mMyAsyncTaskLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getLoaderManager().initLoader(0, savedInstanceState, this);  //(id, Bundle, Callback)

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(mMyAsyncTaskLoader != null && !mMyAsyncTaskLoader.getIsRunning())
                {
                    mMyAsyncTaskLoader.forceLoad();
                }
            }
        });
    }

    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle)
    {
        Uri.Builder b = new Uri.Builder();
        b.scheme("https");
        b.authority("www.googleapis.com");
        b.path("books/v1/volumes");
        b.appendQueryParameter("q", "android");

        return mMyAsyncTaskLoader = new MyAsyncTaskLoader(this, b);
    }


    @Override
    public void onLoadFinished(Loader<String> loader, String s)
    {
        mMyAsyncTaskLoader.setIsRunning(false);
        TextView tv = (TextView)findViewById(R.id.tv);
        tv.setText(s);
    }


    @Override
    public void onLoaderReset(Loader<String> loader)
    {
        mMyAsyncTaskLoader.setIsRunning(false);
        TextView tv = (TextView)findViewById(R.id.tv);
        tv.setText("リセットしました");
    }
}
