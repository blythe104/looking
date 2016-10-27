package com.looking.classicalparty.moudles.main;

import android.content.Intent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.adapter.FragmentAdapter;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.base.fragment.BaseFragment;
import com.looking.classicalparty.lib.common.UserInfo;
import com.looking.classicalparty.lib.widget.CustomViewPager;
import com.looking.classicalparty.moudles.login.view.LoginActivity;
import com.looking.classicalparty.moudles.main.fragment.ClMusicFragment;
import com.looking.classicalparty.moudles.main.fragment.MovieFragment;
import com.looking.classicalparty.moudles.find.fragment.FindFragment;
import com.looking.classicalparty.moudles.mine.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private CustomViewPager mCustomViewPager;
    private List<BaseFragment> baseFragmentList;
    private RadioGroup mRadioGroup;
    private RadioButton mRbFind;
    private RadioButton mRbVideo;
    private RadioButton mRbMusic;
    private RadioButton mRbMine;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        mCustomViewPager = (CustomViewPager) findViewById(R.id.custom_viewpager);
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mRbFind = (RadioButton) findViewById(R.id.rb_find);
        mRbVideo = (RadioButton) findViewById(R.id.rb_video);
        mRbMusic = (RadioButton) findViewById(R.id.rb_music);
        mRbMine = (RadioButton) findViewById(R.id.rb_mine);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_find:
                        mCustomViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.rb_video:
                        mCustomViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_music:
                        mCustomViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb_mine:
                        //isLogin判斷
                        UserInfo userInfo = new UserInfo();
                        if (!userInfo.isLogin()) {
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        } else {
                            mCustomViewPager.setCurrentItem(3, false);
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
        baseFragmentList.add(new ClMusicFragment());
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

}
