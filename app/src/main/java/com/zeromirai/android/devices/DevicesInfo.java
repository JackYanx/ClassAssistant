package com.zeromirai.android.devices;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by initialize on 2018/4/9.
 */

public final class DevicesInfo {

    private static final String TAG = DevicesInfo.class.getName();
    private static final String ERRMSG = "The function in class '" + TAG + "' was used without initialization.The variable 'ApplicationContext' is null";

    private static Context ApplicationContext = null;

    private static int DI_ScreenWidthPix = 0;
    private static int DI_ScreenHeightPix = 0;

    private static int DI_ScreenWidthDp = 0;
    private static int DI_ScreenHeightDp = 0;

    private static float DI_ScreenDensity = 0.0f;
    private static int DI_ScreenDensityDpi = 0;



    public static boolean initDevicesInfo(Context context){
        if(null == context){
            return false;
        }
        synchronized (DevicesInfo.class){
            ApplicationContext = context;

            //屏幕相关信息
            WindowManager wm = (WindowManager)ApplicationContext.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            DI_ScreenWidthPix = dm.widthPixels;         // 屏幕宽度（像素）
            DI_ScreenHeightPix = dm.heightPixels;       // 屏幕高度（像素）
            DI_ScreenDensity = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
            DI_ScreenDensityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
            // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
            DI_ScreenWidthDp = (int) (DI_ScreenWidthPix / DI_ScreenDensity);  // 屏幕宽度(dp)
            DI_ScreenHeightDp = (int) (DI_ScreenHeightPix / DI_ScreenDensity);// 屏幕高度(dp)


            //TODO:其他信息
        }





        return true;
    }

    private static void checkContext(){
        if(null == ApplicationContext){
            Log.e(TAG,ERRMSG);
            System.exit(-1);
        }
    }

    public static Context getApplicationContext(){
        checkContext();
        return ApplicationContext;
    }

    public static int getScreenWidthPix(){
        checkContext();
        return DI_ScreenWidthPix;
    }
    public static int getScreenHeightPix(){
        checkContext();
        return DI_ScreenHeightPix;
    }
    public static int getScreenWidthDp(){
        checkContext();
        return DI_ScreenWidthDp;
    }
    public static int getScreenHeightDp(){
        checkContext();
        return DI_ScreenHeightDp;
    }



}
