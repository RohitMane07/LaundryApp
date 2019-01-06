package com.xbiztechventures.lapp.activity;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xbiztechventures.lapp.Adapter.AnimationListenerAdapter;
import com.xbiztechventures.lapp.R;

public class SplashScreenActivity extends AppCompatActivity {

    // Screen parameters
    private int screenHeightPx;
    private int screenWidthPx;
    private int screenDiagonalPx;

    // Animation values
    private float globalPaddingPx;
    private float helpButtonSizePx;
    private int titleBtnHelpCenterX;
    private int titleBtnHelpCenterY;

    private View titleHelpButton;
    private FrameLayout titleLayout;
    private RelativeLayout helpLayout;
    private LinearLayout mainLayout;
    private ImageView titleIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make activity fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActionBar supportActionBar = getSupportActionBar();
        if (null != supportActionBar)
            supportActionBar.hide();
        setContentView(R.layout.activity_splash_screen);

        // title bar layout
        titleLayout = findViewById(R.id.sr_title_layout);
        titleIcon = findViewById(R.id.sr_title_icon);

        // Get screen dimensions for animation purposes
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidthPx = size.x;
        screenHeightPx = size.y;
        screenDiagonalPx = (int) Math.sqrt(size.x * size.x + size.y * size.y);

        // calculations for animation purposes
        globalPaddingPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
        helpButtonSizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
        titleBtnHelpCenterX = (int) (screenWidthPx - globalPaddingPx - helpButtonSizePx / 2);
        titleBtnHelpCenterY = (int) (globalPaddingPx + helpButtonSizePx / 2);

        playInitialAnimations();


    }

    private void playInitialAnimations() {

        final float titleRowHeightPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
        final float titleIconWidthPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());

        final float titleYCenterPx = screenHeightPx / 2 - titleRowHeightPx / 2 - globalPaddingPx;
        final float titleXCenterPx = screenWidthPx / 2 - titleIconWidthPx / 2 - globalPaddingPx;

        // Title icon appear, slide to left and move up
        titleIcon.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) titleIcon.getBackground()).start();
            }
        }, 400);

        TranslateAnimation slideFromRight = new TranslateAnimation(titleXCenterPx, 0, titleYCenterPx, titleYCenterPx);
        slideFromRight.setStartOffset(1800);
        slideFromRight.setDuration(600);
        slideFromRight.setInterpolator(new AccelerateDecelerateInterpolator());
        slideFromRight.setAnimationListener(new AnimationListenerAdapter() {
            @Override
            public void onAnimationEnd(Animation animation) {
                TranslateAnimation titleIconSlideUp = new TranslateAnimation(0, 0, titleYCenterPx, 0);
                titleIconSlideUp.setInterpolator(new AccelerateDecelerateInterpolator());
                titleIconSlideUp.setStartOffset(300);
                titleIconSlideUp.setDuration(700);
                titleIcon.startAnimation(titleIconSlideUp);
            }
        });

        // Title text slide from right and move up with title icon
        final TextView title = findViewById(R.id.sr_title_text);
        TranslateAnimation titleSlideUp;
        TranslateAnimation titleSlideFromRight = new TranslateAnimation(screenWidthPx - globalPaddingPx, 0, titleYCenterPx, titleYCenterPx);
        titleSlideFromRight.setInterpolator(new AccelerateDecelerateInterpolator());
        titleSlideFromRight.setStartOffset(1830);
        titleSlideFromRight.setDuration(600);
        titleSlideFromRight.setAnimationListener(new AnimationListenerAdapter() {
            @Override
            public void onAnimationEnd(Animation animation) {
                TranslateAnimation titleSlideUp = new TranslateAnimation(0, 0, titleYCenterPx, 0);
                titleSlideUp.setInterpolator(new AccelerateDecelerateInterpolator());
                titleSlideUp.setStartOffset(310);
                titleSlideUp.setDuration(700);
                title.startAnimation(titleSlideUp);

                titleSlideUp.setAnimationListener(new AnimationListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        startActivity(new Intent(SplashScreenActivity.this,HomeActivity.class));
                    }
                });



            }
        });



        // Background show
        final ImageView mainBackground = findViewById(R.id.sr_background_image);
        AlphaAnimation mainBackgroundShow = new AlphaAnimation(0f, 1f);
        mainBackgroundShow.setInterpolator(new DecelerateInterpolator());
        mainBackgroundShow.setDuration(600);
        mainBackgroundShow.setStartOffset(900);

//        // Contact button show
//        TranslateAnimation contactBtnShow = new TranslateAnimation(0, 0, contactButtonHeightPx + globalPaddingPx, 0);
//        contactBtnShow.setStartOffset(2900);
//        contactBtnShow.setDuration(600);
//        contactBtnShow.setInterpolator(new DecelerateInterpolator());

//        // Slider show
//        TranslateAnimation sliderShowFromRight = new TranslateAnimation(screenWidthPx - globalPaddingPx, 0, 0, 0);
//        sliderShowFromRight.setStartOffset(3200);
//        sliderShowFromRight.setDuration(700);
//        sliderShowFromRight.setInterpolator(new DecelerateInterpolator());

//        // Help(Info) button show
//        ScaleAnimation titleHelpBtnShow = new ScaleAnimation(0, 1, 0, 1, helpButtonSizePx / 2, helpButtonSizePx / 2);
//        titleHelpBtnShow.setInterpolator(new BounceInterpolator());
//        titleHelpBtnShow.setStartOffset(3200);
//        titleHelpBtnShow.setDuration(600);



        // Start everything

        mainBackground.startAnimation(mainBackgroundShow);
        title.startAnimation(titleSlideFromRight);
        titleIcon.startAnimation(slideFromRight);

//       titleHelpButton.startAnimation(titleHelpBtnShow);


    }

}

