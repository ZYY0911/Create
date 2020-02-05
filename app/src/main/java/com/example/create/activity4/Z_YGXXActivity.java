package com.example.create.activity4;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.create.R;
import com.example.create.adapter4.YGXXAdapter;
import com.example.create.bean4.YGXX;
import com.example.create.bean4.YGXX2;
import com.example.create.util.ExcelUtil;
import com.example.create.util.PasePing;
import com.example.create.util.SideBar;
import com.example.create.util.SimpData;

import org.litepal.LitePal;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jxl.Sheet;
import jxl.Workbook;

/**
 * Create by 张瀛煜 on 2020-02-04 ：）
 */
public class Z_YGXXActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.side_bar)
    SideBar sideBar;
    @BindView(R.id.float_button)
    FloatingActionButton floatButton;
    @BindView(R.id.listView2)
    ListView listView2;
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.image_find)
    ImageView imageFind;
    @BindView(R.id.bottom_nva)
    BottomNavigationView bottomNva;
    private List<YGXX> parkInfo;
    private int lx = 0;
    public static final int ADD_INFO = 2;
    public static final int UPDATA_INFO = 3;
    private List<YGXX2> ygxx2s;
    private ActionBar actionBar;
    private int select_item = 0;
    private YGXXAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ygxx_layout);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.change);
            actionBar.setTitle("员工信息");
        }
        bottomNva.setVisibility(View.GONE);
        initData();
        initClick();
    }

    private void initClick() {
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Z_YGXXActivity.this, Z_XGXXActivity.class);
                intent.putExtra("data", ygxx2s.get(position));
                startActivityForResult(intent, UPDATA_INFO);
            }
        });
        bottomNva.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_deletc:
                        AlertDialog.Builder builder = new AlertDialog.Builder(Z_YGXXActivity.this);
                        builder.setTitle("提示");
                        builder.setMessage("您确定要删除所选的" + select_item + "项");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for (int i = 0; i < ygxx2s.size(); i++) {
                                    YGXX2 ygxx2 = ygxx2s.get(i);
                                    if (ygxx2.isXz()) {
                                        LitePal.deleteAll(YGXX.class, "name=?", ygxx2s.get(i).getName());
                                    }
                                }
                                dialog.dismiss();
                                Toast.makeText(Z_YGXXActivity.this, "成功删除" + select_item + "个信息", Toast.LENGTH_SHORT).show();
                                select_item = 0;
                                actionBar.setTitle("员工信息");
                                actionBar.setHomeAsUpIndicator(R.mipmap.change);
                                bottomNva.setVisibility(View.GONE);
                                adapter.setIs(false);
                                adapter.notifyDataSetChanged();
                                if (etInput.getText().equals("")) {
                                    initData();
                                } else {
                                    initData(etInput.getText().toString());
                                }
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectNone();
                                dialog.dismiss();
                            }
                        });
                        builder.show();
                        break;
                    case R.id.navigation_all:
                        if (select_item == ygxx2s.size()) {
                            selectNone();
                        } else {
                            for (int i = 0; i < ygxx2s.size(); i++) {
                                YGXX2 ygxx2 = ygxx2s.get(i);
                                ygxx2.setXz(true);
                                select_item = ygxx2s.size();
                                actionBar.setTitle("已选中" + select_item + "个");
                                adapter.notifyDataSetChanged();
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void selectNone() {
        for (int i = 0; i < ygxx2s.size(); i++) {
            YGXX2 ygxx2 = ygxx2s.get(i);
            ygxx2.setXz(false);
            select_item = 0;
            actionBar.setTitle("已选中" + select_item + "个");
            adapter.notifyDataSetChanged();
        }
    }

    private void initData() {
        parkInfo = LitePal.findAll(YGXX.class);
        ygxx2s = new ArrayList<>();
        for (int i = 0; i < parkInfo.size(); i++) {
            YGXX ygxx = parkInfo.get(i);
            String pingying = PasePing.getPinYinFirstLetter(ygxx.getName());
            if (!pingying.matches("[A-Z]")) {
                pingying = "#";
            }
            YGXX2 ygxx2 = new YGXX2(ygxx.getName(), ygxx.getSex(), ygxx.getBirth(), ygxx.getTel(), ygxx.getScx(), ygxx.getZw(), ygxx.getEmail(), ygxx.getAddress(), false);
            ygxx2.setPingying(pingying);
            ygxx2s.add(ygxx2);
        }
        Collections.sort(ygxx2s, new Comparator<YGXX2>() {
            @Override
            public int compare(YGXX2 o1, YGXX2 o2) {
                if (o1.getPingying().equals("#") && !o2.getPingying().equals("#")) {
                    return 1;
                } else if (!o1.getPingying().equals("#") && o2.getPingying().equals("#")) {
                    return -1;
                } else {
                    return o1.getPingying().compareToIgnoreCase(o2.getPingying());
                }
            }
        });
        adapter = new YGXXAdapter(Z_YGXXActivity.this, R.layout.ygxx_item, ygxx2s);
        listView2.setAdapter(adapter);
        adapter.setClick(new YGXXAdapter.Click() {
            @Override
            public void MyClick(int position, boolean xz) {
                Log.i("MyClick:", "MyClick: " + xz);
                if (xz) {
                    select_item = select_item + 1;
                } else {
                    select_item = select_item - 1;
                }
                YGXX2 ygxx2 = ygxx2s.get(position);
                ygxx2.setXz(xz);
                ygxx2s.set(position, ygxx2);
                actionBar.setTitle("已选中" + select_item + "个");
                adapter.notifyDataSetChanged();
            }
        });
        initLisent(ygxx2s);
    }

    private void initData(String name) {
        parkInfo = LitePal.where("name like ?", "%" + name + "%").find(YGXX.class);
        ygxx2s = new ArrayList<>();
        for (int i = 0; i < parkInfo.size(); i++) {
            YGXX ygxx = parkInfo.get(i);
            String pingying = PasePing.getPinYinFirstLetter(ygxx.getName());
            if (!pingying.matches("[A-Z]")) {
                pingying = "#";
            }
            YGXX2 ygxx2 = new YGXX2(ygxx.getName(), ygxx.getSex(), ygxx.getBirth(), ygxx.getTel(), ygxx.getScx(), ygxx.getZw(), ygxx.getEmail(), ygxx.getAddress(), false);
            ygxx2.setPingying(pingying);
            ygxx2s.add(ygxx2);
        }
        Collections.sort(ygxx2s, new Comparator<YGXX2>() {
            @Override
            public int compare(YGXX2 o1, YGXX2 o2) {
                if (o1.getPingying().equals("#") && !o2.getPingying().equals("#")) {
                    return 1;
                } else if (!o1.getPingying().equals("#") && o2.getPingying().equals("#")) {
                    return -1;
                } else {
                    return o1.getPingying().compareToIgnoreCase(o2.getPingying());
                }
            }
        });
        adapter = new YGXXAdapter(Z_YGXXActivity.this, R.layout.ygxx_item, ygxx2s);
        listView2.setAdapter(adapter);
        adapter.setClick(new YGXXAdapter.Click() {
            @Override
            public void MyClick(int position, boolean xz) {
                Log.i("MyClick:", "MyClick: " + xz);
                if (xz) {
                    select_item = select_item + 1;
                } else {
                    select_item = select_item - 1;
                }
                YGXX2 ygxx2 = ygxx2s.get(position);
                ygxx2.setXz(xz);
                ygxx2s.set(position, ygxx2);
                actionBar.setTitle("已选中" + select_item + "个");
                adapter.notifyDataSetChanged();
            }
        });
        initLisent(ygxx2s);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.yg_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (actionBar.getTitle().equals("员工信息")) {
                    finish();
                } else {
                    actionBar.setTitle("员工信息");
                    actionBar.setHomeAsUpIndicator(R.mipmap.change);
                    bottomNva.setVisibility(View.GONE);
                    adapter.setIs(false);
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.in_read:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("追加记录or覆盖记录");
                builder.setPositiveButton("追加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lx = 1;
                        initFG();
                        Toast.makeText(Z_YGXXActivity.this, "追加记录", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("覆盖", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lx = 2;
                        initFG();
                        Toast.makeText(Z_YGXXActivity.this, "覆盖记录", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                break;
            case R.id.out_read:
                File file = new File("/sdcard/AndroidExcel");
                if (!file.exists()) {
                    file.mkdirs();
                }
                if (parkInfo != null) {
                    if (parkInfo.size() == 0) {
                        Toast.makeText(this, "没有可导出的内容", Toast.LENGTH_SHORT).show();
                    } else {
                        String path = "/sdcard/AndroidExcel/" + SimpData.Simp("yyyy-MM-dd", new Date()) + ".xls";
                        String[] title = {"姓名", "年龄", "出生日期", "电话", "生产线", "职务", "邮箱", "家庭住址"};
                        ExcelUtil.initExcel(path, "demoSheet", title);
                        ExcelUtil.writeObjListToExcel(parkInfo, path, this);
                    }
                } else {
                    Toast.makeText(this, "没有可导出的内容", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.delete_select:
                actionBar.setHomeAsUpIndicator(R.mipmap.cuohao);
                actionBar.setTitle("已选中" + select_item + "个");
                bottomNva.setVisibility(View.VISIBLE);
                adapter.setIs(true);
                adapter.notifyDataSetChanged();
                break;
        }
        return true;
    }

    private void initFG() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (data == null) {
                    // 用户未选择任何文件，直接返回
                    return;
                }
                Uri uri = data.getData(); // 获取用户选择文件的URI
                // 通过ContentProvider查询文件路径
                ContentResolver resolver = this.getContentResolver();
                Cursor cursor = resolver.query(uri, null, null, null, null);
                if (cursor == null) {
                    // 未查询到，说明为普通文件，可直接通过URI获取文件路径
                    String path = uri.getPath();
                    readExcelToDB(path, lx);
                    return;
                }
                if (cursor.moveToFirst()) {
                    // 多媒体文件，从数据库中获取文件的真实路径
                    String path = cursor.getString(cursor.getColumnIndex("_data"));
                    readExcelToDB(path, lx);
                }
                cursor.close();
                break;
            case ADD_INFO:
                if (resultCode == RESULT_OK) {
                    initData();
                }
                Toast.makeText(this, "成功添加一条信息", Toast.LENGTH_SHORT).show();
                break;
            case UPDATA_INFO:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "修改信息成功", Toast.LENGTH_SHORT).show();
                    initData();
                } else if (resultCode == RESULT_FIRST_USER) {
                    Toast.makeText(this, "删除信息", Toast.LENGTH_SHORT).show();
                    initData();
                }
                break;
        }
    }

    /*
     * 将excel中的数据读取到数据库中
     * */
    public void readExcelToDB(String path, int lx) {
        try {
            //我们把excel放在Assset目录下，通过Workbook.getWorkbook(inputStream);获取到整个excel。
            InputStream inputStream = new FileInputStream(path);
            Workbook workbook = Workbook.getWorkbook(inputStream);
            //获取第一张excel数据表。
            Sheet sheet = workbook.getSheet(0);
            int rows = sheet.getRows();//获取该表中有多少行数据。
            Log.e("readExcelToDB", rows + "-------rows-------");
            if (lx == 1) {
                if (parkInfo == null) parkInfo = new ArrayList<>();
            } else if (lx == 2) {
                parkInfo = new ArrayList<>();
            }
            Log.i("readExcelToDB", parkInfo + "--" + lx);
            for (int i = 1; i < rows; i++) {
                //sheet.getCell(0,i))，在这里i表示第几行数据，012346表示第几列，从0开始算。
                String name = (sheet.getCell(0, i)).getContents();
                String sex = (sheet.getCell(1, i)).getContents();
                String birth = (sheet.getCell(2, i)).getContents();
                String tel = (sheet.getCell(3, i)).getContents();
                String scx = (sheet.getCell(4, i)).getContents();
                String zw = (sheet.getCell(5, i)).getContents();
                String email = (sheet.getCell(6, i)).getContents();
                String address = (sheet.getCell(7, i)).getContents();
                parkInfo.add(new YGXX(name, sex, birth, tel, scx, zw, email, address));
                Log.e("ExcelUtils", parkInfo.toString());
            }
            List<YGXX2> ygxx2s = new ArrayList<>();
            for (int i = 0; i < parkInfo.size(); i++) {
                YGXX ygxx = parkInfo.get(i);
                String pingying = PasePing.getPinYinFirstLetter(ygxx.getName());
                if (!pingying.matches("[A-Z]")) {
                    pingying = "#";
                }
                YGXX2 ygxx2 = new YGXX2(ygxx.getName(), ygxx.getSex(), ygxx.getBirth(), ygxx.getTel(), ygxx.getScx(), ygxx.getZw(), ygxx.getEmail(), ygxx.getAddress(), false);
                ygxx2.setPingying(pingying);
                ygxx2s.add(ygxx2);
            }
            Collections.sort(ygxx2s, new Comparator<YGXX2>() {
                @Override
                public int compare(YGXX2 o1, YGXX2 o2) {
                    if (o1.getPingying().equals("#") && !o2.getPingying().equals("#")) {
                        return 1;
                    } else if (!o1.getPingying().equals("#") && o2.getPingying().equals("#")) {
                        return -1;
                    } else {
                        return o1.getPingying().compareToIgnoreCase(o2.getPingying());
                    }
                }
            });
            if (lx == 1) {
                for (int i = 0; i < parkInfo.size(); i++) {
                    YGXX ygxx = parkInfo.get(i);
                    ygxx.save();
                }
            } else if (lx == 2) {
                LitePal.deleteAll(YGXX.class);
                for (int i = 0; i < parkInfo.size(); i++) {
                    YGXX ygxx = parkInfo.get(i);
                    ygxx.save();
                }
            }
            listView2.setAdapter(new YGXXAdapter(Z_YGXXActivity.this, R.layout.ygxx_item, ygxx2s));
            initLisent(ygxx2s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initLisent(final List<YGXX2> list) {
        sideBar.setScaleItemCount(1);
        sideBar.setOnStrSelectCallBack(new SideBar.ISideBarSelectCallBack() {
            @Override
            public void onSelectStr(int index, String selectStr) {
                for (int i = 0; i < list.size(); i++) {
                    Log.i("aaa", "onSelectStr: wwww" + selectStr);
                    if (selectStr.equalsIgnoreCase(list.get(i).getPingying())) {
                        listView2.setSelection(i); // 选择到首字母出现的位置
                        Log.i("aaa", "onSelectStr: " + selectStr);
                        return;
                    }
                }
            }
        });
    }

    @OnClick({R.id.image_find, R.id.float_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_find:
                if ("".equals(etInput.getText())) {
                    initData();
                } else {
                    initData(etInput.getText().toString().trim());
                }
                break;
            case R.id.float_button:
                Intent intent = new Intent(this, Z_TJXXActivity.class);
                startActivityForResult(intent, ADD_INFO);
                break;
        }
    }
}
