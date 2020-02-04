package com.example.create.activity2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.create.R;
import com.example.create.util.ImageListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-01-29
 */
public class Z_GHFPActivity extends AppCompatActivity {
    @BindView(R.id.image_view)
    ImageView imageView;
    private String fp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ghfp_layout);
        ButterKnife.bind(this);
        fp = getIntent().getStringExtra("image");
        switch (fp){
            case "fapiao1":
                imageView.setImageResource(R.mipmap.fapiao1);
                break;
            case "fapiao2":
                imageView.setImageResource(R.mipmap.fapiao2);
                break;
            case "fapiao3":
                imageView.setImageResource(R.mipmap.fapiao3);
                break;
            case "fapiao4":
                imageView.setImageResource(R.mipmap.fapiao4);
                break;
            case "fapiao5":
                imageView.setImageResource(R.mipmap.fapiao5);
                break;
        }
        imageView.setOnTouchListener(new ImageListener(imageView));
    }
}
