package com.zeromirai.classassistant.authentication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.zeromirai.android.permission.PermissionManager;
import com.zeromirai.android.util.ZRLog;
import com.zeromirai.classassistant.R;
import com.zeromirai.classassistant.authentication.fragment.LoginFragment;
import com.zeromirai.classassistant.authentication.fragment.RegisterFragment;

import java.lang.ref.WeakReference;

public class AuthenticationActivity extends Activity {

    public static final String TAG = AuthenticationActivity.class.getName();

    private int fragmentStatus = 0;
    private int isInProcess = 0;

    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
    private LoginFragment loginFragment = null;
    private RegisterFragment registerFragment = null;

    private TextView textView_login;
    private TextView textView_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_authentication);
        setView();
        setListeners();
        PermissionManager.verifyStoragePermissions(AuthenticationActivity.this);
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
                ZRLog.d(TAG,"textView_login pressed");

                if(!switchToLoginUI()){
                    return;
                }

            }
        });

        textView_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZRLog.d(TAG,"textView_register pressed");

                if(!switchToRegisterUI()){
                    return;
                }


            }
        });
    }



    @Override
    protected void onStart(){
        super.onStart();
        ZRLog.d(TAG,"onStart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        ZRLog.d(TAG,"onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        ZRLog.d(TAG,"onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        ZRLog.d(TAG,"onStop()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        ZRLog.d(TAG,"onRestart()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ZRLog.d(TAG,"onDestroy()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ZRLog.d(TAG,"onSaveInstanceState()");
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

    public boolean switchToLoginUI(){
        if(fragmentStatus == 0){
            ZRLog.d(TAG,"Already in LoginUI");
            return false;
        }
        fragmentStatus = 0;
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(registerFragment);
        fragmentTransaction.show(loginFragment);
        fragmentTransaction.commit();

        textView_login.setTextColor(getResources().getColor(R.color.colorActivatedText));
        textView_register.setTextColor(getResources().getColor(R.color.colorInactivatedText));

        ZRLog.d(TAG,"switchToLoginUI");
        return true;
    }

    public boolean switchToRegisterUI(){
        if(fragmentStatus == 1){
            ZRLog.d(TAG,"Already in RegisterUI");
            return false;
        }
        fragmentStatus = 1;
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(loginFragment);
        fragmentTransaction.show(registerFragment);
        fragmentTransaction.commit();

        textView_register.setTextColor(getResources().getColor(R.color.colorActivatedText));
        textView_login.setTextColor(getResources().getColor(R.color.colorInactivatedText));

        ZRLog.d(TAG,"switchToRegisterUI");
        return true;
    }


    private Handler mHandler = new AuthenticationHandler(this);

    class AuthenticationHandler extends Handler{
        private WeakReference<AuthenticationActivity> authenticationActivity;
        public AuthenticationHandler(AuthenticationActivity authenticationActivity){
            this.authenticationActivity = new WeakReference<AuthenticationActivity>(authenticationActivity);
        }
        public void handleMessage(Message msg) {
            Activity activity = this.authenticationActivity.get();
            if (activity == null) return;
            /*登录*/
            if(fragmentStatus == 0 && loginFragment != null){
                switch (msg.what){
                    case 0:{
                        loginFragment.dismissWaitingDialog();
                        loginFragment.setViewEnabled();
                        break;
                    }
                    case 1:{
                        loginFragment.dismissWaitingDialog();
                        loginFragment.setViewEnabled();
                        break;
                    }
                    case 2:{
                        loginFragment.dismissWaitingDialog();
                        loginFragment.setViewEnabled();
                        break;
                    }
                    case 3:{
                        loginFragment.dismissWaitingDialog();
                        loginFragment.setViewEnabled();
                        break;
                    }
                    case 4:{
                        loginFragment.dismissWaitingDialog();
                        loginFragment.setViewEnabled();
                        break;
                    }
                    case 5:{
                        loginFragment.dismissWaitingDialog();
                        loginFragment.setViewEnabled();
                        break;
                    }
                    case 6:{
                        loginFragment.dismissWaitingDialog();
                        loginFragment.setViewEnabled();
                        break;
                    }
                    case 7:{
                        loginFragment.dismissWaitingDialog();
                        loginFragment.setViewEnabled();
                        break;
                    }
                    case 8:{
                        loginFragment.dismissWaitingDialog();
                        loginFragment.setViewEnabled();
                        break;
                    }
                    case 9:{
                        loginFragment.dismissWaitingDialog();
                        loginFragment.setViewEnabled();
                        break;
                    }
                    case 10:{
                        loginFragment.dismissWaitingDialog();
                        loginFragment.setViewEnabled();
                        break;
                    }
                    default:{
                        loginFragment.dismissWaitingDialog();
                        loginFragment.setViewEnabled();
                        break;
                    }
                }
            }
            /*注册*/
            else if(fragmentStatus == 1 && registerFragment != null){
                switch (msg.what){
                    case 0:{

                        break;
                    }
                    case 1:{

                        break;
                    }
                    case 2:{

                        break;
                    }
                    case 3:{

                        break;
                    }
                    case 4:{

                        break;
                    }
                    case 5:{

                        break;
                    }
                    case 6:{

                        break;
                    }
                    case 7:{

                        break;
                    }
                    case 8:{

                        break;
                    }
                    case 9:{

                        break;
                    }
                    case 10:{

                        break;
                    }
                    case 11:{

                        break;
                    }
                    case 12:{

                        break;
                    }
                    default:{

                        break;
                    }
                }
            }
            /*其他*/
            else{
                ZRLog.d(TAG, "ERROR STATUS");
                throw new RuntimeException(Config.ERRMSG_XXX);
            }

        }
    }

    public void sendMessageByHandler(int what, Object object) {
        Message message = mHandler.obtainMessage();
        message.what = what;
        message.obj = object;
        mHandler.sendMessage(message);
    }

}
