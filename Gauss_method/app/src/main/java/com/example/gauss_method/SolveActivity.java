package com.example.gauss_method;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SolveActivity extends AppCompatActivity {

    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve);
        textViewResult=findViewById(R.id.textViewResult);
        textViewResult.setText(getIntent().getStringExtra("Gauss result"));
        Button button = findViewById(R.id.remove);
        button.setOnClickListener(openMainActivityForm);
    }

    View.OnClickListener openMainActivityForm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    };
}