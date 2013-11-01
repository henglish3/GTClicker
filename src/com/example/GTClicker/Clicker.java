package com.example.GTClicker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;

public class Clicker extends Activity {


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }


    public void goToAbout(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
    public void goToLogin(View view) {
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }

    public void goToMenu(View view) {
        super.onBackPressed();
    }
}
