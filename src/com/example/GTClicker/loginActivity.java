package com.example.GTClicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.net.Uri;
import android.widget.EditText;

/**
 * User: henglish3
 * Date: 10/23/13
 * Time: 1:17 AM
 */
public class loginActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.GTClicker.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnClose = (Button) findViewById(R.id.menuBtn);

        btnClose.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Closing SecondScreen Activity
                finish();
            }
        });
    }

    /** Called when the user clicks the Send button */
    public void performLogin(View view) {
        // Do something in response to button

        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dev.m.gatech.edu/login?url=GTClicker://loggedin&sessionTransfer=window"));
        startActivity(myIntent);
    }


}