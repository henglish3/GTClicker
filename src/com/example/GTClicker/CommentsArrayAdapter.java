package com.example.GTClicker;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CommentsArrayAdapter extends ArrayAdapter<CommentModel> {
    private final Context context;
    ArrayList<CommentModel> comment_list;

    public CommentsArrayAdapter(Context context, ArrayList<CommentModel> comment_list) {
        super(context, R.layout.list_comments, comment_list);
        this.context = context;
        this.comment_list = comment_list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_comments, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.label);

        CommentModel comment = this.comment_list.get(position);

        if(comment != null){
            textView.setText(comment.comment);
        }

        return rowView;
    }

}