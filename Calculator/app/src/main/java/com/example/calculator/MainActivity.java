package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;



public class MainActivity extends AppCompatActivity {

    private double operand1 = 0, operand2 = 0;
    private StringBuilder currentInput = new StringBuilder();
    private String operator = "";
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        adjustButtonSizes();
    }

    private void adjustButtonSizes() {
        GridLayout keysGL = findViewById(R.id.keys);
        int buttonCount = keysGL.getChildCount();
        int buttonSize = keysGL.getWidth() / keysGL.getColumnCount();

        for (int i = 0; i < buttonCount; i++) {
            Button button = (Button) keysGL.getChildAt(i);
            button.setWidth(buttonSize);
        }
    }

    public void processKeyInput(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "Clear":
                clearDisplay();
                break;
            case "Back":
                deleteLastCharacter();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                handleOperator(buttonText);
                break;
            case "=":
                calculateResult();
                break;
            default:
                appendInput(buttonText);
                break;
        }
    }

    private void clearDisplay() {
        operand1 = 0;
        operand2 = 0;
        currentInput.setLength(0);
        operator = "";
        display.setText("0");
    }

    private void deleteLastCharacter() {
        if (currentInput.length() > 0) {
            currentInput.deleteCharAt(currentInput.length() - 1);
            display.setText(currentInput.length() == 0 ? "0" : currentInput.toString());
        }
    }

    private void handleOperator(String operator) {
        if (currentInput.length() > 0) {
            this.operator = operator;
            operand1 = Double.parseDouble(currentInput.toString());
            currentInput.setLength(0);
        }
    }

    private void appendInput(String input) {
        if (input.equals(".") && currentInput.indexOf(".") != -1) {
            // 如果已經包含小數點，則忽略此次輸入
            return;
        }

        if (input.equals(".") && currentInput.length() == 0) {
            // 如果輸入的是小數點且當前沒有其他數字，則自動添加 0
            currentInput.append("0");
        }

        currentInput.append(input);
        display.setText(currentInput.toString());
    }

    private void calculateResult() {
        double result = 0;
        if (currentInput.length() > 0 && !operator.isEmpty()) {
            operand2 = Double.parseDouble(currentInput.toString());
            result = 0;

            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        display.setText("Error");
                        clearDisplay();
                        return;
                    }
                    break;
            }

            display.setText(String.valueOf(result));
            operand1 = result;
            operand2 = 0;
            currentInput.setLength(0);
            operator = "";
        }
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedResult = df.format(result);
        display.setText(formattedResult);
    }
}
