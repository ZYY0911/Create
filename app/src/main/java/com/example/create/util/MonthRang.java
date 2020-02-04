package com.example.create.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.RangeMonthView;

/**
 * Create by 张瀛煜 on 2020-02-02
 */
public class MonthRang extends RangeMonthView {
    private int mRadius;

    public MonthRang(Context context) {
        super(context);
    }

    @Override
    protected void onPreviewHook() {
        super.onPreviewHook();
        mRadius = Math.min(mItemHeight, mItemWidth) / 5 * 2;
        mSchemePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelectedPre, boolean isSelectedNext) {
        int cx = x + mItemWidth / 2;
        int cy = y + mItemHeight / 2;
        if (isSelectedPre) {
            if (isSelectedNext) {
                canvas.drawRect(x, cy - mRadius, x + mItemWidth, cy + mRadius, mSelectedPaint);
            } else {
                canvas.drawRect(x, cy - mRadius, cx, cy + mRadius, mSelectedPaint);
                canvas.drawCircle(cx, cy, mRadius, mSelectedPaint);
            }
        } else {
            if (isSelectedNext) {
                canvas.drawRect(cx, cy - mRadius, x + mItemWidth, cy + mRadius, mSelectedPaint);
            }
            canvas.drawCircle(cx, cy, mRadius, mSelectedPaint);
        }
        return true;
    }

    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y, boolean isSelected) {

    }

    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        float bY = mTextBaseLine + y;
        int cx = x + mItemWidth / 2;
        if (isSelected) {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, bY, mSelectTextPaint);
        } else {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, bY, calendar.isCurrentDay() ? mCurDayTextPaint :
                    calendar.isCurrentMonth() ? mCurMonthTextPaint : mOtherMonthTextPaint);
        }
    }
}
