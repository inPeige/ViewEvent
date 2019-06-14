package com.liupei.viewdispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class ViewEventGroup extends LinearLayout {
    public final static String CURRENTTAG = "ViewEventGroup:";

    public ViewEventGroup(Context context) {
        super(context);
    }

    public ViewEventGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewEventGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ViewEventGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(MainActivity.TAG, CURRENTTAG + "onTouchEvent");
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(MainActivity.TAG, CURRENTTAG + "onInterceptTouchEvent");
        //将Group的onInterceptTouchEvent返回true
        //return true;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(MainActivity.TAG, CURRENTTAG + "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }
}
