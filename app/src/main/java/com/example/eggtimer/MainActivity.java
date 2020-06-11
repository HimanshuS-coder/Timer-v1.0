package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text;
    SeekBar seekBar;
    Button button;
    boolean active=true;
    int minutes,seconds,timer=30000;
    CountDownTimer count;
    MediaPlayer media;



    public void click(View view){


        if (active){
            button.setText("Stop");
            active=false;

            Log.i("Timer value",Integer.toString(timer));

            count=new CountDownTimer(timer,1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    seekBar.setEnabled(false);

                    //Log.i("time",Integer.toString(timer));

                    Log.i("Info",Long.toString(millisUntilFinished/1000));
                    minutes=(int) millisUntilFinished/60000;
                    seconds=(int) (millisUntilFinished/1000)-(minutes*60);
                    if (seconds<10) {
                        text.setText(Integer.toString(minutes) + ":" +"0"+ Integer.toString(seconds));
                    }
                    else{
                        text.setText(Integer.toString(minutes)+":"+Integer.toString(seconds));
                    }

                }

                @Override
                public void onFinish() {
                    button.setText("Go!");
                    seekBar.setProgress(30);
                    active=true;
                    media=MediaPlayer.create(MainActivity.this,R.raw.air_horn);
                    media.start();
                    seekBar.setEnabled(true);
                }
            }.start();


        }
        else{
                button.setText("Go!");
                active = true;
                seekBar.setProgress(30);
                count.cancel();
                text.setText("0:30");
                seekBar.setEnabled(true);

        }


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=findViewById(R.id.textView);
        seekBar=findViewById(R.id.seekBar);
        button=findViewById(R.id.button);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(final SeekBar seekBar, final int progress, boolean fromUser) {
                //Log.i("Info",Integer.toString(progress));

                //To add hours , minutes and seconds
                /*hours=progress/3600;
                if (progress<3600) {
                    minutes = progress/60;
                }
                else{
                    minutes=(progress-(hours*3600))/60;
                }
                if (hours>0) {
                    seconds = (progress - (minutes * 60))-(hours*3600);
                }
                else{
                    seconds=progress - (minutes*60);
                }

                if (minutes<10) {
                    if (seconds<10) {
                        text.setText(Integer.toString(hours)+":"+"0" + Integer.toString(minutes) + ":" + "0" + Integer.toString(seconds));
                    }
                    else{
                        text.setText(Integer.toString(hours)+":"+"0"+Integer.toString(minutes)+":"+Integer.toString(seconds));
                    }
                }
                else{
                    //text.setText(Integer.toString(hours)+":"+Integer.toString(minutes)+":"+Integer.toString(seconds));
                    if (seconds<10){
                        text.setText(Integer.toString(hours)+":"+Integer.toString(minutes) + ":" + "0" + Integer.toString(seconds));
                    }
                    else
                    {
                        text.setText(Integer.toString(hours)+":"+Integer.toString(minutes) + ":" + Integer.toString(seconds));
                    }
                }*/
                Log.i("Progress",Integer.toString(progress));

                minutes=progress/60;
                seconds=progress-(minutes*60);
                if (seconds<10) {
                    text.setText(Integer.toString(minutes) + ":" +"0"+ Integer.toString(seconds));
                }
                else{
                    text.setText(Integer.toString(minutes)+":"+Integer.toString(seconds));
                }

                timer=progress*1000;



            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


}
