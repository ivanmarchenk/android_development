package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
        setContentView(R.layout.activity_main);

        Intent intentFromMainActivity = getIntent();

        final TextView temp = (TextView) findViewById(R.id.view_value);
        temp.setText(intentFromMainActivity.getStringExtra("message"));
        Button button = (Button) findViewById(R.id.open_form_button);

        button.setOnClickListener((View.OnClickListener) new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText a = (EditText) SecondActivity.this.findViewById(R.id.enter_value);
                String value = "";

                if (a.getText().toString().equals(""))
                    { value = "0"; }
                else
                    { value = a.getText().toString(); }

                int popValue = Integer.parseInt(temp.getText().toString()) + Integer.parseInt(value);

                Intent intentToMainActivity = new Intent(SecondActivity.this, MainActivity.class);
                intentToMainActivity.putExtra("result", Integer.toString(popValue));
                SecondActivity.this.setResult(Activity.RESULT_OK, intentToMainActivity);
                SecondActivity.this.finish();
            }
        });

    }
}