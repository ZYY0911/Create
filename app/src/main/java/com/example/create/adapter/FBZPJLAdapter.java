package com.example.create.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.bean.QYZP;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-01-16
 */
public class FBZPJLAdapter extends ArrayAdapter<QYZP> {
    private int layout;

    public FBZPJLAdapter(@NonNull Context context, int resource, @NonNull List<QYZP> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    public interface Click {
        void Click(int position, boolean is);
    }

    private Click click;

    public void setClick(Click click) {
        this.click = click;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        QYZP qyzp = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemNum.setText(qyzp.getNum());
        holder.itemName.setText(qyzp.getQymc());
        holder.itemAddress.setText(qyzp.getGw());
        holder.itemTime.setText(qyzp.getUser());
        holder.itemTime1.setText(qyzp.getShtime());
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.item_num)
        TextView itemNum;
        @BindView(R.id.item_name)
        TextView itemName;
        @BindView(R.id.item_address)
        TextView itemAddress;
        @BindView(R.id.item_time)
        TextView itemTime;
        @BindView(R.id.item_time1)
        TextView itemTime1;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
