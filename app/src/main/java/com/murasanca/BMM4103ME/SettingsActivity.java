package com.murasanca.BMM4103ME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.math.MathUtils;

import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Button;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity
{
    public static boolean
        isSoundChecked=true,
        isVibrationChecked=true;

    private TextView lowerLimitTextView,upperLimitTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getWindow().setFlags
        (
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_settings);

        CheckBox soundChckBx=findViewById(R.id.soundCheckBox);
        soundChckBx.setChecked(isSoundChecked);

        CheckBox vibrationChckBx=findViewById(R.id.vibrationCheckBox);
        vibrationChckBx.setChecked(isVibrationChecked);

        soundChckBx.setOnCheckedChangeListener((compoundButton, b) -> isSoundChecked=b);

        vibrationChckBx.setOnCheckedChangeListener((compoundButton, b) -> isVibrationChecked=b);

        lowerLimitTextView=findViewById(R.id.lowerLimitTextView);
        lowerLimitTextView.setText(String.valueOf(HomeActivity.lowerLimit));

        upperLimitTextView=findViewById(R.id.upperLimitTextView);
        upperLimitTextView.setText(String.valueOf(HomeActivity.upperLimit));

        Button upperLimitMinusBttn=findViewById(R.id.upperLimitMinusButton);
        Button lowerLimitMinusBttn=findViewById(R.id.lowerLimitMinusButton);
        Button upperLimitPlusBttn=findViewById(R.id.upperLimitPlusButton);
        Button lowerLimitPlusBttn=findViewById(R.id.lowerLimitPlusButton);

        upperLimitMinusBttn.setOnClickListener(view->add2UpperLimit(-1));
        lowerLimitMinusBttn.setOnClickListener(view->add2LowerLimit(-1));
        upperLimitPlusBttn.setOnClickListener(view->add2UpperLimit(1));
        lowerLimitPlusBttn.setOnClickListener(view->add2LowerLimit(1));
    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(SettingsActivity.this,HomeActivity.class));
    }

    private void add2UpperLimit(int addition)
    {
        if(HomeActivity.upperLimit==Integer.MAX_VALUE) // 2^31-1
            return;
        else if(Integer.signum(addition)+HomeActivity.upperLimit<HomeActivity.countInteger)
        {
            if(isSoundChecked)
            {
                RingtoneManager.getRingtone
                        (
                                getApplicationContext(),
                                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                        ).play();
            }
            if(isVibrationChecked)
                ((Vibrator)getSystemService(Context.VIBRATOR_SERVICE)).vibrate(128);
        }

        upperLimitTextView.setText(String.valueOf(HomeActivity.upperLimit= MathUtils.clamp(Integer.signum(addition)+HomeActivity.upperLimit,HomeActivity.countInteger,Integer.MAX_VALUE)));
    }
    private void add2LowerLimit(int addition)
    {
        if(HomeActivity.lowerLimit==Integer.MIN_VALUE) // -2^31
            return;
        else if(Integer.signum(addition)+HomeActivity.lowerLimit>HomeActivity.countInteger) // -2^31
        {
            if(isSoundChecked)
            {
                RingtoneManager.getRingtone
                        (
                                getApplicationContext(),
                                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                        ).play();
            }
            if(isVibrationChecked)
                ((Vibrator)getSystemService(Context.VIBRATOR_SERVICE)).vibrate(128);
        }

        lowerLimitTextView.setText(String.valueOf(HomeActivity.lowerLimit= MathUtils.clamp(Integer.signum(addition)+HomeActivity.lowerLimit,Integer.MIN_VALUE,HomeActivity.countInteger))); // -2^31
    }
}