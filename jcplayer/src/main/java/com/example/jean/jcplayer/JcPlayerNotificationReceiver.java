package com.example.jean.jcplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.jean.jcplayer.JcPlayerExceptions.AudioListNullPointerException;

public class JcPlayerNotificationReceiver extends BroadcastReceiver {
    public JcPlayerNotificationReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        JcAudioPlayer jcAudioPlayer = JcAudioPlayer.getInstance();
        String action = "";

        if(intent.hasExtra(JcNotificationPlayer.ACTION))
            action = intent.getStringExtra(JcNotificationPlayer.ACTION);

        switch (action){
            case JcNotificationPlayer.PLAY:
                try {
                    if(jcAudioPlayer != null) {
                        jcAudioPlayer.continueAudio();
                        jcAudioPlayer.updateNotification();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case JcNotificationPlayer.PAUSE:
                try {
                    if(jcAudioPlayer != null) {
                        jcAudioPlayer.pauseAudio();
                        jcAudioPlayer.updateNotification();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case JcNotificationPlayer.NEXT:
                try {
                    jcAudioPlayer.nextAudio();
                } catch (AudioListNullPointerException e) {
                    try {
                        if(jcAudioPlayer != null) {
                            jcAudioPlayer.continueAudio();
                        }
                    } catch (AudioListNullPointerException e1) {
                        e1.printStackTrace();
                    }
                }
                break;

            case JcNotificationPlayer.PREVIOUS:
                try {
                    jcAudioPlayer.previousAudio();
                } catch (Exception e) {
                    try {
                        if(jcAudioPlayer != null) {
                            jcAudioPlayer.continueAudio();
                        }
                    } catch (AudioListNullPointerException e1) {
                        e1.printStackTrace();
                    }
                }
                break;
        }
    }
}
