package com.novellotus.easyloading;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.reflect.Field;


/**
 * Created by Rich on 2019/4/26.
 */
public class EasyLoading {


    private EasyLoadingView loadingView;

    public EasyLoadingView build(Context context) {
        if (loadingView == null)
            loadingView = new LoadingView(context);
        return loadingView;
    }

    /**
     * @param easyLoadingView 传入自定义加载布局
     */
    public EasyLoadingView setLoadingView(EasyLoadingView easyLoadingView) {
        loadingView = easyLoadingView;
        return loadingView;
    }

    /**
     * 获取状态栏高度,沉浸式布局时需要下移这个高度
     * */
    @SuppressLint("PrivateApi")
    public int getStateBar() {
        Class c;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return loadingView.getContext().getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 动态添加加载布局,将加载布局添加进根布局
     * @param viewGroup 根布局
     * @param marginTop 加载布局下移距离
     */
    public void addLoadingView(ViewGroup viewGroup, int marginTop) {
        if (viewGroup instanceof RelativeLayout) {
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.setMargins(0, marginTop, 0, 0);
            viewGroup.addView(loadingView, lp);
        } else if (viewGroup instanceof LinearLayout) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.setMargins(0, marginTop, 0, 0);
            viewGroup.addView(loadingView, lp);
        } else if (viewGroup instanceof FrameLayout) {
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.setMargins(0, marginTop, 0, 0);
            viewGroup.addView(loadingView, lp);
        } else if (viewGroup instanceof ConstraintLayout) {
            ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.setMargins(0, marginTop, 0, 0);
            viewGroup.addView(loadingView, lp);
        } else if (viewGroup instanceof CoordinatorLayout) {
            CoordinatorLayout.LayoutParams lp = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.setMargins(0, marginTop, 0, 0);
            viewGroup.addView(loadingView, lp);
        } else {
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            viewGroup.addView(loadingView, lp);
        }

    }


}
