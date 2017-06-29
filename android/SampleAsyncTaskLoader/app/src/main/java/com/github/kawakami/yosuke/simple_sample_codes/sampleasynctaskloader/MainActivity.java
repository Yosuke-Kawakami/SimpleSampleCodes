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

public class MainActivity extends AppCompatActivity
{
    MainFragment mMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // 画面回転時はアクティビティが再生成されて onCreate() が実行される.
        // mFragment は未定義だが、タグを設定しておけば回転前のフラグメント回収できる場合がある.
        // 安易に初期化してはいけない.

        boolean hasFragment = (getFragmentManager().findFragmentByTag("TAG") != null);
        if(hasFragment)
        {
            mMainFragment = (MainFragment) getFragmentManager().findFragmentByTag("TAG");
        }
        else
        {
            getFragmentManager()
                .beginTransaction()
                .add(R.id.fragment, mMainFragment = new MainFragment(), "TAG")
                .commit();
        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(mMainFragment != null && !mMainFragment.getIsRunning())
                {
                    mMainFragment.forceLoad();
                }
            }
        });
    }
}
