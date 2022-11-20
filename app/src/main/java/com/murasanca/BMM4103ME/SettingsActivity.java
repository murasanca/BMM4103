package com.murasanca.BMM4103ME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity
{
    public static boolean
        isSoundChecked=true,
        isVibrationChecked=true;

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
    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(SettingsActivity.this,HomeActivity.class));
    }
}