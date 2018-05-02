package com.zeromirai.classassistant.authentication.runnable;

import android.text.TextUtils;

import com.zeromirai.android.network.BaseHttpClient;
import com.zeromirai.android.text.RegexUtils;
import com.zeromirai.android.util.ZRLog;
import com.zeromirai.classassistant.authentication.AuthenticationActivity;
import com.zeromirai.classassistant.authentication.Config;
import com.zeromirai.classassistant.common.cache.LocalCache;

import org.json.JSONObject;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by initialize on 2018/4/16.
 */

public class RegisterRunnable implements Runnable {

    public static final String TAG = RegisterRunnable.class.getName();

    private AuthenticationActivity authenticationActivity;
    private String userName;
    private String password;
    private String phoneNumber;
    private String emailAddress;
    private int regType;
    private BaseHttpClient baseHttpClient;

    public RegisterRunnable(AuthenticationActivity authenticationActivity, String[] args){
        this.authenticationActivity = authenticationActivity;
        regType = Integer.parseInt(args[0]);
        userName = args[1];
        if(regType == 2){
            phoneNumber = args[2];
        }else if(regType == 3){
            emailAddress = args[2];
        }else{
            ZRLog.d(TAG,"regType: " + args[0]);
            throw new RuntimeException(Config.ERRMSG_XXX);
        }
        password = args[3];
    }

    @Override
    public void run(){
        ZRLog.d(TAG,"RegisterRunnableStarting");
        //此变量记录注册进度
        int processCode = 1;
        try{

            /*验证用户名/手机号/邮箱地址输入是否为空*/
            if(TextUtils.isEmpty(userName)){
                throw new Exception();
            }
            processCode = 2;

            /*验证是否为合法用户名/手机号/邮箱地址*/
            if(!RegexUtils.isLegalUsername(userName)){
                throw new Exception();
            }
            processCode = 3;

            if(regType == 2){
                /*验证手机号输入是否为空*/
                if(TextUtils.isEmpty(phoneNumber)){
                    throw new Exception();
                }
                processCode = 4;

                /*验证是否为合法手机号*/
                if(!RegexUtils.isLegalPhoneNumber(phoneNumber)){
                    throw new Exception();
                }
                processCode = 5;

            }else if(regType == 3){
                /*验证邮箱输入是否为空*/
                if(TextUtils.isEmpty(emailAddress)){
                    throw new Exception();
                }
                processCode = 4;

                /*验证是否为合法邮箱地址*/
                if(!RegexUtils.isLegalEmailAdderss(emailAddress)){
                    throw new Exception();
                }
                processCode = 5;

            }else{
                ZRLog.d(TAG,"regType: " + regType);
                throw new RuntimeException(Config.ERRMSG_XXX);
            }

            /*验证密码输入是否为空*/
            if(TextUtils.isEmpty(password)){
                throw new Exception();
            }
            processCode = 6;

            /*验证密码是否合法*/
            if(!RegexUtils.isLegalPassword(password)){
                throw new Exception();
            }
            processCode = 7;

            /*构造Post参数表*/
            SortedMap<String,String> params = new TreeMap<>();
            params.put("userName",userName);
            switch (regType){
                case 2:
                    params.put("phoneNumber",phoneNumber);
                    break;
                case 3:
                    params.put("emailAddress",emailAddress);
                    break;
                default:
                    throw new Exception();
            }
            params.put("password",password);
            params.put("timeStamp",""+System.currentTimeMillis() / 1000);
            params.put("actionType","register");
            processCode = 8;

            /*检查网络连接*/
            if(!BaseHttpClient.isNetworkAvailable(authenticationActivity)){
                ZRLog.d(TAG, "Network is not available.Please check connection or permission about Internet");
                throw new Exception();
            }
            processCode = 9;

            /*发送Post请求(阻塞)*/
            baseHttpClient = new BaseHttpClient();
            String result = baseHttpClient.post(Config.API_REG,params);
            ZRLog.d(TAG, "REG RET MSG:\n" + result);
            processCode = 10;

            /*解析为Json类型数据*/
            JSONObject jsonResult = new JSONObject(result);
            processCode = 11;

            /*响应返回数据错误码(0为成功)*/
            int errCode = jsonResult.optInt("errCode");
            String errMsg = jsonResult.optString("errMsg");
            JSONObject jsonRegData = jsonResult.optJSONObject("data");
            if(errCode != 0 || !"OK".equalsIgnoreCase(errMsg)){
                throw new Exception();
            }
            processCode = 12;

            /*返回数据进行缓存*/
            LocalCache cache = new LocalCache(authenticationActivity);
            cache.clear();
            cache.setUserData(jsonRegData.toString());
            processCode = 0;
            ZRLog.d(TAG,"Register Success");

        }catch (Exception e){
            ZRLog.d(TAG,Config.ERRMSG_REG[processCode] + e.getMessage());
        }finally {
            authenticationActivity.sendMessageByHandler(processCode,Config.ERRMSG_REG[processCode]);
            ZRLog.d(TAG,"RegisterRunnableClosing");
        }
    }

//    public void sendMessage(int what, Object object) {
//        Message message = mHandler.obtainMessage();
//        message.what = what;
//        message.obj = object;
//        mHandler.sendMessage(message);
//    }
}
