package com.example.gauss_method;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gauss_method.service.GaussService;

public class MainActivity extends AppCompatActivity {

    GaussService gaussService;
    boolean isMainServiceBound = false;
    Button buttonSolve;
    int REQUEST_CODE=1;
    EditText[] editText= new EditText[12];

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            GaussService.GaussServiceBinder binder = (GaussService.GaussServiceBinder) service;
            gaussService = binder.getService();
            isMainServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            isMainServiceBound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, GaussService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText[0]=findViewById(R.id.x11);
        editText[1]=findViewById(R.id.x12);
        editText[2]=findViewById(R.id.x13);
        editText[3]=findViewById(R.id.x21);
        editText[4]=findViewById(R.id.x22);
        editText[5]=findViewById(R.id.x23);
        editText[6]=findViewById(R.id.x31);
        editText[7]=findViewById(R.id.x32);
        editText[8]=findViewById(R.id.x33);
        editText[9]=findViewById(R.id.y1);
        editText[10]=findViewById(R.id.y2);
        editText[11]=findViewById(R.id.y3);

        buttonSolve=findViewById(R.id.buttonSolve);
        buttonSolve.setOnClickListener((View v) -> {

            Intent intentToSecondActivity = new Intent(MainActivity.this, SolveActivity.class);
            String gaussResult = gaussService.init(editText);
            intentToSecondActivity.putExtra("Gauss result", gaussResult);

            startActivityForResult(intentToSecondActivity, REQUEST_CODE);
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
        isMainServiceBound = false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                for (int i = 0; i < 12; i++) {
                    editText[i].setText("");
                }

            }
        }
    }
}