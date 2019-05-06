package com.novellotus.easyloading;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by：Rich on 2019/04/30
 */
public class LoadingView extends EasyLoadingView implements View.OnClickListener {


    //提示logo
    public ImageView img_tips;
    //提示文字
    public String tipsString = "网络请求失败";
    //提示文本
    public TextView tv_tips;
    //重新加载按钮
    public Button btn_reload;
    //点击回调接口
    private EasyReloadInterface easyReloadInterface;
    //动画
    private AnimationDrawable animationDrawable;
    //加载状态
    private Status status;
    //加载中图片
    private int iconLoading;
    //加载失败图片
    private int iconLoadingFail;

    public LoadingView(Context context) {
        super(context);
        status = Status.LOADING;
        iconLoading = R.drawable.anim_loading;
        iconLoadingFail = R.drawable.icon_fail;
        addTipsTextView();
        addLoadingImageView();
        addReLoadButton();
        setVisibility(View.GONE);
    }

    @Override
    public void setReloadInterface(EasyReloadInterface easyReloadInterface) {
        this.easyReloadInterface = easyReloadInterface;
    }

    @Override
    public Status getLoadingState() {
        return status;
    }


    /**
     * 加载中状态
     */
    public void loading(String... args) {
        status = Status.LOADING;
        setVisibility(View.VISIBLE);
        String tips = args.length == 0 ? "数据加载中，请稍后" : args[0];
        img_tips.setImageResource(iconLoading);
        if(img_tips.getDrawable() instanceof AnimationDrawable){
            animationDrawable = (AnimationDrawable) img_tips.getDrawable();
            animationDrawable.start();
        }

        tv_tips.setText(tips);
        btn_reload.setVisibility(View.INVISIBLE);
    }

    /**
     * 空数据状态
     */
    @Override
    public void loadEmpty(String... args) {
        status = Status.EMPTY;
        String tips = args.length == 0 ? "当前没有数据" : args[0];
        tv_tips.setText(tips);
        img_tips.setImageResource(R.drawable.icon_fail);
        btn_reload.setVisibility(View.INVISIBLE);
    }

    /**
     * 加载成功
     */
    @Override
    public void loadSuccess() {
        status = Status.SUCCESS;
        setVisibility(View.GONE);
    }

    /**
     * 加载失败
     */
    @Override
    public void loadFail(boolean reload, String... args) {
        status = Status.FAIL;
        if (animationDrawable != null)
            animationDrawable.stop();
        img_tips.setImageResource(iconLoadingFail);
        tv_tips.setText(args[0]);
        if (reload) {
            btn_reload.setVisibility(View.VISIBLE);
        } else {
            btn_reload.setVisibility(View.INVISIBLE);

        }
    }

    /**
     * 设置重新加载按钮的回调
     */
    @Override
    public void onClick(View v) {
        if (easyReloadInterface != null) {
            easyReloadInterface.reload();
        }
    }

    /**
     * 拦截点击事件,防止下层被覆盖的布局响应事件
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }


    /**
     * 设置提示图片,位于文字上方
     */
    @Override
    public void initLoadingIcon(int resId) {
        iconLoading = resId;
    }

    @Override
    public void initLoadingFailIcon(int resId) {
        iconLoadingFail = resId;
    }

    @Override
    void addLoadingImageView() {
        img_tips = new ImageView(getContext());
        img_tips.setImageResource(iconLoading);
        LayoutParams imgLp = new LayoutParams((int) getContext().getResources().getDimension(R.dimen.tips_img_width), (int) getContext().getResources().getDimension(R.dimen.tips_img_height));
        imgLp.addRule(RelativeLayout.ABOVE, R.id.id_text);
        imgLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        imgLp.setMargins(0, 0, 0, (int) getContext().getResources().getDimension(R.dimen.img_margin_bottom));
        img_tips.setId(R.id.id_img);
        addView(img_tips, imgLp);
    }

    /**
     * 提示文字居中显示
     */
    @Override
    public void addTipsTextView() {
        float textSize = getContext().getResources().getDimension(R.dimen.tips_tv_text_size);
        tv_tips = new TextView(getContext());
        tv_tips.setText(tipsString);
        tv_tips.setTextSize(textSize);
        tv_tips.setTextColor(Color.parseColor("#ffffff"));
        LayoutParams mWarnLp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mWarnLp.addRule(RelativeLayout.CENTER_IN_PARENT);
        tv_tips.setId(R.id.id_text);
        addView(tv_tips, mWarnLp);
    }

    /**
     * 重新加载按钮位于TextView下方
     */
    @Override
    public void addReLoadButton() {
        btn_reload = new Button(getContext());
        btn_reload.setText("重新加载");
        btn_reload.setTextSize(15);
        btn_reload.setPadding(30, 10, 30, 10);
        btn_reload.setTextColor(Color.parseColor("#ffffff"));
        btn_reload.setBackgroundResource(R.drawable.selector_button);
        btn_reload.setOnClickListener(this);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) getContext().getResources().getDimension(R.dimen.button_height));
        lp.setMargins(0, (int) getContext().getResources().getDimension(R.dimen.btn_margin_top), 0, 0);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.addRule(RelativeLayout.BELOW, R.id.id_text);
        btn_reload.setId(R.id.id_button);
        addView(btn_reload, lp);
    }

}
