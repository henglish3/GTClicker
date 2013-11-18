package com.example.GTClicker;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CommentsArrayAdapter extends ArrayAdapter<CommentModel> {
    private final Context context;
    static ArrayList<CommentModel> comment_list;

    public CommentsArrayAdapter(Context context, ArrayList<CommentModel> comment_list) {
        super(context, R.layout.list_comments, comment_list);
        this.context = context;
        CommentsArrayAdapter.comment_list = comment_list;
        
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_comments, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.label);

        CommentModel comment = CommentsArrayAdapter.comment_list.get(position);

        if(comment != null){
            textView.setText(comment.comment);
            //rowView.setOnClickListener(onclicklistener);
        }

        return rowView;
    }
    public void makeClass(View view) {
        Log.i("mytag","this one");
        Context mycontext = this.getContext();
        Intent myint = new Intent(mycontext, classActivity.class);
        mycontext.startActivity(myint);
    }
   /* OnClickListener onclicklistener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
        	//Context mContext = context.getApplicationContext();
        	Log.i("mytag","this one");
        	Intent myint = new Intent(v.getContext(), classActivity.class);
        	v.getContext().startActivity(myint);
        	//
        	//makeClass(v);
        }
    };*/

}