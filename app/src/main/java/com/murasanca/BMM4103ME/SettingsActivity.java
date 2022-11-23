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
        soundChckBx.setChecked(SharedPreferencesClass.getSoundCheck());

        CheckBox vibrationChckBx=findViewById(R.id.vibrationCheckBox);
        vibrationChckBx.setChecked(SharedPreferencesClass.getVibrationCheck());

        soundChckBx.setOnCheckedChangeListener((compoundButton, b) -> SharedPreferencesClass.setSoundCheck(b));

        vibrationChckBx.setOnCheckedChangeListener((compoundButton, b) -> SharedPreferencesClass.setVibrationCheck(b));

        lowerLimitTextView=findViewById(R.id.lowerLimitTextView);
        lowerLimitTextView.setText(String.valueOf(SharedPreferencesClass.getLowerLimit()));

        upperLimitTextView=findViewById(R.id.upperLimitTextView);
        upperLimitTextView.setText(String.valueOf(SharedPreferencesClass.getUpperLimit()));

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
        if(SharedPreferencesClass.getUpperLimit()==Integer.MAX_VALUE) // 2^31-1
            return;
        else if(Integer.signum(addition)+SharedPreferencesClass.getUpperLimit()<SharedPreferencesClass.getCountInteger())
        {
            if(SharedPreferencesClass.getSoundCheck())
            {
                RingtoneManager.getRingtone
                    (
                        getApplicationContext(),
                        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                    ).play();
            }
            if(SharedPreferencesClass.getVibrationCheck())
                ((Vibrator)getSystemService(Context.VIBRATOR_SERVICE)).vibrate(128);
        }

        SharedPreferencesClass.setUpperLimit(MathUtils.clamp(Integer.signum(addition)+SharedPreferencesClass.getUpperLimit(),SharedPreferencesClass.getCountInteger(),Integer.MAX_VALUE));
        upperLimitTextView.setText(String.valueOf(SharedPreferencesClass.getUpperLimit()));
    }
    private void add2LowerLimit(int addition)
    {
        if(SharedPreferencesClass.getLowerLimit()==Integer.MIN_VALUE) // -2^31
            return;
        else if(Integer.signum(addition)+SharedPreferencesClass.getLowerLimit()>SharedPreferencesClass.getCountInteger()) // -2^31
        {
            if(SharedPreferencesClass.getSoundCheck())
            {
                RingtoneManager.getRingtone
                    (
                        getApplicationContext(),
                        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                    ).play();
            }
            if(SharedPreferencesClass.getVibrationCheck())
                ((Vibrator)getSystemService(Context.VIBRATOR_SERVICE)).vibrate(128);
        }

        SharedPreferencesClass.setLowerLimit(MathUtils.clamp(Integer.signum(addition)+SharedPreferencesClass.getLowerLimit(),Integer.MIN_VALUE,SharedPreferencesClass.getCountInteger()));
        lowerLimitTextView.setText(String.valueOf(SharedPreferencesClass.getLowerLimit())); // -2^31
    }
}