package com.example.raddiobutton1;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class MainActivity2 extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // 獲取從上一個Activity傳遞過來的數據
        Intent intent = getIntent();
        String output1 = intent.getStringExtra("output1");
        int money = intent.getIntExtra("money", 0);

        // 在相應的TextView中顯示計算結果和其他相關信息
        TextView output = (TextView) findViewById(R.id.outputTxv);
        output.setText(output1+"金額："+money);
    }
}
