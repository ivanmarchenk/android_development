package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText enterValue = (EditText) findViewById(R.id.editTextNumber);
        final TextView viewValue = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button_open_form_2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText enterValue = (EditText) findViewById(R.id.editTextNumber);
                String a = enterValue.getText().toString();

                EditText enterValue2 = (EditText) findViewById(R.id.editTextNumber2);
                int i = Integer.parseInt(a);
                int b = Integer.parseInt(viewValue.getText().toString());
                int c=i+b;

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("OK", Integer.toString(c));
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        if(intent.getStringExtra("OK") != null){
            viewValue.setText(intent.getStringExtra("OK"));
        }

    }

}