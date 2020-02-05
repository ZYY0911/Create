package com.example.create.adapter4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.bean4.YGXX;
import com.example.create.bean4.YGXX2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-02-04 ：）
 */
public class YGXXAdapter extends ArrayAdapter<YGXX2> {
    private int layout;
    private boolean is = false;

    public void setIs(boolean is) {
        this.is = is;
    }

    public interface Click {
        void MyClick(int position, boolean xz);
    }

    private Click click;

    public void setClick(Click click) {
        this.click = click;
    }

    public YGXXAdapter(@NonNull Context context, int resource, @NonNull List<YGXX2> objects) {
        super(context, resource, objects);
        layout = resource;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        YGXX2 ygxx2 = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        String catlog = ygxx2.getPingying();
        if (position == getPositionForSection(catlog)) {
            holder.catalog.setText(catlog);
            holder.catalog.setVisibility(View.VISIBLE);
        } else {
            holder.catalog.setVisibility(View.GONE);
        }
        holder.ygName.setText(ygxx2.getName());
        holder.ygScx.setText(ygxx2.getScx());
        holder.ygSex.setText(ygxx2.getSex());
        holder.ygBirth.setText(ygxx2.getBirth());
        holder.ygTel.setText(ygxx2.getTel());
        holder.ygZw.setText(ygxx2.getZw());
        if (is) {
            holder.cbLayout.setVisibility(View.VISIBLE);
        } else {
            holder.cbLayout.setVisibility(View.GONE);
        }
        holder.cbBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.cbBox.isChecked()){
                    click.MyClick(position, true);
                }else {
                    click.MyClick(position, false);
                }
            }
        });
        if (ygxx2.isXz()){
            holder.cbBox.setChecked(true);
        }else {
            holder.cbBox.setChecked(false);
        }
        return view;
    }

    public int getPositionForSection(String catalog) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = getItem(i).getPingying();
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
        @BindView(R.id.yg_name)
        TextView ygName;
        @BindView(R.id.yg_sex)
        TextView ygSex;
        @BindView(R.id.yg_birth)
        TextView ygBirth;
        @BindView(R.id.yg_tel)
        TextView ygTel;
        @BindView(R.id.yg_scx)
        TextView ygScx;
        @BindView(R.id.yg_zw)
        TextView ygZw;
        @BindView(R.id.cb_layout)
        LinearLayout cbLayout;
        @BindView(R.id.cb_box)
        CheckBox cbBox;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
