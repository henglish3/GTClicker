package com.example.GTClicker;

import java.net.URI;
import java.net.URLEncoder;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.NavUtils;
import android.view.*;
import android.widget.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONStringer;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;

/**
 * User: henglish3
 * Date: 11/1/13
 * Time: 12:22 PM
 */
public class questionActivity extends Activity {
    protected LocationManager locationManager;
    TextView txtLat;
	HttpClient client;
	String checkSub = "http://dev.m.gatech.edu/w/clicker/content/api/isopensubmissions/";
	String getAns = "http://dev.m.gatech.edu/w/clicker/content/api/myanswers/";
	String subAns = "http://dev.m.gatech.edu/w/clicker/content/api/answer/";
	//String subAns = "http://dev.m.gatech.edu/w/usercomments/content/api/comment/31";
	String classID = "";
    Location loc;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        TextView tv = (TextView) findViewById(R.id.textView);
        Intent intent = getIntent();
        String className = intent.getStringExtra(ListCommentsActivity.EXTRA_MESSAGE);
        tv.setText(className);
        Bundle extras = getIntent().getExtras();
        this.classID = extras.getString("classID");
        
       // APILocation = APILocation.concat(classID);
        //Log.i("mytagquest",APILocation);
        
        Button questionBtn = (Button) findViewById(R.id.questionbtn);
        questionBtn.setOnClickListener(null);
        
        EditText edittext = (EditText) findViewById(R.id.editText1);
        edittext.setVisibility(View.GONE);

        CheckBox gps = (CheckBox) findViewById(R.id.checkBox);
        gps.setVisibility(View.GONE);
      
        //when play is clicked show stop button and hide play button
        //questionBtn.setVisibility(View.GONE);
        questionBtn.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                
            	new submitAnswer().execute();
                

            }});

        questionBtn.setVisibility(View.GONE);

        String classID = extras.getString("classID");
        Log.i("mytagclass", classID);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        loc = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
            



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
        //HttpPost httpPut = new HttpPost(subAns);
        HttpPost httpPut = new HttpPost(subAns);
        EditText edittext = (EditText) findViewById(R.id.editText1);
        JSONStringer json = new JSONStringer()
                .object()
                .key("answer").array().value(edittext.getText().toString()).endArray()
                .key("classId").value(classID)
                .endObject();

        //StringEntity entity = new StringEntity(json.toString());
        CheckBox GPS = (CheckBox) findViewById(R.id.checkBox);
        String temp = edittext.getText().toString();
        if(GPS.isChecked()) {

            temp = temp+"@Lat:"+loc.getLatitude()+", Long:" + loc.getLongitude();

        }

        StringEntity entity = new StringEntity("answer=%5B%22" + URLEncoder.encode(temp, "ISO-8859-1") + "%22%5D&classId=" + classID);
        //StringEntity entity = new StringEntity("{\"answer\""[\"Oi\"]\",\"classId\""XLS0816104242201308.201308\"}");
        //StringEntity entity = new StringEntity("{'answer':'[\"Oi\"]','classId':'XLS0816104242201308.201308'}");

        //answer=%5B%22hi%22%5D&classId=XLS0816104242201308.201308
        entity.setContentType("application/x-www-form-urlencoded");
        entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded"));
        //entity.setContentType("application/json;charset=UTF-8");//text/plain;charset=UTF-8
        //entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
        httpPut.setEntity(entity);
        Log.i("myserverentry",entity.toString());
        httpPut.setHeader("Cookie", ListCommentsActivity.sessionName+"="+ListCommentsActivity.sessionId);
        httpPut.setHeader("X-HTTP-Method-Override","PUT");
        //httpPut.
        // Send request to WCF service

        //HttpParams para = new BasicHttpParams();
        //para.setParameter("answer", "%5B%22sent%22%5D");
        // para.setParameter("classId", classID);
        // httpPut.setParams(para);
        //httpPut.getParams().setParameter("classId", classID);
        //httpPut.getParams().setParameter("answer", "Hmmmmmm");
        //Log.i("mymy",(String) para.getParameter("answer"));
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //cURL
        HttpResponse response = httpClient.execute(httpPut);
        HttpEntity entity1 = response.getEntity();
        Log.i("mysend",EntityUtils.toString(entity));

        if(entity1 != null&&(response.getStatusLine().getStatusCode()==201||response.getStatusLine().getStatusCode()==200))
        {
            //--just so that you can view the response, this is optional--
            int sc = response.getStatusLine().getStatusCode();

            String sl = response.getStatusLine().getReasonPhrase();
            Log.i("myserver",sl);
            //Log.i("myservs",response.ge)
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
		    if ((result != "null")&&(!result.contains("\"")))
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
         CheckBox gps = (CheckBox) findViewById(R.id.checkBox);
         gps.setVisibility(View.VISIBLE);
         edittext.setVisibility(View.VISIBLE);
    	 questionBtn.setVisibility(View.VISIBLE);

    	 
    	 new prevSub().execute();
    } 
    tv.setText(message);
}




}


    //Options menu stuff
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater blowUp = getMenuInflater();
        blowUp.inflate(R.menu.rootmenu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.main:
                NavUtils.navigateUpFromSameTask(this);
                break;
            case R.id.login:
                Intent i2 = new Intent(this, Clicker.class);

                break;
            case R.id.aboutUS:
                Intent i3 = new Intent(this, AboutActivity.class);
                startActivity(i3);
                break;
            case R.id.preferences:

                break;
        }

        return false;
    }

    //Menu Button
    public void onMenuButtonClick(View view) {
        this.openOptionsMenu();

    }
}


