package com.example.GTClicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;
import android.util.Log;

/**
 * User: henglish3
 * Date: 10/23/13
 * Time: 1:17 AM
 */
public class classActivity extends Activity implements LocationListener {
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude,longitude;
    protected boolean gps_enabled,network_enabled;
    public final static String EXTRA_MESSAGE = "com.example.GTClicker.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        txtLat = (TextView) findViewById(R.id.textview1);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
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

    @Override
    public void onLocationChanged(Location location) {
        txtLat = (TextView) findViewById(R.id.textview1);
        txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","status");
    }
}







