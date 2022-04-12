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
import android.widget.Toast;

import java.util.ArrayList;

public class Rewards extends Activity {
    ImageButton myMonster, monster1, monster2, monster3, monster4, monster5, monster6, monster7, monster8, monster9;

    ImageView ivError;
    TextView lblError, tvError, coinCount;

    Button unlock;
    Boolean isOwn;
    int coin;

    ArrayList<Monster> monsters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewards);
        myMonster = findViewById(R.id.myMonster);
        monster1 = findViewById(R.id.monster1);
        monster2 = findViewById(R.id.monster2);
        monster3 = findViewById(R.id.monster3);
        monster4 = findViewById(R.id.monster4);
        monster5 = findViewById(R.id.monster5);
        monster6 = findViewById(R.id.monster6);
        monster7 = findViewById(R.id.monster7);
        monster8 = findViewById(R.id.monster8);
        monster9 = findViewById(R.id.monster9);

        coinCount = findViewById(R.id.coinCount);

        SharedPreferences settings = getApplicationContext().getSharedPreferences("badges", 0);
        String monster = settings.getString("monster", String.valueOf(0));
        System.out.println(monster);
        Resources resources = getResources();
        final int resourceId = resources.getIdentifier(monster,
                "drawable", getPackageName());

        myMonster.setImageResource(resourceId);


        SharedPreferences coins = getApplicationContext().getSharedPreferences("coins", 0);
        coin = coins.getInt("coins", 0);

        coinCount.setText(String.valueOf(coin));

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

            } else if (monstids.get(i).equals("monster2")) {
                monster2.setImageResource(R.drawable.two);

            } else if (monstids.get(i).equals("monster3")) {
                monster3.setImageResource(R.drawable.three);

            } else if (monstids.get(i).equals("monster4")) {
                monster4.setImageResource(R.drawable.four);

            } else if (monstids.get(i).equals("monster5")) {
                monster5.setImageResource(R.drawable.five);

            } else if (monstids.get(i).equals("monster6")) {
                monster6.setImageResource(R.drawable.six);

            } else if (monstids.get(i).equals("monster7")) {
                monster7.setImageResource(R.drawable.seven);

            } else if (monstids.get(i).equals("monster8")) {
                monster8.setImageResource(R.drawable.eight);

            } else if (monstids.get(i).equals("monster9")) {
                monster9.setImageResource(R.drawable.nine);

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

        //
        // CHOOSE MONSTER 1
        //


        if (v.getId() == R.id.monster1) {

            myMonster.setImageResource(R.drawable.one);

            SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
            SharedPreferences.Editor editor = sett.edit();
            editor.putString("monster", "one");

            editor.apply();
            final MediaPlayer one = MediaPlayer.create(this, R.raw.onesound);
            one.start();


            //
            // CHOOSE MONSTER 2
            //

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
            } else {

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
                            int cost = 10;
                            Monster billy = new Monster(2, "monster2", "two", "twosound");
                            Database database = new Database(Rewards.this);
                            database.addMonster(billy);

                            myMonster.setImageResource(R.drawable.two);
                            monster2.setImageResource(R.drawable.two);
                            SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
                            SharedPreferences.Editor editor = sett.edit();
                            editor.putString("monster", "two");

                            editor.apply();
                            setCoinAmount(cost);
                            two.start();
                            System.out.println("Unlocked Monster");
                            dialog.dismiss();
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
                        } else {
                            Toast.makeText(Rewards.this, "Not enough coins!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                });

            }

            //
            // CHOOSE MONSTER 3
            //


        } else if (v.getId() == R.id.monster3) {

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
            } else {

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
                            int cost = 10;
                            Monster noodle = new Monster(3, "monster3", "three", "threesound");
                            Database database = new Database(Rewards.this);
                            database.addMonster(noodle);

                            myMonster.setImageResource(R.drawable.three);
                            monster3.setImageResource(R.drawable.three);
                            SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
                            SharedPreferences.Editor editor = sett.edit();
                            editor.putString("monster", "three");

                            editor.apply();
                            setCoinAmount(cost);
                            three.start();
                            System.out.println("Unlocked Monster");
                            dialog.dismiss();
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
                        } else {
                            Toast.makeText(Rewards.this, "Not enough coins!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                });


            }

            //
            // CHOOSE MONSTER 4
            //

        } else if (v.getId() == R.id.monster4) {

            //Creating mediaplayer to play sound
            final MediaPlayer four = MediaPlayer.create(this, R.raw.foursound);


            isOwn = checkOwnMonster(monsters, "monster4");
            System.out.println(isOwn);
            if (isOwn == true) {
                SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
                SharedPreferences.Editor editor = sett.edit();
                editor.putString("monster", "four");

                editor.apply();
                myMonster.setImageResource(R.drawable.four);
                four.start();
            } else {

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
                ivError.setImageResource(R.drawable.greyfour);
                int cost = 15;
                lblError.setText(String.valueOf(cost));
                //Creating onclicklistener for alert button confirm
                unlock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (coin >= 15) {

                            Monster zabie = new Monster(4, "monster4", "four", "foursound");
                            Database database = new Database(Rewards.this);
                            database.addMonster(zabie);

                            myMonster.setImageResource(R.drawable.four);
                            monster4.setImageResource(R.drawable.four);

                            SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
                            SharedPreferences.Editor editor = sett.edit();
                            editor.putString("monster", "four");

                            editor.apply();
                            setCoinAmount(cost);
                            four.start();
                            System.out.println("Unlocked Monster");
                            dialog.dismiss();
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
                        } else {
                            Toast.makeText(Rewards.this, "Not enough coins!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                });


            }


            //
            // CHOOSE MONSTER 5
            //

        } else if (v.getId() == R.id.monster5) {

            //Creating mediaplayer to play sound
            final MediaPlayer five = MediaPlayer.create(this, R.raw.fivesound);


            isOwn = checkOwnMonster(monsters, "monster5");
            System.out.println(isOwn);
            if (isOwn == true) {
                SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
                SharedPreferences.Editor editor = sett.edit();
                editor.putString("monster", "five");

                editor.apply();
                myMonster.setImageResource(R.drawable.five);
                five.start();
            } else {

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
                ivError.setImageResource(R.drawable.greyfive);
                int cost = 15;
                lblError.setText(String.valueOf(cost));
                //Creating onclicklistener for alert button confirm
                unlock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (coin >= 15) {
                            Monster sluggy = new Monster(5, "monster5", "five", "fivesound");
                            Database database = new Database(Rewards.this);
                            database.addMonster(sluggy);

                            myMonster.setImageResource(R.drawable.five);
                            monster5.setImageResource(R.drawable.five);
                            SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
                            SharedPreferences.Editor editor = sett.edit();
                            editor.putString("monster", "five");

                            editor.apply();
                            setCoinAmount(cost);
                            five.start();
                            System.out.println("Unlocked Monster");
                            dialog.dismiss();
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
                        } else {
                            Toast.makeText(Rewards.this, "Not enough coins!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                });


            }
        } else if (v.getId() == R.id.monster6) {

            //Creating mediaplayer to play sound
            final MediaPlayer six = MediaPlayer.create(this, R.raw.sixsound);


            isOwn = checkOwnMonster(monsters, "monster6");
            System.out.println(isOwn);
            if (isOwn == true) {
                SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
                SharedPreferences.Editor editor = sett.edit();
                editor.putString("monster", "six");

                editor.apply();
                myMonster.setImageResource(R.drawable.six);
                six.start();
            } else {

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
                ivError.setImageResource(R.drawable.greysix);
                int cost = 15;
                lblError.setText(String.valueOf(cost));
                //Creating onclicklistener for alert button confirm
                unlock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (coin >= 15) {
                            Monster ant = new Monster(6, "monster6", "six", "sixsound");
                            Database database = new Database(Rewards.this);
                            database.addMonster(ant);

                            myMonster.setImageResource(R.drawable.six);
                            monster6.setImageResource(R.drawable.six);
                            SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
                            SharedPreferences.Editor editor = sett.edit();
                            editor.putString("monster", "six");

                            editor.apply();
                            setCoinAmount(cost);
                            six.start();
                            System.out.println("Unlocked Monster");
                            dialog.dismiss();
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
                        } else {
                            Toast.makeText(Rewards.this, "Not enough coins!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                });


            }
        } else if (v.getId() == R.id.monster7) {

            //Creating mediaplayer to play sound
            final MediaPlayer seven = MediaPlayer.create(this, R.raw.sevensound);


            isOwn = checkOwnMonster(monsters, "monster7");
            System.out.println(isOwn);
            if (isOwn == true) {
                SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
                SharedPreferences.Editor editor = sett.edit();
                editor.putString("monster", "seven");

                editor.apply();
                myMonster.setImageResource(R.drawable.seven);
                seven.start();
            } else {

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
                ivError.setImageResource(R.drawable.greyseven);
                int cost = 20;
                lblError.setText(String.valueOf(cost));
                //Creating onclicklistener for alert button confirm
                unlock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (coin >= 20) {
                            Monster jojo = new Monster(7, "monster7", "seven", "sevensound");
                            Database database = new Database(Rewards.this);
                            database.addMonster(jojo);

                            myMonster.setImageResource(R.drawable.seven);
                            monster7.setImageResource(R.drawable.seven);
                            SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
                            SharedPreferences.Editor editor = sett.edit();
                            editor.putString("monster", "seven");

                            editor.apply();
                            setCoinAmount(cost);
                            seven.start();
                            System.out.println("Unlocked Monster");
                            dialog.dismiss();
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
                        } else {
                            Toast.makeText(Rewards.this, "Not enough coins!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                });


            }
        } else if (v.getId() == R.id.monster8) {

            //Creating mediaplayer to play sound
            final MediaPlayer eight = MediaPlayer.create(this, R.raw.eightsound);


            isOwn = checkOwnMonster(monsters, "monster8");
            System.out.println(isOwn);
            if (isOwn == true) {
                SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
                SharedPreferences.Editor editor = sett.edit();
                editor.putString("monster", "eight");

                editor.apply();
                myMonster.setImageResource(R.drawable.eight);
                eight.start();
            } else {

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
                ivError.setImageResource(R.drawable.greyeight);
                int cost = 20;
                lblError.setText(String.valueOf(cost));
                //Creating onclicklistener for alert button confirm
                unlock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (coin >= 20) {
                            Monster jojo = new Monster(8, "monster8", "eight", "eightsound");
                            Database database = new Database(Rewards.this);
                            database.addMonster(jojo);

                            myMonster.setImageResource(R.drawable.eight);
                            monster8.setImageResource(R.drawable.eight);
                            SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
                            SharedPreferences.Editor editor = sett.edit();
                            editor.putString("monster", "eight");

                            editor.apply();
                            setCoinAmount(cost);
                            eight.start();
                            System.out.println("Unlocked Monster");
                            dialog.dismiss();
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
                        } else {
                            Toast.makeText(Rewards.this, "Not enough coins!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                });


            }
        } else if (v.getId() == R.id.monster9) {

            //Creating mediaplayer to play sound
            final MediaPlayer nine = MediaPlayer.create(this, R.raw.ninesound);


            isOwn = checkOwnMonster(monsters, "monster9");
            System.out.println(isOwn);
            if (isOwn == true) {
                SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
                SharedPreferences.Editor editor = sett.edit();
                editor.putString("monster", "nine");

                editor.apply();
                myMonster.setImageResource(R.drawable.nine);
                nine.start();
            } else {

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
                ivError.setImageResource(R.drawable.greynine);
                int cost = 20;
                lblError.setText(String.valueOf(cost));
                //Creating onclicklistener for alert button confirm
                unlock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (coin >= 20) {
                            Monster jojo = new Monster(9, "monster9", "nine", "ninesound");
                            Database database = new Database(Rewards.this);
                            database.addMonster(jojo);

                            myMonster.setImageResource(R.drawable.nine);
                            monster9.setImageResource(R.drawable.nine);
                            SharedPreferences sett = getApplicationContext().getSharedPreferences("badges", 0);
                            SharedPreferences.Editor editor = sett.edit();
                            editor.putString("monster", "nine");

                            editor.apply();
                            setCoinAmount(cost);
                            nine.start();
                            System.out.println("Unlocked Monster");
                            dialog.dismiss();
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
                        } else {
                            Toast.makeText(Rewards.this, "Not enough coins!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                });


            }
        }
    }


    public void setCoinAmount(int cost) {
        SharedPreferences coinssett = getApplicationContext().getSharedPreferences("coins", 0);
        SharedPreferences.Editor coined = coinssett.edit();
        coined.putInt("coins", (coin - cost));

        coined.apply();
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