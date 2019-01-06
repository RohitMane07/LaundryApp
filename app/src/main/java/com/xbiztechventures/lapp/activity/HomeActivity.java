package com.xbiztechventures.lapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.xbiztechventures.lapp.Adapter.SwipeAdapter;
import com.xbiztechventures.lapp.CommonUtil;
import com.xbiztechventures.lapp.R;
import com.xbiztechventures.lapp.SupportClass.ChildAnimation;
import com.xbiztechventures.lapp.SupportClass.SliderLayout;

import java.util.HashMap;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    TextView wash_text,iron_text,premium_text,dry_text,user,terms,faq,myorder,shareApp;
    Button btnproceed;
    SliderLayout mDemoSlider;
    LinearLayout wash_press,iron_press,dry_press,premium_press,frequentlyAsk;
    int flag_wash=0,flag_ironing=0,flag_dry=0,flag_premium=0;
    ViewPager feedbackViewPager;
    NavigationView navigationView;
    View headerView;
    SwipeAdapter adapter;
    DrawerLayout drawer;
    Toolbar toolbar;
    CommonUtil commonUtilObj;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        slider();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        wash_press.setOnClickListener(this);
        iron_press.setOnClickListener(this);
        dry_press.setOnClickListener(this);
        premium_press.setOnClickListener(this);
        btnproceed.setOnClickListener(this);
        frequentlyAsk.setOnClickListener(this);
        feedbackViewPager.setAdapter(adapter);

    }

    private void init(){

        sharedPreferences = getSharedPreferences("USER_INFO",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        headerView  = navigationView.getHeaderView(0);
        user=(TextView)headerView.findViewById(R.id.username);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
        btnproceed=(Button) findViewById(R.id.btnproceed);
        feedbackViewPager= (ViewPager) findViewById(R.id.viewpager);
        dry_press=(LinearLayout)findViewById(R.id.dry_press);
        dry_text=(TextView) findViewById(R.id.dry_text);
        wash_press=(LinearLayout)findViewById(R.id.wash_press);
        wash_text=(TextView) findViewById(R.id.wash_text);
        premium_press=(LinearLayout)findViewById(R.id.premium_press);
        premium_text=(TextView) findViewById(R.id.premium_text);
        iron_press=(LinearLayout)findViewById(R.id.iron_press);
        iron_text=(TextView) findViewById(R.id.iron_text);
        frequentlyAsk=(LinearLayout)findViewById(R.id.frequentlyAsk);
        commonUtilObj = new CommonUtil();
        adapter=new SwipeAdapter(this);

    }

    private void slider(){

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        user.setText(sharedPreferences.getString("username",""));

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("1", R.mipmap.image3);
        file_maps.put("2", R.mipmap.sliderimage);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .image(file_maps.get(name));
            //.setScaleType(BaseSliderView.ScaleType.CenterCrop)
            //.setOnSliderClickListener((BaseSliderView.OnSliderClickListener) this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new ChildAnimation());
        mDemoSlider.setDuration(3000);

    }

    private void washAndPress(){
        if (flag_wash==0) {
            wash_press.setBackgroundColor(getResources().getColor(R.color.select_wash));
            btnproceed.setBackgroundColor(getResources().getColor(R.color.select_wash));
            wash_text.setTextColor(Color.WHITE);
            btnproceed.setTextColor(Color.WHITE);
            flag_wash=1;
            flag_ironing=0;
            iron_press.setBackgroundDrawable( getResources().getDrawable(R.drawable.textview_border) );
            iron_text.setTextColor(Color.BLACK);
            flag_premium=0;
            premium_press.setBackgroundDrawable( getResources().getDrawable(R.drawable.textview_border) );
            premium_text.setTextColor(Color.BLACK);
            flag_dry=0;
            dry_press.setBackgroundDrawable( getResources().getDrawable(R.drawable.textview_border) );
            dry_text.setTextColor(Color.BLACK);
        }
        else {
            wash_press.setBackgroundDrawable( getResources().getDrawable(R.drawable.textview_border) );
            wash_text.setTextColor(Color.BLACK);
            btnproceed.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_border));
            btnproceed.setTextColor(Color.BLACK);
            flag_wash=0;
        }
    }

    private void ironPress(){
        if (flag_ironing==0) {
            iron_press.setBackgroundColor(getResources().getColor(R.color.select_iron));
            btnproceed.setBackgroundColor(getResources().getColor(R.color.select_iron));
            btnproceed.setTextColor(Color.WHITE);
            iron_text.setTextColor(Color.WHITE);
            flag_ironing=1;

            flag_wash=0;
            wash_press.setBackgroundDrawable( getResources().getDrawable(R.drawable.textview_border) );
            wash_text.setTextColor(Color.BLACK);
            flag_premium=0;
            premium_press.setBackgroundDrawable( getResources().getDrawable(R.drawable.textview_border) );
            premium_text.setTextColor(Color.BLACK);
            flag_dry=0;
            dry_press.setBackgroundDrawable( getResources().getDrawable(R.drawable.textview_border) );
            dry_text.setTextColor(Color.BLACK);

        }
        else {
            iron_press.setBackgroundDrawable( getResources().getDrawable(R.drawable.textview_border) );
            iron_text.setTextColor(Color.BLACK);
            btnproceed.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_border));
            btnproceed.setTextColor(Color.BLACK);
            flag_ironing=0;
        }

    }

    private void dryPress(){
        if (flag_dry==0) {
            dry_press.setBackgroundColor(getResources().getColor(R.color.select_dry));
            btnproceed.setBackgroundColor(getResources().getColor(R.color.select_dry));
            dry_text.setTextColor(Color.WHITE);
            btnproceed.setTextColor(Color.WHITE);
            flag_dry=1;
            flag_wash=0;
            wash_press.setBackgroundDrawable( getResources().getDrawable(R.drawable.textview_border) );
            wash_text.setTextColor(Color.BLACK);
            flag_ironing=0;
            iron_press.setBackgroundDrawable( getResources().getDrawable(R.drawable.textview_border) );
            iron_text.setTextColor(Color.BLACK);
            flag_premium=0;
            premium_press.setBackgroundDrawable( getResources().getDrawable(R.drawable.textview_border) );
            premium_text.setTextColor(Color.BLACK);

        }
        else {
            dry_press.setBackgroundDrawable( getResources().getDrawable(R.drawable.textview_border) );
            dry_text.setTextColor(Color.BLACK);
            btnproceed.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_border));
            btnproceed.setTextColor(Color.BLACK);
            flag_dry=0;
        }
    }

    private void premiumPress(){
        if (flag_premium==0) {

            premium_press.setBackgroundColor(getResources().getColor(R.color.select_premium));
            btnproceed.setBackgroundColor(getResources().getColor(R.color.select_premium));
            premium_text.setTextColor(Color.WHITE);
            btnproceed.setTextColor(Color.WHITE);
            flag_premium=1;
            flag_dry=0;
            dry_press.setBackgroundDrawable( getResources().getDrawable(R.drawable.textview_border) );
            dry_text.setTextColor(Color.BLACK);
            flag_wash=0;
            wash_press.setBackgroundDrawable( getResources().getDrawable(R.drawable.textview_border) );
            wash_text.setTextColor(Color.BLACK);
            flag_ironing=0;
            iron_press.setBackgroundDrawable( getResources().getDrawable(R.drawable.textview_border) );
            iron_text.setTextColor(Color.BLACK);

        }
        else {
            premium_press.setBackgroundDrawable( getResources().getDrawable(R.drawable.textview_border) );
            premium_text.setTextColor(Color.BLACK);
            btnproceed.setBackgroundDrawable( getResources().getDrawable(R.drawable.textview_border) );
            btnproceed.setTextColor(Color.BLACK);
            flag_premium=0;
        }
    }

    private void proceed(){
        if(flag_wash==0 && flag_ironing==0 && flag_dry==0 && flag_premium==0){
            commonUtilObj.showDialog(HomeActivity.this, "Please select one");
        }
        else if(flag_wash==1){
            flag_wash=0;
            flag_ironing=0;
            flag_dry=0;
            flag_premium=0;
            Intent intent=new Intent(HomeActivity.this,SelectTimeSlot.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }
        else if(flag_ironing==1){
            flag_wash=0;
            flag_ironing=0;
            flag_dry=0;
            flag_premium=0;
            startActivity(new Intent(HomeActivity.this,TrackStatusActivity.class));
        }
        else if(flag_dry==1){
            flag_wash=0;
            flag_ironing=0;
            flag_dry=0;
            flag_premium=0;
            startActivity(new Intent(HomeActivity.this,LedgerActivity.class));
        }
        else if(flag_premium==1){
            flag_wash=0;
            flag_ironing=0;
            flag_dry=0;
            flag_premium=0;
            startActivity(new Intent(HomeActivity.this,AboutUsActivity.class));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.frequentlyAsk:
                startActivity(new Intent(HomeActivity.this,FAQActivity.class));
                break;
            case R.id.wash_press:
                washAndPress();
                break;
            case R.id.iron_press:
                ironPress();
                break;
            case R.id.dry_press:
                dryPress();
                break;
            case R.id.premium_press:
                premiumPress();
                break;
            case R.id.btnproceed:
                proceed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_orders) {
            startActivity(new Intent(HomeActivity.this,LedgerActivity.class));
            // Handle the camera action
        } else if (id == R.id.nav_faq) {
            startActivity(new Intent(HomeActivity.this,FAQActivity.class));

        } else if (id == R.id.nav_feedback) {
            startActivity(new Intent(HomeActivity.this,FAQActivity.class));

        } else if (id == R.id.nav_tconditions) {
            startActivity(new Intent(HomeActivity.this,AboutUsActivity.class));

        } else if (id == R.id.nav_share) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
