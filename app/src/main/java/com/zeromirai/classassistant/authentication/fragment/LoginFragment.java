package com.zeromirai.classassistant.authentication.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.zeromirai.classassistant.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public Activity parentActivity;

    private View view;

    private EditText editText_userName;
    private EditText editText_password;
    private LinearLayout linearLayout_btn_login;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.parentActivity = activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.parentActivity = (Activity) context;
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
        linearLayout_btn_login = (LinearLayout) view.findViewById(R.id.linearLayout_btn_login);
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
                        break;
                    }
                    default:{ }
                }
                /*在onTouchEvent的Motion.ACTION_DOWN分支如果返回的是false，那么onTouch事件将不会往下面传递下去，如果返回true，那么onTouch事件将会传递下去，从而可以响应Motion.ACTION_UP分支。*/
                return true;
            }
        });


    }

}
