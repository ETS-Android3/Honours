package com.example.honours;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

public class MainActivity extends Activity {
ImageButton startButton;
    Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Question question;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.playButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });

        final MediaPlayer loading = MediaPlayer.create(this, R.raw.loadingsound);
        loading.start();
        mHandler = new Handler();
        runProgress();
    }


    public void runProgress() {

        Thread athread = new Thread(new Runnable() {
            @Override
            public void run() {
initialiseData();

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {


                    }

                });

            }
        });
        athread.start();
    }
    public void initialiseData() {
        final String PREFS_NAME = "MyPrefsFile";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something
            Database database = new Database(MainActivity.this);

            Question question1 = new Question(1, "200 + 100", 300, 200, 400, 350);
            Question question2 = new Question(2, "400 + 56", 456, 454, 466, 450);
            Question question3 = new Question(3, "632 - 34", 598, 567, 589, 601);
            Question question4 = new Question(4, "242 - 64", 178, 198, 180, 202);
            Question question5 = new Question(5, "30 + 240", 270, 260, 300, 255);
            Question question6 = new Question(6, "60-40", 20, 10, 30, 25);
            Question question7 = new Question(7, "119 + 118", 237, 240, 236, 232);
            Question question8 = new Question(8, "32 + 64", 96, 100, 98, 90);
            Question question9 = new Question(9, "16 + 220", 236, 240, 238, 232);
            Question question10 = new Question(10, "110 - 40", 70, 80, 60, 90);
            Question question11 = new Question(11, "20 + 200", 220, 240, 300, 250);
            Question question12 = new Question(12, "369 - 18", 351, 350, 348, 360);


            database.addQuestion(question1);
            database.addQuestion(question2);
            boolean success = database.addQuestion(question3);
            database.addQuestion(question4);
            database.addQuestion(question5);

            database.addQuestion(question6);

            database.addQuestion(question7);

            database.addQuestion(question8);

            database.addQuestion(question9);

            database.addQuestion(question10);
            database.addQuestion(question11);
            database.addQuestion(question12);
            String path = "one";
            SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
            SharedPreferences.Editor editor = sett.edit();
            editor.putString("monster", path);

            editor.apply();
            Monster newmonster = new Monster(1, "monster1", "one", "onesound");
            database.addMonster(newmonster);
            // first time task


            SharedPreferences coins = getApplicationContext().getSharedPreferences("coins", 0);
            SharedPreferences.Editor edd = coins.edit();
            edd.putInt("coins", 10);

            edd.apply();


            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit();
        }
    }
    public void play(){
        Intent intent = new Intent(MainActivity.this, Activity2.class);
        startActivity(intent);
    }

}