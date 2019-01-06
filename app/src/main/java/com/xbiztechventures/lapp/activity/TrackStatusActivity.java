package com.xbiztechventures.lapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.xbiztechventures.lapp.Adapter.TrackAdapter;
import com.xbiztechventures.lapp.Model.Track;
import com.xbiztechventures.lapp.R;
import com.xbiztechventures.lapp.SupportClass.RecyclerItemClickListener;
import com.xbiztechventures.lapp.Webservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TrackStatusActivity extends AppCompatActivity {

    Webservice webservice;
    SharedPreferences sharedPreferences ;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RadioButton all, pending, picked,rescheduled, delivered;
    RadioGroup radiogrp;
    int pos;
    public ArrayList<Track> tracklist = new ArrayList<>();
    public ArrayList<Track> tracklist_pending = new ArrayList<>();
    public ArrayList<Track> tracklist_reschedule = new ArrayList<>();
    public ArrayList<Track> tracklist_picked = new ArrayList<>();
    public ArrayList<Track> tracklist_delivered = new ArrayList<>();

    String currentdate,tomorrow,next3day,next4day,next5day,next6day,next7day;
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    Button btncancel,btnreschedule,btnresch;
    TextView err_date,err_timeslot,order;
    String order_no_value="" ,rescheduledate="NA",rescheduletimeslot="NA";
    ArrayAdapter<String> spinneradapter;
    ArrayAdapter<String> spinneradaptertime;
    Spinner date1,timeslot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_status);
        init();
        sharedPreferences = getSharedPreferences("USER_INFO",MODE_PRIVATE);
        String username = sharedPreferences.getString("username","pratidnya");
        String mobile_no = sharedPreferences.getString("mobile_no","8655289417");
        new GetOrderAsync().execute(username,mobile_no);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new TrackAdapter(tracklist);
        adapter.notifyItemInserted(tracklist.size());
        recyclerView.setAdapter(adapter);

