package com.emptyminds.emptymindsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAnswer;
    private EditText txtQuery;
    private TextView txtAnswer;
    private Double answer = 0.0;
    private Boolean isDirty = false;
    private String operator;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        btnAnswer = findViewById(R.id.btnEquals);
        btnAnswer.setOnClickListener(this);
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);

        findViewById(R.id.btnClearAll).setOnClickListener(this);
        findViewById(R.id.btnDecimal).setOnClickListener(this);
        findViewById(R.id.btnDivision).setOnClickListener(this);
        findViewById(R.id.btnMultiply).setOnClickListener(this);
        findViewById(R.id.btnAddition).setOnClickListener(this);
        findViewById(R.id.btnSubtraction).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (!isDirty && v.getId() == R.id.btn0) {
            return;
        }

        txtQuery = findViewById(R.id.txtQuery);
        txtAnswer = findViewById(R.id.txtAnswer);
        Button btnTemp;
        btnTemp = findViewById(v.getId());

        switch (v.getId()) {
            case R.id.btn0:
            case R.id.btn1:
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn5:
            case R.id.btn6:
            case R.id.btn7:
            case R.id.btn8:
            case R.id.btn9:
            case R.id.btnDecimal:
                if (isDirty) {
                    txtQuery.append(btnTemp.getText().toString());
                } else {
                    txtQuery.setText(btnTemp.getText().toString());
                    isDirty = true;
                }
                break;
            case R.id.btnDivision:
            case R.id.btnMultiply:
            case R.id.btnAddition:
            case R.id.btnSubtraction:
            case R.id.btnEquals:
                calculate(btnTemp.getText().toString());
                break;
            case R.id.btnClearAll:
                isDirty = false;
                answer = 0.0;
                operator = null;
                txtQuery.setText("");
                txtAnswer.setText("");
                break;
        }
    }

    private void calculate(String operator) {
        if (isDirty) {
            Double temp = Double.parseDouble(txtQuery.getText().toString());

            if (this.operator != null) {
                switch (this.operator) {
                    case "÷":
                        answer /= temp;
                        break;
                    case "×":
                        answer *= temp;
                        break;
                    case "+":
                        answer += temp;
                        break;
                    case "-":
                        answer -= temp;
                        break;
                }
            } else {
                answer = Double.parseDouble(txtQuery.getText().toString());
            }
            this.operator = operator;
            temp = answer;

            StringBuilder txtViewString = new StringBuilder(txtAnswer.getText().toString());
            txtViewString.append(txtQuery.getText().toString()).append(" ").append(operator).append(" ");
            if (operator.equals("=")) {
                txtViewString.append(answer + "\n");
                answer = 0.0;
                this.operator = null;
            }

            txtAnswer.setText(txtViewString);
            txtQuery.setText(temp.toString());
            isDirty = false;
        } else if (this.operator != null && !this.operator.equals("=")) {
            this.operator =  operator;
            String strReplace = txtAnswer.getText().toString();
            txtAnswer.setText(strReplace.substring(0, strReplace.length() - 2).concat(operator + " "));
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

}