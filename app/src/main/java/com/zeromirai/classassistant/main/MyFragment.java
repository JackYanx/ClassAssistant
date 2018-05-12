package com.zeromirai.classassistant.main;

/**
 * Created by 10653 on 2018/4/2.
 */

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zeromirai.classassistant.R;


public class MyFragment extends Fragment {
    private ImageView imageView,imageView2;
    private Intent intent;
    public static MyFragment newInstance(String param1) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public MyFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment, container, false);
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");
        //TextView tv = (TextView)view.findViewById(R.id.container2);
        //tv.setText(agrs1);
        imageView=(ImageView)view.findViewById(R.id.go_cat);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(getActivity(),catActivity.class);
                android.support.v7.app.ActionBar actionbar=((AppCompatActivity)getActivity()).getSupportActionBar();
                actionbar.setDisplayShowTitleEnabled(true);


                startActivity(intent);
            }
        });

        //add more cat
        imageView2=(ImageView)view.findViewById(R.id.cat_add_more);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(getActivity(),catActivity.class);
                android.support.v7.app.ActionBar actionbar=((AppCompatActivity)getActivity()).getSupportActionBar();
                actionbar.setDisplayShowTitleEnabled(true);
                startActivity(intent);
            }
        });

        //initView(view);

//        AvatarImageView aiv = (AvatarImageView) view.findViewById(R.id.aiv);
//        Glide.with(this).load(R.drawable.ic_menu_deal_on)
//                .bitmapTransform(new BlurTransformation(getActivity(), 25), new CenterCrop(getActivity()))
//                .into(avatarImageView);
//
//        Glide.with(this).load(R.drawable.ic_menu_deal_on)
//                .bitmapTransform(new CropCircleTransformation(getActivity()))
//                .into(avatarImageView);
        return view;
    }


//    int Y;
//    int position = 0; //拖动Linearlayout的距离Y轴的距离
//    int scrollviewdistancetotop = 0; //headView的高
//    int menubarHeight = 0;
//    int chufaHeight = 0; //需要触发动画的高
//    float scale; //像素密度
//    int headViewPosition = 0;
//    ImageView userinfo_topbar;
//    static boolean flag = true;
//    static boolean topmenuflag = true;
//    private Context mContext=null;
//    LinearLayout myscrollLinearlayout;
//    RelativeLayout mainactionbar;
//    LinearLayout mainheadview;
//    private void initView(View view) {
//        userinfo_topbar = (ImageView) view.findViewById(R.id.userinfo_topbar);
////获得像素密度
//        scale = this.getResources().getDisplayMetrics().density;
//        mainheadview = (LinearLayout) view.findViewById(R.id.mainheadview);
//        mainactionbar = (RelativeLayout) view.findViewById(R.id.mainactionbar);
//        menubarHeight = (int) (55 * scale);
//        chufaHeight = (int) (110 * scale);
//        scrollviewdistancetotop = (int) ((260 )*scale);
//        position = scrollviewdistancetotop;
//        myscrollLinearlayout = (LinearLayout) view.findViewById(R.id.myscrollLinearlayout);
//        myscrollLinearlayout.setY( scrollviewdistancetotop); //要减去Absolote布局距离顶部的高度
//        myscrollLinearlayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
//        myscrollLinearlayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
////按下的Y的位置
//                        Y = (int) event.getRawY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        int nowY = (int) myscrollLinearlayout.getY(); //拖动界面的Y轴位置
//                        int tempY = (int) (event.getRawY() - Y); //手移动的偏移量
//                        Y = (int) event.getRawY();
//                        if ((nowY + tempY >= 0) && (nowY + tempY <= scrollviewdistancetotop)) {
//                            if ((nowY + tempY <= menubarHeight)&& (topmenuflag == true) ){
//                                userinfo_topbar.setVisibility(View.VISIBLE);
//                                topmenuflag = false;
//                            } else if ((nowY + tempY > menubarHeight) && (topmenuflag == flag)) {
//                                userinfo_topbar.setVisibility(View.INVISIBLE);
//                                topmenuflag = true;
//                            }
//                            int temp = position += tempY;
//                            myscrollLinearlayout.setY(temp);
//                            int headviewtemp = headViewPosition += (tempY/5);
//                            mainheadview.setY(headviewtemp);
//                        }
////顶部的动画效果
//                        if ((myscrollLinearlayout.getY() <= chufaHeight) && (flag == true)) {
//                            ObjectAnimator anim = ObjectAnimator.ofFloat(mainheadview, "alpha", 1, 0.0f);
//                            anim.setDuration(500);
//                            anim.start();
//                            flag = false;
//                        } else if ((myscrollLinearlayout.getY() > chufaHeight + 40) && (flag == false)) {
//                            ObjectAnimator anim = ObjectAnimator.ofFloat(mainheadview, "alpha", 0.0f, 1f);
//                            anim.setDuration(500);
//                            anim.start();
//                            flag = true;
//                        }
//                        break;
//                }
//                return false;
//            }
//        });
//    }

}
