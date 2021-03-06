package com.example.create.adapter3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.bean3.RK;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-02-02
 */
public class CKCXAdapter extends ArrayAdapter<RK> {
    private int layout;
    private List<RK> rks;

    public CKCXAdapter(@NonNull Context context, int resource, @NonNull List<RK> objects) {
        super(context, resource, objects);
        layout = resource;
        rks = objects;
    }

    @Override
    public int getCount() {
        return rks.size()+1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        if (position == 0) {

        } else {
            RK rk = getItem(position - 1);
            holder.itemGys.setText(rk.getGys());
            holder.itemRkr.setText(rk.getKrname());
            holder.itemSj.setText(rk.getTime());
            holder.itemSl.setText(rk.getNum() + "");
            holder.itemXh.setText(rk.getXh());
            holder.itemYl.setText(rk.getYlmc());
        }
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.item_yl)
        TextView itemYl;
        @BindView(R.id.item_xh)
        TextView itemXh;
        @BindView(R.id.item_sj)
        TextView itemSj;
        @BindView(R.id.item_sl)
        TextView itemSl;
        @BindView(R.id.item_gys)
        TextView itemGys;
        @BindView(R.id.item_rkr)
        TextView itemRkr;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
