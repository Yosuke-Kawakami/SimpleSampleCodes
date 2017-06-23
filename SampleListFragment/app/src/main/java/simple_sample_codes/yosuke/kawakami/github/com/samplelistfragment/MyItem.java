package simple_sample_codes.yosuke.kawakami.github.com.samplelistfragment;

/**
 * Created by y-kawakami on 2017/06/20.
 */

public class MyItem
{
    String title;
    String author;

    MyItem(String title, String author)
    {
        this.title  = title;
        this.author = author;
    }

    public String getTitle()  { return title;  }
    public String getAuthor() { return author; }

    public void setTitle(String title)   { this.title  = title; }
    public void setAuthor(String author) { this.author = author;}
}
