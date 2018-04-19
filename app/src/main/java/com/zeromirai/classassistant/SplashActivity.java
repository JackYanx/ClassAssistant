package com.zeromirai.classassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.zeromirai.android.network.BaseHttpClient;
import com.zeromirai.android.permission.PermissionManager;
import com.zeromirai.android.share.SystemShare;
import com.zeromirai.android.text.RegexUtils;
import com.zeromirai.android.util.Math;
import com.zeromirai.android.view.CartesianCoordinateView;
import com.zeromirai.android.view.GeometricView;
import com.zeromirai.classassistant.authentication.AuthenticationActivity;
import com.zeromirai.classassistant.common.model.Classcommittee;
import com.zeromirai.classassistant.common.model.Clazz;
import com.zeromirai.classassistant.main.MainActivity;

public class SplashActivity extends Activity {

    public static final String TAG = SplashActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(new Runnable() {
            @Override
            public void run(){
                try{
                    Thread.sleep(10000);
                    BaseHttpClient baseHttpClient = new BaseHttpClient();
                    baseHttpClient.get("666");
                    RegexUtils.isChinese("666");
                    SystemShare.shareText(SplashActivity.this,"");
                    PermissionManager.verifyStoragePermissions(SplashActivity.this);
                    Math.findKthNum(new int[]{5,5,5},5,5,5);
                    new Classcommittee();
                    new Clazz();
                    new CartesianCoordinateView(SplashActivity.this);
                    new GeometricView(SplashActivity.this);
                }catch (Exception e){

                }

            }
        }).start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isFirstRun = true;
                if(isFirstRun){
                    Log.d(TAG,"isFirstRun:"+isFirstRun+" | Go To AuthenticationActivity");
                    startActivity(new Intent(SplashActivity.this, AuthenticationActivity.class));
                    SplashActivity.this.finish();
                }
                else {
                    Log.d(TAG,"isFirstRun:"+isFirstRun+" | Go To MainActivity");
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    SplashActivity.this.finish();
                }

            }
        }, 2700);

    }
}
