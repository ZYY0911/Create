package com.example.create.util;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Create by 张瀛煜 on 2020-01-15
 */
public class ShowDialog {
    public static void Show(String msg, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(msg);
        builder.setPositiveButton("确定", null);
        builder.show();
    }
}
