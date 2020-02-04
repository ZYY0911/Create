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

import com.example.create.R;
import com.example.create.bean2.GSCP;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-01-28
 */
public class GHCX_LeftAdapter1 extends ArrayAdapter<GSCP> {
    private int layout;

    public GHCX_LeftAdapter1(@NonNull Context context, int resource, @NonNull List<GSCP> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        GSCP gscp = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ghcx_item1, parent, false);
        ViewHolder holder = new ViewHolder(view);
       int image = 0;
        switch (gscp.getPhoto()) {
            case "apple":
                image = R.mipmap.apple;
                break;
            case "orange":
                image = R.mipmap.orange;
                break;
            case "banana":
                image = R.mipmap.banana;
                break;
            case "cherry":
                image = R.mipmap.cherry;
                break;
            case "mango":
                image = R.mipmap.mango;
                break;
            case "grape":
                image = R.mipmap.grape;
                break;
        }
      holder.imageItem.setImageResource(image);
        holder.textItem1.setText("名称:" + gscp.getName());
        holder.textItem2.setText("型号:" + gscp.getXh());
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.image_item)
        ImageView imageItem;
        @BindView(R.id.text_item1)
        TextView textItem1;
        @BindView(R.id.text_item2)
        TextView textItem2;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

