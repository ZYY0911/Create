package com.example.create.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.bean.JLLB;

import java.util.List;

/**
 * Create by 张瀛煜 on 2020-01-15
 */
public class JLLBAdapter2 extends ArrayAdapter<JLLB> {
    private int layout;

    public JLLBAdapter2(@NonNull Context context, int resource, @NonNull List<JLLB> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    public interface MyClick {
        void Click(int position);
    }

    private MyClick myClick;

    public void setMyClick(MyClick myClick) {
        this.myClick = myClick;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        JLLB jllb = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        TextView jlName = view.findViewById(R.id.jl_name);
        TextView jlInfo = view.findViewById(R.id.jl_info);
        TextView jlTime = view.findViewById(R.id.jl_time);
        Button btSend = view.findViewById(R.id.bt_send);
        jlInfo.setText(jllb.getJlInfo());
        jlName.setText(jllb.getJlName());
        jlTime.setText(jllb.getJlTime());
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClick.Click(position);
            }
        });
        return view;
    }
}
