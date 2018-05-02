package com.zeromirai.classassistant.authentication.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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
import com.zeromirai.classassistant.authentication.runnable.LoginRunnable;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public static final String TAG = LoginFragment.class.getName();

    public AuthenticationActivity parentActivity;
    private Handler mHandler;

    private View view;

    private EditText editText_userName;
    private EditText editText_password;
    private LinearLayout linearLayout_btn_login;
    private TextView textView_switchto_register;
    private TextView textView_findpassword;

    private ImageView imageView_btn_del_userName;
    private ImageView imageView_btn_del_password;

    public LoginFragment() {
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

        view = inflater.inflate(R.layout.fragment_login, container, false);
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
        editText_password = (EditText) view.findViewById(R.id.editText_password);
        linearLayout_btn_login = (LinearLayout) view.findViewById(R.id.linearLayout_btn_login);
        textView_switchto_register = (TextView) view.findViewById(R.id.textView_switchto_register);
        textView_findpassword = (TextView) view.findViewById(R.id.textView_findpassword);
        imageView_btn_del_userName = (ImageView) view.findViewById(R.id.imageView_btn_del_userName);
        imageView_btn_del_password = (ImageView) view.findViewById(R.id.imageView_btn_del_password);

    }

    private void setListeners(){

        linearLayout_btn_login.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:{
                        linearLayout_btn_login.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_button_login_pressed));
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        linearLayout_btn_login.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_button_login));

                        new Thread(){
                            @Override
                            public void run(){


                                parentActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        LoginFragment.this.setViewDisabled();
                                        Toast.makeText(parentActivity,"setViewDisabled",Toast.LENGTH_LONG);
                                        ZRLog.d(TAG,"setViewDisabled");
                                    }
                                });
                                String[] args = new String[] {
                                        editText_userName.getText().toString(),
                                        editText_password.getText().toString(),
                                        "",
                                        ""
                                };
                                new Thread(new LoginRunnable(parentActivity,args)).start();


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

        textView_switchto_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZRLog.d(TAG,"textView_switchto_register pressed");
                parentActivity.switchToRegisterUI();
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

        imageView_btn_del_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_password.setText("");
            }
        });

    }

    public void setViewDisabled(){
        editText_userName.setEnabled(false);
        editText_password.setEnabled(false);
        linearLayout_btn_login.setEnabled(false);
        textView_switchto_register.setEnabled(false);
        textView_findpassword.setEnabled(false);
        editText_userName.setTextColor(getResources().getColor(R.color.colorEditTextDisabled));
        editText_password.setTextColor(getResources().getColor(R.color.colorEditTextDisabled));
    }

    public void setViewEnabled(){
        editText_userName.setEnabled(true);
        editText_password.setEnabled(true);
        linearLayout_btn_login.setEnabled(true);
        textView_switchto_register.setEnabled(true);
        textView_findpassword.setEnabled(true);
        editText_userName.setTextColor(getResources().getColor(R.color.colorEditTextEnabled));
        editText_password.setTextColor(getResources().getColor(R.color.colorEditTextEnabled));
    }

}
