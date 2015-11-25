package map;


import android.widget.ImageView;

import java.io.File;
import java.io.Serializable;

/**
 * Created by MSI on 2015/11/23.
 */
public class Picture_marker implements Serializable {

    private String title;
    private String content;
    private File imagefile;

    public Picture_marker()
    {
    }
    public Picture_marker(String _title, String _content, File _imagefile)
    {
        this.title = _title;
        this.content = _content;
        this.imagefile = _imagefile;
    }

    public File getImagefile() {
        return imagefile;
    }

    public void setImagefile(File imagefile) {
        this.imagefile = imagefile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
