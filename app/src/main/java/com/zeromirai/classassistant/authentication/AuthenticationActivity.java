package com.zeromirai.classassistant.authentication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import com.zeromirai.classassistant.R;
import com.zeromirai.classassistant.authentication.fragment.LoginFragment;
import com.zeromirai.classassistant.authentication.fragment.RegisterFragment;

public class AuthenticationActivity extends Activity {

    public static final String TAG = AuthenticationActivity.class.getName();

    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
    private LoginFragment loginFragment = null;
    private RegisterFragment registerFragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        //setView();
        //throw new RuntimeException("突然想抛个异常>_<");
    }

    private void setView(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG,"onStart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG,"onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG,"onStop()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG,"onRestart()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState()");
        /**
         *  例如:
         *  1,屏幕方向改变时,Activity被销毁再重建;
         *  2,当前Activity处于后台,系统资源紧张将其杀死.
         *  3,另外,当跳转到其他Activity或者按Home键回到主屏时该方法也会被调用,系统是为了保存当前View组件的状态.
         *  4,在onStop之前被调用也可能在onPause()之前.
         *  tip: 官方文档为it does so before onStop() and possibly before onPause()..
         *<span style="white-space:pre">  </span>
         *  通常在此方法中保存Activity的一些临时状态.
         */
    }



}
