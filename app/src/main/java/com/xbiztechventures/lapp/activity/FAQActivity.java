package com.xbiztechventures.lapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xbiztechventures.lapp.R;

public class FAQActivity extends AppCompatActivity {
    TextView pickupDelivery,washing,payment;
    LinearLayout Q1,Q_wash,Q_payment;
    int flag_pickup=0;
    int flag_washing=0;
    int flag_payment=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        init();
        pickupDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag_pickup==0) {
                    Q1.setVisibility(View.VISIBLE);
                    flag_pickup=1;
                }
                else{
                    Q1.setVisibility(View.GONE);
                    flag_pickup=0;
                }
            }
        });

        washing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag_washing==0) {
                    Q_wash.setVisibility(View.VISIBLE);
                    flag_washing=1;
                }
                else{
                    Q_wash.setVisibility(View.GONE);
                    flag_washing=0;
                }
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag_payment==0) {
                    Q_payment.setVisibility(View.VISIBLE);
                    flag_payment=1;
                }
                else{
                    Q_payment.setVisibility(View.GONE);
                    flag_payment=0;
                }
            }
        });
    }
    private void init(){
        pickupDelivery=(TextView) findViewById(R.id.pickupDelivery);
        Q1=(LinearLayout) findViewById(R.id.Q1);
        washing=(TextView)findViewById(R.id.washingFAQ);
        Q_wash=(LinearLayout)findViewById(R.id.Q_wash);
        payment=(TextView)findViewById(R.id.payment);
        Q_payment=(LinearLayout)findViewById(R.id.Q_payment);
    }
}
