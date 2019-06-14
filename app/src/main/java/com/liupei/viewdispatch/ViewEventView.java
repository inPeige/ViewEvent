package com.liupei.viewdispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ViewEventView extends View {
    public final static String CURRENTTAG = "ViewEventView:";
    public ViewEventView(Context context) {
        super(context);
    }

    public ViewEventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewEventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ViewEventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(MainActivity.TAG, CURRENTTAG + "onTouchEvent");
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(MainActivity.TAG, CURRENTTAG + "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }
}
