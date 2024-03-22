package com.example.hw;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String DEFAULT_USERNAME = "A111223003";
    private static final String DEFAULT_PASSWORD = "kirito";
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    @SuppressLint({"MissingInflatedId", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                String successMessage = getString(R.string.login_success_message);
                String Uerror = getString(R.string.USERNAME_error_message);
                String Perror = getString(R.string.PASSWORD_error_message);
                String error1 = getString(R.string.error_message);
                String error2 = getString(R.string.error2_message);

                if (username.isEmpty() && password.isEmpty()) {
                    Toast.makeText(MainActivity.this,error1, Toast.LENGTH_SHORT).show();
                } else if (!password.equals(DEFAULT_PASSWORD)&&!username.equals(DEFAULT_USERNAME)){
                    Toast.makeText(MainActivity.this, error2, Toast.LENGTH_SHORT).show();
                } else if(!password.equals(DEFAULT_PASSWORD)){
                    Toast.makeText(MainActivity.this, Perror, Toast.LENGTH_SHORT).show();
                } else if(!username.equals(DEFAULT_USERNAME) ) {
                        Toast.makeText(MainActivity.this, Uerror, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, successMessage, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}