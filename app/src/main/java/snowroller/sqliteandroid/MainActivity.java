package snowroller.sqliteandroid;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private HighScoreAdapter adapter;
    private List<HighScore> list;
    private FloatingActionButton floatingActionButton;

    DBHelper dbHelper = new DBHelper(this);
    private HighScoreCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        //setupListAdapter();
        setupCursorAdapter();
    }

    private void setupCursorAdapter() {
        //Data källa för vår listview
        Cursor c = dbHelper.getAllHighScoresCursor();

        cursorAdapter = new HighScoreCursorAdapter(this, c);

        listView.setAdapter(cursorAdapter);

        listView.setOnItemClickListener((parent, view, position, id)->
                {
                   // dbHelper.deleteHighScore(id);
                   // cursorAdapter.changeCursor(dbHelper.getAllHighScoresCursor());
                    ImageView imageView = view.findViewById(R.id.imageView);

                   DownloadImageTask task = new DownloadImageTask(imageView);
                   task.execute("https://findicons.com/files/icons/385/game_stars/256/mario.png");
                }
        );

        floatingActionButton.setOnClickListener((view)->{
            HighScore highScore = dbHelper.addHighScore("Test", 100);
            cursorAdapter.changeCursor(dbHelper.getAllHighScoresCursor());
        });

    }

    private void setupListAdapter()
    {
        //Data källa för vår listview
        list = dbHelper.getAllHighScores();

        //Custom adapter för ListView
        adapter = new HighScoreAdapter(this, list);

        //Connect listView to arrayAdapter
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id)->
                {
                    HighScore highScore = (HighScore)adapter.getItem(position);
                    dbHelper.deleteHighScore(highScore);
                    adapter.remove(highScore);
                }
        );

        floatingActionButton.setOnClickListener((view)->{
            HighScore highScore = dbHelper.addHighScore("Test", 100);
            adapter.add(highScore);
        });
    }
}
