package com.example.honours;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Rewards extends Activity {
    ImageButton myMonster;
    ImageButton monster1;
    ImageButton monster2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewards);
        myMonster = findViewById(R.id.myMonster);
        monster1 = findViewById(R.id.monster1);
        monster2 = findViewById(R.id.monster2);

        SharedPreferences settings = getApplicationContext().getSharedPreferences("badges", 0);
        String monster = settings.getString("monster", String.valueOf(0));
        System.out.println(monster);
        Resources resources = getResources();
        final int resourceId = resources.getIdentifier(monster,
                "drawable", getPackageName());

        myMonster.setImageResource(resourceId);


    }

    public void goHome(View v){
        Intent intent = new Intent(Rewards.this, Activity2.class);
        startActivity(intent);
    }

public void chooseMonster(View v){
    if (v.getId() == R.id.monster1) {

        myMonster.setImageResource(R.drawable.one);

        SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
        SharedPreferences.Editor editor = sett.edit();
        editor.putString("monster", "one");

        editor.apply();
        final MediaPlayer one = MediaPlayer.create(this, R.raw.onesound);
        one.start();
        } else if (v.getId() == R.id.monster2) {
        System.out.println("monster 2 selected");
        myMonster.setImageResource(R.drawable.two);

        SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
        SharedPreferences.Editor editor = sett.edit();
        editor.putString("monster", "two");

        editor.apply();
        final MediaPlayer two = MediaPlayer.create(this, R.raw.twosound);
        two.start();
    }

}


}