

package com.example.GTClicker;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

/**
 * User: henglish3
 * Date: 11/1/13
 * Time: 12:22 PM
 */
public class questionActivity extends Activity {
	HttpClient client;
	String checkSub = "http://dev.m.gatech.edu/w/clicker/content/api/isopensubmissions/";
	String getAns = "http://dev.m.gatech.edu/w/clicker/content/api/myanswers/";
	String subAns = "http://dev.m.gatech.edu/w/clicker/content/api/answer/";
	//String subAns = "http://dev.m.gatech.edu/w/usercomments/content/api/comment/31";
	String classID = "";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        TextView tv = (TextView) findViewById(R.id.textView);
        Intent intent = getIntent();
        String className = intent.getStringExtra(classActivity.EXTRA_MESSAGE);
        tv.setText(className);
        Bundle extras = getIntent().getExtras();
        this.classID = extras.getString("classID");
        
       // APILocation = APILocation.concat(classID);
        //Log.i("mytagquest",APILocation);
        
        Button questionBtn = (Button) findViewById(R.id.questionbtn);
        questionBtn.setOnClickListener(null);
        
        EditText edittext = (EditText) findViewById(R.id.editText1);
        edittext.setVisibility(View.GONE);
      
        //when play is clicked show stop button and hide play button
        //questionBtn.setVisibility(View.GONE);
        questionBtn.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                
            	new submitAnswer().execute();
                

            }});
        questionBtn.setVisibility(View.GONE);
            

        Button btnClose = (Button) findViewById(R.id.menuBtn);

        btnClose.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Closing SecondScreen Activity
                finish();
            }
        });

        final Button btnOpenPopup = (Button)findViewById(R.id.refreshbtn);
        btnOpenPopup.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                /*LayoutInflater layoutInflater
                        = (LayoutInflater)getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.popup, null);
                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT);

                Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
                btnDismiss.setOnClickListener(new Button.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Button questionBtn = (Button) findViewById(R.id.questionbtn);
                        questionBtn.setVisibility(View.VISIBLE);
                        // TODO Auto-generated method stub
                        popupWindow.dismiss();
                    }});

                popupWindow.showAsDropDown(btnOpenPopup, 50, -30);*/
            	new IsOpen().execute();
                

            }});
        new IsOpen().execute();
        
    }
    
	public class submitAnswer extends AsyncTask<String, Void, String> {
int progress_status;




@Override
protected String doInBackground(String... params) {
	
	String open = "";
	client = new DefaultHttpClient();
	
	try {
			String str = "";
		    //--This code works for updating a record from the feed--
		    HttpPut httpPut = new HttpPut(subAns);
		    EditText edittext = (EditText) findViewById(R.id.editText1);
		    JSONStringer json = new JSONStringer()
		    .object() 
		       .key("answer").array().value(edittext.getText().toString()).endArray()
		       .key("classId").value(classID)
		    .endObject();

		    StringEntity entity = new StringEntity(json.toString());
		    //StringEntity entity = new StringEntity("answer=%5B%22workpls%22%5D&classId=XLS0816104242201308.201308");
		    //StringEntity entity = new StringEntity("{\"answer\":\"[\"Oi\"]\",\"classId\":\"XLS0816104242201308.201308\"}");
		    Log.i("myserverentry",entity.toString());
		    //entity.setContentType("application/x-www-form-urlencoded");
		    //entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded"));
		    entity.setContentType("application/json;charset=UTF-8");//text/plain;charset=UTF-8
		    entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
		    httpPut.setEntity(entity); 
		    httpPut.setHeader("Cookie", ListCommentsActivity.sessionName+"="+ListCommentsActivity.sessionId);

		    // Send request to WCF service 
		    DefaultHttpClient httpClient = new DefaultHttpClient();
		    
		    HttpResponse response = httpClient.execute(httpPut);                     
		    HttpEntity entity1 = response.getEntity(); 
		    Log.i("mysend",EntityUtils.toString(entity));

		    if(entity1 != null&&(response.getStatusLine().getStatusCode()==201||response.getStatusLine().getStatusCode()==200))
		    {
		         //--just so that you can view the response, this is optional--
		         int sc = response.getStatusLine().getStatusCode();
		         
		         String sl = response.getStatusLine().getReasonPhrase();
		         Log.i("myserver",sl);
		         Log.i("myserverresp",EntityUtils.toString(entity1));
		    }
		    else
		    {
		         int sc = response.getStatusLine().getStatusCode();
		         String sl = response.getStatusLine().getReasonPhrase();
		         Log.i("myserver",sl);
		    }
		
		
		
		
		open = str;
		
		
	} catch (Exception e) {
		Log.e("log_tag", "Error in http connection: " + e.toString());
		e.printStackTrace();
	}
	

	return open;
	

}


@Override
protected void onCancelled() {

	super.onCancelled();
}
@Override
protected void onPostExecute(String result) {
	super.onPostExecute(result);
	new IsOpen().execute();
}




}
	public class prevSub extends AsyncTask<String, Void, String> {
		int progress_status;




		@Override
		protected String doInBackground(String... params) {
			
			String sub = "";
			client = new DefaultHttpClient();
			
			try {
				URI api = new URI(getAns.concat(classID));
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
				
				request.setHeader("Cookie", ListCommentsActivity.sessionName+"="+ListCommentsActivity.sessionId);
				
				HttpResponse response = client.execute(request);
				
				HttpEntity entity = response.getEntity();
				String str = EntityUtils.toString(entity);
				//String str = "{\"entityPrefix\": \"membership\", \"membership_collection\":[{\"id\":\"1\",\"locationReference\":\"hi\"},{\"id\":\"2\",\"locationReference\":\"ok\"}]}";
				//String str = "{\"entityPrefix\": \"membership\", \"membership_collection\":\"hi\"}";
				Log.i ("mysubopen",str.substring(6,str.length()-4));
				
				sub = str.substring(6,str.length()-4);
				
				
			} catch (Exception e) {
				Log.e("log_tag", "Error in http connection: " + e.toString());
				e.printStackTrace();
			}

			return sub;
			

		}
		@Override
		protected void onCancelled() {

			super.onCancelled();
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			TextView tv = (TextView) findViewById(R.id.prevSub);
			String message = "No Previous Submission";
			Log.i("mysubstill",result);
		    if (result != "null")
		    {
		    	 message = "Previous Submission: ";
		    	 message = message.concat(result);
		    } 
		    tv.setText(message);
		}
	}
	public class IsOpen extends AsyncTask<String, Void, Integer> {
int progress_status;




@Override
protected Integer doInBackground(String... params) {
	
	int open = 0;
	client = new DefaultHttpClient();
	
	try {
		URI api = new URI(checkSub.concat(classID));
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
		
		request.setHeader("Cookie", ListCommentsActivity.sessionName+"="+ListCommentsActivity.sessionId);
		
		HttpResponse response = client.execute(request);
		
		HttpEntity entity = response.getEntity();
		String str = EntityUtils.toString(entity);
		//String str = "{\"entityPrefix\": \"membership\", \"membership_collection\":[{\"id\":\"1\",\"locationReference\":\"hi\"},{\"id\":\"2\",\"locationReference\":\"ok\"}]}";
		//String str = "{\"entityPrefix\": \"membership\", \"membership_collection\":\"hi\"}";
		Log.i ("mysubopen",str);
		Log.i("mysubopenagain",str.substring(3,4));
		open = Integer.parseInt(str.substring(3,4));
		
		
	} catch (Exception e) {
		Log.e("log_tag", "Error in http connection: " + e.toString());
		e.printStackTrace();
	}
	

	return open;
	

}


@Override
protected void onCancelled() {

	super.onCancelled();
}
@Override
protected void onPostExecute(Integer result) {
	super.onPostExecute(result);
	TextView tv = (TextView) findViewById(R.id.textView1);
	String message = "Submissions are closed.";
	Log.i("mysubopenstill",Integer.toString(result));
    if (result == 1)
    {
    	 message = "Submissions are open.";
    	 Button questionBtn = (Button) findViewById(R.id.questionbtn);
    	 EditText edittext = (EditText) findViewById(R.id.editText1);
         edittext.setVisibility(View.VISIBLE);
    	 questionBtn.setVisibility(View.VISIBLE);
    	 
    	 new prevSub().execute();
    } 
    tv.setText(message);
}




}
public void onRadioButtonClicked(View view) {

        TextView t1 =(TextView)findViewById(R.id.textView1);
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.A:
                if (checked)
                    // Pirates are the best
                    t1.setText("You answered A.");
                    break;
            case R.id.B:
                if (checked)
                    // Ninjas rule
                    t1.setText("You answered B.");
                    break;
            case R.id.C:
                if (checked)
                    // Pirates are the best
                    t1.setText("You answered C.");
                    break;
            case R.id.D:
                if (checked)
                    // Ninjas rule
                    t1.setText("You answered D.");
                    break;
        }
    }

    public void changeAnswer(View v) {
        Button btnOpenPopup = (Button)findViewById(R.id.questionbtn);
        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.popup, null);
        final PopupWindow popupWindow = new PopupWindow(
            popupView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

            Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
            btnDismiss.setOnClickListener(new Button.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        popupWindow.dismiss();
                    }});

                popupWindow.showAsDropDown(btnOpenPopup, 50, -30);

    }
    public void note(View v) {
        Button btnOpenPopup = (Button)findViewById(R.id.ntlBtn);
        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.popup_ntl, null);
        final PopupWindow popupWindow = new PopupWindow(
                popupView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
        btnDismiss.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                popupWindow.dismiss();
            }});

        popupWindow.showAsDropDown(btnOpenPopup, 50, -30);

    }
}


