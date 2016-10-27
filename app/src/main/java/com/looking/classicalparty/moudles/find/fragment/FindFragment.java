package com.looking.classicalparty.moudles.find.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.fragment.BaseFragment;
import com.looking.classicalparty.moudles.find.adapter.RecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by xin on 2016/10/19.
 */
public class FindFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    private List<Integer> datas;
    private SwipeRefreshLayout mRefresh;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_fragment_layout, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mRefresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefresh.setRefreshing(false);
                    }
                }, 5000);
            }
        });
        initRecycleView();
        return view;
    }

    /**
     * 初始化recycleView
     */
    private void initRecycleView() {
        //2.创建一个垂直的线性布局(一个布局管理器layoutManager只能绑定一个Recyclerview)
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager
                .VERTICAL, false);

        //找到RecyclerView，并设置布局管理器
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        //动态设置gridlayout的列数
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.getItemViewType(position) == RecycleViewAdapter.ITEM_TYPE.ITEM_TYPE_THEME.ordinal() ?
                        gridLayoutManager.getSpanCount() : 1;
            }
        });

        //3.取得数据集(此处，应根据不同的主题查询得不同的数据传入到 MyRecyclerCardviewAdapter中构建adapter)
        datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(i);
        }
        //4.创建adapter
        adapter = new RecycleViewAdapter(datas);
        //将RecyclerView组件绑定adapter
        recyclerView.setAdapter(adapter);

        //5.在Adapter中添加好事件后，变可以在这里注册事件实现监听了
        adapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int positon) {
                Crouton.makeText(getActivity(), "您点击了" + positon, Style.CONFIRM).show();
            }

            @Override
            public void toListenClick(View view, int position) {
                Crouton.makeText(getActivity(), "listen music", Style.CONFIRM).show();

            }
        });

    }
}
