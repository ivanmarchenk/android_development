package com.example.lab_2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.media.MediaPlayer;
import android.widget.Toast;

public class MediaService extends Service {

    MediaPlayer ambientMediaPlayer;
    private final IBinder binder=new MediaServiceBinder();

    public class MediaServiceBinder extends Binder {
        public MediaService getService(){
            return MediaService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Toast.makeText(this, "Service starting", Toast.LENGTH_SHORT).show();
        ambientMediaPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onCreate(){
        ambientMediaPlayer= MediaPlayer.create(this, R.raw.song);
        ambientMediaPlayer.setLooping(true);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service done", Toast.LENGTH_SHORT).show();
        ambientMediaPlayer.stop();
    }

}
