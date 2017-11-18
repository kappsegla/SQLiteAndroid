package snowroller.sqliteandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Martin on 2017-11-18.
 */

public class HighScoreAdapter extends BaseAdapter {

    private Context context;
    private List<HighScore> dataSource;
    private LayoutInflater inflater;

    public HighScoreAdapter(Context context, List<HighScore> items) {
        this.context= context;
        this.dataSource = items;
        inflater = (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dataSource.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;
        if(convertView == null) {
            v = inflater.inflate(R.layout.highscorelistview, parent, false);
        }
        else
            v = convertView;

        TextView t1 = v.findViewById(R.id.textView);
        TextView t2 = v.findViewById(R.id.textView2);
        t1.setText(dataSource.get(position).name);
        t2.setText("" + dataSource.get(position).points);
        return v;
    }
}
