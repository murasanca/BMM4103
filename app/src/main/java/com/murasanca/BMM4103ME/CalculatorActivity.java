package com.murasanca.BMM4103ME;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;

import java.util.Objects;

public class CalculatorActivity extends AppCompatActivity
{
    private enum operations
    {
        addition,
        subtraction,
        multiplication,
        division
    }
    private operations operation=null;
    private EditText editText;
    private double x=0,y=0;
    private boolean onX=true;

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

        setContentView(R.layout.activity_calculator);

        editText=findViewById(R.id.editTextNumber);
        editText.setText(String.valueOf(0));
        editText.addTextChangedListener
        (
            new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){}
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2){}
                @Override
                public void afterTextChanged(Editable editable)
                {
                    try
                    {
                        if(onX)
                            x=Double.parseDouble(editable.toString());
                        else
                            y=Double.parseDouble(editable.toString());
                    }
                    catch (Exception exception){}
                }
            }
        );

        findViewById(R.id.allClearButton).setOnClickListener(view -> editText.setText(String.valueOf(x=y=0)));

        findViewById(R.id.clearButton).setOnClickListener
        (
                view ->
                {
                    if(onX)
                        editText.setText(String.valueOf(x=y=0));
                    else
                        editText.setText(String.valueOf(y=0));
                }
        );

        findViewById(R.id.equalButton).setOnClickListener
        (
                view ->
                {
                    switch (operation)
                    {
                        case addition:
                            x+=y;
                            break;
                        case subtraction:
                            x-=y;
                            break;
                        case multiplication:
                            x*=y;
                            break;
                        case division:
                            if(y!=0)
                                x/=y;
                            break;
                        default:
                            break;
                    }
                    editText.setText(String.valueOf(x));
                    onX=false;
                    y=0;
                }
        );

        findViewById(R.id.additionButton).setOnClickListener
        (
                view ->
                {
                    operation=operations.addition;
                    if(onX)
                        onX=false;
                    else
                        x+=y;
                    editText.setText(String.valueOf(0));
                }
        );

        findViewById(R.id.divisionButton).setOnClickListener
        (
                view ->
                {
                    operation=operations.division;
                    if(onX)
                        onX=false;
                    else if(y!=0)
                        x/=y;
                    editText.setText(String.valueOf(0));
                }
        );

        findViewById(R.id.multiplicationButton).setOnClickListener
        (
                view ->
                {
                    operation=operations.multiplication;
                    if(onX)
                        onX=false;
                    else
                        x*=y;
                    editText.setText(String.valueOf(0));
                }
        );

        findViewById(R.id.subtractionButton).setOnClickListener
        (
                view ->
                {
                    operation=operations.subtraction;
                    if(onX)
                        onX=false;
                    else
                        x-=y;
                    editText.setText(String.valueOf(0));
                }
        );

        findViewById(R.id.factorialButton).setOnClickListener
        (
                view ->
                {
                    if(onX)
                        editText.setText(String.valueOf(x=factorial(x)));
                    else
                        editText.setText(String.valueOf(y=factorial(y)));
                }
        );

        findViewById(R.id.sinusButton).setOnClickListener
        (
                view ->
                {
                    if(onX)
                        editText.setText(String.valueOf(x=Math.sin(Math.toRadians(x))));
                    else
                        editText.setText(String.valueOf(y=Math.sin(Math.toRadians(y))));
                }
        );

        findViewById(R.id.cosineButton).setOnClickListener
        (
                view ->
                {
                    if(onX)
                        editText.setText(String.valueOf(x=Math.cos(Math.toRadians(x))));
                    else
                        editText.setText(String.valueOf(y=Math.cos(Math.toRadians(y))));
                }
        );

        findViewById(R.id.tangentButton).setOnClickListener
        (
                view ->
                {
                    if(onX)
                        editText.setText(String.valueOf(x=Math.tan(Math.toRadians(x))));
                    else
                        editText.setText(String.valueOf(y=Math.tan(Math.toRadians(y))));
                }
        );
    }

    private double factorial(double number)
    {
        if(0<=number)
        {
            int f=1;
            for(int i=2;i<1+(int)number;++i)
                f*=i;

            return f;
        }
        else
            return number;
    }
}