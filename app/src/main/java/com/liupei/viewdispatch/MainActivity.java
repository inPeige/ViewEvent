package com.liupei.viewdispatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "ViewEvent";
    public final static String CURRENTTAG = "MainActivity:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, CURRENTTAG + "onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, CURRENTTAG + "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }
}
