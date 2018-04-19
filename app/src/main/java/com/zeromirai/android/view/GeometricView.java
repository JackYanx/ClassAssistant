package com.zeromirai.android.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by initialize on 2018/4/18.
 */

public class GeometricView extends View implements ViewTreeObserver.OnGlobalLayoutListener{

    public static final String TAG = GeometricView.class.getName();

    private Paint mPaint = new Paint();

    /*View宽度与高度*/
    private int geometricViewWidthPix = 0;
    private int geometricViewHeightPix = 0;

    public GeometricView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        //设置监听器,监听View进行Layout操作事件,获取Layout后View的宽度与高度,避免view.getWidth()或是view.getHeiht()方法返回值为0的情况
        this.getViewTreeObserver().addOnGlobalLayoutListener(this);
        //attributeSet.
    }

    public GeometricView(Context context){
        super(context);
        //设置监听器,监听View进行Layout操作事件,获取Layout后View的宽度与高度,避免view.getWidth()或是view.getHeiht()方法返回值为0的情况
        this.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        GeometricView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        int height = GeometricView.this.getMeasuredHeight();
        int width = GeometricView.this.getMeasuredWidth();
        Log.i(TAG,"GeometricView: " + width + " |GeometricView: " + height);
        geometricViewWidthPix = width;
        geometricViewHeightPix = height;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

    }

    public int getGeometricViewWidthPix(){
        return geometricViewWidthPix;
    }

    public int getGeometricViewHeightPix(){
        return geometricViewHeightPix;
    }

}
