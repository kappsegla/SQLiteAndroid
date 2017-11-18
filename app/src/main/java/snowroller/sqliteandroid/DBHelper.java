package snowroller.sqliteandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
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

    public HighScore addHighScore(String name, int points) {

        HighScore highScore = new HighScore();
        highScore.name = name;
        highScore.points = points;
        return addHighScore(highScore);
    }

    public HighScore addHighScore(HighScore highScore){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",highScore.name);
        contentValues.put("points", highScore.points);
        highScore.id = db.insert("Highscores",null,contentValues);
        db.close();
        return highScore;
    }

    public List<HighScore> getAllHighScores() {
        List<HighScore> highScoreList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query("Highscores",null,null,null,null,null,null);

        boolean success = c.moveToFirst();
        if( success) {
            //Loop for every row in the table
            do {
                HighScore highScore = new HighScore();
                highScore.id = c.getLong(0);
                highScore.name = c.getString(1);
                highScore.points = c.getInt(2);

                highScoreList.add(highScore);
                Log.d("SQLiteAndroid", highScore.id + ","
                        + highScore.name + "," + highScore.points);
            } while (c.moveToNext());  //Move cursor to next row. Returns false if end of data
        }
        db.close();
        return  highScoreList;
    }

    /**
     * Searches the database for all rows matching name == name
     * @param name
     * @return
     */
    public List<HighScore> getAllWithName(String name) {
        List<HighScore> highScoreList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String selection = "name=?";
        String[] selectionArgs = new String[] {name};

        Cursor c = db.query("Highscores",null,
                            selection, selectionArgs,
                            null,null,null);

        boolean success = c.moveToFirst();
        if( success) {
            //Loop for every row in the table
            do {
                HighScore highScore = new HighScore();
                highScore.id = c.getLong(0);
                highScore.name = c.getString(1);
                highScore.points = c.getInt(2);

                highScoreList.add(highScore);
                Log.d("SQLiteAndroid", highScore.id + ","
                        + highScore.name + "," + highScore.points);
            } while (c.moveToNext());  //Move cursor to next row. Returns false if end of data
        }
        db.close();
        return  highScoreList;
    }

    public boolean updateHighScore(HighScore highScore){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("name", highScore.name);
        cv.put("points", highScore.points);

        String selection = "id=?";

        String[] selectionArgs = new String[]{Long.toString(highScore.id)};

        int rows = db.update("Highscores",cv, selection, selectionArgs);

        db.close();
        return  rows == 1;
    }

    public boolean deleteHighScore(HighScore highScore)
    {
        return deleteHighScore(highScore.id);
    }

    public boolean deleteHighScore(long id)
    {
        SQLiteDatabase db = getWritableDatabase();

        String [] selectionArgs = new String[]{Long.toString(id)};

        //To remove all rows from table, set whereClause to "1"
        int result = db.delete("Highscores","id=?", selectionArgs);

        db.close();

        return result == 1;
    }

    public long getHighScoreCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long cnt = DatabaseUtils.queryNumEntries(db, "HighScores");
        db.close();
        return cnt;
    }
}

