package com.zeromirai.android.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by JY on 2018/4/9.
 */

public class CartesianCoordinateView extends View implements ViewTreeObserver.OnGlobalLayoutListener{

    public static final String TAG = CartesianCoordinateView.class.getName();

    private Paint mPaint = new Paint();

    /*View宽度与高度*/
    private int coordinateViewWidthPix = 0;
    private int coordinateViewHeightPix = 0;

    /*View左下角原点相对坐标*/
    private int coordinateSystemOriginPointPosiX = 0;
    private int coordinateSystemOriginPointPosiY = 0;

    /*坐标区域宽度与高度*/
    private int coordinateAreaWidthPix = 0;
    private int coordinateAreaHeightPix = 0;

    /*坐标区域边框大小*/
    private int coordinateAreaMarginLeftPix = 0;
    private int coordinateAreaMarginRightPix = 0;
    private int coordinateAreaMarginTopPix = 0;
    private int coordinateAreaMarginBottomPix = 0;

    /*坐标系X与Y轴线宽*/
    private float coordinateSystemXAxisLineStrokePix = 4.0f;
    private float coordinateSystemYAxisLineStrokePix = 4.0f;

    /*坐标系X与Y轴颜色*/
    private int coordinateSystemXAxisLineColor = Color.argb(255,100,100,100);
    private int coordinateSystemYAxisLineColor = Color.argb(255,100,100,100);

    /*坐标背景方格水平线相关属性,分别是1.是否显示背景线,2.线宽,3.线色,4.线间距*/
    private boolean isDisplayingBackgroundGirdHorizontalLine = true;
    private float coordinateBackgroundGirdHorizontalLineStrokePix = 2.0f;
    private int coordinateBackgroundGirdHorizontalLineColor = Color.argb(255,200,200,200);
    private int coordinateBackgroundGirdHorizontalLineSpacingPix = 80;

    /*坐标背景方格垂直线相关属性,分别是1.是否显示背景线,2.线宽,3.线色,4.线间距*/
    private boolean isDisplayingBackgroundGirdVerticalLine = true;
    private float coordinateBackgroundGirdVerticalLineStrokePix = 2.0f;
    private int coordinateBackgroundGirdVerticalLineColor = Color.argb(255,200,200,200);
    private int coordinateBackgroundGirdVerticalLineSpacingPix = 80;

    /*坐标X/Y方向绘图自适应*/
    private boolean coordinateDrawingXDirectAdaptation = false;
    private boolean coordinateDrawingYDirectAdaptation = false;

    /*函数点颜色*/
    private int functionImagePointsColor = Color.argb(255,150,150,150);

    /*函数线相关属性,分别是1.是否显示两点之间连线,2.线宽,3.线色*/
    private boolean isDisplayingFunctionImageLine = true;
    private float functionImageLineStrokePix = 5.0f;
    private int functionImageLineColor = Color.argb(255,100,100,100);


    /*坐标系数学属性内部类*/
    private class MathematicalProperties{
        /*坐标系显示区间,x∈[0,coordinateSystemMaxNumX],y∈[0,coordinateSystemMaxNumY]*/
        private int coordinateSystemMaxNumX = 100;
        private int coordinateSystemMaxNumY = 100;
        private float coordinateSystemXScale = 1.0f;
        private float coordinateSystemYScale = 1.0f;
        private int functionImagePointsMaxNum = 1000;
        private int functionImagePointsNum = 0;
        private LinkedList<Point> points = new LinkedList<>();
    }

    /*坐标系数学属性*/
    private MathematicalProperties mathematicalProperties = new MathematicalProperties();

    /*为坐标系中添加点*/
    public void addPoint(Point point){
        int pn = mathematicalProperties.functionImagePointsNum;
        if(pn > mathematicalProperties.functionImagePointsMaxNum){
            mathematicalProperties.functionImagePointsNum--;
            mathematicalProperties.points.removeFirst();
        }
        mathematicalProperties.functionImagePointsNum++;
        mathematicalProperties.points.addLast(point);
        Log.d(TAG,"addPoint: " + point.toString());
        return;

    }

