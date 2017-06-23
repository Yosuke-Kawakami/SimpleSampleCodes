package com.github.kawakami.yosuke.simple_sample_codes.sampleasynctaskloader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.Uri;

/**
 * Created by y-kawakami on 2017/06/14.
 */

public class MyAsyncTaskLoader extends AsyncTaskLoader
{

    Uri.Builder mBuilder;
    boolean     mIsRunning;

    MyAsyncTaskLoader(Context context, Uri.Builder builder)
    {
        super(context);
        mBuilder   = builder;
        mIsRunning = false;
    };


    @Override
    public String loadInBackground()
    {
        setIsRunning(true);
        return Chores.doHttpGet(mBuilder);
    }


    @Override
    protected void onForceLoad()
    {
        setIsRunning(true);
        super.onForceLoad();
    }


    protected void setIsRunning(boolean isRunning)
    {
        mIsRunning = isRunning;
    }


    protected  boolean getIsRunning()
    {
        return mIsRunning;
    }
}
