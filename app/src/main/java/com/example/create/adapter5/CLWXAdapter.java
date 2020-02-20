package com.example.create.adapter5;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.bean5.WXCLBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-02-06 ：）
 */
public class CLWXAdapter extends ArrayAdapter<WXCLBean> {
    private int layout;
    private boolean is = true;

    public boolean isIs() {
        return is;
    }

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

    public CLWXAdapter(@NonNull Context context, int resource, @NonNull List<WXCLBean> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        WXCLBean wxclBean = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.clwx_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        if (is) {
            holder.itemLayout.setVisibility(View.VISIBLE);
        } else {
            holder.itemLayout.setVisibility(View.GONE);
        }
        holder.itemClbh.setText(wxclBean.getClnum());
        holder.itemClxh.setText(wxclBean.getClxh());
        holder.itemBxsj.setText(wxclBean.getTime());
        String time = wxclBean.getWxtime();
        if ("".equals(time)) {
            holder.itemWxsj.setText("暂无");
        }else {
            holder.itemWxsj.setText(wxclBean.getWxtime());
        }
        holder.itemWt.setText(wxclBean.getQuestion());
        holder.itemCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.itemCb.isChecked()) {
                    click.MyClick(position, true);
                } else {
                    click.MyClick(position, false);
                }
            }
        });
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.item_clbh)
        TextView itemClbh;
        @BindView(R.id.item_clxh)
        TextView itemClxh;
        @BindView(R.id.item_wt)
        TextView itemWt;
        @BindView(R.id.item_bxsj)
        TextView itemBxsj;
        @BindView(R.id.item_wxsj)
        TextView itemWxsj;
        @BindView(R.id.item_cb)
        CheckBox itemCb;
        @BindView(R.id.item_layout)
        LinearLayout itemLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

