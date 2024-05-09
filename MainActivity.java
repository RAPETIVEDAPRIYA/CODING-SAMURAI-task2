package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView result,solution;
    MaterialButton clear,open_bracket,close_bracket;
    MaterialButton division,multiply,add,subtract,equals;
    MaterialButton one,two,three,four,seven,eight,nine,zero,five,six;
    MaterialButton all_clear,dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        result=findViewById(R.id.result);
        solution=findViewById(R.id.solution);
        assignId(clear,R.id.clear);
        assignId(open_bracket,R.id.open_bracket);
        assignId(close_bracket,R.id.close_bracket);
        assignId(division,R.id.division);
        assignId(multiply,R.id.multiply);
        assignId(add,R.id.add);
        assignId(subtract,R.id.subtract);
        assignId(equals,R.id.equals);
        assignId(one,R.id.one);
        assignId(two,R.id.two);
        assignId(three,R.id.three);
        assignId(four,R.id.four);
        assignId(five,R.id.five);
        assignId(six,R.id.six);
        assignId(seven,R.id.seven);
        assignId(eight,R.id.eight);
        assignId(nine,R.id.nine);
        assignId(zero,R.id.zero);
        assignId(all_clear,R.id.all_clear);
        assignId(dot,R.id.dot);
    }

    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution.getText().toString();
        if (buttonText.equals("AC")) {
            solution.setText("");
            result.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            solution.setText(result.getText());
            return;
        }
        if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else
            dataToCalculate = dataToCalculate + buttonText;
        solution.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if(!finalResult.equals("Err")){
            result.setText(finalResult);
        }
    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
           if(finalResult.endsWith(".0"))
               finalResult = finalResult.replace(".0","");
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}