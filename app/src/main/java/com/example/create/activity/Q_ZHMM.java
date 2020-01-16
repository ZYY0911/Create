package com.example.create.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.create.R;
import com.example.create.bean.Q_YHZC_SQL;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Q_ZHMM extends AppCompatActivity {

    @BindView(R.id.zh_yhm)
    EditText Yhmzh;
    @BindView(R.id.zh_xx)
    EditText Xxzh;
    @BindView(R.id.bt_qd)
    Button Qdbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q__zhmm);
        ButterKnife.bind(this);
        setOnclick();
    }

    private void setOnclick() {
    Qdbt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setSQL();

        }
    });

    }

    private void setSQL() {
        List<Q_YHZC_SQL>list= LitePal.findAll(Q_YHZC_SQL.class);
        for (Q_YHZC_SQL sql:list){
            if (Yhmzh.getText().toString().equals(sql.getYhm())&&Xxzh.getText().toString().equals(sql.getMima())){
                Toast.makeText(this, sql.getMima(), Toast.LENGTH_SHORT).show();
                return;
            }
        }

    }

}
