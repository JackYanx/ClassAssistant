package com.zeromirai.classassistant.schedule;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zeromirai.android.util.ZRLog;
import com.zeromirai.classassistant.R;
import com.zeromirai.classassistant.common.cache.LocalCache;
import com.zeromirai.classassistant.schedule.runnable.FetchScheduleRunnable;

import java.lang.ref.WeakReference;

public class JwcLoginActivity extends AppCompatActivity {

    public static final String TAG = JwcLoginActivity.class.getName();

    private EditText editText_sno;
    private EditText editText_password;
    private TextView textView_goto_search;
    private LinearLayout linearLayout_btn_jwclogin;
    private ProgressDialog waitingDialog;
    private LocalCache localCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jwc_login);
        localCache = new LocalCache(this);
        setView();
        setListeners();
        setCacheText();
    }

    private void setView(){
        editText_sno = (EditText) findViewById(R.id.editText_sno);
        editText_password = (EditText) findViewById(R.id.editText_password);
        textView_goto_search=(TextView)findViewById(R.id.textView_goto_search);
        linearLayout_btn_jwclogin = (LinearLayout) findViewById(R.id.linearLayout_btn_jwclogin);
    }

    private void setListeners(){
        linearLayout_btn_jwclogin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ZRLog.d(TAG,"btn_jwclogin onTouch()");
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:{
                        ZRLog.d(TAG,"btn_jwclogin ACT DOWN");
                        linearLayout_btn_jwclogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_button_login_pressed));
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        ZRLog.d(TAG,"btn_jwclogin ACT UP");
                        linearLayout_btn_jwclogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_button_login));
                        setViewDisabled();
                        showWaitingDialog();

                        String[] args = new String[] {
                                editText_sno.getText().toString(),
                                editText_password.getText().toString(),
                                "",
                                ""
                        };
                        new Thread(new FetchScheduleRunnable(JwcLoginActivity.this,mHandler,args)).start();

                        break;
                    }
                    default:{ }
                }
                /*在onTouchEvent的Motion.ACTION_DOWN分支如果返回的是false，那么onTouch事件将不会往下面传递下去，如果返回true，那么onTouch事件将会传递下去，从而可以响应Motion.ACTION_UP分支。*/
                return true;
            }
        });
        textView_goto_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JwcLoginActivity.this,SearchActivity.class));
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
            //ZRLog.d(TAG,"888");
            /*登录*/
            switch (msg.what){
                case 0:{
                    JwcLoginActivity.this.dismissWaitingDialog();
                    JwcLoginActivity.this.setViewEnabled();
                    showSuccessDialog((String) msg.obj + "\n" + localCache.getScheduleData());
                    break;
                }
                case 1:{
//                    JwcLoginActivity.this.dismissWaitingDialog();
//                    JwcLoginActivity.this.setViewEnabled();
//                    break;
                }
                case 2:{
//                    JwcLoginActivity.this.dismissWaitingDialog();
//                    JwcLoginActivity.this.setViewEnabled();
//                    break;
                }
                case 3:{
//                    JwcLoginActivity.this.dismissWaitingDialog();
//                    JwcLoginActivity.this.setViewEnabled();
//                    break;
                }
                case 4:{
//                    JwcLoginActivity.this.dismissWaitingDialog();
//                    JwcLoginActivity.this.setViewEnabled();
//                    break;
                }
                case 5:{
//                    JwcLoginActivity.this.dismissWaitingDialog();
//                    JwcLoginActivity.this.setViewEnabled();
//                    break;
                }
                case 6:{
//                    JwcLoginActivity.this.dismissWaitingDialog();
//                    JwcLoginActivity.this.setViewEnabled();
//                    break;
                }
                case 7:{
//                    JwcLoginActivity.this.dismissWaitingDialog();
//                    JwcLoginActivity.this.setViewEnabled();
//                    break;
                }
                case 8:{
//                    JwcLoginActivity.this.dismissWaitingDialog();
//                    JwcLoginActivity.this.setViewEnabled();
//                    break;
                }
                case 9:{
//                    JwcLoginActivity.this.dismissWaitingDialog();
//                    JwcLoginActivity.this.setViewEnabled();
//                    break;
                }
                case 10:{
//                    JwcLoginActivity.this.dismissWaitingDialog();
//                    JwcLoginActivity.this.setViewEnabled();
//                    break;
                }
                case 11:{
//                    JwcLoginActivity.this.dismissWaitingDialog();
//                    JwcLoginActivity.this.setViewEnabled();
//                    break;
                }
                case 12:{
//                    JwcLoginActivity.this.dismissWaitingDialog();
//                    JwcLoginActivity.this.setViewEnabled();
//                    break;
                }
                case 13:{
//                    JwcLoginActivity.this.dismissWaitingDialog();
//                    JwcLoginActivity.this.setViewEnabled();
//                    break;
                }
                case 14:{
//                    JwcLoginActivity.this.dismissWaitingDialog();
//                    JwcLoginActivity.this.setViewEnabled();
//                    break;
                }
                case 15:{
//                    JwcLoginActivity.this.dismissWaitingDialog();
//                    JwcLoginActivity.this.setViewEnabled();
//                    break;
                }
                case 16:{
//                    JwcLoginActivity.this.dismissWaitingDialog();
//                    JwcLoginActivity.this.setViewEnabled();
//                    break;
                }
                case 17:{
//                    JwcLoginActivity.this.dismissWaitingDialog();
//                    JwcLoginActivity.this.setViewEnabled();
//                    break;
                }
                case 18:{
//                    JwcLoginActivity.this.dismissWaitingDialog();
//                    JwcLoginActivity.this.setViewEnabled();
//                    break;
                }
                case 19:{
                    JwcLoginActivity.this.dismissWaitingDialog();
                    JwcLoginActivity.this.setViewEnabled();
                    showErrorDialog((String) msg.obj);
                    break;
                }
                default:{
                    JwcLoginActivity.this.dismissWaitingDialog();
                    JwcLoginActivity.this.setViewEnabled();
                    break;
                }
            }

        }
    }

    public void setViewDisabled(){
        editText_sno.setEnabled(false);
        editText_password.setEnabled(false);
        linearLayout_btn_jwclogin.setEnabled(false);
        editText_sno.setTextColor(getResources().getColor(R.color.colorEditTextDisabled));
        editText_password.setTextColor(getResources().getColor(R.color.colorEditTextDisabled));
    }

    public void setViewEnabled(){
        editText_sno.setEnabled(true);
        editText_password.setEnabled(true);
        linearLayout_btn_jwclogin.setEnabled(true);
        editText_sno.setTextColor(getResources().getColor(R.color.colorEditTextEnabled));
        editText_password.setTextColor(getResources().getColor(R.color.colorEditTextEnabled));
    }

    public void showWaitingDialog() {
        if(waitingDialog != null && waitingDialog.isShowing()){
            ZRLog.d(TAG,"showWaitingDialog() : waitingDialog is showing");
            return;
        }
        waitingDialog= new ProgressDialog(JwcLoginActivity.this);
        waitingDialog.setTitle("登录中");
        waitingDialog.setMessage("等待中...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);
        waitingDialog.show();
    }

    public void dismissWaitingDialog() {
        if(waitingDialog != null){
            if(waitingDialog.isShowing()) {
                waitingDialog.dismiss();
                return;
            }
            ZRLog.d(TAG,"dismissWaitingDialog() : waitingDialog is not showing");
            return;
        }
        ZRLog.d(TAG,"waitingDialog NullPointer");
    }

    private void showErrorDialog(String msg){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("出错")
                .setMessage(msg)
                //.setPositiveButton("确定",null)
                .setNegativeButton("确定",null)
                .create();
        dialog.show();
    }

    private void showSuccessDialog(String msg){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("成功")
                .setMessage(msg)
                .setPositiveButton("进入课表界面",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(JwcLoginActivity.this,SearchActivity.class));
                                JwcLoginActivity.this.finish();
                            }
                        })
                .create();
        dialog.show();
    }

    private void setCacheText(){
        editText_sno.setText(localCache.getSnoNumber());
        editText_password.setText(localCache.getSnoPassword());
    }


}
