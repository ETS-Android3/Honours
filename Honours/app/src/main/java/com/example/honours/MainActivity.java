package com.example.honours;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
ImageButton startButton;
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
        final String PREFS_NAME = "MyPrefsFile";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something
            Database database = new Database(MainActivity.this);

            Question question1 = new  Question(1,"what is 2+2", 4,3,2,1);
            Question question2 = new  Question(2,"what is 4+5", 9,8,6,10);
            Question question3 = new  Question(2,"what is 632-34",598 ,567,589,601);
            Question question4 = new  Question(1,"what is 2+2", 4,3,2,1);
            Question question5 = new  Question(2,"what is 4+5", 9,8,6,10);

            database.addQuestion(question1);
            database.addQuestion(question2);
            boolean success = database.addQuestion(question3);
            database.addQuestion(question4);
            database.addQuestion(question5);
            Toast.makeText(MainActivity.this, "Success=" + success, Toast.LENGTH_SHORT).show();

          String path = "one";
            SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
            SharedPreferences.Editor editor = sett.edit();
            editor.putString("monster", path);

            editor.apply();


            // first time task

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit();
        }
//        Database database = new Database(MainActivity.this);
//        Question question3 = new Question(2, "what is 632-34", 598, 567, 589, 601);
//        boolean success = database.addQuestion(question3);
     //   Toast.makeText(MainActivity.this, "Success=" + success, Toast.LENGTH_SHORT).show();
    }
    public void play(){
        Intent intent = new Intent(MainActivity.this, Activity2.class);
        startActivity(intent);
    }

}