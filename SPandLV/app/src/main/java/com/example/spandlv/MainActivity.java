package com.example.spandlv;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner orderSpinner;
    private ListView foodListView, otherListView, drinkListView;
    private TextView lblOutput, lblOutput2, lblOutput3;
    private String mainDish, sideDish, drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orderSpinner = findViewById(R.id.order);
        foodListView = findViewById(R.id.food);
        otherListView = findViewById(R.id.other);
        drinkListView = findViewById(R.id.drink);
        lblOutput = findViewById(R.id.lblOutput);
        lblOutput2 = findViewById(R.id.lblOutput2);
        lblOutput3 = findViewById(R.id.lblOutput3);

        // 设置 Spinner 的适配器
        ArrayAdapter<CharSequence> orderAdapter = ArrayAdapter.createFromResource(this,
                R.array.order, android.R.layout.simple_spinner_item);
        orderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderSpinner.setAdapter(orderAdapter);

        // 设置 ListView 的适配器
        final ArrayAdapter<CharSequence> foodAdapter = ArrayAdapter.createFromResource(this,
                R.array.food, android.R.layout.simple_list_item_1);
        final ArrayAdapter<CharSequence> otherAdapter = ArrayAdapter.createFromResource(this,
                R.array.other, android.R.layout.simple_list_item_1);
        final ArrayAdapter<CharSequence> drinkAdapter = ArrayAdapter.createFromResource(this,
                R.array.drink, android.R.layout.simple_list_item_1);

        // 初始显示 foodListView
        foodListView.setAdapter(foodAdapter);

        orderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 隐藏所有 ListView
                foodListView.setVisibility(View.GONE);
                otherListView.setVisibility(View.GONE);
                drinkListView.setVisibility(View.GONE);

                // 根据选择显示对应的 ListView
                if (position == 0) {
                    foodListView.setAdapter(foodAdapter);
                    foodListView.setVisibility(View.VISIBLE);
                } else if (position == 1) {
                    otherListView.setAdapter(otherAdapter);
                    otherListView.setVisibility(View.VISIBLE);
                } else if (position == 2) {
                    drinkListView.setAdapter(drinkAdapter);
                    drinkListView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 当没有选择时不做任何事
            }
        });

        // 设置 ListView 的 ItemClickListener
        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mainDish = (String) parent.getItemAtPosition(position);
                lblOutput.setText("主餐：" + mainDish);
            }
        });

        otherListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sideDish = (String) parent.getItemAtPosition(position);
                lblOutput2.setText("附餐：" + sideDish);
            }
        });

        drinkListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drink = (String) parent.getItemAtPosition(position);
                lblOutput3.setText("飲料：" + drink);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.send) {
            // 检查是否所有项目都已选择
            if (mainDish == null) {
                Toast.makeText(this, "請選擇主餐", Toast.LENGTH_SHORT).show();
                return true;
            } else if (sideDish == null) {
                Toast.makeText(this, "請選擇附餐", Toast.LENGTH_SHORT).show();
                return true;
            } else if (drink == null) {
                Toast.makeText(this, "請選擇飲料", Toast.LENGTH_SHORT).show();
                return true;
            }

            // 处理送出操作，跳转到 SummaryActivity 并传递数据
            Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
            intent.putExtra("MAIN_DISH", mainDish);
            intent.putExtra("SIDE_DISH", sideDish);
            intent.putExtra("DRINK", drink);
            startActivity(intent);
            return true;
        } else if (id == R.id.cancel) {
            // 处理取消操作，重置选择
            mainDish = null;
            sideDish = null;
            drink = null;
            lblOutput.setText("主餐：請選擇");
            lblOutput2.setText("附餐：請選擇");
            lblOutput3.setText("飲料：請選擇");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
