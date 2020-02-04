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
public class YLAdapter extends ArrayAdapter<RK> {
    private int layout;

    public YLAdapter(@NonNull Context context, int resource, @NonNull List<RK> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        RK rk = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.ylmcItem.setText(rk.getYlmc());
        holder.xhItem.setText(rk.getXh());
        holder.kclItem.setText(rk.getNum()+"");
        holder.wzItem.setText(rk.getLocation());
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.ylmc_item)
        TextView ylmcItem;
        @BindView(R.id.xh_item)
        TextView xhItem;
        @BindView(R.id.kcl_item)
        TextView kclItem;
        @BindView(R.id.wz_item)
        TextView wzItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
