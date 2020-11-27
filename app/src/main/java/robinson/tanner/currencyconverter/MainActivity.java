package robinson.tanner.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spin;
    EditText input;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spin = findViewById(R.id.spinner);
        btn = findViewById(R.id.convertButton);
        input = findViewById(R.id.dollars);

        populateSpinner();

        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //create a money variable to convert
                double money;
                money = Double.parseDouble(input.getText().toString());

                //find selected currency from spinner
                String currency = spin.getSelectedItem().toString();

                double convertedValue = 0;
                String sign = "";

                //convert dollar amount into foreign currency
                if(currency.equals("Euros")) {
                    convertedValue = convertEuros(money);
                    sign = "€";
                }
                else if(currency.equals("Pesos (Mexican)")) {
                    convertedValue = convertPesos(money);
                    sign = "$";
                }
                else if (currency.equals("Pounds")) {
                    convertedValue = convertPound(money);
                    sign = "£";
                }
                else if(currency.equals("Yen")) {
                    convertedValue = convertYen(money);
                    sign = "¥";
                }

                String formatted = String.valueOf(convertedValue);
                String value = String.format("%.2f", convertedValue); //convert money to string for display

                //display resulting currency value and money variable.
                TextView textView = findViewById(R.id.result);
                textView.setText(sign + value);
            }
            private double convertEuros(double value){
                return value * .839491;
            }
            private double convertPesos(double value){
                return value * 20.0430;
            }
            private double convertPound(double value){
                return value * .748999;
            }
            private double convertYen(double value){
                return  value * 104.197;
            }
        });
    }

    private void populateSpinner(){
        ArrayAdapter<String> moneyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.money));
        moneyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(moneyAdapter);
    }
}