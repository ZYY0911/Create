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
 * Create by 张瀛煜 on 2020-02-03
 */
public class KCXQAdapter extends ArrayAdapter<RK> {
    private int layout;

    public KCXQAdapter(@NonNull Context context, int resource, @NonNull List<RK> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        RK rk = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemJhsj.setText(rk.getTime());
        holder.itemGys.setText(rk.getGys());
        holder.itemSl.setText(rk.getNum() + "");
        holder.itemDj.setText(rk.getPrice()+"");
        holder.itemZe.setText((rk.getPrice() * rk.getNum()) + "元");
        holder.itemCgw.setText(rk.getShop());
        holder.itemBz.setText("无");
        return view;

    }

    static
    class ViewHolder {
        @BindView(R.id.item_jhsj)
        TextView itemJhsj;
        @BindView(R.id.item_gys)
        TextView itemGys;
        @BindView(R.id.item_sl)
        TextView itemSl;
        @BindView(R.id.item_dj)
        TextView itemDj;
        @BindView(R.id.item_bz)
        TextView itemBz;
        @BindView(R.id.item_ze)
        TextView itemZe;
        @BindView(R.id.item_cgw)
        TextView itemCgw;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

