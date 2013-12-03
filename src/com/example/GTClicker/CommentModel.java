package com.example.GTClicker;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class CommentModel {

    public String id;
    public String comment;
    public int term;

    public CommentModel(JSONObject jObject) {

        try {
            id = jObject.getString("id");
            //comment = jObject.getJSONObject("props").getString("term_eid");
            comment = jObject.getString("title");
            term = Integer.parseInt(jObject.getJSONObject("props").getString("term_eid"));
            Log.i ("myclasses",comment);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public CommentModel(JSONObject jObject, int junk) {

        try {
            id = jObject.getString("Id");
            //comment = jObject.getJSONObject("props").getString("term_eid");
            comment = jObject.getString("Comment");
            term = jObject.getInt("Term");
            Log.i ("myclasses",comment);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public JSONObject getJSONObject() {
        // TODO Auto-generated method stub
        JSONObject obj = new JSONObject();
        try {
            obj.put("Id", id);
            obj.put("Comment", comment);
            obj.put("Term", term);
        } catch (JSONException e) {
            Log.d("bah", "DefaultListItem.toString JSONException: "+e.getMessage());
        }
        return obj;
    }
    public CommentModel(String Id, String Comment, int Term) {
        term = Term;
        id = Id;
        comment = Comment;
    }
}

