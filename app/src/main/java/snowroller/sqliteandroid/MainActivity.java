package snowroller.sqliteandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> list;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        //Data källa för vår listview
        list = new ArrayList<>();
        list.add("List item1");
        list.add("List item2");
        list.add("List item3");
        list.add("List item4");  //Just added

        //Färdig adapter för ListView
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);

        //Connect listView to arrayAdapter
        listView.setAdapter(arrayAdapter);


//        DBHelper dbHelper = new DBHelper(this);
//        dbHelper.addHighScore("Kalle",10);
//
//        //dbHelper.getAllHighScores();
//        List<HighScore> list = dbHelper.getAllWithName("Kalle");
//        HighScore highScore = list.get(0);
//
//        //highScore.points = 777;
//
//        //dbHelper.updateHighScore(highScore);
//
//        dbHelper.deleteHighScore(highScore);
//
//        //For Log.d printing
//        dbHelper.getAllHighScores();
//        dbHelper.getHighScoreCount();
    }

    public void onButtonClick(View v) {

        //Alt 1:
        //list.add("Another text");
        //arrayAdapter.notifyDataSetChanged();
        //Alt 2:
        arrayAdapter.add("Add this");

    }
}