//        recyclerView.addOnItemTouchListener(
//                new RecyclerItemClickListener(TrackStatusActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, final int position) {
//                        final TextView txtorderno, txtStatus;
//                        txtorderno = (TextView) view.findViewById(R.id.order_no);
//                        txtStatus = (TextView) view.findViewById(R.id.status_value);
//                        order_no_value=txtorderno.getText().toString();
//                        if(rescheduled.isChecked()|| txtStatus.getText().toString().contentEquals("Reschedule")){
//                            btnreschedule = (Button) view.findViewById(R.id.btnreschedule);
//
//                            SimpleDateFormat dateFormat = new SimpleDateFormat("EE dd.MM.yyyy");
//                            Calendar currentCal = Calendar.getInstance();
//                            currentdate = dateFormat.format(currentCal.getTime());
//
//                            Calendar currentCal1 = Calendar.getInstance();
//                            currentCal1.add(Calendar.DATE, 1);
//                            tomorrow = dateFormat.format(currentCal1.getTime());
//
//                            Calendar currentCal2 = Calendar.getInstance();
//                            currentCal2.add(Calendar.DATE, 2);
//                            next3day = dateFormat.format(currentCal2.getTime());
//
//                            Calendar currentCal3 = Calendar.getInstance();
//                            currentCal3.add(Calendar.DATE, 3);
//                            next4day = dateFormat.format(currentCal3.getTime());
//
//                            Calendar currentCal4 = Calendar.getInstance();
//                            currentCal4.add(Calendar.DATE, 4);
//                            next5day = dateFormat.format(currentCal4.getTime());
//
//                            Calendar currentCal5 = Calendar.getInstance();
//                            currentCal5.add(Calendar.DATE, 5);
//                            next6day = dateFormat.format(currentCal5.getTime());
//
//                            Calendar currentCal6 = Calendar.getInstance();
//                            currentCal6.add(Calendar.DATE, 6);
//                            next7day = dateFormat.format(currentCal6.getTime());
//
//                            builder=new AlertDialog.Builder(TrackStatusActivity.this);
//                            final View dialogView=View.inflate(TrackStatusActivity.this,R.layout.reschedule_clientside_activity,null);
//
//                            err_date=(TextView)dialogView.findViewById(R.id.err_date);
//                            err_timeslot=(TextView)dialogView.findViewById(R.id.err_timeslot);
//                            order=(TextView)dialogView.findViewById(R.id.order);
//                            order.setText(order_no_value);
//
//                            btnresch=(Button) dialogView.findViewById(R.id.btnreschedule);
//                            btncancel=(Button) dialogView.findViewById(R.id.btncancel);
//                            String days[]={"select date",currentdate,tomorrow,next3day,next4day,next5day,next6day,next7day};
//
//                            date1 =(Spinner) dialogView.findViewById(R.id.date);
//                            spinneradapter=new ArrayAdapter(dialogView.getContext(),android.R.layout.simple_spinner_item,days);
//                            spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                            date1.setAdapter(spinneradapter);
//
//                            String time[]={"select timeslot","8 A.M - 9 A.M","9 A.M - 10 A.M","10 A.M - 11 A.M","11 A.M - 12 P.M","1 P.M - 3 P.M","3 P.M - 5 P.M","5 P.M - 7 P.M","7 P.M - 9 P.M","9 P.M - 10 P.M"};
//                            timeslot=(Spinner)dialogView.findViewById(R.id.timeslot);
//                            spinneradaptertime=new ArrayAdapter<String>(dialogView.getContext(),android.R.layout.simple_spinner_item,time);
//
//                            spinneradaptertime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                            timeslot.setAdapter(spinneradaptertime);
//
//                            /* date spinner */
//                            date1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                                @Override
//                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                    switch ((int)parent.getItemIdAtPosition(position))
//                                    {
//                                        case 0: rescheduledate="NA";
//                                            break;
//                                        case 1: rescheduledate=currentdate;
//                                            break;
//                                        case 2: rescheduledate=tomorrow;
//                                            break;
//                                        case 3: rescheduledate=next3day;
//                                            break;
//                                        case 4: rescheduledate=next4day;
//                                            break;
//                                        case 5: rescheduledate=next5day;
//                                            break;
//                                        case 6: rescheduledate=next6day;
//                                            break;
//                                        case 7: rescheduledate=next7day;
//                                            break;
//                                    }
//                                }
//
//                                @Override
//                                public void onNothingSelected(AdapterView<?> parent) {
//
//                                }
//                            });
//
//                            /* time slot spinner */
//                            timeslot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                                @Override
//                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                    switch ((int)parent.getItemIdAtPosition(position))
//                                    {
//                                        case 0: rescheduletimeslot="NA";
//                                            break;
//                                        case 1: rescheduletimeslot="8 A.M - 9 A.M";
//                                            break;
//                                        case 2: rescheduletimeslot="9 A.M - 10 A.M";
//                                            break;
//                                        case 3: rescheduletimeslot="10 A.M - 11 A.M";
//                                            break;
//                                        case 4: rescheduletimeslot="11 A.M - 12 P.M";
//                                            break;
//                                        case 5: rescheduletimeslot="1 P.M - 3 P.M";
//                                            break;
//                                        case 6: rescheduletimeslot="3 P.M - 5 P.M";
//                                            break;
//                                        case 7: rescheduletimeslot="5 P.M - 7 P.M";
//                                            break;
//                                        case 8: rescheduletimeslot="7 P.M - 9 P.M";
//                                            break;
//                                        case 9: rescheduletimeslot="9 P.M - 10 P.M";
//                                            break;
//                                    }
//                                }
//
//                                @Override
//                                public void onNothingSelected(AdapterView<?> parent) {
//
//                                }
//                            });
//                            btnresch.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    if(rescheduledate.contentEquals("NA") ){
//                                        err_date.setVisibility(View.VISIBLE);
//                                    }
//                                    else if(rescheduletimeslot.contentEquals("NA") ){
//                                        err_timeslot.setVisibility(View.VISIBLE);
//                                    }
//                                    else{
//                                        //new updateStatus().execute();
//                                        startActivity(new Intent(TrackStatusActivity.this,TrackStatusActivity.class));
//                                        Toast.makeText(TrackStatusActivity.this,"Your Order is rescheduled with same order number",Toast.LENGTH_SHORT).show();
//                                        //Snackbar.make(TrackStatus.class,"Your Order is rescheduled with same order number",Snackbar.LENGTH_SHORT ).setAction("Action",null).show();
//                                        alertDialog.dismiss();
//                                    }
//
//                                }
//                            });
//
//                            btncancel.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    alertDialog.dismiss();
//                                }
//                            });
//
//                            builder.setView(dialogView);
//                            alertDialog=builder.create();
//                            Window window=alertDialog.getWindow();
//                            window.setGravity(Gravity.CENTER);
//                            //alertDialog.getWindow().getAttributes().windowAnimations=R.style.PauseDialogAnimation;
//                            alertDialog.setCancelable(false);
//                            alertDialog.show();
//
//                        }
//                    }
//                }));



        radiogrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                pos = radiogrp.indexOfChild(findViewById(checkedId));

                switch (pos) {
                    case 0:
                        adapter = new TrackAdapter(tracklist);
                        adapter.notifyItemInserted(tracklist.size());
                        recyclerView.setAdapter(adapter);
                        break;
                    case 1:
                        adapter = new TrackAdapter(tracklist_pending);
                        adapter.notifyItemInserted(tracklist_pending.size());
                        recyclerView.setAdapter(adapter);
                        break;
                    case 2:
                        adapter = new TrackAdapter(tracklist_picked);
                        adapter.notifyItemInserted(tracklist_picked.size());
                        recyclerView.setAdapter(adapter);
                        break;

                    case 3:
                        adapter = new TrackAdapter(tracklist_reschedule);
                        adapter.notifyItemInserted(tracklist_reschedule.size());
                        recyclerView.setAdapter(adapter);
                        break;
                    case 4:
                        adapter = new TrackAdapter(tracklist_delivered);
                        adapter.notifyItemInserted(tracklist_delivered.size());
                        recyclerView.setAdapter(adapter);
                        break;
                }
            }
        });
    }

    private void init(){
        webservice = new Webservice();
        recyclerView = (RecyclerView) findViewById(R.id.SaveRecyclerView2);
        radiogrp = (RadioGroup) findViewById(R.id.radiogrp);
        all = (RadioButton) findViewById(R.id.all);
        all.setChecked(true);
        pending = (RadioButton) findViewById(R.id.pending);
        picked=(RadioButton)findViewById(R.id.picked);
        delivered=(RadioButton)findViewById(R.id.deliver);
        rescheduled=(RadioButton)findViewById(R.id.reschedule);
    }

    public class GetOrderAsync extends AsyncTask<String,Void,String> {
        ProgressDialog progressDialog ;
        String result ="";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(TrackStatusActivity.this);
            progressDialog.setMessage("Please wait while fetching data");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            result = webservice.getAllOrders("get_all_orders",strings[0],strings[1]);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            if(!TextUtils.isEmpty(result)){
                try {
                    JSONObject object = new JSONObject(s);
                    String arr = object.getString("order_list");
                    JSONArray array = new JSONArray(arr);

                    for(int i=0;i<array.length();i++){
                        JSONObject obj = array.getJSONObject(i);

                        String orderNo = obj.getString("order_no");
                        String date = obj.getString("pickup_date");
                        String timeSlot = obj.getString("pickup_timeslot");
                        String totalcloth = obj.getString("total_cloth");
                        String status = obj.getString("status");

                        tracklist.add(new Track(orderNo, date,totalcloth,"totalamount_null",status));
                        if(status.contentEquals("Booked") || status.contentEquals("Booked(Resheduled)")){
                            tracklist_pending.add(new Track(orderNo, date,totalcloth ,"totalamount_null",status));
                        }
                        if(status.contentEquals("Reschedule")){
                            tracklist_reschedule.add(new Track(orderNo, date,totalcloth ,"totalamount_null",status));
                        }
                        if(status.contentEquals("Picked")){
                            tracklist_picked.add(new Track(orderNo, date,totalcloth ,"totalamount_null",status));
                        }
                        if(status.contentEquals("Delivered")){
                            tracklist_delivered.add(new Track(orderNo, date,totalcloth ,"totalamount_null",status));
                        }
                    }

                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
