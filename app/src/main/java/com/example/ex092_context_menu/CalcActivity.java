package com.example.ex092_context_menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalcActivity extends AppCompatActivity {

    TextView tV;
    ListView listV;
    Button back_btn;

    Intent g_intent;

    Double[] arr_items_sidra = new Double[20];
    double dorq_input, a1_input, sum;
    int n = 0;
    boolean isHashbonit;
    String displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        tV = findViewById(R.id.tV);
        listV = findViewById(R.id.listV);
        back_btn = findViewById(R.id.back_btn);

        g_intent = getIntent();

        a1_input = g_intent.getDoubleExtra("a1_input", 0.0);
        dorq_input = g_intent.getDoubleExtra("dorq_input", 0.0);
        isHashbonit = g_intent.getBooleanExtra("isHashbonit", true);

        if (isHashbonit) {
            for (int i = 0; i < 20; i++)
            {
                arr_items_sidra[i] = a1_input + dorq_input * i;
            }
        }
        else { //handasit
            for (int i = 0; i < 20; i++)
            {
                arr_items_sidra[i] = a1_input * Math.pow(dorq_input, i);
            }
        }

        listV.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<Double> adp = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arr_items_sidra);
        listV.setAdapter(adp);

        // Register ListView for context menu
        registerForContextMenu(listV);
    }

    public void back_to_main_activity(View view)
    {
        finish();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.listV) {
            menu.setHeaderTitle("Actions on the series");
            menu.add(0, 1, 0, "selected n");
            menu.add(0, 2, 1, "series sum");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position; // get selected option position (according to string didnt worked :()
        n = position + 1;

        switch (item.getItemId()) {
            case 1: // selected n
                displayText = "Selected n: " + n + "\n";
                tV.setText(displayText);
                return true;

            case 2: // series sum
                if (isHashbonit)
                {
                    sum = (n / 2.0) * (2 * a1_input + (n - 1) * dorq_input);
                }
                else
                {
                    sum = a1_input * (Math.pow(dorq_input, n) - 1) / (dorq_input - 1);
                }

                if (sum > 1000000000)
                {
                    displayText = "Error: too big of a number";
                }
                else
                {
                    displayText = "Sum: " + String.format("%.5f", sum) + "\n";
                }
                tV.setText(displayText);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}
