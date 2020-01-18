package com.example.create.dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
     //   getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bt_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ck_tz.isChecked()) {
                    Log.i("111111111111", "onClick: ");
                    TZ_Dialog tz_dialog=new TZ_Dialog();
                    tz_dialog.show(getFragmentManager(),"");
                }
                if (ck_dx.isChecked()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.putExtra("address", "186...");
                    intent.putExtra("sms_body", "短信内容");
                    intent.setType("vnd.android-dir/mms-sms");
                    startActivity(intent);
                }
                if (ck_dx.isChecked()&&ck_tz.isChecked()){
                    ck_dh.setChecked(false);
                }else {
                    if (ck_dh.isChecked()){
                        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + "4000788400"));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
                dismiss();
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
