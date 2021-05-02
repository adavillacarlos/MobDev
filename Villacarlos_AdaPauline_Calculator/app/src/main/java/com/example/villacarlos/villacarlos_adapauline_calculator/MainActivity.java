package com.example.villacarlos.villacarlos_adapauline_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button[] buttons = new Button[20];
    EditText et;
    View.OnClickListener ocl;
    float num1, num2;
    char op;
    private static final int[] BUTTON_IDS = {
            R.id.button0,
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7,
            R.id.button8,
            R.id.button9,
            R.id.buttonClear,
            R.id.buttonDivide,
            R.id.buttonMultiply,
            R.id.buttonSubtract,
            R.id.buttonPlus,
            R.id.buttonPercent,
            R.id.buttonEquals,
            R.id.buttonBack,
            R.id.buttonPeriod,
            R.id.buttonPower,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v.getId()==R.id.button0) et.setText(et.getText().toString()+"0"); //0
                if(v.getId()==R.id.button1) et.setText(et.getText().toString()+"1"); //1
                if(v.getId()==R.id.button2) et.setText(et.getText().toString()+"2"); //2
                if(v.getId()==R.id.button3) et.setText(et.getText().toString()+"3"); //3
                if(v.getId()==R.id.button4) et.setText(et.getText().toString()+"4"); //4
                if(v.getId()==R.id.button5) et.setText(et.getText().toString()+"5"); //5
                if(v.getId()==R.id.button6) et.setText(et.getText().toString()+"6"); //6
                if(v.getId()==R.id.button7) et.setText(et.getText().toString()+"7"); //7
                if(v.getId()==R.id.button8) et.setText(et.getText().toString()+"8"); //8
                if(v.getId()==R.id.button9) et.setText(et.getText().toString()+"9"); //9

                if(v.getId()==R.id.buttonPlus){ //Plus
                    num1 = Float.parseFloat(et.getText().toString());
                    et.setText("");
                    op = '+';
                }

                if(v.getId()==R.id.buttonSubtract){ //Minus
                    num1 = Float.parseFloat(et.getText().toString());
                    et.setText("");
                    op = '-';
                }

                if(v.getId()==R.id.buttonMultiply){ //Multiply
                    num1 = Float.parseFloat(et.getText().toString());
                    et.setText("");
                    op = '*';
                }

                if(v.getId()==R.id.buttonDivide){ //Divide
                    num1 = Float.parseFloat(et.getText().toString());
                    et.setText("");
                    op = '/';
                }

                if(v.getId()==R.id.buttonPercent){ //Modulo
                    num1 = Float.parseFloat(et.getText().toString());
                    et.setText("");
                    op = '%';
                }

                if(v.getId()==R.id.buttonClear){ //Clear
                    et.setText("");
                    num1=num2=0;
                }

                if(v.getId()==R.id.buttonBack){ //Backspace
                   String str = et.getText().toString();
                   if(str.length() >= 1){
                       str = str.substring(0,str.length()-1);
                       et.setText(str);
                   } else if (str.length() <=1){
                       et.setText("0");
                   }
                }

                if(v.getId() == R.id.buttonPeriod){
                    et.setText(et.getText()+".");
                }

                if(v.getId() == R.id.buttonPower){
                    num1 = Float.parseFloat(et.getText().toString());
                    et.setText("");
                    op = '^';
                }

                if(v.getId()==R.id.buttonEquals){
                    num2 = Float.parseFloat(et.getText().toString());
                    switch (op){
                        case '+': num1+=num2;
                            break;
                        case '-': num1-=num2;
                            break;
                        case '*': num1*=num2;
                            break;
                        case '/': num1/=num2;
                            break;
                        case '%': num1%=num2;
                            break;
                        case '^': num1= (float) Math.pow(num1,num2);
                            break;
                    }
                    et.setText(""+num1);
                }
            }
        };

        int i=0;
        num1=num2=0.0f;
        for(int id : BUTTON_IDS){
            buttons[i] = (Button)findViewById(id);
            buttons[i].setOnClickListener(ocl);
            i++;
        }
        et = (EditText)findViewById(R.id.eTResult);

    }
}