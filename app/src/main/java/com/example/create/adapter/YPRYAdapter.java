package com.example.create.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.activity.Q_YPRY;
import com.example.create.bean.QYZP;

import java.util.List;

/**
 * Create by 张瀛煜 on 2020-01-18
 */
public class YPRYAdapter extends RecyclerView.Adapter<YPRYAdapter.MyHolder> {
    private List<QYZP> qyzps;
    private List<Integer> integers;
    private Context context;

    public YPRYAdapter(List<QYZP> qyzps, List<Integer> integers) {
        this.qyzps = qyzps;
        this.integers = integers;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        if (context == null) context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.yprylb_item, viewGroup, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        final QYZP qyzp = qyzps.get(i);
        myHolder.item_num.setText("编号:" + qyzp.getNum());
        myHolder.item_gw.setText("岗位:" + qyzp.getGw());
        myHolder.item_name.setText("公司名:" + qyzp.getQymc());
        myHolder.item_rs.setText("应聘人数:" + integers.get(i));
        myHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Q_YPRY.class);
                intent.putExtra("name", qyzp.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return qyzps.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView item_num, item_name, item_gw, item_rs;
        CardView cardView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            item_num = itemView.findViewById(R.id.item_num);
            item_name = itemView.findViewById(R.id.item_name);
            item_gw = itemView.findViewById(R.id.item_gw);
            item_rs = itemView.findViewById(R.id.item_rs);
            cardView = itemView.findViewById(R.id.car_view);
        }
    }
}
