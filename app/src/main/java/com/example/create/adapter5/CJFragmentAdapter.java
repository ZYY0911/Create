package com.example.create.adapter5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.bean5.SCX;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-02-06 ：）
 */
public class CJFragmentAdapter extends ArrayAdapter<SCX> {
    private int layout;

    public CJFragmentAdapter(@NonNull Context context, int resource, @NonNull List<SCX> objects) {
        super(context, resource, objects);
        layout = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SCX scx = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.scx_fragment_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        int res = 0;
        switch (scx.getState()) {
            case "库存已满":
                res = R.drawable.red_shape;
                break;
            case "建设中":
                res = R.drawable.lan_shape;
                break;
            case "生产中":
                res = R.drawable.green_shape;
                break;
            case "缺原材料":
                res = R.drawable.yello_shape;
                break;
        }
        holder.itemFragment.setText(scx.getScx());
        holder.itemFragment.setBackgroundResource(res);
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.item_fragment)
        TextView itemFragment;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
