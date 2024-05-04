package com.example.hw0502;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // 定義 CheckBox 和 ImageView
    private CheckBox chk1, chk2, chk3, chk4, chk5;
    private ImageView img1, img2, img3, img4, img5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化 CheckBox 和 ImageView
        chk1 = findViewById(R.id.chk1);
        chk2 = findViewById(R.id.chk2);
        chk3 = findViewById(R.id.chk3);
        chk4 = findViewById(R.id.chk4);
        chk5 = findViewById(R.id.chk5);

        img1 = findViewById(R.id.output1);
        img2 = findViewById(R.id.output2);
        img3 = findViewById(R.id.output3);
        img4 = findViewById(R.id.output4);
        img5 = findViewById(R.id.output5);

        // 添加 CheckBox 的監聽器
        chk1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            img1.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });

        chk2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            img2.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });

        chk3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            img3.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });

        chk4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            img4.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });

        chk5.setOnCheckedChangeListener((buttonView, isChecked) -> {
            img5.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });
    }
}
