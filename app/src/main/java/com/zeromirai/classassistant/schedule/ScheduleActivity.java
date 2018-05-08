package com.zeromirai.classassistant.schedule;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zeromirai.classassistant.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScheduleActivity extends ListActivity {

//        private String[] mListTitle = { "姓名", "性别", "年龄", "居住地","邮箱"};
//        private String[] mListStr = { "雨松MOMO", "男", "25", "北京",
//                "xuanyusong@gmail.com" };
//        ListView mListView = null;
//        ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_fee);
//
//            mListView = getListView();
//
//            int lengh = mListTitle.length;
//            for(int i =0; i < lengh; i++) {
//                Map<String,Object> item = new HashMap<String,Object>();
//                item.put("title", mListTitle[i]);
//                item.put("text", mListStr[i]);
//                mData.add(item);
//            }
//            SimpleAdapter adapter = new SimpleAdapter(this,mData,android.R.layout.simple_list_item_2,
//                    new String[]{"title","text"},new int[]{android.R.id.text1,android.R.id.text2});
//            setListAdapter(adapter);
//            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int position,
//                                        long id) {
//                    Toast.makeText(FeeActivity.this,"您选择了标题：" + mListTitle[position] + "内容："+mListStr[position], Toast.LENGTH_LONG).show();
//                }
//            });
//            super.onCreate(savedInstanceState);
//        }


    ///22222222222222
    private String[] mListStr = {"姓名：雨松MOMO","性别：男","年龄：25","居住地：北京","邮箱：xuanyusong@gmail.com"};
    ListView mListView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mListView = getListView();
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mListStr));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                    long id) {
                Toast.makeText(ScheduleActivity.this,"您选择了" + mListStr[position], Toast.LENGTH_SHORT).show();
            }
        });

        super.onCreate(savedInstanceState);
    }





}
