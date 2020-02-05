package com.example.create.activity3;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.create.R;
import com.example.create.fragment3.Z_CKXXFragment;
import com.example.create.fragment3.Z_KCFragment;
import com.example.create.fragment3.Z_RKXXFragment;
import com.example.create.fragment3.Z_YZSZFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-02-02
 */
public class Z_YLGLActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.frame_layout3)
    FrameLayout frameLayout3;
    @BindView(R.id.bottom_nav)
    BottomNavigationView bottomNav;
    @BindView(R.id.title1)
    TextView title1;
    private Z_RKXXFragment rkxxFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ylgl_layout);
        ButterKnife.bind(this);
        initView();
        initClick();
    }

    private void initView() {
        rkxxFragment = new Z_RKXXFragment(this);
        title.setText("原料库存管理--设置阈值");
        replace(new Z_YZSZFragment(Z_YLGLActivity.this));
        title1.setText("设置阈值");
    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout3, fragment).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 2:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data);
                    } else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
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
        displayImage(imagePath);
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            rkxxFragment.setTest(imagePath);
        } else {
            Toast.makeText(this, "加载图片失败,请重新选择", Toast.LENGTH_SHORT).show();
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

    private void initClick() {
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Z_YLGLActivity.this, Z_YZSZActivity.class));
            }
        });
        bottomNav.setSelectedItemId(R.id.navigation_yj);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_kc:
                        title.setText("原料库存管理--库存详情");
                        replace(new Z_KCFragment(Z_YLGLActivity.this));
                        title1.setText("");
                        return true;
                    case R.id.navigation_yj:
                        title.setText("原料库存管理--设置阈值");
                        replace(new Z_YZSZFragment(Z_YLGLActivity.this));
                        title1.setText("设置阈值");
                        return true;
                    case R.id.navigation_rk:
                        title.setText("原料库存管理--入库查询");
                        title1.setText("");
                        replace(rkxxFragment);
                        return true;
                    case R.id.navigation_ck:
                        title.setText("原料库存管理--出库");
                        title1.setText("");
                        replace(new Z_CKXXFragment(Z_YLGLActivity.this));
                        return true;
                }
                return false;
            }
        });
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
