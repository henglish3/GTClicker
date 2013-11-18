package com.example.GTClicker;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.net.Uri;
import android.widget.EditText;

/**
 * User: henglish3
 * Date: 10/23/13
 * Time: 1:17 AM
 */
public class classActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.GTClicker.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        TextView tv = (TextView) findViewById(R.id.textView);
        Button btnClose = (Button) findViewById(R.id.menuBtn);
        Intent intent = getIntent();
        String className = intent.getStringExtra(ListCommentsActivity.EXTRA_MESSAGE);
        tv.setText(className);
        Bundle extras = getIntent().getExtras();
        String classID = extras.getString("classID");
        Log.i("mytagclass", classID);
        btnClose.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Closing SecondScreen Activity
                finish();
            }
        });
        }



    public void goToQuestion(View view) {
    	Intent oldIntent = getIntent();
        Intent intent = new Intent(this, questionActivity.class);
        intent.putExtra(EXTRA_MESSAGE, oldIntent.getStringExtra(ListCommentsActivity.EXTRA_MESSAGE));
        intent.putExtra("classID", oldIntent.getExtras().getString("classID"));
        startActivity(intent);
    }




}