package com.example.raddiobutton1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private TextView output;
    private EditText numText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.lblOutput);
        numText = findViewById(R.id.numText);

        RadioGroup rgGender = findViewById(R.id.rgGender);
        rgGender.setOnCheckedChangeListener(this);

        RadioGroup rgTicket = findViewById(R.id.rgp);
        rgTicket.setOnCheckedChangeListener(this);

        RadioGroup rgNum = findViewById(R.id.rgp2);
        rgNum.setOnCheckedChangeListener(this);

        numText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateOutputText();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        Button check = findViewById(R.id.check);
        check.setOnClickListener(v -> {
            String output1 = "";

            RadioButton boy = findViewById(R.id.rdbBoy);
            RadioButton girl = findViewById(R.id.rdbGirl);
            if (boy.isChecked())
                output1 += "性别：" + boy.getText() + "\n";
            else if (girl.isChecked())
                output1 += "性别：" + girl.getText() + "\n";

            int num = Integer.parseInt(numText.getText().toString());
            output1 += "數量：" + num + "\n";

            int Adult = 500;
            int child = 250;
            int Stud = 400;
            int money = 0;
            RadioGroup rgTicketSelected = findViewById(R.id.rgp);
            if (rgTicketSelected.getCheckedRadioButtonId() == R.id.rdbAdult) {
                money = Adult * num;
                output1 += "票種：" + "全票\n";
            } else if (rgTicketSelected.getCheckedRadioButtonId() == R.id.rdbChild) {
                money = child * num;
                output1 += "票種：" + "兒童票\n";
            } else {
                money = Stud * num;
                output1 += "票種：" + "學生票\n";
            }
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);

            intent.putExtra("output1", output1);
            intent.putExtra("money", money);

            startActivity(intent);
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        updateOutputText();
    }

    private void updateOutputText() {
        String genderText = "";
        String ticketText = "";
        String numTextValue = numText.getText().toString();

        RadioButton boy = findViewById(R.id.rdbBoy);
        RadioButton girl = findViewById(R.id.rdbGirl);
        RadioButton Adult = findViewById(R.id.rdbAdult);
        RadioButton Student = findViewById(R.id.rdbStudent);
        RadioButton child = findViewById(R.id.rdbChild);

        if (boy.isChecked()) {
            genderText = boy.getText().toString();
        } else if (girl.isChecked()) {
            genderText = girl.getText().toString();
        }

        if (Adult.isChecked()) {
            ticketText = Adult.getText().toString();
        } else if (Student.isChecked()) {
            ticketText = Student.getText().toString();
        } else if (child.isChecked()) {
            ticketText = child.getText().toString();
        }

        output.setText("性别：" + genderText + "\n" + "票種：" + ticketText + "\n" + "數量：" + numTextValue);
    }
}
