package com.github.kawakami.yosuke.simple_sample_codes.sampleasynctaskloader;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by y-kawakami on 2017/06/23.
 */

public class MainFragment extends Fragment implements LoaderManager.LoaderCallbacks<String>
{
    MyAsyncTaskLoader mMyAsyncTaskLoader;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // 画面回転時にフラグメントを再生成しない.
        // フラグメントは再生成しなくとも、親のアクティビティは画面回転時に再生成される点に注意すること.
        setRetainInstance(true);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment, null);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        onRestoreInstanceState(savedInstanceState);
    }



    @Override
    public void onViewStateRestored(Bundle savedInstanceState)
    {
        super.onViewStateRestored(savedInstanceState);
        // -----------------------------------------------------------
        // このメソッドはフラグメント生成または再アタッチ時に一回だけ処理する.
        // -----------------------------------------------------------
        // 処理的には onActivityCreated() で記述しても等価のはずだが、
        // メソッド名的にはここに書くことが適しているはず.
        getActivity().getLoaderManager().initLoader(0, savedInstanceState, this);
    }



    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        TextView tv = (TextView) getActivity().findViewById(R.id.tv);
        outState.putString("tv", tv.getText().toString());
    }


    /**
     * フラグメントには savedInstanceState がないので代行
     *
     * [要検討] より適切な実装があるはず
     *
     * @param savedInstanceState
     */
    private void onRestoreInstanceState(Bundle savedInstanceState)
    {
        if(savedInstanceState != null && savedInstanceState.containsKey("tv"))
        {
            String s = savedInstanceState.getString("tv");
            TextView tv = (TextView) getActivity().findViewById(R.id.tv);
            tv.setText(s);
        }
    }



    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle)
    {
        Uri.Builder b = new Uri.Builder();
        b.scheme("https");
        b.authority("www.googleapis.com");
        b.path("books/v1/volumes");
        b.appendQueryParameter("q", "android");

        return mMyAsyncTaskLoader = new MyAsyncTaskLoader(getActivity(), b);
    }



    @Override
    public void onLoadFinished(Loader<String> loader, String s)
    {
        MyAsyncTaskLoader matl = (MyAsyncTaskLoader)loader;
        if(matl.getIsRunning())
        {
            matl.setIsRunning(false);

            TextView tv = (TextView) getActivity().findViewById(R.id.tv);
            tv.setText(s);
        }
    }



    @Override
    public void onLoaderReset(Loader<String> loader)
    {
        MyAsyncTaskLoader matl = (MyAsyncTaskLoader)loader;
        matl.setIsRunning(false);
    }



    /**
     *
     * @return
     */
    protected boolean getIsRunning()
    {
        return mMyAsyncTaskLoader.getIsRunning();
    }



    /**
     *
     */
    protected void forceLoad()
    {
        mMyAsyncTaskLoader.forceLoad();
    }
}
