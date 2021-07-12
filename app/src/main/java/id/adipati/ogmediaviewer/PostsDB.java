package id.adipati.ogmediaviewer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PostsDB {
    DBHelper dbHelper;

    public PostsDB(Context context){
        dbHelper = new DBHelper(context);
    }

    public void openDB(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
    }

    public void insertPost(Post post){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.FIELD_POSTS_NAME, post.name);
        contentValues.put(DBHelper.FIELD_POSTS_ORI, post.ori);
        contentValues.put(DBHelper.FIELD_POSTS_THUMBNAIL, post.thumbnail);
        contentValues.put(DBHelper.FIELD_POSTS_LENGTH, post.length);

        db.insert(DBHelper.TABLE_POSTS, null, contentValues);
        db.close();
    }

    public void updatePost(Post post){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.FIELD_POSTS_NAME, post.name);
        contentValues.put(DBHelper.FIELD_POSTS_ORI, post.ori);
        contentValues.put(DBHelper.FIELD_POSTS_THUMBNAIL, post.thumbnail);
        contentValues.put(DBHelper.FIELD_POSTS_LENGTH, post.length);

        String[] selectionArgs = {String.valueOf(post.id)};
        db.update(DBHelper.TABLE_POSTS, contentValues, "id=?", selectionArgs);

        db.close();
    }

    public Post getPostById(Integer id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = "id=?";
        String[] selectionArgs = {id.toString()};

        Cursor cursor = db.query(DBHelper.TABLE_POSTS,
                null, selection, selectionArgs, null, null, null);

        Post post = null;

        if(cursor.moveToFirst()){
            post = new Post(-1,
                    cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_POSTS_NAME)),
                    cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_POSTS_ORI)),
                    cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_POSTS_THUMBNAIL)),
                    cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_POSTS_ORI)));
        }

        db.close();

        return post;
    }

    public ArrayList<Post> getPosts(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_POSTS,
                null, null, null, null, null, null);

        ArrayList<Post> posts = new ArrayList<>();

        Post post;

        cursor.moveToFirst();
        do{
            post = new Post(cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_POSTS_ID)),
                    cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_POSTS_NAME)),
                    cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_POSTS_ORI)),
                    cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_POSTS_THUMBNAIL)),
                    cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_POSTS_ORI)));
            posts.add(post);
        }while(cursor.moveToNext());

        db.close();

        return posts;
    }
}
