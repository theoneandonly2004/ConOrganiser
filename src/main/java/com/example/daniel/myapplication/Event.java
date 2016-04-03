package com.example.daniel.myapplication;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Daniel on 28/03/2016.
 */


public class Event implements Serializable
{
    public static final String COSPLAYROOM = "ROOM BA-00-022";
    public static final String THEHUB = "The HUB";
    public static final String SCREENINGROOM = "Conor Lecture Theatre";


    String eventHost;
    String eventName;
    String eventLocation;
    Calendar eventDate;

    public Event( String pHost, String pName,String pEventLocation, Calendar pDate){
        eventHost = pHost;
        eventName = pName;
        eventDate = pDate;
        eventLocation = pEventLocation;
    }
}
