package com.example.create.activity2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.create.R;
import com.example.create.adapter2.GYSLBAdapter;
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
public class Z_GYSLBActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.myList)
    ListView myList;
    @BindView(R.id.title2)
    ImageView title2;
    @BindView(R.id.tv_none)
    TextView tvNone;
    private List<GYS> gys;
    private int index ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyslb_layout);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        gys = LitePal.findAll(GYS.class);
        if (gys.size() == 0) {
            myList.setVisibility(View.GONE);
            tvNone.setVisibility(View.VISIBLE);
        } else {
            myList.setVisibility(View.VISIBLE);
            tvNone.setVisibility(View.GONE);
            myList.setAdapter(new GYSLBAdapter(this, R.layout.gyslb_item, gys));
            this.registerForContextMenu(myList);
        }
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Z_GYSLBActivity.this, Z_GYSXXActivity.class);
                intent.putExtra("gs", gys.get(position).getGysNum());
                index = position;
                startActivity(intent);
            }
        });
    }

    private void initView() {
        title.setText("供应商--供应商列表");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, 1, Menu.NONE, "删除");
        menu.add(0, 2, Menu.NONE, "修改");
        menu.setGroupCheckable(0, true, true);
        Log.e("eee", "onCreateContextMenu: "+index);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Log.e("eee", "onContextItemSelected: "+menuInfo.position);
        switch (item.getItemId()) {
            case 1:
                final GYS gys1= gys.get(menuInfo.position);
                final AlertDialog.Builder builder  = new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("您确定要删除"+gys1.getGysName()+"供应商吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LitePal.deleteAll(GYS.class,"id=?",gys1.getId()+"");
                        LitePal.deleteAll(GYSP.class, "gysNum=?", gys1.getGysNum()+"");
                        initData();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
            case 2:
                Intent intent = new Intent(this,Z_TJGYSActivity.class);
                intent.putExtra("gs",gys.get(menuInfo.position).getId());
                startActivity(intent);
                break;
        }
        return true;
    }

    @OnClick({R.id.change, R.id.title2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.title2:
                startActivity(new Intent(this, Z_TJGYSActivity.class));
                break;
        }
    }
}
