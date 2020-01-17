package com.example.create.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.bean.QYZP;
import com.example.create.util.Scllo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-01-17
 */
public class ZPXXListAdapter extends ArrayAdapter<QYZP> {
    private int layout;
    private List<Integer> list;
    private int index=999;
    public interface SetData {
        void setdata(int position, int lx, boolean sc);
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public SetData data;

    public void SetData(SetData data) {
        this.data = data;
    }
    public ZPXXListAdapter(@NonNull Context context, int resource, @NonNull List<QYZP> objects,List<Integer> list) {
        super(context, resource, objects);
        layout = resource;
        this.list = list;

    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        QYZP zpxx = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.zpxx_item2, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.tvGsmc.setText("公司名称:"+zpxx.getQymc());
        holder.tvHylx.setText("行业类型:"+zpxx.getHylx());
        holder.tvGwmc.setText("岗位名称:"+zpxx.getGw());
        holder.tvZyyq.setText("专业要求:"+zpxx.getZyyq());
        holder.tvSzcs.setText("所在城市:"+zpxx.getSzd());
        holder.tvXlyq.setText("学历要求:"+zpxx.getXlyq());
        holder.tvXzfw.setText("薪资范围:"+zpxx.getXz());
        holder.tvLxyx.setText("联系邮箱:"+zpxx.getEmail());
        holder.tvFbsj.setText("发布时间:"+zpxx.getTime());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(zpxx.getId())){
                holder.tvSc.setText("已收藏");
                Log.i("sss", "已收藏: "+i);
                break;
            }else {
                holder.tvSc.setText("收藏");
                Log.i("sss", "收藏: "+i);
            }
        }
        if (index==position){
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
                if (index!=position) {
                    if (x > 20) {
                        data.setdata(position, 1, true);
                    }
                }
            }
        });
        holder.tvSc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("收藏".equals(holder.tvSc.getText())) {
                    data.setdata(position, 2, false);
                    Log.i("ssss", "onClick: ");
                }
            }
        });
        holder.tvYp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(position,3,false);
            }
        });
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.tv_gsmc)
        TextView tvGsmc;
        @BindView(R.id.tv_hylx)
        TextView tvHylx;
        @BindView(R.id.tv_gwmc)
        TextView tvGwmc;
        @BindView(R.id.tv_zyyq)
        TextView tvZyyq;
        @BindView(R.id.tv_szcs)
        TextView tvSzcs;
        @BindView(R.id.tv_xlyq)
        TextView tvXlyq;
        @BindView(R.id.tv_xzfw)
        TextView tvXzfw;
        @BindView(R.id.tv_lxyx)
        TextView tvLxyx;
        @BindView(R.id.tv_fbsj)
        TextView tvFbsj;
        @BindView(R.id.tv_sc)
        TextView tvSc;
        @BindView(R.id.tv_yp)
        TextView tvYp;
        @BindView(R.id.scllo)
        Scllo scllo;
        @BindView(R.id.card_view)
        CardView cardView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
