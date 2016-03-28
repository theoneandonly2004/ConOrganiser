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
import java.util.TimeZone;

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
    private LinearLayout screen;

    public EventButton(Context context, String pHost, String pName, Calendar pDate)
    {
        super(context);
        eventHost=pHost;
        eventName=pName;
        eventDate=pDate;
        setOnClick();



        this.setText(pName + "\n " + pHost);

    }

    public int getTimeDifference(){
        Calendar currentTime=Calendar.getInstance();
        int difference =0;

        int startHour = eventDate.get(eventDate.HOUR_OF_DAY);
        int currentHour = currentTime.get(currentTime.HOUR_OF_DAY);

        int startMinute = eventDate.get(eventDate.MINUTE);
        int currentMinute = currentTime.get(currentTime.MINUTE);

        int overallStart = (startHour *60) + startMinute;
        int overallCurrent = (currentHour * 60) + currentMinute;


        int overall = overallStart - overallCurrent;
        //Log.d("current Time", currentTime.toString());

        Toast.makeText(getContext(), "time until event " + eventName + " is " + overall + " minutes away", Toast.LENGTH_SHORT).show();


        return overall;
    }

    public boolean AddButtonToScreen(LinearLayout lineaerLayout1 , LinearLayout.LayoutParams lp){
        screen = lineaerLayout1;
        lineaerLayout1.addView(button, lp);
        return false;
    }

    public void RemoveButtonFromScreen(){
        if(screen != null){
            Log.d("screen removal", "the button has been removed");
            screen.removeView(button);
            screen = null;
        }
        else{
            Log.e("nothing to delete ","the specified variable does not exist");
        }

    }

    public boolean GetIsEventToday(){
        Calendar currentDate = Calendar.getInstance();

        int currentDay = currentDate.get(currentDate.DAY_OF_MONTH);
        int currentMonth = currentDate.get(currentDate.MONTH);
        int currentYear = currentDate.get(currentDate.YEAR);

        int eventDay = eventDate.get(eventDate.DAY_OF_MONTH);
        int eventMonth = eventDate.get(eventDate.MONTH);
        int eventYear = eventDate.get(eventDate.YEAR);

        if(currentYear == eventYear){
            if(currentMonth == eventMonth){
                if(currentDay == eventDay){
                    return true;
                }
            }
        }
        return false;
    }


    public void setOnClick() {
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                FileManager.writeToFile(new Event(eventHost,eventName,eventDate),getContext());

                FileManager.readFromFile(getContext());
              /*  int timeTilEvent =getTimeDifference();
                int day = eventDate.get(eventDate.DAY_OF_MONTH);
                int month=eventDate.get(eventDate.MONTH);
                int year=eventDate.get(eventDate.YEAR);
                int hour=eventDate.get(eventDate.HOUR_OF_DAY);
                int minute=eventDate.get(eventDate.MINUTE);
                String minutestring=""+ minute;

                if(minutestring.length()==1){
                    minutestring = "0"+minute;
                }


                if(timeTilEvent <= 15 && timeTilEvent > 0){
                    Log.d("timeTilEvent " , "there is " + timeTilEvent + " minutes until the event " + eventName);
                }
                else if(timeTilEvent <= -15  && GetIsEventToday() == true){
                    Toast.makeText(getContext(), "sorry you have missed the event " + eventName, Toast.LENGTH_SHORT).show();
                    Log.d("timeTilEvent", "you missed the event " + eventName);
                    RemoveButtonFromScreen();
                }


                String dateOutput= day + "/" + month +"/" + year + "\n " + hour + ":" + minutestring;
                Toast.makeText(getContext(), "speaker: " + eventHost + "\n topic: " + eventName + "\n date: " + dateOutput, Toast.LENGTH_SHORT).show();*/
            }

        });

    }
}
