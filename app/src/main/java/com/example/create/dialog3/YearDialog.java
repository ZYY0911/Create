package com.example.create.dialog3;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.create.R;
import com.example.create.util.SimpData;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-02-02
 */
public class YearDialog extends DialogFragment {
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_day_month)
    TextView tvDayMonth;
    @BindView(R.id.left_after)
    ImageView leftAfter;
    @BindView(R.id.calender_title)
    TextView calenderTitle;
    @BindView(R.id.rigth_next)
    ImageView rigthNext;
    @BindView(R.id.calendar_view)
    CalendarView calendarView;
    @BindView(R.id.exit)
    TextView exit;
    @BindView(R.id.queding)
    TextView queding;
    Unbinder unbinder;
    @BindView(R.id.layout)
    LinearLayout layout;
    private String end, start;
    private TimePickerView timePickerView;
    private TextView tv_year_dialog, tv_day_month_dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.year_dialog, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public interface getDATA {
        void getdata(String start, String end);
    }

    private getDATA data;

    public void setData(getDATA data) {
        this.data = data;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvYear.setText(SimpData.Simp("yyyy", new Date()));
        tvDayMonth.setText(SimpData.Simp("MM月dd日 E", new Date()));
        calenderTitle.setText(SimpData.Simp("yyyy年MM月", new Date()));
        initLisenter();
    }

    private void initLisenter() {
        calendarView.setOnCalendarRangeSelectListener(new CalendarView.OnCalendarRangeSelectListener() {
            @Override
            public void onCalendarSelectOutOfRange(Calendar calendar) {

            }

            @Override
            public void onSelectOutOfRange(Calendar calendar, boolean isOutOfMinRange) {

            }

            @Override
            public void onCalendarRangeSelect(Calendar calendar, boolean isEnd) {
                if (!isEnd) {
                    start = calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay();
                    end = "";
                } else {
                    end = calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.layout, R.id.left_after, R.id.rigth_next, R.id.exit, R.id.queding})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout:
                timePickerView = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        calendarView.scrollToCalendar(Integer.parseInt(SimpData.Simp("yyyy", date)), 1, 1, true);
                    }
                }).setLayoutRes(R.layout.timepicker, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        TextView exit_dialog = v.findViewById(R.id.exit_dialog);
                        TextView queding_dialog = v.findViewById(R.id.queding_dialog);
                        tv_day_month_dialog = v.findViewById(R.id.tv_day_month_dialog);
                        tv_year_dialog = v.findViewById(R.id.tv_year_dialog);
                        tv_year_dialog.setText(SimpData.Simp("yyyy", new Date()));
                        tv_day_month_dialog.setText(1 + "月1日 周二");
                        exit_dialog.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timePickerView.dismiss();
                            }
                        });
                        queding_dialog.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timePickerView.dismiss();
                                timePickerView.returnData();
                            }
                        });
                    }
                }).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        tv_year_dialog.setText(SimpData.Simp("yyyy年", date));
                        tv_day_month_dialog.setText(SimpData.Simp("MM月dd日 E", date));

                    }
                }).isDialog(true).setLabel("", "", "", "", "", "")
                        .setLineSpacingMultiplier(3f).setDividerColor(Color.TRANSPARENT).setTextColorCenter(Color.parseColor("#008577")).setType(new boolean[]{true, false, false, false, false, false}).build();
                timePickerView.getDialog().setCanceledOnTouchOutside(false);
                timePickerView.show();
                break;
            case R.id.left_after:
                calendarView.scrollToPre(true);
                break;
            case R.id.rigth_next:
                calendarView.scrollToNext(true);
                break;
            case R.id.exit:
                getDialog().dismiss();
                break;
            case R.id.queding:
                data.getdata(start, end);
                dismiss();
                break;
        }
    }
}
