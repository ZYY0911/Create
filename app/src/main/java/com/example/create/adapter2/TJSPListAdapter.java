package com.example.create.adapter2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.create.R;
import com.example.create.bean2.GYSP;
import com.example.create.util.Scllo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-01-19
 */
public class TJSPListAdapter extends ArrayAdapter<GYSP> {
    private int layout, index = 999;


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

    public TJSPListAdapter(@NonNull Context context, int resource, @NonNull List<GYSP> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        GYSP gysp = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        Glide.with(getContext()).load(gysp.getYlPhoto()).into(holder.spImage);
        holder.spName.setText("原料名称:" + gysp.getYlName());
        holder.spNum.setText("原料编号:" + gysp.getYlNum());
        holder.spPrice.setText("价钱:" + gysp.getYlPrice());
            holder.scoll.SetData(new Scllo.SetData() {
                @Override
                public void setdata(int x) {
                    if (index != position) {
                        if (x > 20) {
                            data.setdata(position, 1, true);
                        }
                    }
                }
            });
            if (index == position) {
                holder.scoll.post(new Runnable() {
                    @Override
                    public void run() {
                        holder.scoll.fullScroll(View.FOCUS_RIGHT);
                    }
                });
            }
            holder.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.setdata(position, 2, false);

                }
            });
            holder.tvUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.setdata(position, 3, false);
                }
            });
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.sp_image)
        ImageView spImage;
        @BindView(R.id.sp_name)
        TextView spName;
        @BindView(R.id.sp_num)
        TextView spNum;
        @BindView(R.id.sp_price)
        TextView spPrice;
        @BindView(R.id.tv_delete)
        TextView tvDelete;
        @BindView(R.id.tv_update)
        TextView tvUpdate;
        @BindView(R.id.scoll)
        Scllo scoll;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
