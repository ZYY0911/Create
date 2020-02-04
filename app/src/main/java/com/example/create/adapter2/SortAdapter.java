package com.example.create.adapter2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.create.R;
import com.example.create.activity2.Z_LXGYSActivity;
import com.example.create.bean2.GYSCXBean2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-01-21
 */
public class SortAdapter extends ArrayAdapter<GYSCXBean2> {
    private int layout;
    private int lx;

    public SortAdapter(@NonNull Context context, int resource, @NonNull List<GYSCXBean2> objects,int lx) {
        super(context, resource, objects);
        layout = resource;
        this.lx  =lx;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final GYSCXBean2 gyscxBean2 = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.sort_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemText.setText(gyscxBean2.getName());
        Glide.with(getContext()).load(gyscxBean2.getImage()).into(holder.itemTitle);
        String catlog = gyscxBean2.getFirst();
        if (position==getPositionForSection(catlog)){
            holder.catalog.setText(catlog);
            holder.catalog.setVisibility(View.VISIBLE);
        }else {
            holder.catalog.setVisibility(View.GONE);
        }
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Z_LXGYSActivity.class);
                switch (lx) {
                    case 1:
                        intent.putExtra("lx", 5);
                        break;
                    case 2:
                        intent.putExtra("lx", 6);
                        break;
                }
                intent.putExtra("name", gyscxBean2.getName());
                getContext().startActivity(intent);
            }
        });
        return view;
    }


    /**
     * 获取catalog首次出现位置
     */
    public int getPositionForSection(String catalog) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = getItem(i).getFirst();
            if (catalog.equalsIgnoreCase(sortStr)) {
                return i;
            }
        }
        return -1;
    }

    static
    class ViewHolder {
        @BindView(R.id.catalog)
        TextView catalog;
        @BindView(R.id.item_title)
        ImageView itemTitle;
        @BindView(R.id.item_text)
        TextView itemText;
        @BindView(R.id.item_layout)
        LinearLayout itemLayout;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
