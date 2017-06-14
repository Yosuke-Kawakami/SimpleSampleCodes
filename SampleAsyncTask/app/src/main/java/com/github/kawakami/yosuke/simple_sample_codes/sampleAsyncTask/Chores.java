package com.github.kawakami.yosuke.simple_sample_codes.sampleAsyncTask;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by y-kawakami on 2017/06/14.
 */

public class Chores
{
    public static String doHttpUrlClient(Uri.Builder builder)
    {
        StringBuilder     response = new StringBuilder();
        HttpURLConnection huc      = null;

        try
        {
            URL url = new URL(builder.build().toString());
            huc = (HttpURLConnection) url.openConnection();

            huc.setRequestMethod("GET");
        //  huc.setRequestProperty("USER-AGENT", "Mozilla/5.0");
        //  ...more

        /*
            huc.setRequestMethod("POST");
            huc.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            huc.setDoOutput(true);
         */

            BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream(), "UTF-8"));
            String line;
            while((line = br.readLine()) != null)
            {
                response.append(line);
            }
            br.close();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            huc.disconnect();
        }

        return response.toString();
    }
}