    public void addPoints(Point[] points){

    }

    public void clearAllPoints(){
        mathematicalProperties.functionImagePointsNum = 0;
        mathematicalProperties.points.clear();
    }

    private void drawCoordinatePoints(Canvas canvas){
        int pn = mathematicalProperties.functionImagePointsNum;
        Point point1, point2;
        //Iterator iterator = mathematicalProperties.points.iterator();
        ListIterator listIterator = mathematicalProperties.points.listIterator();
        mPaint.setColor(functionImagePointsColor);
        mPaint.setStrokeWidth(functionImageLineStrokePix);
        float x1;
        float y1;
        float x2;
        float y2;
        if(pn == 0){
            return;
        }else if(pn == 1){
            point1 = (Point)listIterator.next();
            x1 = coordinateSystemOriginPointPosiX + point1.x * mathematicalProperties.coordinateSystemXScale;
            y1 = coordinateSystemOriginPointPosiY - point1.y * mathematicalProperties.coordinateSystemYScale;
            canvas.drawPoint(x1,y1,mPaint);
            return;
        }
        for(int i = 0;i < pn - 1;i++){
            point1 = (Point)listIterator.next();
            x1 = coordinateSystemOriginPointPosiX + point1.x * mathematicalProperties.coordinateSystemXScale;
            y1 = coordinateSystemOriginPointPosiY - point1.y * mathematicalProperties.coordinateSystemYScale;
            canvas.drawPoint(x1,y1,mPaint);

            point2 = (Point)listIterator.next();
            x2 = coordinateSystemOriginPointPosiX + point2.x * mathematicalProperties.coordinateSystemXScale;
            y2 = coordinateSystemOriginPointPosiY - point2.y * mathematicalProperties.coordinateSystemYScale;
            canvas.drawPoint(x2,y2,mPaint);

            if(isDisplayingFunctionImageLine){
                canvas.drawLine(x1,y1,x2,y2,mPaint);
            }
            if(x2 > coordinateAreaMarginLeftPix + coordinateAreaWidthPix){
                clearAllPoints();
                return;
            }

            listIterator.previous();
        }


    }



