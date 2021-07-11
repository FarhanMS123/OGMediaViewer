package id.adipati.ogmediaviewer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "OGMedia";
    private static final int DB_VERSION = 1;

    public static final String TABLE_POSTS = "posts";
    public static final String FIELD_POSTS_ID = "id";
    public static final String FIELD_POSTS_ORI = "original_url";
    public static final String FIELD_POSTS_THUMBNAIL = "thumbnail";
    public static final String FIELD_POSTS_LENGTH = "image_count";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_POSTS + " (" +
                FIELD_POSTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FIELD_POSTS_ORI + " TEXT UNIQUE," +
                FIELD_POSTS_THUMBNAIL + " TEXT," +
                FIELD_POSTS_LENGTH + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTS);
        onCreate(sqLiteDatabase);
    }

}
