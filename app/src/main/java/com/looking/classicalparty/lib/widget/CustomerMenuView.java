package com.looking.classicalparty.lib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin on 2016/8/11.
 */
public class CustomerMenuView extends LinearLayout {
    private OnItemListener listener;
    private List<ItemData> datas = new ArrayList<>();

    public CustomerMenuView(Context context) {
        super(context);
        initView();
    }

    public CustomerMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomerMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setOrientation(LinearLayout.VERTICAL);
    }

    /**
     * 设置数据对象
     *
     * @param datas
     */
    private void setDatas(List<ItemData> datas) {
        //第一次创建
        if (getChildCount() == 0) {
            for (ItemData item : datas) {
                //根据传输的内容加载不同的布局
                if (item.content == null) {
                    //定义一个中间的空行
                    LinearLayout ll = new LinearLayout(getContext());
                    ll.setMinimumHeight(30);
                    addView(ll);
                } else {
                    createItemView(item);
                }
            }
        } else {
            //第二次创建
            for (int index = 0; index < getChildCount(); index++) {
                LinearLayout childView = (LinearLayout) getChildAt(index);
                if (childView instanceof MenuMixContorl) {
                    MenuMixContorl menuChildView = (MenuMixContorl) childView;
                    menuChildView.setData(datas.get(index));
                }
            }
            if (datas.size() != getChildCount()) {
                for (int index = getChildCount(); index < datas.size(); index++) {
                    createItemView(datas.get(index));
                }
            }
        }

    }

    /**
     * 更新数据
     *
     * @param data
     */
    public void updateData(ItemData data) {
        //第二次创建
        for (int index = 0; index < getChildCount(); index++) {
            LinearLayout childView = (LinearLayout) getChildAt(index);
            if (childView instanceof MenuMixContorl) {
                MenuMixContorl menuChildView = (MenuMixContorl) childView;
                if (data.flag.equals(menuChildView.getData().flag)) {
                    menuChildView.setData(data);
                }
            }
        }
    }

    private void createItemView(ItemData item) {
        MenuMixContorl menuMixContorl = new MenuMixContorl(getContext());
        menuMixContorl.setData(item);
        addView(menuMixContorl);
        menuMixContorl.setTag(item);
        menuMixContorl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(v);
                }
            }
        });// end listener
    }

    public void setItemClickListener(OnItemListener listener) {
        this.listener = listener;
    }

    public CustomerMenuView addItem(int imgId, String content, String flag) {
        datas.add(new ItemData(imgId, content, flag));
        return this;
    }

    public CustomerMenuView addItem(String content, String flag, int imgId) {
        datas.add(new ItemData(content, flag, imgId));
        return this;
    }

    public CustomerMenuView addItem(int imgId, String content, String flag, String desc) {
        datas.add(new ItemData(imgId, content, flag));
        return this;
    }

    public CustomerMenuView addItem(int imgId, String content, String flag, boolean boo) {
        datas.add(new ItemData(imgId, content, flag, boo));
        return this;
    }

    //只有前面内容的布局，
    public CustomerMenuView addItem(String content, String flag, boolean boo) {
        datas.add(new ItemData(content, flag, boo));
        return this;
    }

    public CustomerMenuView addItem(String content, String desc, String flag, boolean boo) {
        datas.add(new ItemData(content, desc, flag, boo));
        return this;
    }

    public CustomerMenuView addItem(int imgId, String content, String desc, String flag, boolean boo) {
        datas.add(new ItemData(imgId, content, desc, flag, boo));
        return this;
    }

    public CustomerMenuView addDivider() {
        datas.add(new ItemData());
        return this;
    }

    public void build() {
        setDatas(datas);
    }

    public interface OnItemListener {
        void itemClick(View v);
        void itemUpdate(View v);
    }
}
