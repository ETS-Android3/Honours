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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;


public class Quiz extends Activity {
    Button choice1, choice2, choice3, choice4, ok, redo, goBack, selectOkay, selectCancel;

    ImageButton additionMonster;
    MediaPlayer mp;
    int totalquestions;
    int totalcorrect;
    ProgressBar progressBar;
    int currentQuestion = 0;
    int currentposition = 0;
    TextView questionDisplay, noOfQ, wrongAns, correctAns, questAsk;
    int correct = 0;

    ArrayList<Question> questions = new ArrayList<>();

    ArrayList<Question> allquestions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
        Bundle bundle = getIntent().getExtras();
        String category = bundle.getString("category");
        System.out.println(category);
        Database database = new Database(Quiz.this);

        if (category.equals("addition")) {
            System.out.println("addition");
            allquestions = database.getaddQuestions(1);
        } else if (category.equals("multiply")) {
            System.out.println("multiply");
            allquestions = database.getaddQuestions(2);
        }


        for (int i = 0; i < allquestions.size(); i++) {
            int x = getRandom(allquestions.size());
            if (questions.contains(allquestions.get(x))) {
                i = i - 1;
            } else {
                questions.add(allquestions.get(x));
            }
        }


        questionDisplay = findViewById(R.id.questionDisplay);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
        choice4 = findViewById(R.id.choice4);

        mp = MediaPlayer.create(this, R.raw.correct);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        additionMonster = (ImageButton) findViewById(R.id.additionMonster);
        noOfQ = findViewById(R.id.noOfQ);
        noOfQ.setText(String.valueOf("Q" + currentposition));
        //Access which monster user has selected and set the src of imageview
        SharedPreferences settings = getApplicationContext().getSharedPreferences("badges", 0);
        String monster = settings.getString("monster", String.valueOf(0));
        Resources resources = getResources();
        final int resourceId = resources.getIdentifier(monster, "drawable", getPackageName());
        additionMonster.setImageResource(resourceId);

        SharedPreferences sett = getApplicationContext().getSharedPreferences("questions", 0);
        totalquestions = sett.getInt("questions", 0);

        SharedPreferences corr = getApplicationContext().getSharedPreferences("correct", 0);
        totalcorrect = corr.getInt("correct", 0);

