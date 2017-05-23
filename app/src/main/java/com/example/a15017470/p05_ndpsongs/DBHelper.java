package com.example.a15017470.p05_ndpsongs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 15017470 on 23/5/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ndpsongs.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SONG = "Song";
    private static final String COL_ID = "_id";
    private static final String COL1 = "singers";
    private static final String COL2 = "stars";
    private static final String COL3 = "title";
    private static final String COL4 = "year";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Song");
        onCreate(db);
    }

    public long insertSong(String title, String singers, int year, int stars) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL1, title);
        values.put(COL2, singers);
        values.put(COL3, Integer.valueOf(year));
        values.put(COL4, Integer.valueOf(stars));
        long result = db.insert(TABLE_SONG, null, values);
        db.close();
        Log.d("SQL Insert", BuildConfig.FLAVOR + result);
        return result;
    }

    public ArrayList<Song> getAllNotes() {
        ArrayList<Song> songslist = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id,title,singers,year,stars FROM Song", null);
        if (cursor.moveToFirst()) {
            do {
                songslist.add(new Song(cursor.getInt(0), cursor.getString(DATABASE_VERSION), cursor.getString(2), cursor.getInt(3), cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songslist;
    }

    public ArrayList<Song> getAllNotesbyStars(int starsFilter) {
        ArrayList<Song> songslist = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = new String[]{COL_ID, COL1, COL2, COL3, COL4};
        String[] args = new String[DATABASE_VERSION];
        args[0] = String.valueOf(starsFilter);
        Cursor cursor = db.query(TABLE_SONG, columns, "stars>= ?", args, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                songslist.add(new Song(cursor.getInt(0), cursor.getString(DATABASE_VERSION), cursor.getString(2), cursor.getInt(3), cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songslist;
    }

    public int updateNote(Song data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL1, data.getTitle());
        values.put(COL2, data.getSingers());
        values.put(COL3, Integer.valueOf(data.getYearReleased()));
        values.put(COL4, Integer.valueOf(data.getStars()));
        String[] args = new String[DATABASE_VERSION];
        args[0] = String.valueOf(data.getId());
        int result = db.update(TABLE_SONG, values, "_id= ?", args);
        db.close();
        return result;
    }

    public int deleteNote(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] args = new String[DATABASE_VERSION];
        args[0] = String.valueOf(id);
        int result = db.delete(TABLE_SONG, "_id= ?", args);
        db.close();
        return result;
    }
}
