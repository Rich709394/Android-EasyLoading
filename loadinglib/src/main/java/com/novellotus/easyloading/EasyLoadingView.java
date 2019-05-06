package com.novellotus.easyloading;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * Created by Rich on 2019/4/28.
 */
public abstract class EasyLoadingView extends RelativeLayout {

    public EasyLoadingView(Context context) {
        super(context);
    }

    /**
     * 初始化加载中图片ImageView
     */
    public abstract void initLoadingIcon(int resId);

    /**
     * 初始化加载失败图片ImageView
     */
    public abstract void initLoadingFailIcon(int resId);

    /**
     * 添加ImageView
     */
    abstract void addLoadingImageView();

    /**
     * 初始化描述文字TextView
     */
    abstract void addTipsTextView();

    /**
     * 初始化重新加载按钮Button
     */
    abstract void addReLoadButton();

    /**
     * 加载中状态
     */
    public abstract void loading(String... args);

    /**
     * 空数据状态
     */
    public abstract void loadEmpty(String... args);

    /**
     * 加载成功
     */
    public abstract void loadSuccess();

    /**
     * 加载失败
     */
    public abstract void loadFail(boolean reload, String... args);

    /**
     * 添加点击响应
     */
    public abstract void setReloadInterface(EasyReloadInterface easyReloadInterface);

    public abstract Status getLoadingState();


}
