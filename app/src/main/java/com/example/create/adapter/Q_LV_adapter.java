package com.example.create.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.bean.Q_YPRY_bean;

import java.util.List;

public class Q_LV_adapter extends ArrayAdapter<Q_YPRY_bean> {
    private List<Q_YPRY_bean> list;
    private List<Boolean> mCheckedList;

    private boolean is=false;
    public void setIs(boolean is) {
        this.is = is;
    }
    public boolean isIs() {
        return is;
    }

    public Q_LV_adapter(Context context, List<Q_YPRY_bean> list) {
        super(context, 0);
        this.list = list;
    }
    public  interface Click{
        void Click(int position,boolean is);
    }

    public void setClick(Click click) {
        this.click = click;
    }

    private Click click;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.lv_item, null);
        ImageView iv_tx = convertView.findViewById(R.id.iv_tx);
        TextView tv_xm = convertView.findViewById(R.id.tv_xm);
        TextView tv_xl = convertView.findViewById(R.id.tv_xl);
        TextView tv_csny = convertView.findViewById(R.id.tv_csny);
        TextView tv_gzjl = convertView.findViewById(R.id.tv_gzjl);
        final CheckBox cb_xz = convertView.findViewById(R.id.cb_xz);
        cb_xz.setChecked(is);
        cb_xz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb_xz.isChecked()) {
                    click.Click(position,true);
                }else {
                    click.Click(position,false);
                }
            }
        });
        Q_YPRY_bean bean=list.get(position);
        iv_tx.setBackgroundResource(bean.getPhoto());
        tv_xm.setText(bean.getXm());
        tv_xl.setText(bean.getXl());
        tv_csny.setText(bean.getDate());
        tv_gzjl.setText(bean.getJob());
        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Q_YPRY_bean getItem(int position) {
        return list.get(position);
    }
}
