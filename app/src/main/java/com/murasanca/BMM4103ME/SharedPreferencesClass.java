package com.murasanca.BMM4103ME;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesClass
{
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static void Setup(Context context)
    {
        sharedPreferences=context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public static int getCountInteger()
    {
        return sharedPreferences.getInt("countInteger",0);
    }
    public static void setCountInteger(int value)
    {
        editor.putInt("countInteger",value);
        editor.apply();
    }

    public static int getUpperLimit()
    {
        return sharedPreferences.getInt("upperLimit",16);
    }
    public static void setUpperLimit(int value)
    {
        editor.putInt("upperLimit",value);
        editor.apply();
    }

    public static int getLowerLimit()
    {
        return sharedPreferences.getInt("lowerLimit",16);
    }
    public static void setLowerLimit(int value)
    {
        editor.putInt("lowerLimit",value);
        editor.apply();
    }

    public static boolean getVibrationCheck()
    {
        return sharedPreferences.getBoolean("vibrationCheck",true);
    }
    public static void setVibrationCheck(boolean bool)
    {
        editor.putBoolean("vibrationCheck",bool);
        editor.apply();
    }

    public static boolean getSoundCheck()
    {
        return sharedPreferences.getBoolean("soundCheck",true);
    }
    public static void setSoundCheck(boolean bool)
    {
        editor.putBoolean("soundCheck",bool);
        editor.apply();
    }
}
