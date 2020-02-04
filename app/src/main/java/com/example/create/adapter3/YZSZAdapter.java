package com.example.create.adapter3;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.bean3.KCL;
import com.example.create.bean3.YZ;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-02-03 ：）
 */
public class YZSZAdapter extends ArrayAdapter<KCL> {
    private int layout;
    private YZ yzs;
    private int yz, max;

    public YZSZAdapter(@NonNull Context context, int resource, @NonNull List<KCL> objects, YZ yzs) {
        super(context, resource, objects);
        layout = resource;
        this.yzs = yzs;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        KCL kcl = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        max = Integer.parseInt(kcl.getKc());
        holder.itemKc.setText(kcl.getKc());
        holder.itemXh.setText(kcl.getXh());
        holder.itemYlmc.setText(kcl.getYlm());
        switch (kcl.getYlm()) {
            case "苹果":
                yz = yzs.getApple();
                break;
            case "葡萄":
                yz = yzs.getGrape();
                break;
            case "香蕉":
                yz = yzs.getBanban();
                break;
            case "樱桃":
                yz = yzs.getCherry();
                break;
            case "芒果":
                yz = yzs.getMango();
                break;
            case "橙子":
                yz = yzs.getOrange();
                break;
        }
        if (max > yz) {
            holder.itemKc.setTextColor(Color.BLACK);
        }else {
            holder.itemKc.setTextColor(Color.RED);
            sendNot(kcl.getYlm(),yz,max);
        }
        return view;
    }

    private void sendNot(String ylm, int yz, int max) {
        int image = 0 ;
        int index = 0;
        switch (ylm) {
            case "苹果":
                index  =1;
                image = R.mipmap.apple;
                break;
            case "葡萄":
                index  =2;
                image = R.mipmap.grape;
                break;
            case "香蕉":
                index  =3;
                image = R.mipmap.banana;
                break;
            case "樱桃":
                index  =4;
                image = R.mipmap.cherry;
                break;
            case "芒果":
                index  =5;
                image = R.mipmap.mango;
                break;
            case "橙子":
                index  =6;
                image = R.mipmap.orange;
                break;
        }
        Notification notification = new NotificationCompat.Builder(getContext())
                .setContentText(ylm+"原料，库存预警值："+yz+"，当前库存："+max)
                .setContentTitle("原料阈值报警")
                .setSmallIcon(image)
                .setWhen(System.currentTimeMillis())
                .build();
        NotificationManager manager = (NotificationManager)getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(index,notification);
    }

    static
    class ViewHolder {
        @BindView(R.id.item_ylmc)
        TextView itemYlmc;
        @BindView(R.id.item_xh)
        TextView itemXh;
        @BindView(R.id.item_kc)
        TextView itemKc;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
