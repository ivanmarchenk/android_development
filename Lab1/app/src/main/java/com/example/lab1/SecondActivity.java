package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);

        Button button2 = (Button) findViewById(R.id.button_open_form_1);
        TextView viewValue = (TextView) findViewById(R.id.textView2);


        final Intent intent = getIntent();
        viewValue.setText(intent.getStringExtra("OK"));

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText enterValue2 = (EditText) findViewById(R.id.editTextNumber2);
                int i = Integer.parseInt(intent.getStringExtra("OK"));
                int b = Integer.parseInt(enterValue2.getText().toString());
                int c=i+b;
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                intent.putExtra("OK", Integer.toString(c));
                startActivity(intent);
            }
        });



    }
}