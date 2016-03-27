package com.example.daniel.myapplication;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
static int count=0;

    EventButton[] buttons = new EventButton[5];
    public void createButton(){

        final Button button = new Button(this.getBaseContext());
        button.setText("push me");
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "hello world, button " + count + " was pressed", Toast.LENGTH_LONG).show();
                createButton();
            }

        });
        count++;
        LinearLayout ll = (LinearLayout) findViewById(R.id.LinearLayout1);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll.addView(button, lp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        buttons[0]=new EventButton(this.getBaseContext(),"Daniel Boyd","how to be awesome",new GregorianCalendar(2016,05,01,23,30));

        buttons[1]=new EventButton(this.getBaseContext(),"Kate","cosplay masquerade",new GregorianCalendar(2016,05,01,17,00));

        buttons[2]=new EventButton(this.getBaseContext(),"Stephan","how to make cool stuff",new GregorianCalendar(2016,04,31,13,30));


        buttons[3]=new EventButton(this.getBaseContext(),"Lisa","sewing for beginners",new GregorianCalendar(2016,04,31,15,30));

        buttons[4]=new EventButton(this.getBaseContext(),"kate","mandatory jam tart praising session",new GregorianCalendar(2016,04,31,12,00));
        //button.AddButtonToScreen();

        LinearLayout ll = (LinearLayout) findViewById(R.id.LinearLayout1);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for(int count=0;count < buttons.length;count++){
            if(buttons[count] != null) {
                buttons[count].AddButtonToScreen(ll, lp);
            }
        }



        }
    }


