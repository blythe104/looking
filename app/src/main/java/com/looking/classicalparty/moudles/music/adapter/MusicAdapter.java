package com.looking.classicalparty.moudles.music.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.utils.ImageLoaderUtils;
import com.looking.classicalparty.moudles.music.bean.MusicDetailBean;

import java.util.List;

/**
 * Created by xin on 2017/3/29.
 */

public class MusicAdapter extends BaseAdapter {
    private Context context;
    private MusicListener listener;
    
    private List<MusicDetailBean.ActivityEntity> musicDatas;
    
    public MusicAdapter(Context context, List<MusicDetailBean.ActivityEntity> musicDatas) {
        this.context = context;
        this.musicDatas = musicDatas;
    }
    
    @Override
    public int getCount() {
        if (null == musicDatas) {
            return 0;
        }
        return musicDatas.size();
    }
    
    @Override
    public MusicDetailBean.ActivityEntity getItem(int position) {
        return musicDatas.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        
        if (null == convertView) {
            mViewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_music_layout, null);
            mViewHolder.iv_music = (ImageView) convertView.findViewById(R.id.iv_music);
            mViewHolder.tv_musicName = (TextView) convertView.findViewById(R.id.tv_musicName);
            mViewHolder.tv_singer = (TextView) convertView.findViewById(R.id.tv_singer);
            mViewHolder.iv_more = (ImageView) convertView.findViewById(R.id.iv_more);
            mViewHolder.ll_listener_music = (LinearLayout) convertView.findViewById(R.id.ll_listener_music);
            
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        ImageLoaderUtils.display(context, "http://www.jingdian.party/" + musicDatas.get(position).getCover_path(),
                mViewHolder.iv_music);
        mViewHolder.tv_musicName.setText(musicDatas.get(position).getTitle());
        mViewHolder.tv_singer.setText(musicDatas.get(position).getDirector());
        mViewHolder.ll_listener_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.showListenerDialog(position);
                
            }
        });
        mViewHolder.iv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.showMusicDetail(position);
                
            }
        });
        
        
        return convertView;
    }
    
    public void setMusicListener(MusicListener listener) {
        this.listener = listener;
        
    }
    
    public interface MusicListener {
        public void showListenerDialog(int position);
        
        public void showMusicDetail(int position);
    }
    
    static class ViewHolder {
        
        public TextView tv_musicName;
        public TextView tv_singer;
        public ImageView iv_music;
        public ImageView iv_more;
        public LinearLayout ll_listener_music;
        
    }
}
