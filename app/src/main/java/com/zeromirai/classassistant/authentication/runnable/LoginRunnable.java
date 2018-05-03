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

public class LoginRunnable implements Runnable {

    public static final String TAG = LoginRunnable.class.getName();

    private AuthenticationActivity authenticationActivity;
    private String userName;
    private String password;
    private String phoneNumber;
    private String emailAddress;
    private int loginType;
    private BaseHttpClient baseHttpClient;

    public LoginRunnable(AuthenticationActivity authenticationActivity, String[] args){
        this.authenticationActivity = authenticationActivity;
        userName = args[0];
        password = args[1];
    }

    @Override
    public void run(){
        ZRLog.d(TAG,"LoginRunnableStarting");
        //此变量记录登录进度
        int processCode = 1;
        try{

            /*验证用户名/手机号/邮箱地址输入是否为空*/
            if(TextUtils.isEmpty(userName)){
                throw new Exception();
            }
            processCode = 2;

            /*验证是否为合法用户名/手机号/邮箱地址*/
            if(RegexUtils.isLegalUsername(userName)){
                loginType = Config.LOGIN_WITH_USERNAME;
            }
            else if(RegexUtils.isLegalPhoneNumber(userName)){
                loginType = Config.LOGIN_WITH_PHONENUMBER;
                phoneNumber = userName;
            }
            else if(RegexUtils.isLegalEmailAdderss(userName)){
                loginType = Config.LOGIN_WITH_EMAILADDRESS;
                emailAddress = userName;
            }
            else {
                throw new Exception();
            }
            processCode = 3;

            /*验证密码输入是否为空*/
            if(TextUtils.isEmpty(password)){
                throw new Exception();
            }
            processCode = 4;

            /*验证密码是否合法*/
            if(!RegexUtils.isLegalPassword(password)){
                throw new Exception();
            }
            processCode = 5;

            /*构造Post参数表*/
            SortedMap<String,String> params = new TreeMap<>();
            switch (loginType){
                case Config.LOGIN_WITH_USERNAME:
                    params.put("userName",userName);
                    break;
                case Config.LOGIN_WITH_PHONENUMBER:
                    params.put("phoneNumber",phoneNumber);
                    break;
                case Config.LOGIN_WITH_EMAILADDRESS:
                    params.put("emailAddress",emailAddress);
                    break;
                default:
                    throw new Exception();
            }
            params.put("password",password);
            params.put("timeStamp",""+System.currentTimeMillis() / 1000);
            params.put("actionType","login");
            processCode = 6;

            /*检查网络连接*/
            if(!BaseHttpClient.isNetworkAvailable(authenticationActivity)){
                ZRLog.d(TAG, "Network is not available.Please check connection or permission about Internet");
                throw new Exception();
            }
            processCode = 7;

            /*发送Post请求(阻塞)*/
            baseHttpClient = new BaseHttpClient();
            String result = baseHttpClient.post(Config.API_LOGIN,params);
            ZRLog.d(TAG, "LOGIN RET MSG:\n" + result);
            processCode = 8;

            /*解析为Json类型数据*/
            JSONObject jsonResult = new JSONObject(result);
            processCode = 9;

            /*响应返回数据错误码(0为成功)*/
            int errCode = jsonResult.optInt("errCode");
            String errMsg = jsonResult.optString("errMsg");
            JSONObject jsonLoginData = jsonResult.optJSONObject("data");
            if(errCode != 0 || !"OK".equalsIgnoreCase(errMsg)){
                throw new Exception();
            }
            processCode = 10;

            /*返回数据进行缓存*/
            LocalCache cache = new LocalCache(authenticationActivity);
            cache.clear();
            cache.setUserData(jsonLoginData.toString());
            processCode = 0;

        }catch (Exception e){
            ZRLog.d(TAG,Config.ERRMSG_LOGIN[processCode] + e.getMessage());
        }finally {
            authenticationActivity.sendMessageByHandler(processCode,Config.ERRMSG_LOGIN[processCode]);
            ZRLog.d(TAG,"LoginRunnableClosing");
        }
    }

//    public void sendMessage(int what, Object object) {
//        Message message = mHandler.obtainMessage();
//        message.what = what;
//        message.obj = object;
//        mHandler.sendMessage(message);
//    }

}
