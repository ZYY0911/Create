package com.example.create.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.create.R;
import com.example.create.bean.TZ_SQL;
import com.example.create.util.ShowDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-01-18
 */
@SuppressLint("ValidFragment")
public class RequestDailog extends DialogFragment {
    @BindView(R.id.et_love)
    EditText etLove;
    Unbinder unbinder;
    @BindView(R.id.bt_send)
    Button btSend;
    private int id;

    public interface MyClick {
        void clcik();
    }

    private MyClick myClick;

    public void setMyClick(MyClick myClick) {
        this.myClick = myClick;
    }

    public RequestDailog(int id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.request_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(etLove.getText().toString())) {
                    ShowDialog.Show("请输入回复内容", getContext());
                } else {
                    TZ_SQL tz_sql = new TZ_SQL();
                    tz_sql.setIs(2);
                    tz_sql.setRequestInfo(etLove.getText().toString());
                    tz_sql.updateAll("id=?", id + "");
                    Toast.makeText(getContext(), "回复成功", Toast.LENGTH_SHORT).show();
                    myClick.clcik();
                    getDialog().dismiss();
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
