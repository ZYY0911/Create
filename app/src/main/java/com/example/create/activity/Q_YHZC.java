package com.example.create.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.create.R;
import com.example.create.bean.Q_YHZC_SQL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Q_YHZC extends AppCompatActivity {

    @BindView(R.id.zc_yhm)
    EditText Yhmzc;
    @BindView(R.id.zc_mima)
    EditText Mimazc;
    @BindView(R.id.zc_zmima)
    EditText Zmimazc;
    @BindView(R.id.zc_yx)
    EditText Yxzc;
    @BindView(R.id.zc_qd)
    Button Qdzc;
    @BindView(R.id.zc_qx)
    Button Qxzc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q__yhzc);
        ButterKnife.bind(this);
    }

    private boolean charck(String s) {
        return s.matches("/^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,16}$/\n");
    }
    private  boolean yx(String s1){
        return s1.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
    }

    @OnClick({R.id.zc_qd, R.id.zc_qx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zc_qd:
                if (charck(Mimazc.getText().toString())) {
                    Toast.makeText(this, "密码格式错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!yx(Yxzc.getText().toString())){
                    Toast.makeText(this, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Mimazc.getText().toString().equals(Zmimazc.getText().toString())) {
                    Q_YHZC_SQL sql = new Q_YHZC_SQL(Yhmzc.getText().toString(), Mimazc.getText().toString(), 1000,Yxzc.getText().toString());
                    sql.save();
                } else {
                    Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case R.id.zc_qx:
                //TODO: add click handling
                Intent intent = new Intent(Q_YHZC.this, Q_YHDR.class);
                startActivity(intent);
                break;
        }
    }
}
