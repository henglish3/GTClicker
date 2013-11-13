package com.example.GTClicker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);


        Button questionBtn = (Button) findViewById(R.id.questionbtn);
        //when play is clicked show stop button and hide play button
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

