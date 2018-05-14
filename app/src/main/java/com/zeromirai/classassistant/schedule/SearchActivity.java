package com.zeromirai.classassistant.schedule;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import com.zeromirai.classassistant.R;

public class SearchActivity extends AppCompatActivity {

    private Spinner mSpinner_week,mSpinner_day,mSpinner_num;
    private EditText mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSpinner_week = (Spinner) findViewById(R.id.spinner_timeselect_week);
        mSpinner_day = (Spinner) findViewById(R.id.spinner_timeselect_day);
        mSpinner_num = (Spinner) findViewById(R.id.spinner_timeselect_numk);

        //只是为了展示我们的实现效果，故可不要
        mTv = (EditText) findViewById(R.id.editText);

        //数据源  选第几周
        ArrayList<String> spinners = new ArrayList<>();
        spinners.add("1");
        spinners.add("2");
        spinners.add("3");
        spinners.add("4");
        spinners.add("5");
        spinners.add("6");
        spinners.add("7");
        spinners.add("8");
        spinners.add("9");
        spinners.add("10");
        spinners.add("11");
        spinners.add("12");
        spinners.add("13");
        spinners.add("14");
        spinners.add("15");
        spinners.add("16");
        spinners.add("17");
        spinners.add("18");
        spinners.add("19");
        spinners.add("20");

        //设置ArrayAdapter内置的item样式-这里是单行显示样式
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinners);
        //这里设置的是Spinner的样式 ， 输入 simple_之后会提示有4人，如果专属spinner的话应该是俩种，在特殊情况可自己定义样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        //设置Adapter了
        mSpinner_week.setAdapter(adapter);
        //监听Spinner的操作
        mSpinner_week.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //选取时候的操作
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTv.setText(adapter.getItem(position));
            }
            //没被选取时的操作
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mTv.setText("No anything");
            }
        });


        //数据源   星期几
        ArrayList<String> spinners2 = new ArrayList<>();
        spinners2.add("1");
        spinners2.add("2");
        spinners2.add("3");
        spinners2.add("4");
        spinners2.add("5");
        spinners2.add("6");
        spinners2.add("7");

        //设置ArrayAdapter内置的item样式-这里是单行显示样式
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinners2);
        //这里设置的是Spinner的样式 ， 输入 simple_之后会提示有4人，如果专属spinner的话应该是俩种，在特殊情况可自己定义样式
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        //设置Adapter了
        mSpinner_day.setAdapter(adapter2);
        //监听Spinner的操作
        mSpinner_day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //选取时候的操作
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTv.setText(adapter2.getItem(position));
            }
            //没被选取时的操作
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mTv.setText("No anything");
            }
        });


        //数据源   第几大节
        ArrayList<String> spinners3 = new ArrayList<>();
        spinners3.add("1");
        spinners3.add("2");
        spinners3.add("3");
        spinners3.add("4");
        spinners3.add("5");

        //设置ArrayAdapter内置的item样式-这里是单行显示样式
        final ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinners3);
        //这里设置的是Spinner的样式 ， 输入 simple_之后会提示有4人，如果专属spinner的话应该是俩种，在特殊情况可自己定义样式
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
        //设置Adapter了
        mSpinner_num.setAdapter(adapter3);
        //监听Spinner的操作
        mSpinner_num.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //选取时候的操作
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTv.setText(adapter3.getItem(position));
            }
            //没被选取时的操作
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mTv.setText("No anything");
            }
        });


    }
}