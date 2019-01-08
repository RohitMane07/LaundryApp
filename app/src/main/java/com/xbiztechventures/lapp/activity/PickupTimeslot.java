package com.xbiztechventures.lapp.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xbiztechventures.lapp.R;

public class PickupTimeslot extends AppCompatActivity {
    TextView timeslot1,timeslot2,timeslot3,timeslot4,timeslot5,timeslot6,timeslot7,timeslot8,timeslot9;
    CardView date1,date2,date3,date4,date5,date6,date7;
    Button btnProceed;
    String timing=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_timeslot);
        init();
        timeslot1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                changeBackground(timeslot1);
            }
        });

        timeslot2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                changeBackground(timeslot2);

            }
        });
        timeslot3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                changeBackground(timeslot3);

            }
        });
        timeslot4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                changeBackground(timeslot4);

            }
        });
        timeslot5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                changeBackground(timeslot5);

            }
        });
        timeslot6.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                changeBackground(timeslot6);

            }
        });
        timeslot7.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                changeBackground(timeslot7);
            }
        });

        timeslot8.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                changeBackground(timeslot8);
            }
        });

        timeslot9.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                changeBackground(timeslot9);
            }
        });

        date1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                changeBackground(date1);
            }
        });

        date2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                changeBackground(date2);
            }
        });

        date3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                changeBackground(date3);
            }
        });

        date4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                changeBackground(date4);
            }
        });

        date5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                changeBackground(date5);
            }
        });

        date6.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                changeBackground(date6);
            }
        });
        date7.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                changeBackground(date7);
            }
        });

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timing.isEmpty()){
                    Toast.makeText(PickupTimeslot.this,"Please select time slot",Toast.LENGTH_SHORT).show();
                }
                else{
                    finish();
                }
            }
        });


    }

    private void init(){
        timeslot1 = findViewById(R.id.timeSlot1);
        timeslot2 = findViewById(R.id.timeSlot2);
        timeslot3 = findViewById(R.id.timeSlot3);
        timeslot4 = findViewById(R.id.timeSlot4);
        timeslot5 = findViewById(R.id.timeSlot5);
        timeslot6 = findViewById(R.id.timeSlot6);
        timeslot7 = findViewById(R.id.timeSlot7);
        timeslot8 = findViewById(R.id.timeSlot8);
        timeslot9 = findViewById(R.id.timeSlot9);
        btnProceed = findViewById(R.id.btnProceed);

        date1=findViewById(R.id.date1);
        date2=findViewById(R.id.date2);
        date3=findViewById(R.id.date3);
        date4=findViewById(R.id.date4);
        date5=findViewById(R.id.date5);
        date6=findViewById(R.id.date6);
        date7=findViewById(R.id.date7);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    void changeBackground(TextView textView){
        timing = textView.getText().toString();
        resetBackground();
        textView.setBackground(getResources().getDrawable( R.drawable.color_round_corner));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    void resetBackground(){

        timeslot1.setBackground(getResources().getDrawable( R.drawable.round_corner));
        timeslot2.setBackground(getResources().getDrawable( R.drawable.round_corner));
        timeslot3.setBackground(getResources().getDrawable( R.drawable.round_corner));
        timeslot4.setBackground(getResources().getDrawable( R.drawable.round_corner));
        timeslot5.setBackground(getResources().getDrawable( R.drawable.round_corner));
        timeslot6.setBackground(getResources().getDrawable( R.drawable.round_corner));
        timeslot7.setBackground(getResources().getDrawable( R.drawable.round_corner));
        timeslot8.setBackground(getResources().getDrawable( R.drawable.round_corner));
        timeslot9.setBackground(getResources().getDrawable( R.drawable.round_corner));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    void changeBackground(CardView cardView){
        resetDateBackground();
        cardView.setBackground(getResources().getDrawable( R.drawable.color_round_corner));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    void resetDateBackground(){
        date1.setBackground(getResources().getDrawable( R.drawable.round_corner));
        date2.setBackground(getResources().getDrawable( R.drawable.round_corner));
        date3.setBackground(getResources().getDrawable( R.drawable.round_corner));
        date4.setBackground(getResources().getDrawable( R.drawable.round_corner));
        date5.setBackground(getResources().getDrawable( R.drawable.round_corner));
        date6.setBackground(getResources().getDrawable( R.drawable.round_corner));
        date7.setBackground(getResources().getDrawable( R.drawable.round_corner));
    }

}
