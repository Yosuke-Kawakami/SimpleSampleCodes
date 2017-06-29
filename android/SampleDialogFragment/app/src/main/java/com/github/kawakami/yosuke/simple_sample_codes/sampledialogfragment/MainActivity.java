package com.github.kawakami.yosuke.simple_sample_codes.sampledialogfragment;

import android.app.Activity;
import android.content.Intent;
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
                Bundle b = new Bundle();
                b .putString("TITLE", "動的なタイトル");

                MyDialogFragment mdf = new MyDialogFragment();
                mdf.setArguments(b);
                mdf.show(getFragmentManager(), "TAG");
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK)
        {
            switch (requestCode)
            {
                case 999:
                    TextView tv = (TextView) findViewById(R.id.hello_world);
                    tv.setText(data.getStringExtra(Intent.EXTRA_TEXT));
            }
        }
    }
}
