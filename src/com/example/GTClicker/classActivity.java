package com.example.GTClicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
        }



    public void goToQuestion(View view) {
        Intent intent = new Intent(this, questionActivity.class);
        startActivity(intent);
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
                Intent i2 = new Intent(this, loginActivity.class);
                startActivity(i2);
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