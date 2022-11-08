package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView result,textview;
    MaterialButton b0,b1,b2,b3,b4,b5,b6,b7,b8,b9;
    MaterialButton div,plus,moin,fois,eguale,del,C,br_right,br_left;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        textview = findViewById(R.id.textview);
        assignId(b1,R.id.b1);
        assignId(b2,R.id.b2);
        assignId(b3,R.id.b3);
        assignId(b4,R.id.b4);
        assignId(b5,R.id.b5);
        assignId(b6,R.id.b6);
        assignId(b7,R.id.b7);
        assignId(b8,R.id.b8);
        assignId(b9,R.id.b9);
        assignId(div,R.id.div);
        assignId(plus,R.id.plus);
        assignId(moin,R.id.moin);
        assignId(fois,R.id.fois);
        assignId(eguale,R.id.eguale);
        assignId(b0,R.id.b0);
        assignId(del,R.id.del);
        assignId(C,R.id.C);
        assignId(br_right,R.id.br_right);
        assignId(br_left,R.id.br_left);


    }

    public void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button=(MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = result.getText().toString();

        if(buttonText.equals("AC")){
            textview.setText("");
            result.setText("0");
            return;
        }

        if(buttonText.equals("=")){
            textview.setText(result.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        result.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);
        if( !finalResult.equals("Erreur")){
            result.setText(finalResult);
        }
    }
    public String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            return finalResult;
        }catch (Exception e){
            return "Erreur";
        }
    }
}