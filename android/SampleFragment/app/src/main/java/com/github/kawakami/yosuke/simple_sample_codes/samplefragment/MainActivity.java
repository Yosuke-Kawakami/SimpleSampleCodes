package com.github.kawakami.yosuke.simple_sample_codes.samplefragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    List<String> mTags = Arrays.asList("a", "b", "c");


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null)
        {
            getFragmentManager()
                .beginTransaction()
                .add(R.id.container, new Fragment_a(), "a")
                .commit();
        }
    }


    public void changeFragment(View view)
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        String   tag   = getFragmentManager().findFragmentById(R.id.container).getTag();
        int      index = mTags.indexOf(tag);

        switch(index)
        {
            case  2: ft.replace(R.id.container, new Fragment_a(), "a"); break;
            case  1: ft.replace(R.id.container, new Fragment_c(), "c"); break;
            case  0:
            default: ft.replace(R.id.container, new Fragment_b(), "b"); break;
        }

        ft.commit();
    }
}
