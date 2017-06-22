package com.example.jz36.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText calculateString;

    Button getResult;
    TextView resultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculateString = (EditText) findViewById(R.id.calculateString);

        getResult = (Button) findViewById(R.id.getResult);

        resultText = (TextView) findViewById(R.id.tvResult);

        getResult.setOnClickListener(this);
        calculateString.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    int lengthString = calculateString.getText().length();
                    if ((lengthString > 1) && (count - before > 0)) {
                        Character lastSymbol = calculateString.getText().toString().charAt(lengthString-2);
                        Character currentKey = charSequence.charAt(start);
                        if (!("+-/*".indexOf(lastSymbol) == -1) && !("+-*/".indexOf(currentKey) == -1)) {
                            calculateString.getText().delete(lengthString - 2, lengthString - 1);
                        }
                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View v){
        try {
            String result = ReversePolishNotation.Calculate(calculateString.getText().toString());
            resultText.setText(result);
        }catch(ArithmeticException e){
            resultText.setText(e.getMessage());
        }catch (Exception e){
            resultText.setText("Неверное выражение");
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
