package snowroller.sqliteandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private HighScoreAdapter adapter;
    private List<HighScore> list;
    private FloatingActionButton floatingActionButton;

    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        //Data källa för vår listview
        list = dbHelper.getAllHighScores();

        //Custom adapter för ListView
        adapter = new HighScoreAdapter(this,list);

        //Connect listView to arrayAdapter
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id)->
                {
                    HighScore highScore = (HighScore)adapter.getItem(position);
                    dbHelper.deleteHighScore(highScore);
                    list.remove(highScore);
                    adapter.notifyDataSetChanged();
                }
        );

        floatingActionButton.setOnClickListener((view)->{
           HighScore highScore = dbHelper.addHighScore("Test", 100);
           list.add(highScore);
           adapter.notifyDataSetChanged();
        });
    }
}
