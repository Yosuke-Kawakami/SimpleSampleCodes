package simple_sample_codes.yosuke.kawakami.github.com.samplelistfragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    MyListFragment    mFragment;

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
            mFragment = (MyListFragment) getFragmentManager().findFragmentByTag("TAG");
        }
        else
        {
            getFragmentManager()
                .beginTransaction()
                .add(R.id.fragment, mFragment = new MyListFragment(), "TAG")
                .commit();
        }


        //
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(mFragment != null && !mFragment.isRunning())
                {
                    mFragment.forceLoad();
                }
            }
        });
    }

/*
    // オプションメニューが使いたい場合がアンコメントする.

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch(item.getItemId())
        {
            case R.id.menu_clear_list:
                mFragment.clearRows();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
*/
}
