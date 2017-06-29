package com.github.kawakami.yosuke.simple_sample_codes.sampleAsyncTask;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
   @Override
    protected void onCreate(Bundle savedInstanceState)
   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            //  面倒くさいけどビルダーに任せておけば文字列をエンコードする手間が省ける。
                Uri.Builder b = new Uri.Builder();
                b.scheme("http");
                b.authority("www.google.com");
            //  b.path("/foo/bar/");
            //  b.appendQueryParameter("fuga", "hoge");


                new AsyncTask<Uri.Builder, Void, String>()
                {
                    TextView tv = (TextView)findViewById(R.id.tv);

                    @Override
                    protected void onPreExecute()
                    {
                        tv.setText("通信処理中");
                    }

                    @Override
                    protected String doInBackground(Uri.Builder... builders)
                    {
                        return Chores.doHttpGet(builders[0]);
                    }

                    @Override
                    protected void onPostExecute(String s)
                    {
                        tv.setText(s);
                    }
                }
                .execute(b);
            }
        });
    }
}
