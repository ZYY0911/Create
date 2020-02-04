package com.example.create.adapter3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.bean3.KCL;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-02-03 ：）
 */
public class YZSZAdapter2 extends ArrayAdapter<KCL> {
    private int layout;

    public interface MyClick {
        void myClick(int position, int lx, int num);
    }

    private MyClick click;

    public void setClick(MyClick click) {
        this.click = click;
    }

    public YZSZAdapter2(@NonNull Context context, int resource, @NonNull List<KCL> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        KCL kcl = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        int image = 0;
        switch (kcl.getYlm()) {
            case "苹果":
                image = R.mipmap.apple;
                break;
            case "葡萄":
                image = R.mipmap.grape;
                break;
            case "香蕉":
                image = R.mipmap.banana;
                break;
            case "樱桃":
                image = R.mipmap.cherry;
                break;
            case "芒果":
                image = R.mipmap.mango;
                break;
            case "橙子":
                image = R.mipmap.orange;
                break;
        }
        holder.ylImage.setImageResource(image);
        holder.ylName.setText(kcl.getYlm());
        holder.itemNum.setText(kcl.getKc());
        holder.itemNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!"".equals(s.toString())) {
                    click.myClick(position, 3, Integer.parseInt(s.toString()));
                }
            }
        });
        holder.itemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.myClick(position, 1, 1);
            }
        });
        holder.itemRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.myClick(position, 2, 1);
            }
        });
        return view;

    }

    static
    class ViewHolder {
        @BindView(R.id.yl_name)
        TextView ylName;
        @BindView(R.id.yl_image)
        ImageView ylImage;
        @BindView(R.id.item_add)
        ImageView itemAdd;
        @BindView(R.id.item_num)
        EditText itemNum;
        @BindView(R.id.item_remove)
        ImageView itemRemove;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
