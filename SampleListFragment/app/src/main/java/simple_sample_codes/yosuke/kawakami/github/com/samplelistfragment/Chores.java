package simple_sample_codes.yosuke.kawakami.github.com.samplelistfragment;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by y-kawakami on 2017/06/20.
 */

public class Chores
{
    /**
     *
     *
     * @param builder
     * @return
     */
    public static String doHttpUrlClient(Uri.Builder builder)
    {
        StringBuilder     response = new StringBuilder();
        HttpURLConnection huc      = null;
        try
        {
            URL url = new URL(builder.build().toString());
            huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("GET");

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


    /**
     *
     *
     * @param s
     * @return
     */
    public static ArrayList<MyItem> getListFromJson(String s)
    {
        ArrayList<MyItem> retValue = new ArrayList<MyItem>();

        try
        {
            JSONObject jo = new JSONObject(s);
            JSONArray  ja = jo.getJSONArray("items");
            for(int i=0; i < ja.length(); i++)
            {
                String title  = ja.getJSONObject(i).getJSONObject("volumeInfo").getString("title");
                String author = ja.getJSONObject(i).getJSONObject("volumeInfo").getJSONArray("authors").getString(0);

                retValue.add(new MyItem(title, author));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return retValue;
        }
    }
}
