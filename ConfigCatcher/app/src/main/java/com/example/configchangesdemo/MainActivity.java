package com.example.configchangesdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton brightnessEnablerButton;
    private SeekBar brightnessChangerBar;
    private ImageView arrowButton;
    private ConstraintLayout innerLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.registerReceiver(new BatChargeReceiverPlug(), new IntentFilter(
                "android.intent.action.ACTION_POWER_CONNECTED"));
        this.registerReceiver(new BatChargeReceiverUnplug(), new IntentFilter(
                "android.intent.action.ACTION_POWER_DISCONNECTED"));

        brightnessEnablerButton = findViewById(R.id.changeBrightnessIB);
        brightnessEnablerButton.setOnClickListener(this::onBrightnessIBClick);

        innerLayout = findViewById(R.id.constraintLayout);
        arrowButton = findViewById(R.id.imageView2);
        brightnessChangerBar = findViewById(R.id.seekBar);

        final Animation hideAnimation = AnimationUtils.loadAnimation(this, R.anim.hide_objects);
        final Animation showAnimation = AnimationUtils.loadAnimation(this, R.anim.show_objects);

        brightnessChangerBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float backLightValue = (float) i / 100;

                WindowManager.LayoutParams layoutParams = getWindow()
                        .getAttributes();
                layoutParams.screenBrightness = backLightValue;
                getWindow().setAttributes(layoutParams);

                if (i < 25) {onWinter();}
                else if (i < 50) {onSpring();}
                else if (i < 75) {onSummer();}
                else {onFall();}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                brightnessEnablerButton.startAnimation(hideAnimation);
                innerLayout.startAnimation(hideAnimation);
                brightnessEnablerButton.setVisibility(View.INVISIBLE);
                innerLayout.setVisibility(View.INVISIBLE);
                // brightnessChangerBar.setBackground(null);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                brightnessEnablerButton.startAnimation(showAnimation);
                innerLayout.startAnimation(showAnimation);
                brightnessEnablerButton.setVisibility(View.VISIBLE);
                innerLayout.setVisibility(View.VISIBLE);
            }
        });

        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.standard_arrow_anim);
        arrowButton.startAnimation(animAlpha);
    }

    private void onBrightnessIBClick(View v) {

        if (arrowButton.getVisibility() == View.VISIBLE){
            final Animation animArrow = AnimationUtils.loadAnimation(this, R.anim.arrow_disappear);
            arrowButton.startAnimation(animArrow);
            arrowButton.setVisibility(View.INVISIBLE);
        }

        if (brightnessChangerBar.getVisibility() == View.VISIBLE){
            final Animation animRepeatedClicks = AnimationUtils.loadAnimation(this, R.anim.tap_brightness_button);
            brightnessEnablerButton.startAnimation(animRepeatedClicks);
        }
        else{
            final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.enable_brightness_change_tap);
            v.startAnimation(animAlpha);
            brightnessChangerBar.setVisibility(View.VISIBLE);
        }


    }

    private void onWinter(){
        this.getWindow().getDecorView().setBackground(
                AppCompatResources.getDrawable(this, R.drawable.background_winter));
    }

    private void onFall(){
        this.getWindow().getDecorView().setBackground(
                AppCompatResources.getDrawable(this, R.drawable.background_fall));
    }

    private void onSummer(){
        this.getWindow().getDecorView().setBackground(
                AppCompatResources.getDrawable(this, R.drawable.background_summer));
    }

    private void onSpring(){

        this.getWindow().getDecorView().setBackground(
                AppCompatResources.getDrawable(this, R.drawable.background_spring));
    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(getApplicationContext(), R.string.orientation_change_info_landscape, Toast.LENGTH_LONG).show();
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(getApplicationContext(), R.string.orientation_change_info_portrait, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        openQuitDialog();
    }

    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                MainActivity.this);
        quitDialog.setTitle(R.string.quitting);

        quitDialog.setPositiveButton(R.string.quit_agree, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        quitDialog.setNegativeButton(R.string.quit_disagree, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
            }
        });

        quitDialog.show();
    }

    }