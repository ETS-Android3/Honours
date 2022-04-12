package com.example.honours;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class Activity2 extends Activity {
    Button addition,multiplyButt;
    ImageButton rewardBtn,returnHome;
    TextView coinAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        multiplyButt = findViewById(R.id.multiplyButt);
        addition = findViewById(R.id.addition);
        rewardBtn = findViewById(R.id.rewardBtn);
        coinAmount = findViewById(R.id.coinAmount);

        returnHome = findViewById(R.id.backToStart);




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

        SharedPreferences coins = getApplicationContext().getSharedPreferences("coins", 0);
        int coin = coins.getInt("coins", 0);

        coinAmount.setText(String.valueOf(coin));

    }
    public void returntoMain(View v) {
        Intent intent = new Intent(Activity2.this, MainActivity.class);
        startActivity(intent);
    }

    public void rewardsmenu() {

        Intent intent = new Intent(Activity2.this, Rewards.class);

        startActivity(intent);
    }
    public void playMultiplication(View v) {

        String multiply = "multiply";
        Intent intent = new Intent(Activity2.this, Addition.class);
        intent.putExtra("category", multiply);
        startActivity(intent);
    }

    public void playAddition() {

String addition = "addition";
        Intent intent = new Intent(Activity2.this, Addition.class);
        intent.putExtra("category", addition);
           startActivity(intent);
    }
}