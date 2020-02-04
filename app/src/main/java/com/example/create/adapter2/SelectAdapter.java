package com.example.create.adapter2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.create.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-01-20
 */
public class SelectAdapter extends ArrayAdapter<String> {
    private int layout;
    private int index;

    public void setIndex(int index) {
        this.index = index;
    }

    public SelectAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    public int getIndex() {
        return index;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.gyscxItem.setText(getItem(position));
        if (index == position) {
            holder.gyscxItem.setBackgroundResource(R.drawable.left_select);
        } else {
            holder.gyscxItem.setBackgroundResource(R.drawable.left_noselect);
        }
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.gyscx_item)
        TextView gyscxItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
