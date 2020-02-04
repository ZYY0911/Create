package com.example.create.adapter2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.activity2.Z_GHFPActivity;
import com.example.create.bean2.JYSJ;
import com.example.create.bean3.CK;
import com.example.create.bean3.RK;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-01-29
 */
public class GHCF_LeftAdapter2 extends ArrayAdapter<RK> {
    private int layout;

    public GHCF_LeftAdapter2(@NonNull Context context, int resource, @NonNull List<RK> objects) {
        super(context, resource, objects);
        layout = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final RK jysj = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ghcx_item2, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.textJysj.setText(jysj.getTime());
        holder.textDj.setText("单价:" + jysj.getPrice() + "个/元");
        holder.textSl.setText("数量:" + jysj.getNum() + "个");
        holder.textZje.setText("总金额:" + (jysj.getNum()*jysj.getPrice()) + "元");
        holder.textDfzh.setText("对方账号:" + jysj.getCountId());
        holder.textCgy.setText("采购员:" + jysj.getShop());
        holder.textLxr.setText("联系人:" + jysj.getContacts());
        holder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Z_GHFPActivity.class);
                intent.putExtra("image", jysj.getFp());
                getContext().startActivity(intent);
            }
        });
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.item_card)
        CardView itemCard;
        @BindView(R.id.text_jysj)
        TextView textJysj;
        @BindView(R.id.text_dj)
        TextView textDj;
        @BindView(R.id.text_sl)
        TextView textSl;
        @BindView(R.id.text_zje)
        TextView textZje;
        @BindView(R.id.text_dfzh)
        TextView textDfzh;
        @BindView(R.id.text_cgy)
        TextView textCgy;
        @BindView(R.id.text_lxr)
        TextView textLxr;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
