package com.looking.classicalparty.lib.common;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.looking.classicalparty.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;

public class PullToRefreshListView extends ListView {

    private View mHeaderView;
    private View mFooterView;

    private int mHeaderViewHeight;
    private int mFooterViewHeight;

    private boolean isHeaderViewEnable = true;// 头布局是否可用，默认可用
    private boolean isFooterViewEnable = true;// 脚布局是否可用，默认可用

    private TextView tv_header_title;
    private ImageView iv_arrow;
    private ImageView iv_refresh;
    private ImageView iv_refresh_complete;

    private TextView tv_footer_title;
    private ImageView iv_footer_arrow;
    private ImageView iv_footer_refresh;

    private int startY = -1;
    private boolean isOnRefresh = false;// 当前是否正在刷新
    private boolean isOnLoadingMore = false;// 当前是否加载更多
    private final static int RATIO = 3; // 实际的padding的距离与界面上偏移距离的比例

    private static final int STATE_PULL_TO_REFRESH = 1;// 下拉刷新
    private static final int STATE_RELEASE_TO_REFRESH = 2;// 松开刷新
    private static final int STATE_REFRESHING = 3;// 正在刷新
    private int mCurrentRefreshState = STATE_PULL_TO_REFRESH;// 当前状态初始值为下拉刷新

    private static final int STATE_PULL_TO_LOADMORE = 4;// 上拉加载更多
    private static final int STATE_RELEASE_TO_LOADMORE = 5;// 松开载入更多
    private static final int STATE_LOADMOREING = 6;// 正在加载更多
    private int mCurrentFooterState = STATE_PULL_TO_LOADMORE;// 当前状态初始值为上拉加载更多

    private RotateAnimation animUp;// 箭头向上动画
    private RotateAnimation animDown;// 箭头向下动画
    private RotateAnimation animRefresh;// 正在刷新动画

    private OnRefreshListener mListener;

