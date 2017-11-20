package snowroller.sqliteandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Martin on 2017-11-20.
 */

class HighScoreAdapter extends BaseAdapter {

    private List<HighScore> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public HighScoreAdapter(Context context, List<HighScore> list) {
        this.list = list;
        this.context = context;
        layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void remove(HighScore highScore)
    {
        list.remove(highScore);
        notifyDataSetChanged();
    }

    public void add(HighScore highScore)
    {
        list.add(highScore);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        if( convertView == null)
        {
            view = layoutInflater.inflate(R.layout.highscorelistview,
                    parent,false);
        }
        else
        {
            view = convertView;
        }

        TextView textView = view.findViewById(R.id.textView);
        textView.setText( list.get(position).name );
        TextView textView2 = view.findViewById(R.id.textView2);
        textView2.setText( Integer.toString( list.get(position).points ) );

        return view;
    }
}









