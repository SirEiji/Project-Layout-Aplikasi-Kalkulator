package com.example.layoutkalkulator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {
    // id tombol nomor
    private int[] tombolNomor = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};

    //id tombol operator
    private int[] operator = {R.id.tambah, R.id.kurang, R.id.kali, R.id.bagi, R.id.clear, R.id.hasil, R.id.percent, R.id.del, R.id.koma};

    //display output
    private TextView output;

    private boolean lastNumeric;
    private boolean stateError;
    private boolean lastDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //menampilkan TextView
        this.output = (TextView) findViewById(R.id.output);

        //menambahkan fungsi klik pada nomor
        setNumericOnClickListener();

        //menambahkan fungsi klik pada operator
        setOperatorOnCLickListener();
    }

    private void setNumericOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (stateError) {
                    output.setText(button.getText());
                    stateError = false;
                } else {
                    output.append(button.getText());
                }
                lastNumeric = true;
            }
        };
        for (int id : tombolNomor) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorOnCLickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric && !stateError) {
                    Button button = (Button) v;
                    output.append(button.getText());
                    lastNumeric = false;
                    lastDot = false;
                }
            }
        };

        for (int id : operator) {
            findViewById(id).setOnClickListener(listener);
            findViewById(R.id.koma).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastNumeric && !stateError && !lastDot) {
                        output.append(".");
                        lastNumeric = false;
                        lastDot = true;
                    }
                }
            });
            // Clear button
            findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    output.setText("");  // Clear the screen
                    // Reset all the states and flags
                    lastNumeric = false;
                    stateError = false;
                    lastDot = false;
                }
            });
            // DEL button
            findViewById(R.id.del).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    output.setText("");  // Clear the text
                    lastNumeric = true;
                    stateError = false;
                    lastDot = true;
                }
            });
            // Percent button
            findViewById(R.id.percent).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    output.setText("%");  // output
                    lastNumeric = false;
                    stateError = true;
                    lastDot = true;
                }
            });
            // Bagi button
            findViewById(R.id.bagi).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    output.setText("/");  // output
                    lastNumeric = false;
                    stateError = true;
                    lastDot = true;
                }
            });
            // Kali button
            findViewById(R.id.kali).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    output.setText("X");  // output
                    lastNumeric = true;
                    stateError = false;
                    lastDot = false;
                }
            });
            // Kurang button
            findViewById(R.id.kurang).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    output.setText("-");  // output
                    lastNumeric = true;
                    stateError = false;
                    lastDot = false;
                }
            });
            // Tambah button
            findViewById(R.id.tambah).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    output.setText("+");  // output
                    lastNumeric = true;
                    stateError = false;
                    lastDot = false;
                }
            });
        }
    }
}