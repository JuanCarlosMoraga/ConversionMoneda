package com.moraga.conversionmoneda;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextAmount, editTextCurrency;
    private Spinner spinnerCurrency;
    private Button buttonConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAmount = findViewById(R.id.editTextAmount);
        editTextCurrency = findViewById(R.id.editTextCurrency);
        spinnerCurrency = findViewById(R.id.spinnerCurrency);
        buttonConvert = findViewById(R.id.buttonConvert);

        // Carga las monedas disponibles en el Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currencies, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurrency.setAdapter(adapter);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        // Obtén la cantidad ingresada por el usuario
        String amountStr = editTextAmount.getText().toString();
        if (amountStr.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter amount", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);

        // Obtén la moneda de origen y de destino
        String sourceCurrency = editTextCurrency.getText().toString();
        String targetCurrency = spinnerCurrency.getSelectedItem().toString();

        // Calcula la cantidad convertida (tasa de cambio fija para este ejemplo)
        double conversionRate = 0.85; // 1 USD = 0.85 EUR
        double convertedAmount = amount * conversionRate;

        // Muestra la cantidad convertida al usuario
        String result = String.format("%.2f %s = %.2f %s", amount, sourceCurrency, convertedAmount, targetCurrency);
        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
    }
}