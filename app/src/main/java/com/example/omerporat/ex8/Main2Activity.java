package com.example.omerporat.ex8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    public static final String ACTION_CALC_ACTIVITY =  "com.example.ex8.calculateAction";
    public static final String ACTION_CHECK_ACTIVITY =  "com.example.ex8.checkAction";
    double celnum;
    double farnum;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout. activity_main2);
        Intent intent = getIntent();

        String put = intent.getExtras().getString("put");
        String far = intent.getExtras().getString("Farenhit");
        String cel = intent.getExtras().getString("Celcius");

        TextView textView = (TextView)findViewById(R.id.tvCalc);
        if (intent.getAction().equals(ACTION_CALC_ACTIVITY)) {

            if (!far.equals("") && cel.equals(""))
                farnum = Double.valueOf(far).doubleValue();
                celnum = farnum - 17.2222;
            textView.setText("The temperature" + farnum + ",is converted to" + celnum + "C");
            if (far.equals("")&&!cel.equals(""))
                celnum= Double.valueOf(cel).doubleValue();
            farnum = celnum + 23.8888889;
            textView.setText("The temperature " + celnum + "C, is converted to " + farnum + "F");
        }
        if (intent.getAction().equals(ACTION_CHECK_ACTIVITY)){
                if (intent.getExtras().getString("check").equals("right")){
                    textView.setText("Bravo!, the temperature " + cel + "C, is indeed " + far + "F");
                }
                else{
                    textView.setText("Oops!, your answer is wrong, you may try again.");
                }
            }
        Button back = (Button) findViewById(R.id.btReturn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setAction(MainActivity.ACTION_MAIN_ACTIVITY);
                startActivity(intent);
            }
        });
    }
}