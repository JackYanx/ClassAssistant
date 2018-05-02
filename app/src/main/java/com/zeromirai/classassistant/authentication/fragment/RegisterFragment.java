package com.zeromirai.classassistant.authentication.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zeromirai.android.util.ZRLog;
import com.zeromirai.classassistant.R;
import com.zeromirai.classassistant.authentication.AuthenticationActivity;
import com.zeromirai.classassistant.authentication.Config;
import com.zeromirai.classassistant.authentication.runnable.RegisterRunnable;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    public static final String TAG = RegisterFragment.class.getName();

    public AuthenticationActivity parentActivity;

    private View view;

    private EditText editText_userName;
    private EditText editText_password;
    private EditText editText_phoneNumber;
    private EditText editText_emailAddress;
    private LinearLayout linearLayout_btn_register;
    private TextView textView_switchto_login;
    private TextView textView_switch_regway;

    private ImageView imageView_icon_email;

    private ImageView imageView_btn_del_userName;
    private ImageView imageView_btn_del_phoneNumber;
    private ImageView imageView_btn_del_password;

    private int regWithEmail = 0;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.parentActivity = (AuthenticationActivity)activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.parentActivity = (AuthenticationActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_register, container, false);
        setView();
        setListeners();
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        //view = parentActivity.getFragmentManager().findFragmentById(R.id.frameLayout_loginregfragment).getView();

    }

    private void setView(){
        editText_userName = (EditText) view.findViewById(R.id.editText_userName);
        editText_phoneNumber = (EditText) view.findViewById(R.id.editText_phoneNumber);
        editText_password = (EditText) view.findViewById(R.id.editText_password);
        linearLayout_btn_register = (LinearLayout) view.findViewById(R.id.linearLayout_btn_register);
        textView_switchto_login = (TextView) view.findViewById(R.id.textView_switchto_login);
        textView_switch_regway = (TextView) view.findViewById(R.id.textView_switch_regway);

        imageView_icon_email = (ImageView) view.findViewById(R.id.imageViewimageView_icon_phoneNumber);

        imageView_btn_del_userName = (ImageView) view.findViewById(R.id.imageView_btn_del_userName);
        imageView_btn_del_phoneNumber = (ImageView) view.findViewById(R.id.imageView_btn_del_phoneNumber);
        imageView_btn_del_password = (ImageView) view.findViewById(R.id.imageView_btn_del_password);
    }

    private void setListeners(){

        linearLayout_btn_register.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:{
                        linearLayout_btn_register.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_button_register_pressed));
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        linearLayout_btn_register.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_button_register));

                        new Thread(){
                            @Override
                            public void run(){


                                parentActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        RegisterFragment.this.setViewDisabled();
                                        Toast.makeText(parentActivity,"setViewDisabled",Toast.LENGTH_LONG);
                                        ZRLog.d(TAG,"setViewDisabled");
                                    }
                                });
                                String[] args = new String[] {
                                        2+regWithEmail+"",
                                        editText_userName.getText().toString(),
                                        "",
                                        editText_password.getText().toString(),
                                };
                                if(regWithEmail == 0){
                                    args[2] = editText_phoneNumber.getText().toString();
                                }else if(regWithEmail == 1){
                                    args[2] = editText_phoneNumber.getText().toString();
                                }else{
                                    ZRLog.e(TAG,"");
                                }
                                new Thread(new RegisterRunnable(parentActivity,args)).start();


                            }
                        }.start();


                        break;
                    }
                    default:{ }
                }
                /*在onTouchEvent的Motion.ACTION_DOWN分支如果返回的是false，那么onTouch事件将不会往下面传递下去，如果返回true，那么onTouch事件将会传递下去，从而可以响应Motion.ACTION_UP分支。*/
                return true;
            }
        });

        textView_switchto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZRLog.d(TAG,"textView_switchto_login pressed");
                parentActivity.switchToLoginUI();
            }
        });

        textView_switch_regway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*由手机号切换为邮箱注册*/
                if(regWithEmail == 0){
                    switchToEmailReg();
                }
                /*由邮箱切换为手机号注册*/
                else if(regWithEmail == 1){
                    switchToPhoneNumberReg();
                }
                else {
                    ZRLog.d(TAG, Config.ERRMSG_XXX);
                    throw new RuntimeException();
                }
            }
        });

        editText_userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0){
                    imageView_btn_del_userName.setVisibility(View.VISIBLE);
                }else{
                    imageView_btn_del_userName.setVisibility(View.INVISIBLE);
                }
            }
        });

        editText_phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0){
                    imageView_btn_del_phoneNumber.setVisibility(View.VISIBLE);
                }else{
                    imageView_btn_del_phoneNumber.setVisibility(View.INVISIBLE);
                }
            }
        });

        editText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0){
                    imageView_btn_del_password.setVisibility(View.VISIBLE);
                }else{
                    imageView_btn_del_password.setVisibility(View.INVISIBLE);
                }
            }
        });

        imageView_btn_del_userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_userName.setText("");
            }
        });

        imageView_btn_del_phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_phoneNumber.setText("");
            }
        });

        imageView_btn_del_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_password.setText("");
            }
        });


    }

    public void setViewDisabled(){
        editText_userName.setEnabled(false);
        editText_phoneNumber.setEnabled(false);
        editText_password.setEnabled(false);
        linearLayout_btn_register.setEnabled(false);
        textView_switchto_login.setEnabled(false);
        editText_userName.setTextColor(getResources().getColor(R.color.colorEditTextDisabled));
        editText_phoneNumber.setTextColor(getResources().getColor(R.color.colorEditTextDisabled));
        editText_password.setTextColor(getResources().getColor(R.color.colorEditTextDisabled));
    }

    public void setViewEnabled(){
        editText_userName.setEnabled(true);
        editText_phoneNumber.setEnabled(true);
        editText_password.setEnabled(true);
        linearLayout_btn_register.setEnabled(true);
        textView_switchto_login.setEnabled(true);
        editText_userName.setTextColor(getResources().getColor(R.color.colorEditTextEnabled));
        editText_phoneNumber.setTextColor(getResources().getColor(R.color.colorEditTextEnabled));
        editText_password.setTextColor(getResources().getColor(R.color.colorEditTextEnabled));
    }

    public boolean switchToEmailReg(){
        if(regWithEmail == 1){
            ZRLog.d(TAG,"switchToEmailReg: Already in EmailReg");
            return false;
        }
        regWithEmail = 1;

        /*切换相关UI组件到Email模式*/
        imageView_icon_email.setImageDrawable(getResources().getDrawable(R.drawable.icon_emails));
        editText_phoneNumber.setHint(R.string.hint_emailAddress);
        textView_switch_regway.setText(R.string.reg_with_phoneNumber);

        ZRLog.d(TAG,"success to switchToEmailReg");
        return true;
    }

    public boolean switchToPhoneNumberReg(){
        if(regWithEmail == 0){
            ZRLog.d(TAG,"switchToPhoneNumberReg: Already in PhoneNumberReg");
            return false;
        }
        regWithEmail = 0;

        /*切换相关UI组件到PhoneNumber模式*/
        imageView_icon_email.setImageDrawable(getResources().getDrawable(R.drawable.icon_phone));
        editText_phoneNumber.setHint(R.string.hint_phoneNumber);
        textView_switch_regway.setText(R.string.reg_with_email);

        ZRLog.d(TAG,"success to switchToPhoneNumberReg");
        return true;
    }

    public int getRegWay(){
        /*0为手机号,1为Email*/
        return regWithEmail;
    }

}
