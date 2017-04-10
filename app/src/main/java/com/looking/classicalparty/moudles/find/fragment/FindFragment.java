package com.looking.classicalparty.moudles.find.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.fragment.BaseFragment;
import com.looking.classicalparty.lib.constants.ConstantApi;
import com.looking.classicalparty.lib.constants.StringContants;
import com.looking.classicalparty.lib.http.HttpUtils;
import com.looking.classicalparty.lib.http.Param;
import com.looking.classicalparty.lib.http.ResultCallback;
import com.looking.classicalparty.lib.utils.ImageLoaderUtils;
import com.looking.classicalparty.lib.utils.LogUtils;
import com.looking.classicalparty.lib.utils.SharedPreUtils;
import com.looking.classicalparty.moudles.find.adapter.ReviewAdapter;
import com.looking.classicalparty.moudles.find.adapter.RvMusicAdapter;
import com.looking.classicalparty.moudles.find.bean.BannerBean;
import com.looking.classicalparty.moudles.find.bean.FindBean;
import com.looking.classicalparty.moudles.find.bean.MusicBean;
import com.looking.classicalparty.moudles.find.bean.ReviewsBean;
import com.looking.classicalparty.moudles.movie.view.MovieDetailActivity;
import com.looking.classicalparty.moudles.music.service.PlayerService;
import com.squareup.okhttp.Request;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin on 2016/10/19.
 */
public class FindFragment extends BaseFragment {
    
    List<String> images;
    private RecyclerView recyclerView;
    private ReviewAdapter adapter;
    private List<ReviewsBean> datas;
    private List<MusicBean> musicdatas;
    private Banner mBanner;
    private RecyclerView rvMusic;
    private RvMusicAdapter musicAdapter;
    private ViewPager findViewPager;
    
    
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_fragment_layout, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        rvMusic = (RecyclerView) view.findViewById(R.id.rv_music);
        
        mBanner = (Banner) view.findViewById(R.id.find_banner);
        findViewPager = (ViewPager) view.findViewById(R.id.find_viewpager);
        initBannerData();
        
        getHomeData();
        initReviewRv();
        initReviewMusic();
        return view;
    }
    
    private void initBannerData() {
        
        //设置图片加载集合
        images = new ArrayList<>();
        
    }
    
    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();
    }
    
    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }
    
    /**
     * 初始化音乐
     */
    private void initReviewMusic() {
        //2.创建一个垂直的线性布局(一个布局管理器layoutManager只能绑定一个Recyclerview)
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        
        //找到RecyclerView，并设置布局管理器
        rvMusic.setLayoutManager(layoutManager);
        rvMusic.setHasFixedSize(true);
        //动态设置gridlayout的列数
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return musicAdapter.getItemViewType(position) == RvMusicAdapter.ITEM_TYPE.ITEM_TYPE_THEME.ordinal() ?
                        layoutManager.getSpanCount() : 1;
            }
        });
        
        //3.取得数据集(此处，应根据不同的主题查询得不同的数据传入到 MyRecyclerCardviewAdapter中构建adapter)
        musicdatas = new ArrayList<>();
        
        //4.创建adapter
        musicAdapter = new RvMusicAdapter(getActivity(), musicdatas);
        //将RecyclerView组件绑定adapter
        rvMusic.setAdapter(musicAdapter);
        
        //5.在Adapter中添加好事件后，变可以在这里注册事件实现监听了
        musicAdapter.setOnItemClickListener(new RvMusicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int positon) {
                Intent intent = new Intent();
                intent.putExtra("path", "http://www.jingdian.party/" + musicdatas.get(positon).getV_path());
                intent.putExtra("MSG", StringContants.PLAY_MSG);
                intent.setClass(getContext(), PlayerService.class);
                getContext().startService(intent);       //启动服务
            }
        });
        
    }
    
    /**
     * 初始化recycleView
     */
    private void initReviewRv() {
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
                return adapter.getItemViewType(position) == ReviewAdapter.ITEM_TYPE.ITEM_TYPE_THEME.ordinal() ?
                        gridLayoutManager.getSpanCount() : 1;
            }
        });
        
        //3.取得数据集(此处，应根据不同的主题查询得不同的数据传入到 MyRecyclerCardviewAdapter中构建adapter)
        datas = new ArrayList<>();
        
        //4.创建adapter
        adapter = new ReviewAdapter(getActivity(), datas);
        //将RecyclerView组件绑定adapter
        recyclerView.setAdapter(adapter);
        
        //5.在Adapter中添加好事件后，变可以在这里注册事件实现监听了
        adapter.setOnItemClickListener(new ReviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int positon) {
                Intent detailIntent = new Intent(getActivity(), MovieDetailActivity.class);
                detailIntent.putExtra("id", datas.get(positon).getId());
                startActivity(detailIntent);
            }
        });
        
    }
    
    /**
     * 获取发现页的全部数据信息
     */
    public void getHomeData() {
        List<Param> paramList = new ArrayList<>();
        Param key = new Param("key", SharedPreUtils.getString(StringContants.KEY, ""));
        paramList.add(key);
        HttpUtils.post(ConstantApi.HOME, paramList, new ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                LogUtils.d("find data---" + response.toString());
                FindBean findBean = new Gson().fromJson(response.toString(), FindBean.class);
                if (findBean.getResult() == 200) {
                    datas.addAll(findBean.getContent().getReviews());
                    musicdatas.addAll(findBean.getContent().getMusic());
                    findViewPager.setAdapter(new BannerAdapter(getActivity(), findBean.getContent().getBanner()));
                }
                adapter.notifyDataSetChanged();
                musicAdapter.notifyDataSetChanged();
            }
            
            @Override
            public void onFailure(Request request, Exception e) {
                
            }
            
            @Override
            public void onNoNetWork(String resultMsg) {
                
            }
        });
        
    }
    
    private class BannerAdapter extends PagerAdapter {
        
        private static final String TAG = "GetGameBannerAdapter";
        
        private Context context;
        
        private List<BannerBean> datas;
        
        public BannerAdapter(Context context, List<BannerBean> datas) {
            this.context = context;
            this.datas = datas;
        }
        
        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
        
        @Override
        public int getCount() {
            if (null == datas)
                return 0;
            return datas.size();
        }
        
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        
        @Override
        public Object instantiateItem(final ViewGroup container, final int position) {
            ImageView image = new ImageView(context);
            ImageLoaderUtils.display(context, datas.get(position).getFirstimage(), image);
            container.addView(image);
            return image;
        }
        
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
