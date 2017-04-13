package com.looking.classicalparty.moudles.main.fragment;

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
import com.looking.classicalparty.moudles.music.adapter.MusicAdapter;
import com.looking.classicalparty.moudles.music.bean.MusicDetailBean;
import com.looking.classicalparty.moudles.music.dialog.MusicDetailDialog;
import com.looking.classicalparty.moudles.music.dialog.MusicDialog;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin on 2016/10/19.
 */
public class MusicFragment extends BaseFragment implements View.OnClickListener {
    
    private ListView lv_music;
    private List<MusicDetailBean.ActivityEntity> musicDatas;
    private MusicAdapter musicAdapter;
    private int curPage = 1;
    private View loadMoreFoot;
    private LinearLayout loadMore;
    private TextView mTvDesc;
    private int totalPage;
    private MusicDetailDialog musicDetailDialog;
    
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.music_fragment_layout, null);
        
        musicDetailDialog = new MusicDetailDialog(getActivity());
        lv_music = (ListView) view.findViewById(R.id.listview_music);
        loadMoreFoot = View.inflate(getActivity(), R.layout.ll_foot_layout, null);
        loadMore = (LinearLayout) loadMoreFoot.findViewById(R.id.ll_load_more);
        mTvDesc = (TextView) loadMoreFoot.findViewById(R.id.tv_desc);
        
        lv_music.addFooterView(loadMoreFoot);
        
        musicDatas = new ArrayList<>();
        musicAdapter = new MusicAdapter(getActivity(), musicDatas);
        lv_music.setAdapter(musicAdapter);
        loadMore.setOnClickListener(this);
        musicAdapter.setMusicListener(new MusicAdapter.MusicListener() {
            @Override
            public void showListenerDialog(int position) {
                musicDialog("http://www.jingdian.party/" + musicDatas.get(position).getCover_path(), "http://www" +
                        "" + ".jingdian.party/" + musicDatas.get(position).getV_path(), musicDatas.get(position)
                        .getTitle(), musicDatas.get(position).getDirector());
            }
            
            @Override
            public void showMusicDetail(int position) {
                musicDetailDialog.initMusicData(musicDatas.get(position).getTitle());
                musicDetailDialog.show();
                
            }
        });
        return view;
    }
    
    private void musicDialog(String imgPath, String path, String title, String singer) {
        MusicDialog musicDialog = new MusicDialog(getActivity());
        musicDialog.show();
        musicDialog.initMusicData(imgPath, path, title, singer);
    }
    
    @Override
    public void loadData() {
        super.loadData();
        getMusicDatas(curPage);
    }
    
    /**
     * 获取音乐列表数据
     */
    private void getMusicDatas(int curPage) {
        List<Param> paramList = new ArrayList<>();
        Param key = new Param("key", SharedPreUtils.getString(StringContants.KEY));
        Param page = new Param("page", curPage + "");
        Param pageCount = new Param("pageCount", 10 + "");
        paramList.add(key);
        paramList.add(page);
        paramList.add(pageCount);
        HttpUtils.post(ConstantApi.MUSIC, paramList, new ResultCallback() {
            @Override
            public void onSuccess(Object response) {
                MusicDetailBean musicDetailBean = new Gson().fromJson(response.toString(), MusicDetailBean.class);
                if (musicDetailBean.getResult() == 200) {
                    musicDatas.addAll(musicDetailBean.getActivity());
                }
                totalPage = musicDetailBean.getTotalPage();
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
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_load_more:
                curPage++;
                if (curPage <= totalPage) {
                    getMusicDatas(curPage);
                } else {
                    mTvDesc.setText("已加载全部");
                    mTvDesc.setTextColor(Color.parseColor("#8c909d"));
                }
                break;
        }
        
    }
    
    
}
