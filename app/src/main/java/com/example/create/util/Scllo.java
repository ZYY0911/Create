package com.example.create.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Create by 张瀛煜 on 2020-01-17
 */
public class Scllo extends HorizontalScrollView {


    public Scllo(Context context) {
        super(context);
    }

    public Scllo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Scllo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface SetData {
        void setdata(int x);
    }

    public SetData data;

    public void SetData(SetData data) {
        this.data = data;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        data.setdata(l);
    }
}

