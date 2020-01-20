package com.example.create.adapter2;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.bean2.GYS;
import com.example.create.bean2.GYSCXBean;

import java.util.List;

/**
 * Create by 张瀛煜 on 2020-01-20
 */
public class GYSCXAdapter extends RecyclerView.Adapter<GYSCXAdapter.MyViewholder> {
    private List<GYSCXBean> gyscxBeans;

    public GYSCXAdapter(List<GYSCXBean> gyscxBeans) {
        this.gyscxBeans = gyscxBeans;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gyscx_item, viewGroup, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder myViewholder, int i) {
        GYSCXBean gyscxBean = gyscxBeans.get(i);
        myViewholder.itemText.setText(gyscxBean.getName());
        myViewholder.itemImage.setImageResource(gyscxBean.getImage());
    }

    @Override
    public int getItemCount() {
        return gyscxBeans.size();
    }

    static class MyViewholder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemText;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemText = itemView.findViewById(R.id.item_text);
        }
    }
}
