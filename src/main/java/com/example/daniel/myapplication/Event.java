package com.example.daniel.myapplication;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Daniel on 28/03/2016.
 */
public class Event implements Serializable
{
    String eventHost;
    String eventName;
    Calendar eventDate;

    public Event( String pHost, String pName, Calendar pDate){
        eventHost = pHost;
        eventName = pName;
        eventDate = pDate;
    }
}
