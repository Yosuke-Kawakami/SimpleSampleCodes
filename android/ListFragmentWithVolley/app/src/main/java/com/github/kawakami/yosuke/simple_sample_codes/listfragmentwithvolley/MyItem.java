package com.github.kawakami.yosuke.simple_sample_codes.listfragmentwithvolley;

/**
 * Created by y-kawakami on 2017/06/27.
 */

public class MyItem
{
    String title;
    String author;
    String image_url;

    MyItem(String title, String author, String image_url)
    {
        this.title     = title;
        this.author    = author;
        this.image_url = image_url;
    }

    public String getTitle()     { return title;    }
    public String getAuthor()    { return author;   }
    public String getImage_url() { return image_url;}

    public void setTitle(String title)         { this.title  = title; }
    public void setAuthor(String author)       { this.author = author;}
    public void setImage_url(String image_url) { this.image_url = image_url;}
}
