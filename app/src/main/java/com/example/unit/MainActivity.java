package com.example.unit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = findViewById(R.id.millilitre_spinner);
        Spinner gramSpinner = (Spinner) findViewById(R.id.gram_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.milliliters_to_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> gramAdapter = ArrayAdapter.createFromResource(this, R.array.grams, android.R.layout.simple_spinner_item);
        gramAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gramSpinner.setAdapter(gramAdapter);

        EditText field1 = findViewById(R.id.from_fluid);
        EditText field2 = findViewById(R.id.from_gram_text);
        TextView text = findViewById(R.id.text);
        Button button = findViewById(R.id.calc_button);
        button.setOnClickListener(corkyListener);
        field1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                runBoth();
            }
        });

        field2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                runBoth();
            }
        });
    }


    public int calculateMill() {
        TextInputEditText amount = findViewById(R.id.from_fluid);
        String amountText = amount.getText().toString();
        SwitchMaterial round = findViewById(R.id.round_up_switch);
        Spinner spinner = findViewById(R.id.millilitre_spinner);
        TextView resultView = findViewById(R.id.fluid_answer);
        double calculatedAmount;
        double roundedAmount;
        String MF = "Millilitres to Fluid ounce";
        System.out.println("This is the output for the amount" + amount.getText());
        if (amountText.isEmpty()) {
            System.out.println("I run here");
            return 0;
        } else {
            System.out.println(spinner.getSelectedItem());
            if (spinner.getSelectedItem() == MF) {
                calculatedAmount = Double.parseDouble(amount.getText().toString()) / 1 * 0.033814;
            } else {
                calculatedAmount = Double.parseDouble(amount.getText().toString()) / 0.033814 * 1;
            }
            if (round.isChecked()) {
                roundedAmount = Math.round(calculatedAmount);
            } else {
                roundedAmount = calculatedAmount;
            }
            resultView.setText("" + roundedAmount);
        }
        return 0;
    }

    public int calculateGram() {
        TextInputEditText amount = findViewById(R.id.from_gram_text);
        String amountText = amount.getText().toString();
        SwitchMaterial round = findViewById(R.id.round_up_switch);
        Spinner spinner = findViewById(R.id.gram_spinner);
        TextView resultView = findViewById(R.id.gram_answer);
        double calculatedAmount;
        double roundedAmount;
        String GC = "From gram to cups";
        if (amountText.isEmpty()) {
            return 0;
        }
        if (round.isChecked()) {
            if (spinner.getSelectedItem() == GC) {
                calculatedAmount = Double.parseDouble(amount.getText().toString()) / 201 * 1;

                roundedAmount = calculatedAmount;

            }else {
                calculatedAmount = Double.parseDouble(amount.getText().toString()) / 1 * 201;
                roundedAmount = Math.round(calculatedAmount);


            }
        } else {
            if (spinner.getSelectedItem() == GC) {
                calculatedAmount = Double.parseDouble(amount.getText().toString()) / 201 * 1;

                roundedAmount = calculatedAmount;

            }else {
                calculatedAmount = Double.parseDouble(amount.getText().toString()) / 1 * 201;
                roundedAmount = Math.round(calculatedAmount);


            }

        }
        resultView.setText("" + roundedAmount);

        return 0;
    }

    public void runBoth() {
        calculateMill();
        calculateGram();
    }

    private View.OnClickListener corkyListener = new View.OnClickListener() {
        public void onClick(View v) {
            runBoth();
        }
    };
}