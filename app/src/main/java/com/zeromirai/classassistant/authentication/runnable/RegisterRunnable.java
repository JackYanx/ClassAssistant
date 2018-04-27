package com.zeromirai.classassistant.authentication.runnable;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * Created by initialize on 2018/4/16.
 */

public class RegisterRunnable implements Runnable {

    public static final String TAG = RegisterRunnable.class.getName();

    private Context mContext;
    private Handler mHandler;
    private String userName;
    private String password;
    private String phoneNumber;
    private String emailAddress;

    public RegisterRunnable(Context context, Handler handler, String[] args){
        mContext = context;
        mHandler = handler;
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

    public void sendMessage(int what, Object object) {
        Message message = mHandler.obtainMessage();
        message.what = what;
        message.obj = object;
        mHandler.sendMessage(message);
    }
}
