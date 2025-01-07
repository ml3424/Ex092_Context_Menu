package com.example.ex092_context_menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Switch sw1;
    EditText eTa1, eTdq;
    Button next_btn;
    TextView tVdorq;

    Intent f_intent;

    boolean isHashbonit = true;

    String S_a1_input = "";
    String S_dorq_input = "";
    double a1_input = 0;
    double dorq_input = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sw1 = findViewById(R.id.sw1);
        eTa1 = findViewById(R.id.eTa1);
        eTdq = findViewById(R.id.eTdq);
        next_btn = findViewById(R.id.next_btn);
        tVdorq = findViewById(R.id.tVdorq);


    }

    public void swClick(View view) {
        if(sw1.isChecked())
        {
            isHashbonit = false;
            tVdorq.setText("q:");
        }
        else
        {
            isHashbonit = true;
            tVdorq.setText("d:");
        }
    }

    public void clicked_calc(View view)
    {
        S_a1_input = eTa1.getText().toString();
        S_dorq_input = eTdq.getText().toString();

        if(!isValidInput(S_a1_input))
        {
            Toast t = Toast.makeText(this, "Invalid a1!", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        else if(!isValidInput(S_dorq_input))
        {
            Toast t = Toast.makeText(this, "Invalid d/q!", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        a1_input = Double.valueOf(S_a1_input);;
        dorq_input = Double.valueOf(S_dorq_input);;

        f_intent = new Intent(MainActivity.this, CalcActivity.class);
        f_intent.putExtra("a1_input", a1_input);
        f_intent.putExtra("dorq_input", dorq_input);
        f_intent.putExtra("isHashbonit", isHashbonit);
        startActivity(f_intent);

    }

    public boolean isValidInput(String input)
    {
        if (input.isEmpty())
        {
            return false;
        }
        // check if input is a single character and is not a digit
        else if (input.length() == 1 && !Character.isDigit(input.charAt(0)))
        {
            return false;
        }
        return true;
    }
}