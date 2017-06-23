package simple_sample_codes.yosuke.kawakami.github.com.samplelistfragment;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.Uri;

/**
 * Created by y-kawakami on 2017/06/20.
 */

public class MyAsyncTaskLoader extends AsyncTaskLoader
{
    Uri.Builder mBuilder;
    boolean     mIsRunning;

    String mData;

    MyAsyncTaskLoader(Context context, Uri.Builder builder)
    {
        super(context);
        mBuilder   = builder;
        mIsRunning = false;
        mData      = null;
    };


    @Override
    public String loadInBackground()
    {
        setIsRunning(true);
        return Chores.doHttpUrlClient(mBuilder);
    }


    @Override
    protected void onForceLoad()
    {
        setIsRunning(true);
        super.onForceLoad();
    }


    @Override
    public void deliverResult(Object data)
    {
        super.deliverResult(data);
        mData = String.valueOf(data);
    }


    /**
     *
     * @param isRunning
     */
    protected void setIsRunning(boolean isRunning)
    {
        mIsRunning = isRunning;
    }


    /**
     *
     * @return
     */
    protected  boolean getIsRunning()
    {
        return mIsRunning;
    }
}
