package snowroller.sqliteandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 2017-11-08.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, "MyDataBase", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE Highscores ( id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "points INTEGER);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addHighScore(String name, int points) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("points", points);
        long id = db.insert("Highscores",null,contentValues);
        db.close();
    }

    public List<HighScore> getAllHighScores() {
        List<HighScore> highScoreList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
            //TODO: get all entries from database and put in highScoreList
        Cursor c = db.query("Highscores",null,null,null,null,null,null);

        boolean success = c.moveToFirst();
        if( !success)
            return highScoreList;  //Returns an empty list. Found nothing in database

        //Loop for every row in the table
        do {
            HighScore highScore = new HighScore();
            highScore.id = c.getLong(0);
            highScore.name = c.getString(1);
            highScore.points = c.getInt(2);

            highScoreList.add(highScore);
            Log.d("SQLiteAndroid",highScore.id  + ","
                    + highScore.name +","+ highScore.points);
        }while(c.moveToNext());  //Move cursor to next row. Returns false if end of data

        db.close();
        return  highScoreList;
    }
}
