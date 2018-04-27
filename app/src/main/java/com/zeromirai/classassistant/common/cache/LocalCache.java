package com.zeromirai.classassistant.common.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.json.JSONObject;

import static com.zeromirai.android.text.JsonUtils.optStrToJsonObject;

/**
 * Created by initialize on 2018/4/18.
 * LocalCache类,用于处理用户信息缓存
 * 基于SharedPreferences
 */

public class LocalCache extends AbstractCache {

    public static final String USERDATAINFO = "USERDATAINFO";

    /*
    * 构造函数,传入Context
    * */
    public LocalCache(Context context){
        super(context);
    }

    /*
    * 清除LocalCache模块下的全部缓存(用户信息缓存)
    */
    @Override
    public void clear() {
        clearUserData();
    }

    /*
    * 从缓存中获取用户信息
    */
    @Nullable
    public String getUserData(){
        String str = sharedPreferences.getString(USERDATAINFO, "");
        return str;
    }

    /*
    * 从缓存中获取用户信息(JSON)
    */
    @Nullable
    public JSONObject getUserDataJSON(){
        return optStrToJsonObject(getUserData());
    }

    /*
    * 更新缓存中用户信息
    * */
    public void setUserData(String str){
        if(TextUtils.isEmpty(str)){
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERDATAINFO, str);
        editor.apply();
    }

    /*
    * 更新缓存中用户信息(JSON)
    * */
    public void setUserDataJSON(JSONObject json){
        if(null == json){
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERDATAINFO, json.toString());
        editor.apply();
    }

    /*
    *清除缓存中用户信息
    * */
    public void clearUserData(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERDATAINFO, "");
        editor.apply();
    }
}
