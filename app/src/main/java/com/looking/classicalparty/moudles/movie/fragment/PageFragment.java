package com.looking.classicalparty.moudles.movie.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.fragment.BaseFragment;
import com.looking.classicalparty.lib.constants.ConstantApi;
import com.looking.classicalparty.lib.constants.StringContants;
import com.looking.classicalparty.lib.http.HttpUtils;
import com.looking.classicalparty.lib.http.Param;
import com.looking.classicalparty.lib.http.ResultCallback;
import com.looking.classicalparty.lib.utils.SharedPreUtils;
import com.looking.classicalparty.moudles.movie.adapter.VideoAdapter;
import com.looking.classicalparty.moudles.movie.bean.GetVideoBean;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

public class PageFragment extends BaseFragment implements View.OnClickListener {

    public static final String ARG_PAGE = "ARG_PAGE";
    List<GetVideoBean.ActivityEntity> videoDatas;
    int curPage = 1;
    private String mVid;
    private ListView llVideo;
    private int totalPage;
    private VideoAdapter videoAdapter;
    private View loadMoreFoot;
    private LinearLayout loadMore;
    private TextView mTvDesc;

    public static PageFragment newInstance(int page, String vid) {
        Bundle args = new Bundle();
        args.putString(ARG_PAGE, vid);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVid = getArguments().getString(ARG_PAGE);
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        llVideo = (ListView) view.findViewById(R.id.ll_video);

        loadMoreFoot = View.inflate(getActivity(), R.layout.ll_foot_layout, null);
        loadMore = (LinearLayout) loadMoreFoot.findViewById(R.id.ll_load_more);
        mTvDesc = (TextView) loadMoreFoot.findViewById(R.id.tv_desc);

        llVideo.addFooterView(loadMoreFoot);

        videoDatas = new ArrayList<>();
        videoAdapter = new VideoAdapter(getActivity(), videoDatas);
        llVideo.setAdapter(videoAdapter);
        loadMore.setOnClickListener(this);
        return view;
    }

    @Override
    protected void loadData() {
        super.loadData();
        getVideoDatas(curPage, mVid);
    }

    /**
     * 获取音乐列表数据
     */
    private void getVideoDatas(int curPage, String vid) {
        List<Param> paramList = new ArrayList<>();
        Param key = new Param("key", SharedPreUtils.getString(StringContants.KEY));
        Param page = new Param("page", curPage + "");
        Param videoid = new Param("cate_id", vid);
        Param pageCount = new Param("pageCount", 10 + "");
        paramList.add(key);
        paramList.add(page);
        paramList.add(pageCount);
        paramList.add(videoid);
        HttpUtils.post(ConstantApi.VIDEO, paramList, new ResultCallback() {
            @Override
            public void onSuccess(Object response) {
                GetVideoBean getVideoBean = new Gson().fromJson(response.toString(), GetVideoBean.class);
                if (getVideoBean.getResult() == 200) {
                    videoDatas.addAll(getVideoBean.getActivity());
                }
                totalPage = getVideoBean.getTotalPage();
                videoAdapter.notifyDataSetChanged();
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_load_more:
                curPage++;
                if (curPage <= totalPage) {
                    getVideoDatas(curPage, mVid);
                } else {
                    mTvDesc.setText("已加载全部");
                    mTvDesc.setTextColor(Color.parseColor("#8c909d"));
                }
                break;
        }


    }
}
