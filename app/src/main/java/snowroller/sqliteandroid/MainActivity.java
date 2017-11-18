package snowroller.sqliteandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> list;
    private ArrayAdapter<String> arrayAdapter;
    private FloatingActionButton floatingActionButton;

    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        //Data källa för vår listview
        list = new ArrayList<>();
        list.add("List item1");
        list.add("List item2");
        list.add("List item3");

        //Färdig adapter för ListView
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);

        //Connect listView to arrayAdapter
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener((parent, view, position, id)->
                {
                    arrayAdapter.remove(arrayAdapter.getItem(position));
                }
        );

        floatingActionButton.setOnClickListener((view)->{
            arrayAdapter.add("Hello there");
        });
    }
}
