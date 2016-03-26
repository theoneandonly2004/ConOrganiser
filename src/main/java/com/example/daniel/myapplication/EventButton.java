package com.example.daniel.myapplication;

import android.app.ActionBar;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Daniel on 25/03/2016.
 */
public class EventButton extends Button
{
    public Button button = this;
    public String eventHost;
    public String eventName;
    public Calendar eventDate;
    public Calendar backup;

    public EventButton(Context context, String pHost, String pName, Calendar pDate)
    {
        super(context);
        eventHost=pHost;
        eventName=pName;
        eventDate=pDate;
        setOnClick();



        this.setText(pName + "\n " + pHost);

    }

    public boolean AddButtonToScreen(LinearLayout lineaerLayout1 , LinearLayout.LayoutParams lp){

        lineaerLayout1.addView(button, lp);
        return false;
    }


    public void setOnClick() {
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int day = eventDate.get(eventDate.DAY_OF_MONTH);
                int month=eventDate.get(eventDate.MONTH);
                int year=eventDate.get(eventDate.YEAR);
                int hour=eventDate.get(eventDate.HOUR_OF_DAY);
                int minute=eventDate.get(eventDate.MINUTE);
                String minutestring=""+ minute;

                if(minutestring.length()==1){
                    minutestring = "0"+minute;
                }




                String dateOutput= day + "/" + month +"/" + year + "\n " + hour + ":" + minutestring;
                Toast.makeText(getContext(), "speaker: " + eventHost + "\n topic: " + eventName + "\n date: " + dateOutput, Toast.LENGTH_SHORT).show();
            }

        });

    }
}
