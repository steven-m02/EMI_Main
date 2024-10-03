package com.example.emi_main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView textView4, textView5, textView6, textView8, textView7,
            textView9;
    EditText inputTextMortgage, inputTextInterest, inputTextAmmortization, inputTextFrequency;
    Button calculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        textView8 = findViewById(R.id.textView8);


        calculate = findViewById(R.id.button);
        inputTextMortgage = findViewById(R.id.inputTextMortgage);
        inputTextInterest = findViewById(R.id.inputTextInterest);
        inputTextFrequency = findViewById(R.id.inputTextFrequency);


        calculate.setOnClickListener(v -> result());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void result() {

        try {
            // Convert strings to double

            // Get text from EditText, convert to String, and then parse to double
            String mortgageString = inputTextMortgage.getText().toString();
            String interestString = inputTextInterest.getText().toString();
            String frequencyString = inputTextFrequency.getText().toString();

            double mortgage = Double.parseDouble(mortgageString);
            double interest = Double.parseDouble(interestString);
            double frequency = Double.parseDouble(frequencyString);

            interest = interest / (12 * 100);
            frequency = frequency * 12;
            double EMI = (mortgage * interest * (float) Math.pow(1 + interest, frequency)) / (float) (Math.pow(1 + interest, frequency) - 1);

            textView8.setText("EMI: " + String.format("%.2f", EMI));

        } catch (NumberFormatException e) {
            // Handle the exception if the input is not a valid number
            textView8.setText("Invalid input. Please enter valid numbers.");
        }
    }






}