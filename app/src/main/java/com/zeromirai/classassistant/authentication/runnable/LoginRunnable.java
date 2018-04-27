package com.zeromirai.classassistant.authentication.runnable;

import com.zeromirai.classassistant.authentication.AuthenticationActivity;

/**
 * Created by initialize on 2018/4/16.
 */

public class LoginRunnable implements Runnable {

    public static final String TAG = LoginRunnable.class.getName();

    private AuthenticationActivity authenticationActivity;
    private String userName;
    private String password;
    private String phoneNumber;
    private String emailAddress;

    public LoginRunnable(AuthenticationActivity authenticationActivity, String[] args){
        this.authenticationActivity = authenticationActivity;
        userName = args[0];
        password = args[1];
        phoneNumber = args[2];
        emailAddress = args[3];
    }

    @Override
    public void run(){
        //此变量记录注册进度
        int processCode = 1;
        try{

        }catch (Exception e){

        }finally {

        }
    }

//    public void sendMessage(int what, Object object) {
//        Message message = mHandler.obtainMessage();
//        message.what = what;
//        message.obj = object;
//        mHandler.sendMessage(message);
//    }

}
