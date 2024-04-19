package com.example.raddiobutton1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=(Button) findViewById(R.id.button);
        Button check=(Button) findViewById(R.id.check);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String outputStr = "";

                RadioButton boy = (RadioButton) findViewById(R.id.rdbBoy);
                RadioButton girl = (RadioButton) findViewById(R.id.rdbGirl);
                if (boy.isChecked())
                    outputStr += "男生\n";
                else if (girl.isChecked())
                    outputStr += "女生\n";

                EditText numText =(EditText) findViewById(R.id.numText);
                outputStr+=numText.getText().toString()+"\n";

                RadioGroup type = (RadioGroup) findViewById(R.id.rgType);
                if(type.getCheckedRadioButtonId()==R.id.rdbAdult)
                    outputStr+="全票\n";
                else if(type.getCheckedRadioButtonId()==R.id.rdbChild)
                    outputStr+="兒童票\n";
                else
                    outputStr+="學生票\n";
                TextView output = (TextView) findViewById(R.id.lblOutput);
                output.setText(outputStr);

            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String output1 = "";

                RadioButton boy = (RadioButton) findViewById(R.id.rdbBoy);
                RadioButton girl = (RadioButton) findViewById(R.id.rdbGirl);
                if (boy.isChecked())
                    output1 += "男生\n";
                else if (girl.isChecked())
                    output1 += "女生\n";

                EditText numText =(EditText) findViewById(R.id.numText);
                output1+=numText.getText().toString()+"\n";

                int Adult=500;
                int child=250;
                int Stud=400;
                int money=0;
                int num= Integer.parseInt(numText.getText().toString());
                RadioGroup type = (RadioGroup) findViewById(R.id.rgType);
                if(type.getCheckedRadioButtonId()==R.id.rdbAdult) {
                    money = Adult * num;
                    output1 += "全票\n";
                }else if(type.getCheckedRadioButtonId()==R.id.rdbChild) {
                    money = child * num;
                    output1 += "兒童票\n";
                }else {
                    money = Stud * num;
                    output1 += "學生票\n";
                }
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                // 在Intent中添加需要傳遞的數據
                intent.putExtra("output1", output1);
                intent.putExtra("money", money);

                // 啟動下一個Activity
                startActivity(intent);
            }
        });
    }
}