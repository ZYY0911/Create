package com.example.create.dialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.create.R;

public class YPXX_Dialog extends DialogFragment {
    private View view;
    private CheckBox ck_tz, ck_dx, ck_dh;
    private Button bt_qd, bt_qx;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.ypxx_item, null);
        ck_tz = view.findViewById(R.id.ck_tz);
        ck_dx = view.findViewById(R.id.ck_dx);
        ck_dh = view.findViewById(R.id.ck_dh);
        bt_qd = view.findViewById(R.id.bt_qd);
        bt_qx = view.findViewById(R.id.bt_qx);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bt_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ck_tz.isChecked()){

                }
                if (ck_dx.isChecked()){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.putExtra("address", "186...");
                    intent.putExtra("sms_body", "短信内容");
                    intent.setType("vnd.android-dir/mms-sms");
                    startActivity(intent);
                }
            }
        });
        bt_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }



}
