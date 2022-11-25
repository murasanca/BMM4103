package com.murasanca.BMM4103ME;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

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
        setEditText(0);
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
                    catch (Exception exception)
                    {
                        Toast.makeText(getApplicationContext(),"Enter a number.",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        );

        findViewById(R.id.allClearButton).setOnClickListener(view ->setEditText(x=y=0));

        findViewById(R.id.clearButton).setOnClickListener
        (
                view ->
                {
                    if(onX)
                        setEditText(x=y=0);
                    else
                        setEditText(y=0);
                }
        );

        findViewById(R.id.equalButton).setOnClickListener
        (
                view ->
                {
                    if(operation!=null)
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
                    setEditText(x);
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
                    setEditText(0);
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
                    setEditText(0);
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
                    setEditText(0);
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
                    setEditText(0);
                }
        );

        findViewById(R.id.factorialButton).setOnClickListener
        (
                view ->
                {
                    if(onX)
                        setEditText(x=factorial(x));
                    else
                        setEditText(y=factorial(y));
                }
        );

        findViewById(R.id.sinusButton).setOnClickListener
        (
                view ->
                {
                    if(onX)
                        setEditText(x=Math.sin(Math.toRadians(x)));
                    else
                        setEditText(y=Math.sin(Math.toRadians(y)));
                }
        );

        findViewById(R.id.cosineButton).setOnClickListener
        (
                view ->
                {
                    if(onX)
                        setEditText(x=Math.cos(Math.toRadians(x)));
                    else
                        setEditText(y=Math.cos(Math.toRadians(y)));
                }
        );

        findViewById(R.id.tangentButton).setOnClickListener
        (
                view ->
                {
                    if(onX)
                        setEditText(x=Math.tan(Math.toRadians(x)));
                    else
                        setEditText(y=Math.tan(Math.toRadians(y)));
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

    private void setEditText(double value)
    {
        if((int)value==value)
            editText.setText(String.valueOf((int)value));
        else
            editText.setText(String.valueOf(value));
    }
}