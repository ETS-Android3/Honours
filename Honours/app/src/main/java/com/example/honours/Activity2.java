package com.example.honours;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class Activity2 extends Activity {
    Button addition;
    ImageButton rewardBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        addition = findViewById(R.id.addition);
        rewardBtn = findViewById(R.id.rewardBtn);

        //Listener for selecting adding/subtracting game
        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAddition();
            }
        });


        //Accessing the monster badge the user has currently selected from shared preferences
        SharedPreferences settings = getApplicationContext().getSharedPreferences("badges", 0);
        String monster = settings.getString("monster", String.valueOf(0));

        //Setting the imageview to the monster user has selected which is stored in shared preferences
        Resources resources = getResources();
        final int resourceId = resources.getIdentifier(monster,
                "drawable", getPackageName());

        rewardBtn.setImageResource(resourceId);

        rewardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rewardsmenu();
            }
        });


    }


    public void rewardsmenu() {
        Intent intent = new Intent(Activity2.this, Rewards.class);
        startActivity(intent);
    }

    public void playAddition() {
        Intent intent = new Intent(Activity2.this, Addition.class);
        startActivity(intent);
    }
}