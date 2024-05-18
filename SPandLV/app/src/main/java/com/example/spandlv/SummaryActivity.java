package com.example.spandlv;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    private TextView mainDishTextView;
    private TextView sideDishTextView;
    private TextView drinkTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        mainDishTextView = findViewById(R.id.mainDishTextView);
        sideDishTextView = findViewById(R.id.sideDishTextView);
        drinkTextView = findViewById(R.id.drinkTextView);
        backButton = findViewById(R.id.backButton);

        // 获取传递的数据
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String mainDish = extras.getString("MAIN_DISH");
            String sideDish = extras.getString("SIDE_DISH");
            String drink = extras.getString("DRINK");

            mainDishTextView.setText("主餐：" + mainDish);
            sideDishTextView.setText("附餐：" + sideDish);
            drinkTextView.setText("飲料：" + drink);
        }

        // 设置返回按钮的点击事件
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 返回上一界面
                finish();
            }
        });
    }
}