        //Start questions
        nextQuestion();

    }

    //Code for buttons and determining if user selected the correct answer
    public void answer(View v) {

        if (v.getId() == R.id.choice1) {

            int chose1 = Integer.parseInt(choice1.getText().toString());

            if (chose1 == (questions.get(currentQuestion - 1).getCorrectAnswer())) {
                correct++;
                totalcorrect++;


                mp.start();
            } else {
                String wrong = choice1.getText().toString();
                String right = Integer.toString(questions.get(currentQuestion - 1).getCorrectAnswer());
                showFeedback(wrong, right, questions.get(currentQuestion - 1).getUserQuestion());

            }
            nextQuestion();
        } else if (v.getId() == R.id.choice2) {

            int chose2 = Integer.parseInt(choice2.getText().toString());

            if (chose2 == (questions.get(currentQuestion - 1).getCorrectAnswer())) {
                correct++;
                totalcorrect++;

                mp.start();
            } else {
                String wrong = choice2.getText().toString();
                String right = Integer.toString(questions.get(currentQuestion - 1).getCorrectAnswer());
                showFeedback(wrong, right, questions.get(currentQuestion - 1).getUserQuestion());
            }
            nextQuestion();
        } else if (v.getId() == R.id.choice3) {

            int chose3 = Integer.parseInt(choice3.getText().toString());

            if (chose3 == (questions.get(currentQuestion - 1).getCorrectAnswer())) {
                correct++;
                totalcorrect++;

                mp.start();
            } else {
                String wrong = choice3.getText().toString();
                String right = Integer.toString(questions.get(currentQuestion - 1).getCorrectAnswer());
                showFeedback(wrong, right, questions.get(currentQuestion - 1).getUserQuestion());
            }
            nextQuestion();
        } else if (v.getId() == R.id.choice4) {

            int chose4 = Integer.parseInt(choice4.getText().toString());

            if (chose4 == (questions.get(currentQuestion - 1).getCorrectAnswer())) {
                correct++;
                totalcorrect++;

                mp.start();
            } else {
                String wrong = choice4.getText().toString();
                String right = Integer.toString(questions.get(currentQuestion - 1).getCorrectAnswer());
                showFeedback(wrong, right, questions.get(currentQuestion - 1).getUserQuestion());
            }
            nextQuestion();

        }

    }


    private void nextQuestion() {
        totalquestions++;
        SharedPreferences qsett = getApplicationContext().getSharedPreferences("questions", 0);
        SharedPreferences.Editor qedd = qsett.edit();
        qedd.putInt("questions", (totalquestions - 1));

        qedd.apply();

        SharedPreferences corrsett = getApplicationContext().getSharedPreferences("correct", 0);
        SharedPreferences.Editor corredd = corrsett.edit();
        corredd.putInt("correct", totalcorrect);

        corredd.apply();

        currentposition++;
        System.out.println(correct);
        noOfQ.setText(String.valueOf("Q" + currentposition));
        progressBar.setProgress(correct);

//        if ((correct)== (5)){
//            System.out.println("Halfway to new monster!");
//        }
        // System.out.println(correct);

        //Randomising answers before displaying them in buttons
        if (currentQuestion < questions.size() && correct < 10) {
            Question q = questions.get(currentQuestion);
            questionDisplay.setText(q.getUserQuestion());
            int[] answers = q.getAnswers(q);
            ArrayList num = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                int x = getRandom(4);
                if (num.contains(x)) {
                    i = i - 1;
                } else {
                    num.add(x);
                }
            }
            //Setting the answers to buttons
            choice1.setText(Integer.toString(answers[(int) num.get(0)]));
            choice2.setText(Integer.toString(answers[(int) num.get(1)]));
            choice3.setText(Integer.toString(answers[(int) num.get(2)]));
            choice4.setText(Integer.toString(answers[(int) num.get(3)]));
            currentQuestion++;
        } else if (currentQuestion == questions.size() && correct < 10) {
            questions.clear();
            for (int i = 0; i < allquestions.size(); i++) {
                int x = getRandom(allquestions.size());
                if (questions.contains(allquestions.get(x))) {
                    i = i - 1;
                } else {
                    questions.add(allquestions.get(x));
                }
            }
            currentQuestion = 0;
            nextQuestion();

        } else if (correct == 10) {
            SharedPreferences settings = getApplicationContext().getSharedPreferences("coins", 0);
            int coins = settings.getInt("coins", 0);
            int newcoins = coins + 10;

            SharedPreferences coinssett = getApplicationContext().getSharedPreferences("coins", 0);
            SharedPreferences.Editor coined = coinssett.edit();
            coined.putInt("coins", (newcoins));

            coined.apply();
            System.out.println("Questions have all done!");
            AlertDialog.Builder builder = new AlertDialog.Builder(Quiz.this);
            View view = getLayoutInflater().inflate(R.layout.finishedalert, null);


            builder.setView(view);
            AlertDialog dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            redo = view.findViewById(R.id.redo);
            goBack = view.findViewById(R.id.goBack);
            redo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    mp.release();
                    mp = null;
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }
            });
            goBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    backHome(v);
                    mp.release();
                    mp = null;
                }
            });
            //endQuiz();
        }
    }

    //Random function
    private static int getRandom(int max) {
        int rnd = (int) (Math.random() * max);
        return rnd;
    }

    public void draw(View v) {
        PaintView paintview = new PaintView(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(Quiz.this);
        paintview.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
        builder.setView(paintview);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        dialog.getWindow().setLayout(1000, 1300);
    }

    public void showFeedback(String wrong, String right, String questAsked) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Quiz.this);
        View view = getLayoutInflater().inflate(R.layout.feedbackalert, null);
        ok = view.findViewById(R.id.ok);
        correctAns = view.findViewById(R.id.correctAns);
        wrongAns = view.findViewById(R.id.wrongAns);
        questAsk = view.findViewById(R.id.questAsk);
        questAsk.setText(questAsked);
        correctAns.setText(right);
        wrongAns.setText(wrong);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    //Go back to category page
    public void backHome(View v) {
        Intent intent = new Intent(Quiz.this, Category.class);
        startActivity(intent);
    }

    public void playMoney(View v) {
        final MediaPlayer money = MediaPlayer.create(this, R.raw.money);
        money.start();
    }

    //Go back to category page
    public void goToRewards(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Quiz.this);
        View view = getLayoutInflater().inflate(R.layout.changemonsteralert, null);
        selectOkay = view.findViewById(R.id.selectOkay);
        selectCancel = view.findViewById(R.id.selectCancel);


        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        selectOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Quiz.this, Rewards.class);
                startActivity(intent);
            }
        });
        selectCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
    }
}