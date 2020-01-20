package com.example.create.adapter2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.create.R;
import com.example.create.bean2.GYS;
import com.example.create.bean2.GYSP;

import java.util.List;

/**
 * Create by 张瀛煜 on 2020-01-19
 */
public class GYSXXAdapter extends RecyclerView.Adapter<GYSXXAdapter.MyHolder> {
    private List<GYSP> gysps;
    private Context context;

    public GYSXXAdapter(List<GYSP> gysps) {
        this.gysps = gysps;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (context == null) context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.gysxx_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        GYSP gysp = gysps.get(i);
        Glide.with(context).load(gysp.getYlPhoto()).into(myHolder.spImage);
        myHolder.spName.setText("原料名称:" + gysp.getYlName());
        myHolder.spNum.setText("原料编号:" + gysp.getYlNum());
        myHolder.spPrice.setText("价钱:" + gysp.getYlPrice());
    }

    @Override
    public int getItemCount() {
        return gysps.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        ImageView spImage;
        TextView spName, spNum, spPrice;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            spImage = itemView.findViewById(R.id.sp_image);
            spName = itemView.findViewById(R.id.sp_name);
            spNum = itemView.findViewById(R.id.sp_num);
            spPrice = itemView.findViewById(R.id.sp_price);
        }
    }
}
