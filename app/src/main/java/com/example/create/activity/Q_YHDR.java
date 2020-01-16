package com.example.create.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.create.Dialog.DJS_dialog;
import com.example.create.R;
import com.example.create.bean.Q_YHZC_SQL;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Q_YHDR extends AppCompatActivity {

    @BindView(R.id.tv_yhzc)
    TextView Yhzctv;
    @BindView(R.id.tv_zhma)
    TextView Zhmatv;
    @BindView(R.id.et_ymh)
    EditText Ymhet;
    @BindView(R.id.et_mima)
    EditText Mimaet;
    @BindView(R.id.cb_1)
    CheckBox cb1;
    @BindView(R.id.cb_2)
    CheckBox cb2;
    @BindView(R.id.bt_qd)
    Button Qdbt;
    @BindView(R.id.bt_qx)
    Button Qxbt;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private DJS_dialog djs_dialog;
    private int x = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q__yhdr);
        ButterKnife.bind(this);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            Ymhet.setText(account);
            Mimaet.setText(password);
            cb1.setChecked(true);
        }

    }


    private void setRun() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    try {
                        Thread.sleep(30000);
                        djs_dialog.dismiss();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (true);
            }
        }).start();
    }

    private void setData() {
        List<Q_YHZC_SQL> list = LitePal.findAll(Q_YHZC_SQL.class);
        for (Q_YHZC_SQL sql : list) {
            String account = Ymhet.getText().toString();
            String password = Mimaet.getText().toString();
            if (account.equals(sql.getYhm()) && password.equals(sql.getMima())) {
                editor = pref.edit();
                if (cb1.isChecked()) {
                    editor.putBoolean("remember_password", true);
                    editor.putString("account", account);
                    editor.putString("password", password);
                } else {
                    editor.clear();
                }
                editor.apply();
                Toast.makeText(this, "登入成功", Toast.LENGTH_SHORT).show();
                return;
            } else if (!account.equals(sql.getYhm()) && password.equals(sql.getMima())) {
                x=x-1;
                if (x == 0) {
                    djs_dialog = new DJS_dialog();
                    djs_dialog.setCancelable(false);
                    djs_dialog.show(getSupportFragmentManager(), "");
                    setRun();
                }
            }
        }


    }

    @OnClick({R.id.bt_qd, R.id.bt_qx, R.id.tv_yhzc, R.id.tv_zhma})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_qd:
                setData();
                break;
            case R.id.bt_qx:
                break;
            case R.id.tv_yhzc:
                Intent intent = new Intent(Q_YHDR.this, Q_YHZC.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_zhma:
                Intent intent1 = new Intent(Q_YHDR.this, Q_ZHMM.class);
                startActivity(intent1);
                finish();
                break;
        }
    }
}
