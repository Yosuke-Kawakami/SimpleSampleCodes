 package com.github.kawakami.yosuke.simple_sample_codes.sampledialogfragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.app.Activity.RESULT_OK;

 /**
 * Created by y-kawakami on 2017/06/26.
 */

public class MyDialogFragment extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        LayoutInflater li = LayoutInflater.from(getActivity());
        ViewGroup      vg = (ViewGroup) getActivity().findViewById(R.id.dialog_layout_root);
        View v = li.inflate(R.layout.dialog, vg);
    /*
        TextView tv = (TextView) v.findViewById(R.id.tv);
        tv.setText("動的に設定する場合はこんな感じ");
    */
        String title = getArguments().getString("TITLE");

        return new AlertDialog.Builder(getActivity())
            .setTitle(title)
            .setView(v)
            .setPositiveButton(
                android.R.string.ok, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        System.out.println("CLICKED");

                        try {
                            int reqCode = 999;
                            Intent data = new Intent();
                            data.putExtra(Intent.EXTRA_TEXT, "帰ってきたアタイ");
                            getActivity()
                                .createPendingResult(reqCode, data, PendingIntent.FLAG_ONE_SHOT)
                                .send(Activity.RESULT_OK);
                        }
                        catch (PendingIntent.CanceledException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            )
            .create();
    }
}
