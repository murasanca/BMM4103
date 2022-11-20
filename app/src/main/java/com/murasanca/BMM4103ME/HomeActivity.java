package com.murasanca.BMM4103ME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        plusBttn.setOnClickListener(view -> counterTextView.setText(String.valueOf(++countInteger)));

        Button minusBttn= findViewById(R.id.minusButton);
        minusBttn.setOnClickListener(view -> counterTextView.setText(String.valueOf(--countInteger)));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN)
        {
            counterTextView.setText(String.valueOf(countInteger-=5));
            return true;
        }
        else if(keyCode==KeyEvent.KEYCODE_VOLUME_UP)
        {
            counterTextView.setText(String.valueOf(countInteger+=5));
            return true;
        }
        else
            return super.onKeyDown(keyCode,event);
    }
}