package ru.mirea.donetskaya.looper;

import android.annotation.SuppressLint;
import android.os.Looper;
import android.util.Log;
import android.os.Message;
import android.os.Handler;

public class MyLooper extends Thread {
    private int number = 0;
    Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    public void run(){
        Log.d("MyLooper", "run");
        Looper.prepare();
        handler = new Handler(){ @Override
        public void handleMessage(Message msg){
            Log.d("MyLooper", number + ":"+ msg.getData().getString("KEY"));
            number++;
        }
        };
        Looper.loop();
    }
}

