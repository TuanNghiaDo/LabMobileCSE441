package com.examples_8.intent_service.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.examples_8.intent_service.R;


public class Myservice extends Service {
    MediaPlayer myMedia;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    //Goi ham onCreate de khoi tao doi tuong ma Service quan li
    public void onCreate() {
        super.onCreate();
        myMedia = MediaPlayer.create(Myservice.this, R.raw.daydiongchauoi);
        myMedia.setLooping(true); //Cho phep lap lai nhac
    }

    @Override
    //Goi ham onStartCommand de khoi chay doi tuong ma Service quan li
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(myMedia.isPlaying()){
            myMedia.pause();
        }else{
            myMedia.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    //Goi ham onDestroy de huy doi tuong ma Service quan li
    public void onDestroy() {
        super.onDestroy();
        if(myMedia != null){
            myMedia.stop();
        }
    }
}
