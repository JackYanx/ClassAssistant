package com.zeromirai.classassistant.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zeromirai.classassistant.R;

/**
 * Created by 10653 on 2018/4/13.
 */

public class catActivity extends Activity implements View.OnClickListener
{
    @Override
    public void onClick(View view) {

    }

    protected void onCreate(Bundle savedStateInstanced){
        super.onCreate(savedStateInstanced);
        setContentView(R.layout.cat_activity);

//
//        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
//        //需要调用该函数才能设置toolbar的信息
//        setSupportActionBar(toolbar);
//        //显示向上箭头
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
