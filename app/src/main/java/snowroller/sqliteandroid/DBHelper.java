package snowroller.sqliteandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                "points INTEGMER);";

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
        db.close();
        return  highScoreList;
    }
}
