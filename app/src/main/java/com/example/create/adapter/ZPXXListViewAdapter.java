package com.example.create.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.create.R;

import java.util.List;

/**
 * Create by 张瀛煜 on 2020-01-16
 */
public class ZPXXListViewAdapter extends ArrayAdapter<String> {
    private int layout;

    public ZPXXListViewAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        layout = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(layout,parent,false);
        LinearLayout itemLayout = view.findViewById(R.id.item_layout);
        TextView itemName = view.findViewById(R.id.item_name);
        itemName.setText(getItem(position));
        itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
