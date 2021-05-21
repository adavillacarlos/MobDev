package com.example.villacarlos.movielibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "moviedb";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "myLibrary";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "movie_title";
    private static final String COLUMN_YEAR = "movie_year";
    private static final String COLUMN_DIRECTOR="movie_director";
    private static final String COLUMN_SUMMARY ="movie_summary";



    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_YEAR + " INT, " +
                        COLUMN_DIRECTOR + " TEXT, " +
                        COLUMN_SUMMARY + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
    }

    public boolean addMovie(Movie movie){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, movie.getTitle());
        cv.put(COLUMN_YEAR, movie.getYear());
        cv.put(COLUMN_DIRECTOR, movie.getDirector());
        cv.put(COLUMN_SUMMARY, movie.getSummary());

        long insert = db.insert(TABLE_NAME, null, cv);

        if(insert !=-1){
            return true;
        } else{
            return false;
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db!=null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    public boolean updateData(String row_id,  String title, String year, String director, String summary){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_YEAR, year);
        cv.put(COLUMN_DIRECTOR, director);
        cv.put(COLUMN_SUMMARY, summary);
        long result = db.update(TABLE_NAME,cv,"_id=?",new String[]{row_id});
        if(result == -1){
            return false;
        } else{
            return true;
        }
    }

    public boolean deleteRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long delete = db.delete(TABLE_NAME,"_id=?",new String[]{row_id});
        if(delete == -1){
            return false;
        } else
            return true;
    }


    public boolean deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        return true;
    }


}
