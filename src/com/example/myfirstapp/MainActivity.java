package com.example.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent; 


public class MainActivity extends Activity
{
    SingletouchView mSingletouchView; 

    private static final String TAG = "MainActivity";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        // mSingletouchView = new SingletouchView(,);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // setContentView(mSingletouchView);
    }
    /** no back button */
    @Override
    public void onBackPressed() { 
        Log.i(TAG, "back"); 
    }

}