    public PullToRefreshListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public PullToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PullToRefreshListView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
//        initAnim();// 初始化动画效果
        initHeaderView();
        initFooterView();
    }

    /**
     * 设置头布局刷新是否可用，默认true
     */
    public void setHeaderViewEnable(boolean isHeaderViewEnable) {
        this.isHeaderViewEnable = isHeaderViewEnable;
    }

    /**
     * 设置脚布局加载更多是否可用，默认true
     */
    public void setFooterViewEnable(boolean isFooterViewEnable) {
        this.isFooterViewEnable = isFooterViewEnable;
    }

    /**
     * 设置刷新头布局的背景颜色
     */
    public void setHeaderBackGround(int color) {
        mHeaderView.setBackgroundColor(color);
    }

    /**
     * 设置加载教布局的背景颜色
     */
    public void setFooterBackGround(int color) {
        mFooterView.setBackgroundColor(color);
    }

    /**
     * 初始化头布局
     */
    private void initHeaderView() {
        mHeaderView = View.inflate(getContext(), R.layout.item_refresh_header, null);
        // 在activity创建时，手动测量布局，获取测量布局的高度
        measureView(mHeaderView);
        mHeaderViewHeight = mHeaderView.getMeasuredHeight();
        // 设置padding隐藏头布局
        mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
        mHeaderView.invalidate();
        iv_refresh_complete = (ImageView) mHeaderView.findViewById(R.id.iv_refresh_complete);
        tv_header_title = (TextView) mHeaderView.findViewById(R.id.tv_header_title);

        final AnimationDrawable anim = (AnimationDrawable) iv_refresh.getDrawable();

        iv_refresh.post(new Runnable() {
            @Override
            public void run() {
                anim.stop();
                anim.start();
            }
        });

        this.addHeaderView(mHeaderView);// 添加头布局
    }

    /**
     * 初始化脚布局
     */
    private void initFooterView() {
        mFooterView = View.inflate(getContext(), R.layout.item_refresh_footer, null);
        // 在activity创建时，手动测量布局，获取测量布局的高度
        measureView(mFooterView);
        mFooterViewHeight = mFooterView.getMeasuredHeight();
        // 设置padding隐藏脚布局
        mFooterView.setPadding(0, 0, 0, -mFooterViewHeight);
        mFooterView.invalidate();

        tv_footer_title = (TextView) mFooterView.findViewById(R.id.tv_footer_title);
        iv_footer_refresh = (ImageView) mFooterView.findViewById(R.id.iv_footer_refresh);

        final AnimationDrawable anim = (AnimationDrawable) iv_footer_refresh.getDrawable();

        iv_footer_refresh.post(new Runnable() {
            @Override
            public void run() {
                anim.stop();
                anim.start();
            }
        });

        this.addFooterView(mFooterView);// 添加脚布局
    }

    /**
     * 初始化箭头的动画效果
     */
    private void initAnim() {
        // 向上转动的动画效果
        animUp = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animUp.setDuration(500);
        animUp.setFillAfter(true);
        // 向下转动的动画效果
        animDown = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animDown.setDuration(500);
        animDown.setFillAfter(true);
        // 正在刷新的动画效果
        animRefresh = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animRefresh.setDuration(500);
        animRefresh.setInterpolator(new LinearInterpolator());
        animRefresh.setRepeatCount(-1);
        animRefresh.setRepeatMode(Animation.RESTART);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = (int) ev.getY(); // 设置初始值为-1
                break;
            case MotionEvent.ACTION_MOVE:
                // 触摸按下时的事件有可能没有响应到，再次获取开始时的坐标
                if (startY == -1) {
                    startY = (int) ev.getY();
                }
                // 滑动时当前状态为正在刷新，什么都不用做
                if (mCurrentRefreshState == STATE_REFRESHING || mCurrentFooterState == STATE_LOADMOREING) {
                    break;
                }

                int endY = (int) ev.getY();
                int dY = endY - startY;

                /******** 向下划并且当前在第一个条目位置 ********/
                if (dY > 0 && getFirstVisiblePosition() == 0 && !isOnRefresh && !isOnLoadingMore && isHeaderViewEnable) {
                    int paddingTop = dY / RATIO - mHeaderViewHeight;
                    // 控制下拉的居上的间距不能超过控件高度
                    if (paddingTop > mHeaderViewHeight) {
                        paddingTop = mHeaderViewHeight;
                    }
                    if (paddingTop >= 0 && mCurrentRefreshState != STATE_RELEASE_TO_REFRESH) {
                        // 切换到松开刷新
                        mCurrentRefreshState = STATE_RELEASE_TO_REFRESH;
                        refreshHeaderState();
                    } else if (paddingTop < 0 && mCurrentRefreshState != STATE_PULL_TO_REFRESH) {
                        // 切换到下拉刷新
                        mCurrentRefreshState = STATE_PULL_TO_REFRESH;
                        refreshHeaderState();
                    }
                    mHeaderView.setPadding(0, paddingTop, 0, 0);
                    setSelection(0);
                }

                /******** 向上划且当前在最后一个条目位置 ********/
                if (dY < 0 && getLastVisiblePosition() == getCount() - 1 && !isOnLoadingMore && !isOnRefresh
                        && isFooterViewEnable && isFillScreenItem()) {
                    int paddingBottom = Math.abs(dY) / RATIO - mFooterViewHeight;
                    if (paddingBottom > mFooterViewHeight) {
                        paddingBottom = mFooterViewHeight;
                    }
                    if (paddingBottom >= 0 && mCurrentFooterState != STATE_RELEASE_TO_LOADMORE) {
                        // 切换到松开载入更多
                        mCurrentFooterState = STATE_RELEASE_TO_LOADMORE;
                        refreshFooterState();
                    } else if (paddingBottom <= 0 && mCurrentFooterState != STATE_PULL_TO_LOADMORE) {
                        // 切换到上拉加载更多
                        mCurrentFooterState = STATE_PULL_TO_LOADMORE;
                        refreshFooterState();
                    }
                    mFooterView.setPadding(0, 0, 0, paddingBottom);
                    setSelection(getCount() - 1);// 显示最后一个位置
                }
                break;
            case MotionEvent.ACTION_UP:
                startY = -1;// 起始坐标归零

                /******** 刷新操作 ********/
                if (isHeaderViewEnable) {
                    if (mCurrentRefreshState == STATE_RELEASE_TO_REFRESH) {
                        // 如果当前是松开刷新, 就要切换为正在刷新
                        mCurrentRefreshState = STATE_REFRESHING;
                        isOnRefresh = true;
                        // 显示头布局
                        mHeaderView.setPadding(0, 0, 0, 0);
                        refreshHeaderState();
                        // 下拉刷新回调
                        if (mListener != null) {
                            mListener.onrefresh();
                        }
                    } else if (mCurrentRefreshState == STATE_PULL_TO_REFRESH) {
                        // 隐藏头布局
                        mHeaderView.setPadding(0, 0, 0, -mHeaderViewHeight);
                    }
                }

                /******** 加载更多操作 ********/
                if (isFooterViewEnable) {
                    if (mCurrentFooterState == STATE_RELEASE_TO_LOADMORE) {
                        // 如果当前是松开载入更多, 就要切换为正在加载更多
                        mCurrentFooterState = STATE_LOADMOREING;
                        isOnLoadingMore = true;
                        // 显示脚布局
                        mFooterView.setPadding(0, 0, 0, 0);
                        refreshFooterState();
                        // 加载更多回调
                        if (mListener != null) {
                            mListener.onLoadMore();
                        }
                    } else if (mCurrentFooterState == STATE_PULL_TO_LOADMORE) {
                        // 隐藏脚布局
                        mFooterView.setPadding(0, 0, 0, -mFooterViewHeight);
                    }
                }
                break;
            default:
                break;
        }


        return super.onTouchEvent(ev);
    }

    /**
     * 根据当前的刷新状态刷新头布局
     */
    private void refreshHeaderState() {
        switch (mCurrentRefreshState) {
            case STATE_PULL_TO_REFRESH:
                tv_header_title.setText("下拉刷新↓");
                iv_refresh.clearAnimation();
                iv_refresh.setVisibility(View.INVISIBLE);
                iv_arrow.clearAnimation();
                iv_arrow.setVisibility(View.INVISIBLE);
//                iv_arrow.startAnimation(animDown);
                break;
            case STATE_RELEASE_TO_REFRESH:
                tv_header_title.setText("松开立即刷新↑");
                iv_arrow.setVisibility(View.INVISIBLE);
                iv_refresh.setVisibility(View.VISIBLE);
                final AnimationDrawable anim = (AnimationDrawable) iv_refresh.getDrawable();
                anim.start();
                break;
            case STATE_REFRESHING:
                tv_header_title.setText("努力加载中...");
                iv_refresh.setVisibility(View.VISIBLE);
                iv_arrow.clearAnimation();// 必须清除动画,才能隐藏控件
                iv_arrow.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }
    }

    /**
     * 根据当前加载更多的状态刷新脚布局
     */
    private void refreshFooterState() {
        switch (mCurrentFooterState) {
            case STATE_PULL_TO_LOADMORE:
                tv_footer_title.setText("上拉加载更多↑");
                iv_footer_refresh.clearAnimation();
                iv_footer_refresh.setVisibility(View.INVISIBLE);
                iv_footer_arrow.setVisibility(View.VISIBLE);
                break;
            case STATE_RELEASE_TO_LOADMORE:
                tv_footer_title.setText("松开立即加载↓");
                iv_footer_refresh.clearAnimation();
                iv_footer_refresh.setVisibility(View.INVISIBLE);
                iv_footer_arrow.setVisibility(View.VISIBLE);
                break;
            case STATE_LOADMOREING:
                tv_footer_title.setText("努力加载中...");
                iv_footer_refresh.setVisibility(View.VISIBLE);
                iv_footer_arrow.clearAnimation();
                iv_footer_arrow.setVisibility(View.INVISIBLE);
                break;
        }
    }

    /**
     * 刷新完成调用的方法
     */
    public void onRefreshAndLoadComplete() {
        if (isOnRefresh) {
            // 隐藏头布局，恢复初始状态
            isOnRefresh = false;
            final ValueAnimator va = ValueAnimator.ofInt(0, mHeaderViewHeight);
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int height = (Integer) va.getAnimatedValue();
                    mHeaderView.setPadding(0, -height, 0, 0);
                }

            });
            va.addListener(new Animator.AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mCurrentRefreshState = STATE_PULL_TO_REFRESH;
                    tv_header_title.setText("下拉刷新↓");
                    iv_refresh_complete.setVisibility(View.INVISIBLE);
                    iv_arrow.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }

            });
            va.setDuration(500);
            va.start();
            tv_header_title.setText("刷新完成");
            iv_refresh.clearAnimation();
            iv_refresh.setVisibility(View.INVISIBLE);
            iv_refresh_complete.setVisibility(View.VISIBLE);
        }
        if (isOnLoadingMore) {
            // 隐藏脚布局，恢复初始状态
            isOnLoadingMore = false;
            mFooterView.setPadding(0, 0, 0, -mFooterViewHeight);
            mCurrentFooterState = STATE_PULL_TO_LOADMORE;
            tv_footer_title.setText("上拉加载更多");
            iv_footer_refresh.clearAnimation();
            iv_footer_refresh.setVisibility(INVISIBLE);
            iv_footer_arrow.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 条目是否填满整个屏幕
     */
    private boolean isFillScreenItem() {

        final int firstVisiblePosition = this.getFirstVisiblePosition();
        final int lastVisiblePostion = this.getLastVisiblePosition();

        final int visibleItemCount = lastVisiblePostion - firstVisiblePosition + 1;
        final int totalItemCount = this.getCount();

        if (visibleItemCount < totalItemCount) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 测量View的宽高
     */
    private void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    /**
     * 重写条目item点击事件
     */
    private OnItemClickListener mItemListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemListener = listener;
        super.setOnItemClickListener(new OnItemClickListener() {// 将点击事件由当前类实现

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mItemListener != null) {
                    mItemListener.onItemClick(parent, view, position - getHeaderViewsCount(), id);
                }
            }
        });
    }

    /**
     * 重写条目item长按事件
     */
    private OnItemLongClickListener mItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mItemLongClickListener = listener;
        super.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (mItemLongClickListener != null) {
                    mItemLongClickListener.onItemLongClick(parent, view, position - getHeaderViewsCount(), id);
                }
                return true;
            }
        });
    }

    /**
     * 设置回调接口
     */
    public void setOnRefreshListener(OnRefreshListener listener) {
        mListener = listener;
    }

    public interface OnRefreshListener {
        // 下拉刷新的回调用法
        public void onrefresh();

        // 上拉加载更多回调方法
        public void onLoadMore();
    }

}
