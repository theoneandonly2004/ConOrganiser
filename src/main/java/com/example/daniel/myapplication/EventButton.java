package com.example.daniel.myapplication;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
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
    public Event event;
    public String eventHost;
    public String eventName;
    public String eventLocation;
    public Calendar eventDate;
    private LinearLayout screen;
    private int startingColor;

    int day;
    int month;
    int year;
    int hour;
    int minute;
    String dateOutput;

    public EventButton(Context context , Event pEvent){
        super(context);
        eventDate = pEvent.eventDate;
        eventName = pEvent.eventName;
        eventHost = pEvent.eventHost;
        eventLocation = pEvent.eventLocation;
        event = pEvent;
        startingColor =button.getDrawingCacheBackgroundColor();

        day = eventDate.get(eventDate.DAY_OF_MONTH);
        month = eventDate.get(eventDate.MONTH);
        minute = eventDate.get(eventDate.MINUTE);
        hour = eventDate.get(eventDate.HOUR_OF_DAY);
        year = eventDate.get(eventDate.YEAR);

        String minutestring = "" + minute;

        if (minutestring.length() == 1) {
            minutestring = "0" + minute;
        }
        dateOutput = day + "/" + month + "/" + year + "  " + hour + ":" + minutestring;

        if(FileManager.compare(event)){
            button.setBackgroundColor(Color.RED);
            button.setText(eventName + "\n " + eventHost + "\n" + eventLocation + "\n" + dateOutput);
        }
        else{
            removeInterest(false);
        }

        setOnClick();


    }

    public EventButton(Context context, String pHost, String pName, Calendar pDate, String pEventLocation)
    {
        super(context);
        eventHost=pHost;
        eventName=pName;
        eventDate=pDate;
        eventLocation = pEventLocation;
        event = new Event(pHost, pName, pEventLocation, pDate);


        day = eventDate.get(eventDate.DAY_OF_MONTH);
        month = eventDate.get(eventDate.MONTH);
        minute = eventDate.get(eventDate.MINUTE);
        hour = eventDate.get(eventDate.HOUR_OF_DAY);
        year = eventDate.get(eventDate.YEAR);

        String minutestring = "" + minute;

        if (minutestring.length() == 1) {
            minutestring = "0" + minute;
        }
        dateOutput = day + "/" + month + "/" + year + "  " + hour + ":" + minutestring;

        if(FileManager.compare(event)){
            button.setBackgroundColor(Color.RED);
            button.setText(eventName + "\n " + eventHost + "\n" + eventLocation + "\n" + dateOutput);
        }
        else{
            removeInterest(false);
        }

        setOnClick();





    }

    public void removeInterest(boolean shouldUpdateFile){
        if(shouldUpdateFile) {
            FileManager.clearDuplicates(event);
            FileManager.eventList.remove(event);
            FileManager.updateFile(getContext());
        }
        button.setBackgroundResource(android.R.drawable.btn_default);
        button.setText(eventName + "\n " + eventHost);
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

        //Toast.makeText(getContext(), "time until event " + eventName + " is " + overall + " minutes away", Toast.LENGTH_SHORT).show();


        return overall;
    }

    public boolean AddButtonToScreen(){
        screen = MainActivity.linearLayout;
        MainActivity.linearParams.topMargin = 3;
        MainActivity.linearParams.bottomMargin=3;
        MainActivity.linearLayout.addView(button, MainActivity.linearParams);
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

    public boolean GetIsEventToday(int year , int month , int day){
        int currentDay = day;
        int currentMonth = month + 1;
        int currentYear = year;

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

        Log.d("date provided" , currentDay +"/" + currentMonth + "/" + currentYear);
        Log.d("event date" , eventDay + "/" + eventMonth + "/" + eventYear);
        return false;
    }


    public void setOnClick() {
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                boolean isFound=FileManager.compare(event);

                if (!isFound) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Event:" + eventName + "\nHost:" + eventHost + "\nLocation: " + eventLocation + "\nWhen: " + dateOutput);
                    builder.setCancelable(true);

                    builder.setPositiveButton(
                            "Add Interest",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    FileManager.writeToFile(new Event(eventHost, eventName, eventLocation, eventDate), getContext());
                                    FileManager.readFromFile(getContext());
                                    button.setBackgroundColor(Color.RED);
                                    button.setText(eventName + "\n " + eventHost + "\n" + eventLocation + "\n" + dateOutput);

                                }
                            });

                    builder.setNegativeButton(
                            "Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert1 = builder.create();
                    alert1.show();
                }
                else if (isFound) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Event:" + eventName + "\nHost:" + eventHost + "\nLocation: " + eventLocation + "\nWhen: " + dateOutput);
                    builder.setCancelable(true);


                    builder.setPositiveButton(
                            "Remove Interest",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    removeInterest(true);
                                    //FileManager.writeToFile(new Event(eventHost, eventName, eventLocation, eventDate), getContext());
                                    //FileManager.readFromFile(getContext());
                                }
                            });

                    builder.setNegativeButton(
                            "Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert1 = builder.create();
                    alert1.show();
                }

            }


             /*   if(timeTilEvent <= 15 && timeTilEvent > 0){
                    Log.d("timeTilEvent " , "there is " + timeTilEvent + " minutes until the event " + eventName);
                }
                else if(timeTilEvent <= -15  && GetIsEventToday() == true){
                    Toast.makeText(getContext(), "sorry you have missed the event " + eventName, Toast.LENGTH_SHORT).show();
                    Log.d("timeTilEvent", "you missed the event " + eventName);
                    RemoveButtonFromScreen();
                }



                Toast.makeText(getContext(), "speaker: " + eventHost + "\n topic: " + eventName + "\n date: " + dateOutput, Toast.LENGTH_SHORT).show();*/
            });

        }

    }

