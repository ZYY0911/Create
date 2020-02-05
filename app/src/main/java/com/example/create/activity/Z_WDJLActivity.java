package com.example.create.activity;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.create.R;
import com.example.create.dialog.Z_SCJLDialog;
import com.example.create.fragment.Z_JBCCFragment;
import com.example.create.fragment.Z_JLLBFragment;

import java.io.FileNotFoundException;

/**
 * Create by 张瀛煜 on 2020-01-15
 */
public class Z_WDJLActivity extends AppCompatActivity {
    private ImageView change;
    private TextView title;
    private FrameLayout frameLayout;
    private TextView tvJbxx;
    private TextView tvJllb;
    private Z_JBCCFragment z_jbccFragment;
    public static final int CHOOSE_PHOTO = 2;
    public static final int REQUEST_CODE = 1;
    private Z_SCJLDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wdjl_layout);
        initView();
        initClick();
        replaceFrag(z_jbccFragment);
        tvJbxx.setTextSize(35);
        tvJllb.setTextSize(30);
    }

    private void replaceFrag(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment).commit();
    }

    private void initClick() {
        tvJbxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFrag(z_jbccFragment);
                tvJbxx.setTextSize(35);
                tvJllb.setTextSize(30);
            }
        });
        tvJllb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFrag(new Z_JLLBFragment(dialog));
                tvJbxx.setTextSize(30);
                tvJllb.setTextSize(35);
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
                    dialog.etWj.setText(path);
                    return;
                }
                if (cursor.moveToFirst()) {
                    // 多媒体文件，从数据库中获取文件的真实路径
                    String path = cursor.getString(cursor.getColumnIndex("_data"));
                    dialog.etWj.setText(path);
                }
                cursor.close();
                break;
            case CHOOSE_PHOTO:
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
            z_jbccFragment.imageUrl = imagePath;
            Glide.with(this).load(imagePath).into(z_jbccFragment.imagePhoto);
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

    private void initView() {
        z_jbccFragment = new Z_JBCCFragment(getApplicationContext());
        dialog = new Z_SCJLDialog();
        change = findViewById(R.id.change);
        title = findViewById(R.id.title);
        frameLayout = findViewById(R.id.frame_layout);
        tvJbxx = findViewById(R.id.tv_jbxx);
        tvJllb = findViewById(R.id.tv_jllb);
        title.setText("人才市场--我的简历");
    }
}
