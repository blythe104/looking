package com.looking.classicalparty.moudles.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.fragment.BaseFragment;
import com.looking.classicalparty.lib.constants.ConstantApi;
import com.looking.classicalparty.lib.constants.StringContants;
import com.looking.classicalparty.lib.http.HttpUtils;
import com.looking.classicalparty.lib.http.Param;
import com.looking.classicalparty.lib.http.ResultCallback;
import com.looking.classicalparty.lib.utils.ImageLoaderUtils;
import com.looking.classicalparty.lib.utils.PackageManagerUtils;
import com.looking.classicalparty.lib.utils.SharedPreUtils;
import com.looking.classicalparty.lib.widget.CircleImageView;
import com.looking.classicalparty.lib.widget.CustomerMenuView;
import com.looking.classicalparty.lib.widget.ItemData;
import com.looking.classicalparty.moudles.about.view.AboutUsActivity;
import com.looking.classicalparty.moudles.feedback.FeedBackActivity;
import com.looking.classicalparty.moudles.login.observer.ObserverListener;
import com.looking.classicalparty.moudles.login.observer.ObserverManager;
import com.looking.classicalparty.moudles.mine.PersonBean;
import com.looking.classicalparty.moudles.mine.bean.VersionBean;
import com.looking.classicalparty.moudles.mine.bean.VersionResponse;
import com.looking.classicalparty.moudles.mine.view.PersonalActivity;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


/**
 * Created by xin on 2016/10/19.
 */
public class MineFragment extends BaseFragment implements ObserverListener {
    
    private static final int EXIT = 1001;
    private CustomerMenuView mCustomMenu;
    private Toolbar mToolBar;
    private VersionBean mVersion;
    private TextView tickName;
    private CircleImageView circleImageView;
    private TextView tvSign;
    
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_layout, null);
        
        ObserverManager.getInstance().add(this);
        
        String version = PackageManagerUtils.getVersionName(getActivity());
        
        mCustomMenu = (CustomerMenuView) view.findViewById(R.id.custom_menu);
        
        circleImageView = (CircleImageView) view.findViewById(R.id.circle_image);
        tvSign = (TextView) view.findViewById(R.id.tv_sign);
        tickName = (TextView) view.findViewById(R.id.tick_name);
        mCustomMenu.addDivider().addItem(R.mipmap.ic_person_msg, "个人信息", "personmsg")//
                // .addItem(R.mipmap.ic_secure_setting, "安全设置", "security")//
                .addDivider()//
                .addItem(R.mipmap.ic_feedback, "意见反馈", "feedback")//
                .addItem(R.mipmap.ic_about_us, "关于我们", "aboutus")//
                .addItem(R.mipmap.ic_app_version, "版本信息", "version", "V" + version)//
                .addDivider().addItem(R.mipmap.ic_clean, "退出登录", "exit").build();
        mCustomMenu.setItemClickListener(new CustomerMenuView.OnItemListener() {
            @Override
            public void itemClick(View v) {
                switch (((ItemData) v.getTag()).flag) {
                    case "personmsg":
                        startActivity(new Intent(getActivity(), PersonalActivity.class));
                        break;
                    //                    case "security":
                    //                        startActivity(new Intent(getActivity(), SecuritySettingActivity.class));
                    //                        break;
                    case "feedback":
                        startActivity(new Intent(getActivity(), FeedBackActivity.class));
                        break;
                    case "aboutus":
                        startActivity(new Intent(getActivity(), AboutUsActivity.class));
                        break;
                    case "version":
                        getAppVersion();
                        break;
                    case "exit":
                        SharedPreUtils.saveString(StringContants.TOKEN, "");
                        ObserverManager.getInstance().notifyObserver(EXIT);
                        break;
                }
                
            }
            
            
        });
        
        return view;
        
    }
    
    /**
     * 获取用户详情
     */
    private void getPersonDetail() {
        List<Param> paramList = new ArrayList<>();
        Param key = new Param("key", SharedPreUtils.getString(StringContants.KEY));
        Param token = new Param("Token", SharedPreUtils.getString(StringContants.TOKEN));
        paramList.add(key);
        paramList.add(token);
        HttpUtils.post(ConstantApi.DETAIL, paramList, new ResultCallback() {
            @Override
            public void onSuccess(Object response) {
                PersonBean personBean = new Gson().fromJson(response.toString(), PersonBean.class);
                if (personBean.getResult() == 200) {
                    // TODO: 2017/3/27 获取用户信息填充
                    tickName.setText(personBean.getContent().get(0).getMusername());
                    tvSign.setText(personBean.getContent().get(0).getMsignature());
                    ImageLoaderUtils.display(getActivity(), personBean.getContent().get(0).getMavatar(), R.mipmap
                            .mine_two, circleImageView);
                } else {
                    Crouton.makeText(getActivity(), personBean.getResultMsg(), Style.CONFIRM).show();
                }
            }
            
            @Override
            public void onFailure(Request request, Exception e) {
                
            }
            
            @Override
            public void onNoNetWork(String resultMsg) {
                
            }
        });
        
        
    }
    
    /**
     * 获取版本信息
     */
    public void getAppVersion() {
        List<Param> paramList = new ArrayList<>();
        Param key = new Param("key", SharedPreUtils.getString(StringContants.KEY));
        Param apptype = new Param("apptype", "android");
        paramList.add(key);
        paramList.add(apptype);
        HttpUtils.post(ConstantApi.VERSION, paramList, new ResultCallback() {
            @Override
            public void onSuccess(Object response) {
                VersionResponse versionResponse = new Gson().fromJson(response.toString(), VersionResponse.class);
                Log.d("version---", versionResponse.toString());
                if (200 == versionResponse.getResult()) {
                    mVersion = versionResponse.getVersion();
                    if (mVersion == null) {
                        //版本信息为空
                    } else {
                        //判断版本信息  版本高的话弹窗提示是否更新
                        if (PackageManagerUtils.getVersionCode(getActivity()) < Double.parseDouble(mVersion
                                .getVersionCode())) {
                            Toast.makeText(mActivity, "更新", Toast.LENGTH_SHORT).show();
                        } else {
                            //没有提示
                            Toast.makeText(mActivity, "没有新版本", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
            
            @Override
            public void onFailure(Request request, Exception e) {
                
            }
            
            @Override
            public void onNoNetWork(String resultMsg) {
                
            }
        });
    }
    
    @Override
    protected void loadData() {
        super.loadData();
        getPersonDetail();
    }
    
    
    @Override
    public void observerIsLogin(int isLogin) {
        
    }
}
