package id.adipati.ogmediaviewer;

public class Post {
    public int id;
    public String ori;
    public String thumbnail;
    public int length;

    public Post(String ori, String thumbnail, int length){
        this.ori = ori;
        this.thumbnail = thumbnail;
        this.length = length;
    }
}
