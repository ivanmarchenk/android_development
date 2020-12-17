package com.example.lab_2;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.view.MenuInflater;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MediaService mediaService;
    boolean isMainServiceBound = false;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            MediaService.MediaServiceBinder binder = (MediaService.MediaServiceBinder) service;
            mediaService = binder.getService();
            isMainServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mediaService = null;
            isMainServiceBound = false;
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView headerView = (TextView) findViewById(R.id.text_view);
        headerView.setText("Чтобы выполнить действия, откройте меню настроек!");

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Intent intent = new Intent(this, MediaService.class);
        //bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
        isMainServiceBound = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
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