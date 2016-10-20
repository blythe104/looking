package com.looking.classicalparty.lib.base.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.looking.classicalparty.lib.utils.LogUtils;

/**
 * Created by xin on 2016/10/18.
 */
public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";

    public FragmentActivity mActivity;
    private View mRootView;// 缓存Fragment view

    private boolean isVisible;// fragment对用户是否可见
    private boolean isPrepared;// 标志位，标记已经初始化完成。
    private boolean hasLoadOnce = false;// 标志位，标记是否已经加载过一次数据，第二次就不要再加载数据了。

    /**
     * 获取是否加载过一次数据
     */
    protected boolean isHasLoadOnce() {
        return hasLoadOnce;
    }

    /**
     * 设置是否只加载一次数据
     */
    public void setHasLoadOnce(boolean hasLoadOnce) {
        this.hasLoadOnce = hasLoadOnce;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG + "--onCreate", getClass().getSimpleName());
        // Fragment被创建时获取其所在Activity对象
        mActivity = getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        LogUtils.d(TAG + "--onCreateView", getClass().getSimpleName());
        // Fragment创建视图时初始化视图
        if (null != mRootView) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (null != parent) {
                parent.removeView(mRootView);
            }
        } else {
            mRootView = initView(inflater, container, savedInstanceState);
        }
        isPrepared = true;
        onVisibleData();
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.d(TAG + "--onActivityCreated", getClass().getSimpleName());
        // Fragment所在的Activity创建结束时初始化数据
        loadData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //        LogUtils.d(TAG + "--setUserVisibleHint", getClass().getSimpleName());
        LogUtils.d(TAG + "--setUserVisibleHint", getClass().getSimpleName() + "---" + getUserVisibleHint());
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisibleData();
        } else {
            isVisible = false;
        }
    }

    /**
     * 初始化布局,子类必须实现
     */
    protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 初始化时加载数据
     */
    protected void loadData() {
    }

    /**
     * 对用户可见时加载数据
     */
    protected void onVisibleData() {
        // 布局未初始化好或不可见状态，不加载数据
        if (!isPrepared || !isVisible) {
            return;
        }
        // 已经加载过一次数据，不再加载数据
        if (hasLoadOnce) {
            return;
        }
        OnLazyLoadData();
    }

    /**
     * fragment对用户可见时加载数据,并可设置是否只加载一次
     */
    protected void OnLazyLoadData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d(TAG + "--onResume", getClass().getSimpleName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG + "--onDestory", getClass().getSimpleName());
    }
}
