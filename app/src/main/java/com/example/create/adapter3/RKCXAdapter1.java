package com.example.create.adapter3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.bean3.RK;
import com.example.create.util.Scllo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-02-02
 */
public class RKCXAdapter1 extends ArrayAdapter<RK> {
    private int layout;
    private int index = 999;

    public RKCXAdapter1(@NonNull Context context, int resource, @NonNull List<RK> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    public interface SetData {
        void setdata(int position, int lx, boolean sc);
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public SetData data;

    public void setData(SetData data) {
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        RK rk = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemGys.setText(rk.getGys());
        holder.itemRkr.setText(rk.getKrname());
        holder.itemSj.setText(rk.getTime());
        holder.itemSl.setText(rk.getNum() + "");
        holder.itemXh.setText(rk.getXh());
        holder.itemYl.setText(rk.getYlmc());
        holder.btOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(position, 2, false);
            }
        });
        if (index == position) {
            holder.scllo.post(new Runnable() {
                @Override
                public void run() {
                    holder.scllo.fullScroll(View.FOCUS_RIGHT);
                }
            });
        }
        holder.scllo.SetData(new Scllo.SetData() {
            @Override
            public void setdata(int x) {
                if (index != position) {
                    if (x > 20) {
                        data.setdata(position, 1, true);
                    }
                }
            }
        });
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
        @BindView(R.id.bt_out)
        Button btOut;
        @BindView(R.id.scllo)
        Scllo scllo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
