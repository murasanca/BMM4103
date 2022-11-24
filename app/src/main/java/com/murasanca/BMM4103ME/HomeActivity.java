package com.murasanca.BMM4103ME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.math.MathUtils;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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
    private TextView counterTextView;

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

        setContentView(R.layout.activity_home);

        SharedPreferencesClass.getInstance(getApplicationContext());

        ImageButton settingsImageButton=findViewById(R.id.settingsButton);
        settingsImageButton.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this,SettingsActivity.class)));

        counterTextView= findViewById(R.id.counterText);
        counterTextView.setText(String.valueOf(SharedPreferencesClass.getCountInteger()));

        Button plusBttn= findViewById(R.id.plusButton);
        plusBttn.setOnClickListener(view -> add2Counter(1));

        Button minusBttn= findViewById(R.id.minusButton);
        minusBttn.setOnClickListener(view -> add2Counter(-1));

        findViewById(R.id.calculateImageButton).setOnClickListener(view -> startActivity(new Intent(HomeActivity.this,CalculatorActivity.class)));

        SensorManager sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensorShake=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener sensorEventListener=new SensorEventListener()
        {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent)
            {
                if
                (
                        sensorEvent!=null&&
                        (
                                16<sensorEvent.values[0]||
                                -16>sensorEvent.values[0]||
                                16<sensorEvent.values[1]||
                                -16>sensorEvent.values[1]||
                                16<sensorEvent.values[2]||
                                -16>sensorEvent.values[2]
                        )
                )
                {
                    SharedPreferencesClass.setLowerLimit(0);
                    SharedPreferencesClass.setUpperLimit(16);
                    SharedPreferencesClass.setCountInteger(0);
                    counterTextView.setText(String.valueOf(SharedPreferencesClass.getCountInteger()));
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i){}
        };
        sensorManager.registerListener(sensorEventListener,sensorShake,SensorManager.SENSOR_DELAY_NORMAL);
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

    private void add2Counter(int addition)
    {
        if(addition+SharedPreferencesClass.getCountInteger()< SharedPreferencesClass.getLowerLimit() || addition+SharedPreferencesClass.getCountInteger()>SharedPreferencesClass.getUpperLimit())
        {
            if(SharedPreferencesClass.getVibrationCheck())
                ((Vibrator)getSystemService(Context.VIBRATOR_SERVICE)).vibrate(128);
            if(SharedPreferencesClass.getSoundCheck())
            {
                RingtoneManager.getRingtone
                        (
                                getApplicationContext(),
                                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                        ).play();
            }
        }

        SharedPreferencesClass.setCountInteger(MathUtils.clamp(addition+SharedPreferencesClass.getCountInteger(), SharedPreferencesClass.getLowerLimit(), SharedPreferencesClass.getUpperLimit()));
        counterTextView.setText(String.valueOf(SharedPreferencesClass.getCountInteger()));
    }
}