package com.example.GTClicker;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;

public class Clicker extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /** Called when the user clicks the Send button */
    public void performLogin(View view) {
        // Do something in response to button

        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dev.m.gatech.edu/login/private?url=gtclicker://loggedin&sessionTransfer=window"));
        startActivity(myIntent);
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
	//public final static String EXTRA_MESSAGE = "com.example.GTClicker.MESSAGE";
    /**
     * Called when the activity is first created.
     */
    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }


    public void goToLogin(View view) {
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }
    public void goToClass(View view) {
        Intent intent = new Intent(this, classActivity.class);
        startActivity(intent);
    }
    public void goToAbout(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }



    public void goToMenu(View view) {
        super.onBackPressed();
    }   */
}
