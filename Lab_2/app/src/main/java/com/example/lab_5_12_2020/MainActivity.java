package com.example.lab_5_12_2020;

import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView headerView = (TextView) findViewById(R.id.text_view);
        headerView.setText("Чтобы выполнить действия, откройте меню настроек!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent i = new Intent(this, MediaService.class);
        TextView headerView = (TextView) findViewById(R.id.text_view);
        switch(id){

            case R.id.action_settings :
                startService(i);
                headerView.setText("Воспроизведение начато");
                return true;
            case R.id.save_settings:
                stopService(i);
                headerView.setText("Воспроизведение остановлено");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}