package com.zeromirai.classassistant.schedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zeromirai.android.util.ZRLog;
import com.zeromirai.classassistant.R;
import com.zeromirai.classassistant.authentication.JwcLoginActivity;
import com.zeromirai.classassistant.main.MainActivity;
import com.zeromirai.classassistant.schedule.runnable.FetchScheduleRunnable;

import java.lang.ref.WeakReference;


public class JwcLoginActivity extends AppCompatActivity {

    public static final String TAG = JwcLoginActivity.class.getName();

    private EditText editText_sno;
    private EditText editText_password;
    private LinearLayout linearLayout_btn_jwclogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jwc_login);
    }

    private void setView(){
        editText_sno = (EditText) findViewById(R.id.editText_sno);
        editText_password = (EditText) findViewById(R.id.editText_password);
    }

    private void setListeners(){
        linearLayout_btn_jwclogin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:{
                        linearLayout_btn_jwclogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_button_login_pressed));
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        linearLayout_btn_jwclogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_button_login));

                        startActivity(new Intent(JwcLoginActivity.this,MainActivity.class));

                        JwcLoginActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //LoginFragment.this.setViewDisabled();
                                Toast.makeText(JwcLoginActivity.this,"setViewDisabled",Toast.LENGTH_LONG);
                                ZRLog.d(TAG,"setViewDisabled");
                            }
                        });
                        String[] args = new String[] {
                                editText_sno.getText().toString(),
                                editText_password.getText().toString(),
                                "",
                                ""
                        };
                        new Thread(new FetchScheduleRunnable(JwcLoginActivity.this,,args)).start();
                        //showWaitingDialog();

                        break;
                    }
                    default:{ }
                }
                /*在onTouchEvent的Motion.ACTION_DOWN分支如果返回的是false，那么onTouch事件将不会往下面传递下去，如果返回true，那么onTouch事件将会传递下去，从而可以响应Motion.ACTION_UP分支。*/
                return true;
            }
        });
    }


    private Handler mHandler = new JwcDataHandler(this);

    class JwcDataHandler extends Handler{
        private WeakReference<JwcLoginActivity> jwcLoginActivity;
        public JwcDataHandler(JwcLoginActivity jwcLoginActivity){
            this.jwcLoginActivity = new WeakReference<JwcLoginActivity>(jwcLoginActivity);
        }
        public void handleMessage(Message msg) {
            Activity activity = this.jwcLoginActivity.get();
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


}
