package simple_sample_codes.yosuke.kawakami.github.com.samplelistfragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by y-kawakami on 2017/06/20.
 */

public class MyArrayAdapter extends ArrayAdapter<MyItem>
{
    MyArrayAdapter(Context context, int resource,  ArrayList<MyItem> data)
    {
        super(context, resource, data);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View v;

        if(convertView == null)
        {
            LayoutInflater li = LayoutInflater.from(getContext());
            v = li.inflate(R.layout.row, parent, false);
        }
        else
        {
            v = convertView;
        }


        //
        MyItem item = getItem(position);

        TextView title = (TextView) v.findViewById(R.id.row_title);
        title.setText(item.getTitle());

        TextView author = (TextView) v.findViewById(R.id.row_author);
        author.setText(item.getAuthor());

        return v;
    }


    /**
     *  リストを取得する.
     *
     * @return ArrayList<MyItem>
     */
    public ArrayList<MyItem> getArrayList()
    {
        ArrayList<MyItem> retValue = new ArrayList<MyItem>();

        // １つずつ格納しないとメモリから溢れる場合がある
        for(int i=0; i < getCount(); i++)
        {
            retValue.add(getItem(i));
        }
        return retValue;
    }
}
