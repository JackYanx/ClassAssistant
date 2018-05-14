package com.zeromirai.classassistant.main;

/**
 * Created by 10653 on 2018/4/2.
 */

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zeromirai.android.util.ZRLog;
import com.zeromirai.classassistant.R;
import com.zeromirai.classassistant.common.cache.LocalCache;
import com.zeromirai.classassistant.schedule.JwcLoginActivity;
import com.zeromirai.classassistant.schedule.SearchActivity;
import com.zeromirai.classassistant.statcheck.StatcheckActivity;


public class ClassServiceFragment extends Fragment {
    private Context mContext=getActivity();
    private Button bschedule,bstat;
    public static ClassServiceFragment newInstance(String param1) {
        ClassServiceFragment fragment = new ClassServiceFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ClassServiceFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scan_fragment, container, false);

        bstat=(Button)view.findViewById(R.id.gotoStat);
        bstat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), StatcheckActivity.class);
                startActivity(intent);
            }
        });
        bschedule=(Button)view.findViewById(R.id.gotoSchedule);
        bschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalCache localCache = new LocalCache(getActivity());
                String s = localCache.getScheduleData();
                if(TextUtils.isEmpty(s) || s.length() < 10){
                    ZRLog.d("schedule_wt","课程数据为空");
                    Intent intent=new Intent(getActivity(), JwcLoginActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent=new Intent(getActivity(), SearchActivity.class);
                    startActivity(intent);
                }

            }
        });


        return view;
    }
}
