package com.example.create.activity2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.create.R;
import com.example.create.adapter2.GYSXXAdapter;
import com.example.create.bean2.GYS;
import com.example.create.bean2.GYSP;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-19
 */
public class Z_GYSXXActivity extends AppCompatActivity {

    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.image_photo)
    ImageView imagePhoto;
    @BindView(R.id.et_gys_name)
    EditText etGysName;
    @BindView(R.id.et_gys_num)
    EditText etGysNum;
    @BindView(R.id.et_gys_city)
    EditText etGysCity;
    @BindView(R.id.et_gys_location)
    EditText etGysLocation;
    @BindView(R.id.et_gys_law)
    EditText etGysLaw;
    @BindView(R.id.et_gys_people)
    EditText etGysPeople;
    @BindView(R.id.et_gys_tel)
    EditText etGysTel;
    @BindView(R.id.et_gys_range)
    EditText etGysRange;
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.tv_none)
    TextView tvNone;
    @BindView(R.id.layout)
    LinearLayout layout;
    private int gsId;
    private List<GYSP> gysps;
    private GridLayoutManager gridLayoutManager;
    private GYS gys;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gysxx_layout);
        ButterKnife.bind(this);
        initView();
        title.setText("供应商--供应商详情");
    }

    private void initView() {
        gsId = getIntent().getIntExtra("gs", 1);
        gys = LitePal.where("gysNum=?", gsId + "").find(GYS.class).get(0);
        etGysName.setText(gys.getGysName());
        etGysCity.setText(gys.getGysCity());
        etGysLaw.setText(gys.getGysLaw());
        etGysLocation.setText(gys.getGysLocation());
        etGysPeople.setText(gys.getGysPeople());
        etGysRange.setText(gys.getGysRange());
        etGysTel.setText(gys.getGysTel());
        etGysNum.setText(gys.getGysNum() + "");
        Glide.with(this).load(gys.getGysPhoto()).into(imagePhoto);
        gysps = LitePal.where("gysNum=?", gsId + "").find(GYSP.class);
        if (gysps.size() == 0) {
            layout.setVisibility(View.GONE);
            tvNone.setVisibility(View.VISIBLE);
        } else {
            layout.setVisibility(View.VISIBLE);
            tvNone.setVisibility(View.GONE);
            gridLayoutManager = new GridLayoutManager(this, 3);
            recycleView.setLayoutManager(gridLayoutManager);
            recycleView.setAdapter(new GYSXXAdapter(gysps));
        }
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
