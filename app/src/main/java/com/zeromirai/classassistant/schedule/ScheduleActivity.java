package com.zeromirai.classassistant.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.zeromirai.classassistant.R;

public class ScheduleActivity extends Activity {
    private String[] mListStr = {"姓名：雨松MOMO","性别：男","年龄：25","居住地：北京","邮箱：xuanyusong@gmail.com"};
    ListView mListView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
//        mListView = getListView();
//        setListAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, mListStr));
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position,
//                                    long id) {
//                Toast.makeText(ScheduleActivity.this,"您选择了" + mListStr[position], Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        super.onCreate(savedInstanceState);
    }





}
