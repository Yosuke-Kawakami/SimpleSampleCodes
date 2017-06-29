package com.github.kawakami.yosuke.simple_sample_codes.listfragmentwithvolley;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by y-kawakami on 2017/06/27.
 */

public class MyListFragment extends ListFragment
{
    MyArrayAdapter    mMyArrayAdapter;
    ArrayList<MyItem> mList;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
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
}
