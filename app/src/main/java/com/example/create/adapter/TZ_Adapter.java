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
import com.example.create.bean.TZ_SQL;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-01-18
 */
public class TZ_Adapter extends ArrayAdapter<TZ_SQL> {
    private int layout;

    public interface MyClick {
        void clcik(int psoiton);
    }

    private MyClick myClick;

    public void setMyClick(MyClick myClick) {
        this.myClick = myClick;
    }

    public TZ_Adapter(@NonNull Context context, int resource, @NonNull List<TZ_SQL> objects) {
        super(context, resource, objects);
        layout = resource;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TZ_SQL tz_sql = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemNr.setText("通知内容：" + tz_sql.getNeirong());
        holder.itemSj.setText("通知时间：" + tz_sql.getTime());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClick.clcik(position);
            }
        });
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.item_nr)
        TextView itemNr;
        @BindView(R.id.item_sj)
        TextView itemSj;
        @BindView(R.id.button)
        Button button;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
