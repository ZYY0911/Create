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
import android.widget.TextView;

import com.example.create.R;
import com.example.create.bean3.CK_CG;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-02-03
 */
public class YLGLCKAdapter extends ArrayAdapter<CK_CG> {
    private int layout;
    private boolean is = true;

    public boolean isIs() {
        return is;
    }

    public void setIs(boolean is) {
        this.is = is;
    }

    public interface InputNum {
        void inputNum(int position, String num, EditText editText);
    }

    private InputNum inputNum;

    public void setInputNum(InputNum inputNum) {
        this.inputNum = inputNum;
    }

    public YLGLCKAdapter(@NonNull Context context, int resource, @NonNull List<CK_CG> objects, boolean is) {
        super(context, resource, objects);
        layout = resource;
        this.is = is;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CK_CG ck_cg = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.tvItemCgl.setText("采购量：" + ck_cg.getCgl());
        holder.tvItemGhs.setText("供货商：" + ck_cg.getGys());
        holder.tvItemYl.setText("余量：" + ck_cg.getYl());
        if (is) {
            holder.etInput.setVisibility(View.GONE);
        } else {
            holder.etInput.setVisibility(View.VISIBLE);
        }
        holder.etInput.setText(ck_cg.getInput()+"");
        holder.etInput.setSelection((ck_cg.getInput()+"").length());
        holder.etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    inputNum.inputNum(position, s.toString().trim(), holder.etInput);
                }
            }
        });
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.tv_item_ghs)
        TextView tvItemGhs;
        @BindView(R.id.tv_item_cgl)
        TextView tvItemCgl;
        @BindView(R.id.tv_item_yl)
        TextView tvItemYl;
        @BindView(R.id.et_input)
        EditText etInput;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
