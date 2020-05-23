package com.example.homecooperation;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.framework.base.BaseUIActivity;
import com.example.framework.fragment.Tabfragment;
import com.example.framework.view.Tabview;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseUIActivity {

    private ViewPager mViewPager;

    private List<String> mtitles = new ArrayList<>(Arrays.asList("查询", "聊天", "看点", "动态"));

    private Tabview mBtnMsg;
    private Tabview mBtnPeople;
    private Tabview mBtnLooking;
    private Tabview mBtnDynamic;

    private List<Tabview> mTabs = new ArrayList<>();

    private SparseArray<Tabfragment> mFragments = new SparseArray<>();

    public static final String BUNDLE_KEY_POS = "bundle_key_pos";

    private int mCurTabPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            mCurTabPos = savedInstanceState.getInt(BUNDLE_KEY_POS,0);
        }
        initViews();
        initViewPagerAdapter();
        initEvents();

    }

    private void initEvents() {
        for (int i = 0; i < mTabs.size(); i++){
            Tabview tabview = mTabs.get(i);
            final  int finalI = i;
            tabview.setOnClickListener(v -> {
                mViewPager.setCurrentItem(finalI, false);
                setCurrentTab(finalI);
            });
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_KEY_POS,mViewPager.getCurrentItem());
    }

    private void initViewPagerAdapter() {
        mViewPager.setOffscreenPageLimit(mtitles.size());
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                Tabfragment fragement = Tabfragment.newInstance(mtitles.get(i));
//                if (i == 0) {
//                    fragement.setOnTitleClickListener(new Tabfragment.onTitleClickListener() {
//                        @Override
//                        public void onClick(String title) {
//                            changeWeChatTab(title);
//                        }
//                    });
//                }
                return fragement;
            }

            @Override
            public int getCount() {
                return mtitles.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                Tabfragment fragment = (Tabfragment) super.instantiateItem(container, position);
                mFragments.put(position, fragment);
                return fragment;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                mFragments.remove(position);
                super.destroyItem(container, position, object);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (positionOffset > 0) {
                    Tabview left = mTabs.get(position);
                    Tabview right = mTabs.get(position + 1);

                    left.setProgress(1 - positionOffset);
                    right.setProgress(positionOffset);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViews() {
        mViewPager = findViewById(R.id.vp_main);
        mBtnMsg = findViewById(R.id.tab_msg);
        mBtnPeople = findViewById(R.id.tab_people);
        mBtnLooking = findViewById(R.id.tab_looking);
        mBtnDynamic = findViewById(R.id.tab_dynamic);

        mBtnMsg.setIconAndText(R.drawable.contact_normal, R.drawable.contact, "消息");
        mBtnPeople.setIconAndText(R.drawable.conversation_normal, R.drawable.conversation, "联系人");
        mBtnLooking.setIconAndText(R.drawable.plugin_normal, R.drawable.plugin, "看点");
        mBtnDynamic.setIconAndText(R.drawable.see_normal, R.drawable.see, "动态");


        mTabs.add(mBtnMsg);
        mTabs.add(mBtnPeople);
        mTabs.add(mBtnLooking);
        mTabs.add(mBtnDynamic);

        mBtnMsg.setProgress(1);

        setCurrentTab(mCurTabPos);

        mBtnMsg.setOnClickListener(view -> {
            Tabfragment tabfragment = mFragments.get(0);
            if (tabfragment != null) {
                tabfragment.changeTitle("微信 changed");
            }
        });
    }

    private void setCurrentTab(int pos) {
        for (int i = 0; i < mTabs.size(); i++) {
            Tabview tabView = mTabs.get(i);
            if (i == pos) {
                tabView.setProgress(1);
            }else {
                tabView.setProgress(0);
            }
        }
    }

//    public void changeWeChatTab(String title) {
//        mBtnMsg.setTe(title);
//    }
}
