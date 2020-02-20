package com.example.create.adapter5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.bean5.SCX;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-02-07 ：）
 */
public class SCX_Adapter extends ArrayAdapter<SCX> {
    private int layout;

    public SCX_Adapter(@NonNull Context context, int resource, @NonNull List<SCX> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SCX scx = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.scx_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemCj.setText(scx.getCj()+"号车间");
        holder.itemScx.setText(scx.getScx());
        holder.itemZt.setText(scx.getState());
        holder.itemTs.setText(scx.getTs());
        holder.itemSchj.setText(scx.getHj());
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.item_schj)
        TextView itemSchj;
        @BindView(R.id.item_zt)
        TextView itemZt;
        @BindView(R.id.item_ts)
        TextView itemTs;
        @BindView(R.id.item_cj)
        TextView itemCj;
        @BindView(R.id.item_scx)
        TextView itemScx;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
