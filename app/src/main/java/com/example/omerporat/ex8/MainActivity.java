package com.example.omerporat.ex8;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends Activity implements TextWatcher{

    public static final String ACTION_MAIN_ACTIVITY= "com.Ex8.MainActivity";
    private final int CALC_ACTIVITY_REQUEST = 1;
    RadioButton check, calculate;
    RadioGroup rg;
    EditText far, cel;
    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        far = (EditText)findViewById(R.id.etFar);

        rg = (RadioGroup)findViewById(R.id.radioGroup);
        far.addTextChangedListener(this);
        cel = (EditText)findViewById(R.id.etCel);
        cel.addTextChangedListener(this);
        go = (Button)findViewById(R.id.bGo);

        check = (RadioButton) findViewById(R.id.rbCheck);
        check.setOnClickListener(new checkListener());

        calculate = (RadioButton) findViewById(R.id.rbCalculate);
        calculate.setOnClickListener(new calculateListener());


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        check = (RadioButton) findViewById(R.id.rbCheck);
        calculate = (RadioButton) findViewById(R.id.rbCalculate);
        far = (EditText) findViewById(R.id.etFar);
        cel = (EditText) findViewById(R.id.etCel);
        if(check.isChecked()){
            findViewById(R.id.bGo).setEnabled(!(far.getText().toString().isEmpty()) && !(cel.getText().toString().isEmpty()));
        }
        if(calculate.isChecked()){
            findViewById(R.id.bGo).setEnabled(!(far.getText().toString().isEmpty()) || !(cel.getText().toString().isEmpty()));
        }
    }

    private class checkListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            cel.setEnabled(true);
            far.setEnabled(true);

            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                    if (rg.getCheckedRadioButtonId() == R.id.rbCalculate)
                        intent.setAction(Main2Activity.ACTION_CALC_ACTIVITY);
                    else
                        intent.setAction(Main2Activity.ACTION_CHECK_ACTIVITY);
                    intent.putExtra("Farenhit", far.getText().toString());
                    intent.putExtra("Celcius", cel.getText().toString());
                    startActivity(intent);


                }
            });
        }
    }

    private class calculateListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            cel.setEnabled(true);
            far.setEnabled(true);

            go.setEnabled(true);
            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                        intent.setAction(Main2Activity.ACTION_CALC_ACTIVITY);
                        if(!(far.getText().toString().isEmpty())){
                            intent.putExtra("put", "far");
                            intent.putExtra("Farenhit", far.getText().toString());
                            double celcu = (Double.parseDouble(far.getText().toString())-32)*(5/9);
                            intent.putExtra("Celcius", celcu);
                        }
                        else{
                            intent.putExtra("put", "cel");
                            intent.putExtra("Celcius", cel.getText().toString());
                            double fare = Double.parseDouble(cel.getText().toString())*(9/5)+32;
                            intent.putExtra("Farenhit", fare);
                        }
                        startActivity(intent);
                }
            });
        }
    }
}