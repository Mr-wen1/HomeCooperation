package com.example.homecooperation.ui;

import androidx.viewpager.widget.ViewPager;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framework.base.BasePagerAdapter;
import com.example.framework.base.BaseUIActivity;
import com.example.framework.manager.MediaPlayerManager;
import com.example.framework.utils.AnimationUtils;
import com.example.homecooperation.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseUIActivity implements View.OnClickListener {

    private ViewPager mViewPager;

    private ImageView iv_music;
    private TextView skip;

    private ImageView iv_guide_star;
    private ImageView iv_guide_night;
    private ImageView iv_guide_smile;

    private View view_guide1;
    private View view_guide2;
    private View view_guide3;

    private List<View> mViewList = new ArrayList<>();

    private BasePagerAdapter mPagerAdapter;

    private ImageView point1;
    private ImageView point2;
    private ImageView point3;

    private MediaPlayerManager mMPManager;
    private ObjectAnimator mAnim;

    /**
     * 1.ViewPager : 适配器|帧动画播放
     * 2.小圆点的逻辑
     * 3.歌曲的播放
     * 4.属性动画旋转
     * 5.跳转
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
    }

    private void initView() {
        mViewPager = findViewById(R.id.viewpager);

        iv_music = findViewById(R.id.iv_music);
        skip = findViewById(R.id.tv_skip);

        view_guide1 = View.inflate(this, R.layout.layout_guide_view1, null);
        view_guide2 = View.inflate(this, R.layout.layout_guide_view2, null);
        view_guide3 = View.inflate(this, R.layout.layout_guide_view3, null);

        point1 = findViewById(R.id.iv_guide_point1);
        point2 = findViewById(R.id.iv_guide_point2);
        point3 = findViewById(R.id.iv_guide_point3);

        mViewList.add(view_guide1);
        mViewList.add(view_guide2);
        mViewList.add(view_guide3);

        //预加载
        mViewPager.setOffscreenPageLimit(mViewList.size());
        mPagerAdapter = new BasePagerAdapter(mViewList);
        mViewPager.setAdapter(mPagerAdapter);

        iv_music.setOnClickListener(this);
        skip.setOnClickListener(this);

        //帧动画
        iv_guide_star = view_guide1.findViewById(R.id.iv_guide_night);
        iv_guide_night = view_guide2.findViewById(R.id.iv_guide_star);
        iv_guide_smile = view_guide3.findViewById(R.id.iv_guide_smile);
        //播放动画
        AnimationDrawable ad_star = (AnimationDrawable) iv_guide_star.getBackground();
        ad_star.start();
        AnimationDrawable ad_night = (AnimationDrawable) iv_guide_night.getBackground();
        ad_night.start();
        AnimationDrawable ad_smile = (AnimationDrawable) iv_guide_smile.getBackground();
        ad_smile.start();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                choosePoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //歌曲的逻辑
        startMusic();

    }

    private void startMusic() {
        mMPManager = new MediaPlayerManager();
        mMPManager.setLooping(true);
        AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.guide);
        mMPManager.startPlay(file);

        mMPManager.setOnComplteionListener(mp -> mMPManager.startPlay(file));

        //旋转动画
        mAnim = AnimationUtils.rotation(iv_music);
        mAnim.start();
    }

    /**
     * 选择小圆点
     *
     * @param position
     */
    private void choosePoint(int position) {
        switch (position) {
            case 0:
                point1.setImageResource(R.drawable.img_guide_point_c);
                point2.setImageResource(R.drawable.img_guide_point);
                point3.setImageResource(R.drawable.img_guide_point);
                break;
            case 1:
                point1.setImageResource(R.drawable.img_guide_point);
                point2.setImageResource(R.drawable.img_guide_point_c);
                point3.setImageResource(R.drawable.img_guide_point);
                break;
            case 2:
                point1.setImageResource(R.drawable.img_guide_point);
                point2.setImageResource(R.drawable.img_guide_point);
                point3.setImageResource(R.drawable.img_guide_point_c);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_music:
                if (mMPManager.MEDIA_STATUS == MediaPlayerManager.MEDIA_STATUS_PAUSE) {
                    mAnim.start();
                    mMPManager.continuePlay();
                    iv_music.setImageResource(R.drawable.img_guide_music);
                } else if (mMPManager.MEDIA_STATUS == MediaPlayerManager.MEDIA_STATUS_PLAY) {
                    mAnim.pause();
                    mMPManager.pausePlay();
                    iv_music.setImageResource(R.drawable.img_guide_music_off);
                }
                break;
            case R.id.tv_skip:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }
}
