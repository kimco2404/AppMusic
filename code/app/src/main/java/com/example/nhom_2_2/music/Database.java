package com.example.nhom_2_2.music;

/**
 * Created by kimcodev on 3/7/2017.
 */
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Database extends SQLiteOpenHelper{
    static int DB_VERSION = 1;
    static String DB_NAME = "music.db";
    static SQLiteDatabase db = null;
    Context context;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sqlQuery1 = "CREATE TABLE music(Music_patch TEXT PRIMARY KEY, Music_name TEXT, Music_folderName)";
        db.execSQL(sqlQuery1);
    }

    boolean isDatabaseExist() {
        return (db != null);
    }

    void createOrOpenDatabase() {
        // if(!isDatabaseExist()){
        db = getWritableDatabase();
        // Log.d("test", "create DB");
        // }
    }
    //write music, update and delete and find and read
    long writeMusic(Music music) {
        this.createOrOpenDatabase();
        ContentValues values = new ContentValues();
        values.put("Music_patch", music.getPatch());
        values.put("Music_name", music.getMusic_name());
        values.put("Music_folderName",music.getFolder_name());
        long ok = db.insert("music", null, values);
        return ok;
    }


    public void writeMusics(ArrayList<Music> musics) {
        createOrOpenDatabase();
        boolean ok = true;

        for (Music amusic : musics) {
            long position = writeMusic(amusic);
            if (position == -1)
                ok = false;
        }

        if (ok) {
            Toast.makeText(context,
                    "All of Songs are saved on the Database",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(
                    context,
                    "Some of Songs are saved on the Database, some are not!",
                    Toast.LENGTH_LONG).show();
        }

        this.close();
    }

    public void readMusics(ArrayList<Music> musics) {
        createOrOpenDatabase();
        String query = "select * from music"; /// vkl vkl

        Cursor cur = db.rawQuery(query, null);
      //  Toast.makeText(context,cur.toString(),Toast.LENGTH_LONG).show();
        if (cur.moveToFirst()) {
            do {
                Music mMusic = new Music();
                mMusic.setPatch(cur.getString(cur
                        .getColumnIndex("Music_patch")));
                mMusic.setMusic_name(cur.getString(cur
                        .getColumnIndex("Music_name")));
                musics.add(mMusic);

            } while (cur.moveToNext());

        }
       // Toast.makeText(context,"null",Toast.LENGTH_LONG).show();
        this.close();
    }
    //read music new song
    public void readMusicsNewsong(ArrayList<Music> musics) {
        createOrOpenDatabase();
        String query = "select * from music"; /// vkl vkl

        Cursor cur = db.rawQuery(query, null);
        //  Toast.makeText(context,cur.toString(),Toast.LENGTH_LONG).show();
        if (cur.moveToLast()) {
            do {
                Music mMusic = new Music();
                mMusic.setPatch(cur.getString(cur
                        .getColumnIndex("Music_patch")));
                mMusic.setMusic_name(cur.getString(cur
                        .getColumnIndex("Music_name")));
                musics.add(mMusic);

            } while (cur.moveToPrevious());

        }
        this.close();

    }

    public long updateMusic(Music old, Music newvalue) {
        createOrOpenDatabase();
        ContentValues values = new ContentValues();
        values.put("Music_patch", newvalue.getPatch());
        values.put("Music_name", newvalue.getMusic_name());
        values.put("Music_folderName",newvalue.getFolder_name());
        long ok = db.update("music", values, "Music_patch = ?",
                new String[] { old.getPatch() });
        if (ok != -1) {
            Toast.makeText(context, "The student is updated on the Database",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context,
                    "The student is NOT updated on the Database",
                    Toast.LENGTH_LONG).show();
        }
        this.close();
        return ok;
    }
    public void deleteMusic(String patch) {
        createOrOpenDatabase();
        db.delete("music", "Music_patch = ?", new String[] { patch });
        this.close();
    }

    public Music findMusic(Music music) {
        Music mMusic = new Music();
        createOrOpenDatabase();

        String query = "Select * from music where Music_name =" + "\""
                + music.getMusic_name() + "\"";
        Cursor cur = db.rawQuery(query, null);
        if (cur.moveToFirst()) {
            // do{
            mMusic.setMusic_name(cur.getString(cur.getColumnIndex("Music_name")));
        }

        this.close();
        return mMusic;
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }


}
