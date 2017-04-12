package com.looking.classicalparty.moudles.main;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.adapter.FragmentAdapter;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.base.fragment.BaseFragment;
import com.looking.classicalparty.lib.common.UserInfo;
import com.looking.classicalparty.lib.widget.CustomViewPager;
import com.looking.classicalparty.moudles.find.fragment.FindFragment;
import com.looking.classicalparty.moudles.login.observer.ObserverListener;
import com.looking.classicalparty.moudles.login.observer.ObserverManager;
import com.looking.classicalparty.moudles.login.view.LoginActivity;
import com.looking.classicalparty.moudles.main.fragment.MovieFragment;
import com.looking.classicalparty.moudles.main.fragment.MusicFragment;
import com.looking.classicalparty.moudles.mine.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ObserverListener {
    
    private static final int EXIT = 1001;
    private static final int LSUCCESS = 2000;
    private CustomViewPager mCustomViewPager;
    private List<BaseFragment> baseFragmentList;
    private RadioGroup mRadioGroup;
    private RadioButton mRbFind;
    private RadioButton mRbVideo;
    private RadioButton mRbMusic;
    private RadioButton mRbMine;
    private int currentIndex = 0;
    private long exitTime = 0;
    
    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        //注册观察者
        ObserverManager.getInstance().add(this);
        
        mCustomViewPager = (CustomViewPager) findViewById(R.id.custom_viewpager);
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mRbFind = (RadioButton) findViewById(R.id.rb_find);
        mRbVideo = (RadioButton) findViewById(R.id.rb_video);
        mRbMusic = (RadioButton) findViewById(R.id.rb_music);
        mRbMine = (RadioButton) findViewById(R.id.rb_mine);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("checkedId", "checkedId   =  " + checkedId);
                switch (checkedId) {
                    case R.id.rb_find:
                        currentIndex = 0;
                        mCustomViewPager.setCurrentItem(currentIndex, false);
                        break;
                    case R.id.rb_video:
                        currentIndex = 1;
                        mCustomViewPager.setCurrentItem(currentIndex, false);
                        break;
                    case R.id.rb_music:
                        currentIndex = 2;
                        mCustomViewPager.setCurrentItem(currentIndex, false);
                        break;
                    case R.id.rb_mine:
                        //isLogin
                        UserInfo userInfo = new UserInfo();
                        if (!userInfo.isLogin()) {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            currentIndex = 3;
                            mCustomViewPager.setCurrentItem(currentIndex, false);
                        }
                        break;
                }
                
            }
        });
        
    }
    
    @Override
    public void initListener() {
        
    }
    
    
    @Override
    public void initData() {
        baseFragmentList = new ArrayList<>();
        baseFragmentList.add(new FindFragment());
        baseFragmentList.add(new MovieFragment());
        baseFragmentList.add(new MusicFragment());
        baseFragmentList.add(new MineFragment());
        
        mCustomViewPager.setOffscreenPageLimit(4);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), baseFragmentList, null);
        mCustomViewPager.setAdapter(fragmentAdapter);
        fragmentAdapter.notifyDataSetChanged();
        
        mRadioGroup.check(R.id.rb_find);
        mCustomViewPager.setCurrentItem(0);
        
    }
    
    @Override
    public void processClick(View v) {
        
    }
    
    /**
     * 观察者
     *
     * @param isLogin
     */
    @Override
    public void observerIsLogin(int isLogin) {
        switch (isLogin) {
            case EXIT:
                mRbFind.setChecked(true);
                mCustomViewPager.setCurrentItem(0, false);
                break;
            case LSUCCESS: {
                if (mCustomViewPager != null) {
                    mCustomViewPager.setCurrentItem(3, false);
                }
            }
            break;
            case 4000: {
                switch (currentIndex) {
                    case 0:
                        mRbFind.setChecked(true);
                        break;
                    case 1:
                        mRbVideo.setChecked(true);
                        break;
                    case 2:
                        mRbMusic.setChecked(true);
                        break;
                }
            }
        }
        
    }
}
