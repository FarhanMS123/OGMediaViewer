package id.adipati.ogmediaviewer;

import androidx.annotation.Nullable;

public class Post {
    public int id;
    public String name;
    public String ori;
    public String thumbnail;
    public int length;

    public Post(int id, @Nullable String name, @Nullable String ori, @Nullable String thumbnail, @Nullable int length){
        this.id = id;
        this.name = name;
        this.ori = ori;
        this.thumbnail = thumbnail;
        this.length = length;
    }
}
