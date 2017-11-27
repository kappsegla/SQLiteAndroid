package snowroller.sqliteandroid;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Martin on 2017-11-26.
 */

public class HighScoreCursorAdapter extends CursorAdapter {

    private final LayoutInflater cursorInflater;

    public HighScoreCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
        cursorInflater = (LayoutInflater) 	           								   context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        //Inflate a row layout
        return cursorInflater.inflate(R.layout.highscorelistview, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //Bind data from Cursor to rowview
        TextView textView = view.findViewById(R.id.textView);
        textView.setText( cursor.getString(1) );
        TextView textView2 = view.findViewById(R.id.textView2);
        textView2.setText( Integer.toString(cursor.getInt(2)) );
    }
}
