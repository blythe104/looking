package com.looking.classicalparty.moudles.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.looking.classicalparty.R;
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
            mViewHolder.writer = (TextView) convertView.findViewById(R.id.writer);

            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.iv_movie.setImageResource(R.mipmap.movie);
        mViewHolder.movie.setText(musicDatas.get(position).getTitle());
        mViewHolder.writer.setText(musicDatas.get(position).getDirector());

        return convertView;
    }

    static class ViewHolder {

        public TextView movie;
        public TextView writer;
        public ImageView iv_movie;

    }
}
