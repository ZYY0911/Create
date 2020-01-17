package com.example.create.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.bean.JLFS;
import com.example.create.bean.QYZP;

import java.util.List;

/**
 * Create by 张瀛煜 on 2020-01-17
 */
public class FSJLAdapter extends RecyclerView.Adapter<FSJLAdapter.MyHolder> {
    private List<JLFS> jlfs;
    private List<QYZP> zpxxes;
    private Context context;
    public FSJLAdapter(List<JLFS> jlfs,List<QYZP> zpxxes){
        this.jlfs =jlfs;
        this.zpxxes = zpxxes;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (context == null) context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.zpxx_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolhder, int i) {
        QYZP zpxx = zpxxes.get(i);
        JLFS jlfs1 = jlfs.get(i);
        myHolhder.tv_gsmc.setText("公司名称:"+zpxx.getQymc());
        myHolhder.tv_hylx.setText("行业类型:"+zpxx.getHylx());
        myHolhder.tv_gwmc.setText("岗位名称:"+zpxx.getGw());
        myHolhder.tv_zyyq.setText("专业要求:"+zpxx.getZyyq());
        myHolhder.tv_szcs.setText("所在城市:"+zpxx.getSzd());
        myHolhder.tv_xlyq.setText("学历要求:"+zpxx.getXlyq());
        myHolhder.tv_xzfw.setText("薪资范围:"+zpxx.getXz());
        myHolhder.tv_lxyx.setText("联系邮箱:"+zpxx.getEmail());
        myHolhder.tv_fbsj.setText("发布时间:"+zpxx.getTime());
        myHolhder.tv_scsj.setText("应聘时间"+jlfs1.getSendTime());
    }

    @Override
    public int getItemCount() {
        return jlfs.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        CardView card_view;
        TextView tv_gsmc, tv_hylx, tv_gwmc, tv_zyyq, tv_szcs, tv_xlyq, tv_xzfw, tv_lxyx, tv_fbsj,tv_scsj;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            card_view = itemView.findViewById(R.id.card_view);
            tv_gsmc = itemView.findViewById(R.id.tv_gsmc);
            tv_hylx = itemView.findViewById(R.id.tv_hylx);
            tv_gwmc = itemView.findViewById(R.id.tv_gwmc);
            tv_zyyq = itemView.findViewById(R.id.tv_zyyq);
            tv_szcs = itemView.findViewById(R.id.tv_szcs);
            tv_xlyq = itemView.findViewById(R.id.tv_xlyq);
            tv_xzfw = itemView.findViewById(R.id.tv_xzfw);
            tv_lxyx = itemView.findViewById(R.id.tv_lxyx);
            tv_fbsj = itemView.findViewById(R.id.tv_fbsj);
            tv_scsj = itemView.findViewById(R.id.tv_scsj);
        }
    }
}
