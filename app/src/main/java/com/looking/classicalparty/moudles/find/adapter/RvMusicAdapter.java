package com.looking.classicalparty.moudles.find.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.looking.classicalparty.R;
import com.looking.classicalparty.moudles.find.bean.MusicBean;

import java.util.List;

/**
 * Created by xin on 2016/10/25.
 */

public class RvMusicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<MusicBean> mDatas;
    private OnItemClickListener mOnItemClickListener;
    private Context context;


    public RvMusicAdapter(List<MusicBean> datas) {
        this.mDatas = datas;
    }

    public RvMusicAdapter(FragmentManager fm, Context context) {
        this.context = context;
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_THEME.ordinal()) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.theme_desc_layout, parent, false);
            return new ThemeViewHolder(view);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_MUSIC.ordinal()) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_detail_layout, parent, false);
            return new MusicViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ThemeViewHolder) {
            ((ThemeViewHolder) holder).mTvTitle.setText("MUSIC");
            ((ThemeViewHolder) holder).mIvTitle.setImageResource(R.mipmap.img_music);
        } else if (holder instanceof MusicViewHolder) {
            ((MusicViewHolder) holder).mTvName.setText(mDatas.get(position).getTitle());
            if (mOnItemClickListener != null) {
                ((MusicViewHolder) holder).mTvListen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.toListenClick(((MusicViewHolder) holder).mTvListen, position);

                    }
                });
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position < 0 ? ITEM_TYPE.ITEM_TYPE_THEME.ordinal() : ITEM_TYPE.ITEM_TYPE_MUSIC.ordinal();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    // 定义类型
    public static enum ITEM_TYPE {
        ITEM_TYPE_THEME,
        ITEM_TYPE_MUSIC
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int positon);

        void toListenClick(View view, int position);

    }

    /**
     * 定义的主题ViewHolder
     */
    public class ThemeViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvTitle;
        private TextView mTvTitle;

        public ThemeViewHolder(View itemView) {
            super(itemView);
            mIvTitle = (ImageView) itemView.findViewById(R.id.iv_title);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }


    public class MusicViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName;
        private TextView mTvListen;

        public MusicViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            mTvListen = (TextView) itemView.findViewById(R.id.tv_listen);

        }
    }
}
