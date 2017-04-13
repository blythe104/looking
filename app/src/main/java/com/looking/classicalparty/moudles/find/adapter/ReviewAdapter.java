package com.looking.classicalparty.moudles.find.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.utils.ImageLoaderUtils;
import com.looking.classicalparty.moudles.find.bean.ReviewsBean;

import java.util.List;

/**
 * Created by xin on 2016/10/25.
 */

public class ReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<ReviewsBean> mDatas;
    private OnItemClickListener mOnItemClickListener;
    private Context context;


    public ReviewAdapter(Context context, List<ReviewsBean> datas) {
        this.context = context;
        this.mDatas = datas;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_THEME.ordinal()) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.theme_desc_layout, parent, false);
            return new ThemeViewHolder(view);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_MOVIE.ordinal()) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_detail_layout, parent, false);
            return new MovieViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ThemeViewHolder) {
            ((ThemeViewHolder) holder).mTvTitle.setText("MOVIE");
            ((ThemeViewHolder) holder).mIvTitle.setImageResource(R.mipmap.img_music);
        } else if (holder instanceof MovieViewHolder) {
            ImageLoaderUtils.display(context, mDatas.get(position).getCover_path(), ((MovieViewHolder) holder)
                    .mIvPhoto);
            ((MovieViewHolder) holder).mTvName.setText(mDatas.get(position).getTitle());
            ((MovieViewHolder) holder).mTvWriter.setText(mDatas.get(position).getDirector());
            ((MovieViewHolder) holder).mRecommer.setText(mDatas.get(position).getMusername());
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(((MovieViewHolder) holder).mIvPhoto, position);
                    }
                });
            }
        }

    }


    @Override
    public int getItemViewType(int position) {
        return position < 0 ? ITEM_TYPE.ITEM_TYPE_THEME.ordinal() : ITEM_TYPE.ITEM_TYPE_MOVIE.ordinal();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    // 定义类型
    public static enum ITEM_TYPE {
        ITEM_TYPE_THEME,
        ITEM_TYPE_MOVIE,

    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int positon);
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

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIvPhoto;
        private TextView mTvName;
        private TextView mTvWriter;
        private TextView mRecommer;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mIvPhoto = (ImageView) itemView.findViewById(R.id.iv_photo);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            mTvWriter = (TextView) itemView.findViewById(R.id.tv_writer);
            mRecommer = (TextView) itemView.findViewById(R.id.tv_recommer);
        }
    }
}
