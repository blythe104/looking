package com.looking.classicalparty.moudles.movie.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.utils.ImageLoaderUtils;
import com.looking.classicalparty.moudles.movie.bean.GetVideoBean;

import java.util.List;

/**
 * Created by xin on 2017/3/29.
 */

public class VideoAdapter extends BaseAdapter {
    private Context context;
    
    private List<GetVideoBean.ActivityEntity> musicDatas;
    
    public VideoAdapter(Context context, List<GetVideoBean.ActivityEntity> musicDatas) {
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
    public GetVideoBean.ActivityEntity getItem(int position) {
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
            convertView = View.inflate(context, R.layout.item_movie_layout, null);
            mViewHolder.iv_movie = (ImageView) convertView.findViewById(R.id.iv_movie);
            mViewHolder.movie = (TextView) convertView.findViewById(R.id.movie);
            mViewHolder.score = (TextView) convertView.findViewById(R.id.score);
            mViewHolder.director = (TextView) convertView.findViewById(R.id.director);
            mViewHolder.actors = (TextView) convertView.findViewById(R.id.actors);
            mViewHolder.musername = (TextView) convertView.findViewById(R.id.musername);
            
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        ImageLoaderUtils.display(context, "http://www.jingdian.party/" + musicDatas.get(position).getCover_path(),
                mViewHolder.iv_movie);
        mViewHolder.movie.setText(musicDatas.get(position).getTitle());
        mViewHolder.director.setText("导演：" + musicDatas.get(position).getDirector());
        mViewHolder.score.setText(musicDatas.get(position).getScores());
        mViewHolder.actors.setText("演员：" + musicDatas.get(position).getActors());
        String musername;
        if (TextUtils.isEmpty(musicDatas.get(position).getMusername())) {
            musername = "classical";
        } else {
            musername = musicDatas.get(position).getMusername();
        }
        mViewHolder.musername.setText("推荐者：" + musername);
        
        return convertView;
    }
    
    static class ViewHolder {
        
        public TextView movie;
        public ImageView iv_movie;
        public TextView score;
        public TextView director;
        public TextView actors;
        public TextView musername;
        
    }
}
