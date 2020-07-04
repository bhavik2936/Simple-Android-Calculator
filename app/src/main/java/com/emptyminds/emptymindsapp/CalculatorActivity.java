package com.emptyminds.emptymindsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAnswer;
    private EditText txtQuery;
    private TextView txtAnswer;
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Double answer = 0.0;
    private Boolean isDirty = false;
    private String operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        btnAnswer = findViewById(R.id.btnEquals);
        btnAnswer.setOnClickListener(this);
        btn0 = findViewById(R.id.btn0);
        btn0.setOnClickListener(this);
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(this);
        btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(this);
        btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(this);
        btn8 = findViewById(R.id.btn8);
        btn8.setOnClickListener(this);
        btn9 = findViewById(R.id.btn9);
        btn9.setOnClickListener(this);

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
}