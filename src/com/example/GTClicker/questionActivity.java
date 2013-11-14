package com.example.GTClicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.*;
import android.widget.TextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

/**
 * User: henglish3
 * Date: 11/1/13
 * Time: 12:22 PM
 */
public class questionActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //finds question button
        Button questionBtn = (Button) findViewById(R.id.questionbtn);

        //only shows change answer button once question has been asked
        questionBtn.setVisibility(View.GONE);

        final Button btnOpenPopup = (Button)findViewById(R.id.refreshbtn);
        btnOpenPopup.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater
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

                popupWindow.showAsDropDown(btnOpenPopup, 50, -30);

            }});
    }



    //Multiple choice answer question.
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

    //change Answer button
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

    //Note to leader method
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

