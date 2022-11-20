package com.murasanca.BMM4103ME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.math.MathUtils;

import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity
{
    int countInteger=0;
    TextView counterTextView;

    private final int
        lowerLimit=0,
        upperLimit=16;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_home);

        ImageButton settingsImageButton=findViewById(R.id.settingsButton);
        settingsImageButton.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this,SettingsActivity.class)));

        counterTextView= findViewById(R.id.counterText);
        counterTextView.setText(String.valueOf(countInteger));

        Button plusBttn= findViewById(R.id.plusButton);
        plusBttn.setOnClickListener(view -> add2Counter(1));

        Button minusBttn= findViewById(R.id.minusButton);
        minusBttn.setOnClickListener(view -> add2Counter(-1));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN)
        {
            add2Counter(-5);
            return true;
        }
        else if(keyCode==KeyEvent.KEYCODE_VOLUME_UP)
        {
            add2Counter(5);
            return true;
        }
        else
            return super.onKeyDown(keyCode,event);
    }

    //TODO: add2Counter Method.
    private void add2Counter(int addition)
    {
        if(addition+countInteger<lowerLimit || addition+countInteger>upperLimit)
        {
            if(SettingsActivity.isVibrationChecked)
                ((Vibrator)getSystemService(Context.VIBRATOR_SERVICE)).vibrate(128);
            if(SettingsActivity.isSoundChecked)
            {
                RingtoneManager.getRingtone
                        (
                                getApplicationContext(),
                                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                        ).play();
            }
        }

        counterTextView.setText(String.valueOf(countInteger= MathUtils.clamp(addition+countInteger,lowerLimit,upperLimit)));
    }
}