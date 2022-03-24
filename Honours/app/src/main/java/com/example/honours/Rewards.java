package com.example.honours;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Rewards extends Activity {
    ImageButton myMonster;
    ImageButton monster1;
    ImageButton monster2;
    ImageButton monster3;
    ImageView ivError;
    TextView lblError;
    TextView tvError;
    Button unlock;


    ArrayList<Monster> monsters = new ArrayList<>();
    Boolean isOwn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewards);
        myMonster = findViewById(R.id.myMonster);
        monster1 = findViewById(R.id.monster1);
        monster2 = findViewById(R.id.monster2);
        monster3 = findViewById(R.id.monster3);
        SharedPreferences settings = getApplicationContext().getSharedPreferences("badges", 0);
        String monster = settings.getString("monster", String.valueOf(0));
        System.out.println(monster);
        Resources resources = getResources();
        final int resourceId = resources.getIdentifier(monster,
                "drawable", getPackageName());

        myMonster.setImageResource(resourceId);


        Database database = new Database(Rewards.this);
        monsters = database.getMonsters();
        ArrayList<String> monstids = new ArrayList<>();
        int i;
        for (i = 0; i < monsters.size(); i++) {

            monstids.add(monsters.get(i).getMonsterID());

        }

        for (i = 0; i < monstids.size(); i++) {

            if (monstids.get(i).equals("monster1")) {

                monster1.setImageResource(R.drawable.one);
                System.out.println("U HAVE MONSTER 1");
            } else if (monstids.get(i).equals("monster2")) {
                monster2.setImageResource(R.drawable.two);
                System.out.println("U HAVE MONSTER 2");
            }  else if (monstids.get(i).equals("monster3")) {
            monster3.setImageResource(R.drawable.three);
            System.out.println("U HAVE MONSTER 3");
        }
        }


    }


    public void goHome(View v) {
        Intent intent = new Intent(Rewards.this, Activity2.class);
        startActivity(intent);
    }

    public void chooseMonster(View v) {
        //Getting how many coins player has from shared preferences
        SharedPreferences coins = getApplicationContext().getSharedPreferences("coins", 0);
        int coin = coins.getInt("coins", 0);
        if (v.getId() == R.id.monster1) {

            myMonster.setImageResource(R.drawable.one);

            SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
            SharedPreferences.Editor editor = sett.edit();
            editor.putString("monster", "one");

            editor.apply();
            final MediaPlayer one = MediaPlayer.create(this, R.raw.onesound);
            one.start();


        } else if (v.getId() == R.id.monster2) {





            //Creating mediaplayer to play sound
            final MediaPlayer two = MediaPlayer.create(this, R.raw.twosound);


            isOwn = checkOwnMonster(monsters, "monster2");
System.out.println(isOwn);
if (isOwn == true) {
    SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
    SharedPreferences.Editor editor = sett.edit();
    editor.putString("monster", "two");

    editor.apply();
    myMonster.setImageResource(R.drawable.two);
    two.start();
}else{

    //Displaying alert box
    AlertDialog.Builder builder = new AlertDialog.Builder(Rewards.this);
    View view = getLayoutInflater().inflate(R.layout.alert, null);

    ivError = view.findViewById(R.id.ivError);
    lblError = view.findViewById(R.id.lblError);
    tvError = view.findViewById(R.id.tvError);
    unlock = view.findViewById(R.id.unlock);

    builder.setView(view);
    AlertDialog dialog = builder.create();
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    dialog.show();

    //Creating onclicklistener for alert button confirm
    unlock.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (coin >= 10) {
                Monster billy = new Monster(2, "monster2", "two", "twosound");
                Database database = new Database(Rewards.this);
                database.addMonster(billy);

                myMonster.setImageResource(R.drawable.two);
                monster2.setImageResource(R.drawable.two);
                SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
                SharedPreferences.Editor editor = sett.edit();
                editor.putString("monster", "two");

                editor.apply();

                two.start();
                System.out.println("Unlocked Monster");
                dialog.hide();
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            } else {
                System.out.println("Not enough coins");
            }
        }

    });

}







        }else if (v.getId() == R.id.monster3) {

            //Creating mediaplayer to play sound
            final MediaPlayer three = MediaPlayer.create(this, R.raw.threesound);


            isOwn = checkOwnMonster(monsters, "monster3");
            System.out.println(isOwn);
            if (isOwn == true) {
                SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
                SharedPreferences.Editor editor = sett.edit();
                editor.putString("monster", "three");

                editor.apply();
                myMonster.setImageResource(R.drawable.three);
                three.start();
            }else{

                //Displaying alert box
                AlertDialog.Builder builder = new AlertDialog.Builder(Rewards.this);
                View view = getLayoutInflater().inflate(R.layout.alert, null);

                ivError = view.findViewById(R.id.ivError);
                lblError = view.findViewById(R.id.lblError);
                tvError = view.findViewById(R.id.tvError);
                unlock = view.findViewById(R.id.unlock);


                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                ivError.setImageResource(R.drawable.greythree);

                //Creating onclicklistener for alert button confirm
                unlock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (coin >= 10) {
                            Monster noodle = new Monster(3, "monster3", "three", "threesound");
                            Database database = new Database(Rewards.this);
                            database.addMonster(noodle);

                            myMonster.setImageResource(R.drawable.three);
                            monster3.setImageResource(R.drawable.three);
                            SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
                            SharedPreferences.Editor editor = sett.edit();
                            editor.putString("monster", "three");

                            editor.apply();

                            three.start();
                            System.out.println("Unlocked Monster");
                            dialog.hide();
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
                        } else {
                            System.out.println("Not enough coins");
                        }
                    }

                });




            }



        }

    }

    public boolean checkOwnMonster(ArrayList<Monster> monsters, String id) {
        int i;
        for (i = 0; i < monsters.size(); i++) {

            if (monsters.get(i).getMonsterID().equals(id)) {
                return true;
            }
        }

        return false;
    }

}