package com.zeromirai.android.util;

import android.util.Log;

/**
 * Created by initialize on 2018/5/2.
 */

public final class ZRLog {

    public static int d(String tag, String msg){
        Log.d(tag,msg);

        return 0;
    }
    public static int i(String tag, String msg){
        Log.i(tag,msg);

        return 0;
    }
    public static int w(String tag, String msg){
        Log.w(tag,msg);

        return 0;
    }
    public static int v(String tag, String msg){
        Log.v(tag,msg);

        return 0;
    }
    public static int e(String tag, String msg){
        Log.e(tag,msg);

        return 0;
    }
}
