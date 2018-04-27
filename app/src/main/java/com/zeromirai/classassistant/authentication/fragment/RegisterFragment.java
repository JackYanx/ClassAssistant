package com.zeromirai.classassistant.authentication.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zeromirai.classassistant.R;
import com.zeromirai.classassistant.authentication.AuthenticationActivity;

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

    private ImageView imageView_btn_del_userName;
    private ImageView imageView_btn_del_phoneNumber;
    private ImageView imageView_btn_del_password;

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
                Log.d(TAG,"textView_switchto_login pressed");
                parentActivity.switchToLoginUI();
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

}
