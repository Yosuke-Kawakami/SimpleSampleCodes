package simple_sample_codes.yosuke.kawakami.github.com.samplelistfragment;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by y-kawakami on 2017/06/20.
 */

public class MyListFragment extends ListFragment implements LoaderManager.LoaderCallbacks
{
    MyArrayAdapter    mMyArrayAdapter;
    ArrayList<MyItem> mList;
    MyAsyncTaskLoader mMyAsyncTaskLoader;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // 画面回転時にフラグメントを再生成しない.
        // フラグメントは再生成しなくとも、親のアクティビティは画面回転時に再生成される点に注意すること.
        setRetainInstance(true);

        mList = new ArrayList<MyItem>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.list, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mMyArrayAdapter = new MyArrayAdapter(getActivity(), R.layout.row, mList);
        setListAdapter(mMyArrayAdapter);
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


    /**
     *
     * @param item
     */
    public void addRow(MyItem item)
    {
        mMyArrayAdapter.add(item);
        mMyArrayAdapter.notifyDataSetChanged();
    }


    /**
     *
     * @param items
     */
    public void addRow(ArrayList<MyItem> items)
    {
        for(MyItem item : items)
        {
            mMyArrayAdapter.add(item);
        }
        mMyArrayAdapter.notifyDataSetChanged();
    }


    /**
     *
     */
    public void clearRows()
    {
        mMyArrayAdapter.clear();
        mMyArrayAdapter.notifyDataSetChanged();
    }








    /* ----- 以下、MyAsyncTaskLoader の記述 ----- */

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
    public void onLoadFinished(Loader loader, Object data)
    {
        // フラグメントが attach した際、タスクローダーが null でない場合は onLoadFinished が呼出される.
        // この場合、過去に取得した data が持ち込まれるため、適切に制御しないと意図せぬデータが挿入されてしまう.
        if(mMyAsyncTaskLoader != null && mMyAsyncTaskLoader.getIsRunning())
        {
            mMyAsyncTaskLoader.setIsRunning(false);
            ArrayList<MyItem> list = Chores.getListFromJson(String.valueOf(data));
            addRow(list);
        }
    }


    /**
     *
     * @param loader
     */
    @Override
    public void onLoaderReset(Loader loader)
    {
        if(mMyAsyncTaskLoader != null) mMyAsyncTaskLoader.setIsRunning(false);
    }


    /**
     *
     */
    public void forceLoad()
    {
        mMyAsyncTaskLoader.forceLoad();
    }


    /**
     *
     * @return
     */
    public boolean isRunning()
    {
        return mMyAsyncTaskLoader.getIsRunning();
    }
}
