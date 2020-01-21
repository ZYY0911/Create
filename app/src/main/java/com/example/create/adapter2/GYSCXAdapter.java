package com.example.create.adapter2;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.activity2.Z_LXGYSActivity;
import com.example.create.bean2.GYS;
import com.example.create.bean2.GYSCXBean;

import java.util.List;

/**
 * Create by 张瀛煜 on 2020-01-20
 */
public class GYSCXAdapter extends RecyclerView.Adapter<GYSCXAdapter.MyViewholder> {
    private List<GYSCXBean> gyscxBeans;
    private int lx;
    private Context context;

    public GYSCXAdapter(List<GYSCXBean> gyscxBeans, int lx) {
        this.gyscxBeans = gyscxBeans;
        this.lx = lx;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (context == null) context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.gyscx_item, viewGroup, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder myViewholder, int i) {
        final GYSCXBean gyscxBean = gyscxBeans.get(i);
        myViewholder.itemText.setText(gyscxBean.getName());
        myViewholder.itemImage.setImageResource(gyscxBean.getImage());
        myViewholder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Z_LXGYSActivity.class);
                switch (lx) {
                    case 1:
                        intent.putExtra("lx", 1);
                        break;
                    case 2:
                        intent.putExtra("lx", 2);
                        break;
                    case 3:
                        intent.putExtra("lx",3);
                        break;
                }
                intent.putExtra("name", gyscxBean.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gyscxBeans.size();
    }

    static class MyViewholder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemText;
        CardView cardView;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemText = itemView.findViewById(R.id.item_text);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
