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
        spinners.add("第一周");
        spinners.add("第二周");
        spinners.add("第三周");
        spinners.add("第四周");
        spinners.add("第五周");
        spinners.add("第六周");
        spinners.add("第七周");
        spinners.add("第八周");
        spinners.add("第九周");
        spinners.add("第十周");
        spinners.add("第十一周");
        spinners.add("第十二周");
        spinners.add("第十三周");
        spinners.add("第十四周");
        spinners.add("第十五周");
        spinners.add("第十六周");
        spinners.add("第十七周");
        spinners.add("第十八周");
        spinners.add("第十九周");
        spinners.add("第二十周");

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
        spinners2.add("星期一");
        spinners2.add("星期二");
        spinners2.add("星期三");
        spinners2.add("星期四");
        spinners2.add("星期五");
        spinners2.add("星期六");
        spinners2.add("星期日");

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
        spinners3.add("第一节");
        spinners3.add("第二节");
        spinners3.add("第三节");
        spinners3.add("第四节");
        spinners3.add("第五节");

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