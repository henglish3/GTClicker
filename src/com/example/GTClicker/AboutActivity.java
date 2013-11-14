package com.example.GTClicker;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.*;
import android.widget.Button;

/**
 * User: henglish3
 * Date: 10/22/13
 * Time: 10:22 PM
 */
public class AboutActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Button btnClose = (Button) findViewById(R.id.close);

        btnClose.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Closing SecondScreen Activity
                finish();
            }
        });
    }

}