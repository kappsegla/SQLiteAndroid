package snowroller.sqliteandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        dbHelper.addHighScore("Kalle",10);

        //dbHelper.getAllHighScores();
        List<HighScore> list = dbHelper.getAllWithName("Kalle");
        HighScore highScore = list.get(0);

        //highScore.points = 777;

        //dbHelper.updateHighScore(highScore);

        dbHelper.deleteHighScore(highScore);

        //For Log.d printing
        dbHelper.getAllHighScores();
        dbHelper.getHighScoreCount();
    }
}
