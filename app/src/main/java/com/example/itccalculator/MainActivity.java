package com.example.itccalculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.Expression;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText display;

    private int parN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }

        });
    }
    private void updateText(String newChar){
        String oldStr = display.getText().toString();
        int cursorPosition = display.getSelectionStart();
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(newChar);
        }else {

            display.setText(String.format("%s%s%s",oldStr.substring(0,cursorPosition),newChar,oldStr.substring(cursorPosition)));
            }
        display.setSelection(cursorPosition + 1);
    }
    private void updateText(String newChar,int curMove){
        String oldStr = display.getText().toString();
        int cursorPosition = display.getSelectionStart();
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(newChar);
        }else {

            display.setText(String.format("%s%s%s",oldStr.substring(0,cursorPosition),newChar,oldStr.substring(cursorPosition)));
        }
        display.setSelection(cursorPosition + curMove);
    }
    public void zeroB(View view){
        updateText("0");
    }

    public void oneB(View view){
        updateText("1");
    }
    public void twoB(View view){
        updateText("2");
    }
    public void threeB(View view){
        updateText("3");
    }
    public void fourB(View view){
        updateText("4");
    }
    public void fiveB(View view){
        updateText("5");
    }
    public void sixB(View view){
        updateText("6");
    }
    public void sevenB(View view){
        updateText("7");
    }
    public void eightB(View view){
        updateText("8");
    }
    public void nineB(View view){
        updateText("9");
    }
    public void pointB(View view){
        updateText(".");
    }
    public void divB(View view){
        updateText("/");
    }
    public void mulB(View view){
        updateText("*");
    }
    public void plusB(View view){
        updateText("+");
    }
    public void minusB(View view){
        updateText("-");
    }
    public void modB(View view){
        updateText("%");
    }
    public void plusMinusB(View view){updateText(String.format("%s",getString(R.string.plusMinus)));}

    public void parenB(View view){
        int cursorPosition = display.getSelectionStart();
        if (display.getText().toString().substring(0, cursorPosition).equals(getString(R.string.display)) || (String.format("*(/+-%s",getString(R.string.mod)).indexOf(display.getText().toString().charAt(cursorPosition-1))!=-1)){
            parN++;
            updateText("(");
        } else if (parN==0) {if ("123456789.)".indexOf(display.getText().toString().charAt(cursorPosition-1))!=-1){
            parN++;
            updateText("*(",2);

        }

        }else {
            parN--;
            updateText(")");

        }

    }
    public void clearB(View view){
        display.setText("");
    }
    public void equalB(View v){
        Expression e = new Expression(display.getText().toString());
        display.setText(""+e.calculate());
        display.setSelection(display.getText().length());

    }
    public void backspaceB(View view){
        int cursorPosition = display.getSelectionStart();
        int textLen = display.getText().toString().length();
        if (cursorPosition != 0 && textLen != 0){
            String oldStr = display.getText().toString();
            display.setText(String.format("%s%s",oldStr.substring(0,cursorPosition-1),oldStr.substring(cursorPosition)));
            display.setSelection(cursorPosition - 1);
        }

    }

}