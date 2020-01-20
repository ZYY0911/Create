package com.example.create.activity2;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.create.R;
import com.example.create.adapter2.TJSPListAdapter;
import com.example.create.bean2.GYS;
import com.example.create.bean2.GYSP;
import com.example.create.dialog2.TJSPDialog;
import com.example.create.util.ShowDialog;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-19
 */
public class Z_TJGYSActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
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
    @BindView(R.id.image_photo)
    ImageView imagePhoto;
    @BindView(R.id.add_photo)
    Button addPhoto;
    @BindView(R.id.add_sp)
    Button addSp;
    @BindView(R.id.list_splb)
    ListView listSplb;
    @BindView(R.id.tv_none)
    TextView tvNone;
    @BindView(R.id.add_save)
    Button addSave;
    private String imageUri;
    public static final int CHOOSE_PHOTO = 2;
    public static final int CHOOSE_PHOTO_FRAGMENT = 3;
    private TJSPDialog dialog;
    private List<GYSP> gysps;
    private int gsId;
    private GYS gys;
    private TJSPListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tjgys_layout);
        ButterKnife.bind(this);
        gsId = getIntent().getIntExtra("gs", 0);
        if (gsId == 0) {

        } else {
            already();
        }
        initView();
    }

    private void already() {
        gys = LitePal.where("id=?", gsId + "").find(GYS.class).get(0);
        etGysName.setText(gys.getGysName());
        etGysCity.setText(gys.getGysCity());
        etGysLaw.setText(gys.getGysLaw());
        etGysLocation.setText(gys.getGysLocation());
        etGysPeople.setText(gys.getGysPeople());
        etGysRange.setText(gys.getGysRange());
        etGysTel.setText(gys.getGysTel());
        etGysNum.setText(gys.getGysNum() + "");
        Glide.with(this).load(gys.getGysPhoto()).into(imagePhoto);
        gysps = LitePal.where("gysNum=?", gys.getGysNum() + "").find(GYSP.class);
        adapter = new TJSPListAdapter(Z_TJGYSActivity.this, R.layout.tjsp_item, gysps);
        listSplb.setAdapter(adapter);
        adapter.setData(new TJSPListAdapter.SetData() {
            @Override
            public void setdata(int position, int lx, boolean sc) {
                if (lx == 1) {
                    adapter.setIndex(position);
                } else if (lx == 2) {
                    LitePal.deleteAll(GYSP.class, "id=?", gysps.get(position).getId() + "");
                    gysps.remove(position);
                } else if (lx == 3) {
                    dialog = new TJSPDialog(Z_TJGYSActivity.this, Integer.parseInt(etGysNum.getText().toString().trim()));
                    dialog.show(getSupportFragmentManager(), "aaa");
                    dialog.setClick(new TJSPDialog.MyClick() {
                        @Override
                        public void click() {
                            already();
                        }
                    });
                }
                adapter.notifyDataSetChanged();
            }
        });
    }


    private void initView() {
        title.setText("供应商--添加供应商");
    }

    @OnClick({R.id.change, R.id.add_photo, R.id.add_sp, R.id.add_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.add_photo:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
                break;
            case R.id.add_sp:
                if ("".equals(etGysNum.getText().toString().trim())) {
                    ShowDialog.Show("请输入供货商信息", this);
                    return;
                }
                dialog = new TJSPDialog(this, Integer.parseInt(etGysNum.getText().toString().trim()));
                dialog.show(getSupportFragmentManager(), "aaa");
                dialog.setClick(new TJSPDialog.MyClick() {
                    @Override
                    public void click() {
                        tvNone.setVisibility(View.GONE);
                        listSplb.setVisibility(View.VISIBLE);
                        gysps = LitePal.where("gysNum=?", etGysNum.getText().toString().trim()).find(GYSP.class);
                        adapter = new TJSPListAdapter(Z_TJGYSActivity.this, R.layout.tjsp_item, gysps);
                        listSplb.setAdapter(adapter);
                        adapter.setData(new TJSPListAdapter.SetData() {
                            @Override
                            public void setdata(int position, int lx, boolean sc) {
                                adapter.setIndex(position+1);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
                break;
            case R.id.add_save:
                GYS gys = new GYS();
                gys.setGysNum(Integer.parseInt(etGysNum.getText().toString().trim()));
                gys.setGysName(etGysName.getText().toString().trim());
                gys.setGysCity(etGysCity.getText().toString().trim());
                gys.setGysLaw(etGysLaw.getText().toString().trim());
                gys.setGysLocation(etGysLocation.getText().toString().trim());
                gys.setGysPeople(etGysPeople.getText().toString().trim());
                gys.setGysTel(etGysTel.getText().toString().trim());
                gys.setGysPhoto(imageUri);
                gys.setGysRange(etGysRange.getText().toString().trim());
                if (gsId == 0) {
                    gys.save();
                    Toast.makeText(this, "保存信息成功", Toast.LENGTH_SHORT).show();
                } else {
                    gys.updateAll("id=?", gsId + "");
                    Toast.makeText(this, "修改信息成功", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:

                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data, 1);
                    } else {
                        handleImageBeforeKitKat(data, 1);
                    }
                }
                break;
            case CHOOSE_PHOTO_FRAGMENT:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data, 2);
                    } else {
                        handleImageBeforeKitKat(data, 2);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void handleImageBeforeKitKat(Intent data, int lx) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        if (lx == 1) {
            displayImage(imagePath);
        } else {
            displayImage2(imagePath);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handleImageOnKitKat(Intent data, int lx) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];//解析书数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
        if (lx == 1) {
            displayImage(imagePath);
        } else {
            displayImage2(imagePath);
        }
    }

    private void displayImage2(String imagePath) {
        if (imagePath != null) {
            dialog.imagURl = imagePath;
            Glide.with(Z_TJGYSActivity.this).load(imagePath).into(dialog.imagePhoto);
        } else {
            Toast.makeText(this, "错误,请重新选中", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageUri = imagePath;
            imagePhoto.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "错误,请重新选中", Toast.LENGTH_SHORT).show();
            imageUri = "";
        }
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
}
