package com.example.daniel.myapplication;

import android.app.ActionBar;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    public Calendar setupDate(int year, int month, int day , int hour, int minute){
        return new GregorianCalendar(year,month,day,hour,minute);
    }


static int count=0;
    static LinearLayout linearLayout;
    static LinearLayout.LayoutParams linearParams;

    //EventButton[] buttons = new EventButton[15];
    static ArrayList<EventButton> buttons = new ArrayList<EventButton>();

    public void addButtonToList(String pHost, String pName,String pEventLocation, Calendar pDate){
        Event event = new Event(pHost, pName, pEventLocation, pDate);
        buttons.add(new EventButton(this, event));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        FileManager.readFromFile(getBaseContext());

        Button calendarButton = (Button)findViewById(R.id.calendar_button);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DialogFragment newFragment = new DatePickerFragment();
                    newFragment.show(getFragmentManager(), "datePicker");
                }

            });


        addButtonToList(Users.lisa + " & " + Users.bekaton, " Meet and greet", Event.COSPLAYROOM, setupDate(2016, 04, 30, 10, 00));
        addButtonToList(Users.stephen, " Screening: Teen Titans", Event.SCREENINGROOM, setupDate(2016, 04, 30, 10, 00));

        addButtonToList(Users.phoebe, " Cards Against Humanity", Event.THEHUB, setupDate(2016, 04, 30, 11, 00));
        addButtonToList(Users.stephen, " Screening: Over the wall", Event.SCREENINGROOM, setupDate(2016, 04, 30, 11, 00));

        addButtonToList(Users.lisa +" & " + Users.bekaton, "OPEN FOR ALL COSPLAY SKITS", Event.COSPLAYROOM, setupDate(2016, 04, 30, 12, 00));
        addButtonToList(Users.stephen +" & " + Users.luke, "Panel: Dark Souls", Event.SCREENINGROOM, setupDate(2016, 04, 30, 12, 00));
        addButtonToList("NEEDS NAME", "DND", Event.THEHUB, setupDate(2016, 04, 30, 12, 00));

        addButtonToList(Users.phoebe + " & " + Users.fergal, "Panel:Pokémon", Event.SCREENINGROOM, setupDate(2016, 04, 30, 13, 00));
        addButtonToList(Users.rebecca, "Pathfinder", Event.THEHUB, setupDate(2016, 04, 30, 13, 00));

        addButtonToList(Users.stephen +" & " + Users.bekaton, "Cosplay scrap battle", Event.COSPLAYROOM, setupDate(2016, 04, 30, 14, 00));
        addButtonToList(Users.fergal,"Screening: Baccano",Event.SCREENINGROOM,setupDate(2016,04,30,14,00));

        addButtonToList(Users.lisa +" & " + Users.bekaton + " & " + Users.lorna, "artist copetition", Event.COSPLAYROOM, setupDate(2016, 04, 30, 15, 00));
        addButtonToList(Users.kate + " & " + Users.ciara,"Screening: JOJOs Bizarre adventure",Event.SCREENINGROOM,setupDate(2016,04,30,15,00));
        addButtonToList(Users.andrew, "Valor Quest", Event.THEHUB, setupDate(2016, 04, 30, 13, 00));


        addButtonToList(Users.lisa +" & " + Users.bekaton + " & " + Users.lorna + " & " + Users.stephen, "Cosplay Contest Judging", Event.COSPLAYROOM, setupDate(2016, 04, 30, 16, 00));
        addButtonToList(Users.kate + " & " + Users.ciara,"Panel: JOJOs Bizarre adventure",Event.SCREENINGROOM,setupDate(2016,04,30,16,00));

        addButtonToList(Users.lisa +" & " + Users.bekaton + " & " + Users.lorna + " & " + Users.stephen, "Cosplay Parade", Event.COSPLAYROOM, setupDate(2016, 04, 30, 16, 00));

        addButtonToList(Users.lisa +" & " + Users.bekaton + " & " + Users.lorna + " & " + Users.stephen, "random event", Event.COSPLAYROOM, setupDate(2016, 05, 01, 16, 00));




      /*  addButtonToList("Lisa Mc Kenna & Beckatan Wolflion", " Meet and greet", Event.COSPLAYROOM, setupDate(2016, 04, 30, 10, 00));
        addButtonToList("Stephen Cole", " Screening: Over the wall", Event.SCREENINGROOM, setupDate(2016, 04, 30, 11, 00));
        addButtonToList("Lisa Mc Kenna & Beckatan Wolflion", "OPEN FOR ALL COSPLAY SKITS", Event.COSPLAYROOM, setupDate(2016, 04, 30, 12, 00));
        addButtonToList("Stephen Cole & Luke Mars", "Panel: Dark Souls", Event.SCREENINGROOM, setupDate(2016, 04, 30, 12, 00));

       */


        linearLayout = (LinearLayout) findViewById(R.id.LinearLayout1);
        linearParams = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearParams.gravity = Gravity.CENTER;

        for(int count=0;count < buttons.size();count++){
            if(buttons.get(count) != null) {
                buttons.get(count).AddButtonToScreen();
            }
        }



        }
    }


