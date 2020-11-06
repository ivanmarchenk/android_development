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

    public static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView temp = (TextView) findViewById(R.id.view_value);
        final EditText a = (EditText) findViewById(R.id.enter_value);
        Button button = (Button) findViewById(R.id.open_form_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = "";

                if (a.getText().toString().equals(""))
                    { value = "0"; }
                else
                    { value = a.getText().toString(); }

                int pushValue = Integer.parseInt(temp.getText().toString()) + Integer.parseInt(value);

                Intent intentToSecondActivity = new Intent(MainActivity.this, SecondActivity.class);
                intentToSecondActivity.putExtra("message", Integer.toString(pushValue));
                MainActivity.this.startActivityForResult(intentToSecondActivity, REQUEST_CODE);
                a.setText("");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                TextView temp = (TextView) findViewById(R.id.view_value);
                temp.setText(data.getStringExtra("result"));
            }
        }

    }
}