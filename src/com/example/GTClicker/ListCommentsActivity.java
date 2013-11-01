package com.example.GTClicker;


import java.net.URI;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ListCommentsActivity extends ListActivity {
    HttpClient client;
    ArrayList<CommentModel> listItems;
    ProgressDialog progressDialog;
    private final String apiLocation = "http://dev.m.gatech.edu/w/clicker/c/api/sites/";
    //private final String apiLocation = "http://dev.m.gatech.edu/d/mfogg3/w/GTNav/content/api/comment/";

    String sessionName;
    String sessionId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        // To get the action of the intent use
        String action = intent.getAction();

        if (!action.equals(Intent.ACTION_VIEW)) {
            throw new RuntimeException("Should not happen");
        }
        // To get the data use
        Uri data = intent.getData();
        sessionName = data.getQueryParameter("sessionName");
        sessionId = data.getQueryParameter("sessionId");

        // SHOULD DO VALIDATION HERE

        new FetchCommentsTask().execute();

        // TODO Auto-generated method stub
    }

    public class FetchCommentsTask extends
            AsyncTask<String, Integer, ArrayList<CommentModel>> {
        int progress_status;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            progressDialog = new ProgressDialog(ListCommentsActivity.this);
            progressDialog.setCancelable(true);
            progressDialog.setMessage("Loading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.show();
            progress_status = 0;

        }

        @Override
        protected ArrayList<CommentModel> doInBackground(String... params) {

            listItems = new ArrayList<CommentModel>();
            client = new DefaultHttpClient();
            while (progress_status < 100) {

                progress_status += 1;

                publishProgress(progress_status);

            }
            try {
                URI api = new URI(apiLocation);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(api);
				
				/*
				CookieStore cookieStore = new BasicCookieStore(); 
				BasicClientCookie cookie = new BasicClientCookie(sessionName, sessionId);
				cookieStore.addCookie(cookie); 
				
				HttpContext localContext = new BasicHttpContext();
				localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
				
				HttpResponse response = client.execute(request, localContext);
				*/

                request.setHeader("Cookie", sessionName+"="+sessionId);

                HttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();
                String str = EntityUtils.toString(entity);
                //String str = "{\"entityPrefix\": \"membership\", \"membership_collection\":[{\"id\":\"1\",\"locationReference\":\"hi\"},{\"id\":\"2\",\"locationReference\":\"ok\"}]}";
                //String str = "{\"entityPrefix\": \"membership\", \"membership_collection\":\"hi\"}";
                Log.i ("myinfo",str);


                try {
                    JSONObject JsonObj = new JSONObject(str);
                    JSONArray JsonArrayForResult = JsonObj.getJSONArray("site_collection");
                    Log.i ("myentry",JsonArrayForResult.toString());
                    int i = 0;
                    int x = 0;
                    int max = 0;


                    for (i = 0; i < JsonArrayForResult.length(); i++) {
                        JSONObject jsonObject = JsonArrayForResult.getJSONObject(i);
                        listItems.add(new CommentModel(jsonObject));

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection: " + e.toString());
                e.printStackTrace();
            }

            return listItems;

        }

        @Override
        protected void onPostExecute(ArrayList<CommentModel> result) {

            super.onPostExecute(result);
            Message message = new Message();
            handler.sendMessage(message);

        }

        private Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                super.handleMessage(msg);
                CommentsArrayAdapter myAdapter = new CommentsArrayAdapter(getApplicationContext(),listItems);
                setListAdapter(myAdapter);
                progressDialog.dismiss();
                myAdapter.setNotifyOnChange(true);
            }
        };

        @Override
        protected void onCancelled() {

            super.onCancelled();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

    }
}
