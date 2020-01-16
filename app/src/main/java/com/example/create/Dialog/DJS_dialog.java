package com.example.create.Dialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.create.R;

import java.util.Timer;
import java.util.TimerTask;

public class DJS_dialog extends DialogFragment {
    private int recLen = 30;
    private TextView tv_djs;
    Timer timer = new Timer();
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.djs_dialog, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_djs = view.findViewById(R.id.tv_djs);
        timer.schedule(task, 1000, 1000);
    }
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    tv_djs.setText("" + recLen);
                    if (recLen < 0) {
                        timer.cancel();
                        tv_djs.setVisibility(View.GONE);
                    }
            }
            return false;
        }
    });
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            recLen--;
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };
}
