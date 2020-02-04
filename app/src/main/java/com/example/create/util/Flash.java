package com.example.create.util;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.create.R;


/**
 * Create by 张瀛煜 on 2019/10/31
 */
public class Flash extends ListView implements AbsListView.OnScrollListener {
    private View headre;
    private final int NONE = 0;
    private final int PULL = 1;
    private final int RELASE = 2;
    private final int RELASEING = 3;
    private final int FINISH = 4;
    private int startY, state, scrollState, firstVisibleItem;
    private int headerHeight = 150;
    private boolean isRemark;

    public Flash(Context context) {
        super(context);
        initView(context);
    }

    public interface ReFlash{
        void reFlash();
    }

    private ReFlash reFlash;

    public void setReFlash(ReFlash reFlash) {
        this.reFlash = reFlash;
    }

    public Flash(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public Flash(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        headre = inflater.inflate(R.layout.flahs_header, null);
        topPadding(-headerHeight);
        addHeaderView(headre);
        setOnScrollListener(this);
    }

    private void topPadding(int topPadding) {
        headre.setPadding(headre.getPaddingLeft(), topPadding, headre.getPaddingRight(), headre.getPaddingBottom());
        headre.invalidate();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        this.scrollState = scrollState;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.firstVisibleItem = firstVisibleItem;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (firstVisibleItem == 0) {
                    isRemark = true;
                    startY = (int) ev.getY();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                omMove(ev);
                break;
            case MotionEvent.ACTION_UP:
                if (state == RELASE) {
                    state = RELASEING;
                    MyFefalash();
                    reFlash.reFlash();
                } else {
                    state = NONE;
                    isRemark = false;
                    MyFefalash();
                }
                break;
        }
        return super.onTouchEvent(ev);

    }

    private void omMove(MotionEvent ev) {
        if (!isRemark) {
            return;
        }
        int tempY = (int) ev.getY();
        int space = tempY - startY;
        int topPadding = space - headerHeight;
        switch (state) {
            case NONE:
                if (space > 0) {
                    state = PULL;
                    MyFefalash();
                }
                break;
            case PULL:
                topPadding(topPadding);
                if (space > headerHeight + 60 && scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    state = RELASE;
                    MyFefalash();
                }
                break;
            case RELASE:
                topPadding(topPadding);
                if (space < headerHeight + 60) {
                    state = PULL;
                    MyFefalash();
                } else if (space    <= 0) {
                    state = NONE;
                    isRemark = false;
                    MyFefalash();
                }
                break;
        }
    }

    private void MyFefalash() {
        TextView tip = headre.findViewById(R.id.tip);
        ImageView arrow = headre.findViewById(R.id.arrow);
        ProgressBar progress = headre.findViewById(R.id.progress);
        RotateAnimation anim = new RotateAnimation(0, 180
                , RotateAnimation.RELATIVE_TO_SELF, 0.5f
                , RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(500);
        anim.setFillAfter(true);
        RotateAnimation anim1 = new RotateAnimation(180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f
                , RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        anim1.setDuration(500);
        anim1.setFillAfter(true);
        switch (state) {
            case NONE:
                arrow.clearAnimation();
                topPadding(-headerHeight);
                break;
            case PULL:
                arrow.setImageResource(R.drawable.jiantou);
                arrow.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                tip.setText("下拉刷新");
                arrow.clearAnimation();
                arrow.setAnimation(anim1);
                break;
            case RELASE:
                arrow.setImageResource(R.drawable.jiantou);
                arrow.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                tip.setText("即将刷新");
                arrow.clearAnimation();
                arrow.setAnimation(anim);
                break;
            case RELASEING:
                topPadding(40);
                arrow.setVisibility(View.GONE);
                progress.setVisibility(View.VISIBLE);
                tip.setText("正在刷新");
                arrow.clearAnimation();
                break;
            case FINISH:
                topPadding(40);
                arrow.setImageResource(R.drawable.shuxinwanche);
                arrow.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                tip.setText("刷新完成");
                arrow.clearAnimation();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        state = NONE;
                        MyFefalash();
                    }
                }, 1500);
                break;
        }
    }

    public void Conpty(){
        state = FINISH;
        isRemark = false;
        MyFefalash();
    }
}
