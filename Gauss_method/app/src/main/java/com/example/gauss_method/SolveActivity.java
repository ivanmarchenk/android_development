package com.example.gauss_method;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SolveActivity extends AppCompatActivity {

    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve);
        textViewResult=findViewById(R.id.textViewResult);
        textViewResult.setText(getIntent().getStringExtra("Gauss result"));
    }

}