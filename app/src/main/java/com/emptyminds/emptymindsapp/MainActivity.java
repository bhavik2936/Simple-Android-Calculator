package com.emptyminds.emptymindsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtEmail, txtPassword;
    private Button btnLogin;
    private long backPressedTime;
    private Toast toast;

    @Override
    public void onBackPressed() {
        try {
            toast.cancel();
        } catch (NullPointerException e) {
        }
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            toast.cancel();
            super.onBackPressed();
            return;
        } else {
            toast = Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT);
            toast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            toast.cancel();
        } catch (NullPointerException e) {
        }

        switch (v.getId()) {
            case R.id.btnLogin:
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if (email.equals(getResources().getString(R.string.username)) && password.equals(getResources().getString(R.string.password))) {
                    Intent calcIntent = new Intent(this, CalculatorActivity.class);
                    startActivity(calcIntent);
//                    finish();

//                    toast = Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT);
                } else {
                    toast = Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }
}