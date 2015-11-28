package model;

/**
 * Created by MSI on 2015/11/25.
 */
public class Atn {

    private String title;
    private String content;
    private String image;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Atn(String content, String image, String title) {
        this.content = content;
        this.image = image;
        this.title = title;
    }

    public Atn() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