    public CartesianCoordinateView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        //设置监听器,监听View进行Layout操作事件,获取Layout后View的宽度与高度,避免view.getWidth()或是view.getHeiht()方法返回值为0的情况
        this.getViewTreeObserver().addOnGlobalLayoutListener(this);
        //attributeSet.
    }

    public CartesianCoordinateView(Context context){
        super(context);
        //设置监听器,监听View进行Layout操作事件,获取Layout后View的宽度与高度,避免view.getWidth()或是view.getHeiht()方法返回值为0的情况
        this.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        CartesianCoordinateView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        int height = CartesianCoordinateView.this.getMeasuredHeight();
        int width = CartesianCoordinateView.this.getMeasuredWidth();
        Log.i(TAG,"CartesianCoordinateViewWidth: " + width + " |CartesianCoordinateViewHeight: " + height);
        coordinateViewWidthPix = width;
        coordinateViewHeightPix = height;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        calcAttr();



        drawCoordinateBackgroundGird(canvas);
        drawCoordinateXYAxisLine(canvas);
        drawCoordinatePoints(canvas);
    }

    private void drawCoordinateBackgroundGird(Canvas canvas){

        /*绘制坐标背景方格水平线*/
        if(isDisplayingBackgroundGirdHorizontalLine && coordinateBackgroundGirdHorizontalLineSpacingPix > 0){
            int point1_x = coordinateSystemOriginPointPosiX, point1_y = coordinateSystemOriginPointPosiY - coordinateBackgroundGirdHorizontalLineSpacingPix;
            int point2_x = coordinateSystemOriginPointPosiX + coordinateAreaWidthPix, point2_y = coordinateSystemOriginPointPosiY - coordinateBackgroundGirdHorizontalLineSpacingPix;
            mPaint.setColor(coordinateBackgroundGirdHorizontalLineColor);
            mPaint.setStrokeWidth(coordinateBackgroundGirdHorizontalLineStrokePix);
            while(point1_y >= coordinateAreaMarginTopPix){
                canvas.drawLine(point1_x,point1_y,point2_x,point2_y,mPaint);
                point1_y -= coordinateBackgroundGirdHorizontalLineSpacingPix;
                point2_y = point1_y;
            }
        }

        /*绘制坐标背景方格垂直线*/
        if(isDisplayingBackgroundGirdVerticalLine && coordinateBackgroundGirdVerticalLineSpacingPix > 0){
            int point1_x = coordinateSystemOriginPointPosiX + coordinateBackgroundGirdVerticalLineSpacingPix, point1_y = coordinateSystemOriginPointPosiY;
            int point2_x = coordinateSystemOriginPointPosiX + coordinateBackgroundGirdVerticalLineSpacingPix, point2_y = coordinateAreaMarginBottomPix;
            mPaint.setColor(coordinateBackgroundGirdVerticalLineColor);
            mPaint.setStrokeWidth(coordinateBackgroundGirdVerticalLineStrokePix);
            while(point1_x <= coordinateAreaWidthPix + coordinateAreaMarginLeftPix){
                canvas.drawLine(point1_x,point1_y,point2_x,point2_y,mPaint);
                point1_x += coordinateBackgroundGirdVerticalLineSpacingPix;
                point2_x = point1_x;
            }
        }

    }

    private void drawCoordinateXYAxisLine(Canvas canvas){

        /*绘制X轴*/
        mPaint.setColor(coordinateSystemXAxisLineColor);
        mPaint.setStrokeWidth(coordinateSystemXAxisLineStrokePix);
        canvas.drawLine(coordinateSystemOriginPointPosiX, coordinateSystemOriginPointPosiY, coordinateSystemOriginPointPosiX + coordinateAreaWidthPix, coordinateSystemOriginPointPosiY, mPaint);

        /*绘制Y轴*/
        mPaint.setColor(coordinateSystemYAxisLineColor);
        mPaint.setStrokeWidth(coordinateSystemYAxisLineStrokePix);
        canvas.drawLine(coordinateSystemOriginPointPosiX, coordinateSystemOriginPointPosiY, coordinateSystemOriginPointPosiX, coordinateAreaMarginTopPix,mPaint);

        /*由drawLine()原理可知,线宽是从中点向两侧计算的,当线宽大于1.0时左下角会有一点缺失,将该点绘制*/
        canvas.drawPoints(new float[]{coordinateSystemOriginPointPosiX,coordinateSystemOriginPointPosiY},mPaint);

    }








    /*计算相关属性值*/
    private void calcAttr(){
        coordinateAreaWidthPix = coordinateViewWidthPix - coordinateAreaMarginLeftPix - coordinateAreaMarginRightPix;
        coordinateAreaHeightPix = coordinateViewHeightPix - coordinateAreaMarginTopPix - coordinateAreaMarginBottomPix;
        coordinateSystemOriginPointPosiX = coordinateAreaMarginLeftPix;
        coordinateSystemOriginPointPosiY = coordinateViewHeightPix - coordinateAreaMarginBottomPix;
    }

    public void setCoordinateAreaMarginPix(int top,int right,int bottom,int left){
        this.coordinateAreaMarginTopPix = top;
        this.coordinateAreaMarginRightPix = right;
        this.coordinateAreaMarginBottomPix = bottom;
        this.coordinateAreaMarginLeftPix = left;
        calcAttr();
        //Log.d(TAG,""+coordinateAreaMarginTopPix+" "+coordinateAreaMarginRightPix+" "+coordinateAreaMarginBottomPix+" "+coordinateAreaMarginLeftPix+" "++" "++" "++" "++" "+);
    }

    public int[] getCoordinateAreaMarginPix(){
        return new int[]{this.coordinateAreaMarginTopPix, this.coordinateAreaMarginRightPix, this.coordinateAreaMarginBottomPix, this.coordinateAreaMarginLeftPix};
    }

    public int getCoordinateAreaWidthPix(){
        return this.coordinateAreaWidthPix;
    }

    public int getCoordinateViewHeightPix(){
        return this.coordinateViewHeightPix;
    }

}
