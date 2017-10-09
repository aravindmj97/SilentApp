package com.iamaravind.silentapp;

import android.app.NotificationManager;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.os.Build.VERSION.SDK;

public class MainActivity extends AppCompatActivity {

    private AudioManager audioManager;
    private boolean sound;
    TextView textView;
    Button normal, silent, vibrate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        normal = (Button)findViewById(R.id.normal);
        silent = (Button)findViewById(R.id.silent);
        vibrate = (Button)findViewById(R.id.vibrate);
        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        getmode();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)&& (!notificationManager.isNotificationPolicyAccessGranted())){
        Intent intent = new Intent(
                android.provider.Settings
                        .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);

        startActivity(intent);}
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  normal();
            }
        });
        silent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                silent();
            }
        });
        vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrate();
            }
        });
    }
    public void getmode()
    {
        textView = (TextView)findViewById(R.id.textView);
        int get;
        get = audioManager.getRingerMode();
        if (get == audioManager.RINGER_MODE_NORMAL)
               textView.setText("Normal");
        else if(get == audioManager.RINGER_MODE_SILENT)
             textView.setText("Silent");
        else if(get == audioManager.RINGER_MODE_VIBRATE)
             textView.setText("Vibrate");
    }
    public void normal()
    {
        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        textView.setText("Normal");
    }
    public void silent()
    {
        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        textView.setText("Silent");
    }
    public void vibrate()
    {
        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        textView.setText("Vibrate");
    }
}
