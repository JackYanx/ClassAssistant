package com.zeromirai.classassistant.fee;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
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

public class FeeActivity extends ListActivity {

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
                Toast.makeText(FeeActivity.this,"您选择了" + mListStr[position], Toast.LENGTH_LONG).show();
            }
        });

        super.onCreate(savedInstanceState);
    }

//    private String[] mListTitle = { "姓名", "性别", "年龄", "居住地","邮箱"};
//    private String[] mListStr = { "雨松MOMO", "男", "25", "北京",
//            "xuanyusong@gmail.com" };
//    ListView mListView = null;
//    FeeActivity myAdapter = null;
//    ArrayList arrayList = null;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        arrayList =FeeActivity.this;
//        mListView = getListView();
//        myAdapter = new MyListAdapter(R.layout.activity_fee);
//        setListAdapter(myAdapter);
//        super.onCreate(savedInstanceState);
//    }
//
//    public class MyListAdapter extends ArrayAdapter<Object> {
//        int mTextViewResourceID = 0;
//        private Context mContext;
//        public MyListAdapter(Context context, int textViewResourceId) {
//            super(context, textViewResourceId);
//            mTextViewResourceID = textViewResourceId;
//            mContext = context;
//        }
//
//        private int[] colors = new int[] { 0xff626569, 0xff4f5257 };
//
//        public int getCount() {
//            return mListStr.length;
//        }
//
//        @Override
//        public boolean areAllItemsEnabled() {
//            return false;
//        }
//
//        public Object getItem(int position) {
//            return position;
//        }
//
//        public long getItemId(int position) {
//            return position;
//        }
//
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            ImageView iamge = null;
//            TextView title = null;
//            TextView text = null;
//            Button button = null;
//            if (convertView == null) {
//                convertView = LayoutInflater.from(mContext).inflate(
//                        mTextViewResourceID, null);
//                iamge = (ImageView) convertView.findViewById(R.id.array_image);
//                title = (TextView) convertView.findViewById(R.id.array_title);
//                text = (TextView) convertView.findViewById(R.id.array_text);
//                button = (Button)convertView.findViewById(R.id.array_button);
//                button.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View arg0) {
//                        Toast.makeText(arrayList,"您点击的第"+position +"个按钮", Toast.LENGTH_LONG).show();
//
//                    }
//                });
//            }
//            int colorPos = position % colors.length;
//            convertView.setBackgroundColor(colors[colorPos]);
//            title.setText(mListTitle[position]);
//            text.setText(mListStr[position]);
//            if(colorPos == 0)
//                iamge.setImageResource(R.drawable.jay);
//            else
//                iamge.setImageResource(R.drawable.image);
//            return convertView;
//        }
//    }



}
