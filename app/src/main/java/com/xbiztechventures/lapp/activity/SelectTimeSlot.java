package com.xbiztechventures.lapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.xbiztechventures.lapp.R;

public class SelectTimeSlot extends AppCompatActivity {
    FrameLayout addressLayout,timeLayout,clothLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time_slot);

        addressLayout = findViewById(R.id.address_layout);
        timeLayout = findViewById(R.id.time_layout);
        clothLayout = findViewById(R.id.cloth_layout);
        addressLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(SelectTimeSlot.this,SelectTimeSlot.class));
            }
        });

        timeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectTimeSlot.this,PickupTimeslot.class));
            }
        });

        clothLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectTimeSlot.this,PickupTimeslot.class));
            }
        });


    }
}
