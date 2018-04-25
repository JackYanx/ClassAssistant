package com.zeromirai.classassistant.authentication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zeromirai.classassistant.R;
import com.zeromirai.classassistant.authentication.fragment.LoginFragment;
import com.zeromirai.classassistant.authentication.fragment.RegisterFragment;

public class AuthenticationActivity extends Activity {

    public static final String TAG = AuthenticationActivity.class.getName();

    private int fragmentStatus = 0;

    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
    private LoginFragment loginFragment = null;
    private RegisterFragment registerFragment = null;

    private TextView textView_login;
    private TextView textView_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        setView();
        setListeners();
        //throw new RuntimeException("突然想抛个异常>_<");
    }

    private void setView(){

        textView_login = (TextView) findViewById(R.id.textView_login);
        textView_register = (TextView) findViewById(R.id.textView_register);

        /*初始化相关Fragments*/
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        /*添加第一个fragment并隐藏*/
        if(null == loginFragment){
            loginFragment = new LoginFragment();
        }
        if(!loginFragment.isAdded()){
            fragmentTransaction.add(R.id.frameLayout_loginregfragment,loginFragment);
        }
        fragmentTransaction.hide(loginFragment);

        /*添加第二个fragment并隐藏*/
        if(null == registerFragment){
            registerFragment = new RegisterFragment();
        }
        if(!registerFragment.isAdded()){
            fragmentTransaction.add(R.id.frameLayout_loginregfragment,registerFragment);
        }
        fragmentTransaction.hide(registerFragment);

        /*显示默认fragment*/
        fragmentTransaction.show(loginFragment);
        fragmentTransaction.commit();
        fragmentStatus = 0;
    }

    private void setListeners(){

        textView_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"textView_login pressed");

                if(fragmentStatus == 0){
                    return;
                }
                fragmentStatus = 0;
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(registerFragment);
                fragmentTransaction.show(loginFragment);
                fragmentTransaction.commit();

                textView_login.setTextColor(getResources().getColor(R.color.colorActivatedText));
                textView_register.setTextColor(getResources().getColor(R.color.colorInactivatedText));

                Log.d(TAG,"change to loginFragment");
            }
        });

        textView_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"textView_register pressed");

                if(fragmentStatus == 1){
                    return;
                }
                fragmentStatus = 1;
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(loginFragment);
                fragmentTransaction.show(registerFragment);
                fragmentTransaction.commit();

                textView_register.setTextColor(getResources().getColor(R.color.colorActivatedText));
                textView_login.setTextColor(getResources().getColor(R.color.colorInactivatedText));

                Log.d(TAG,"change to registerFragment");
            }
        });
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
