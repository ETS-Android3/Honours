package com.example.honours;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;


public class Addition extends Activity {
    Button choice1;
    Button choice2;
    Button choice3;
    Button choice4;
    ImageButton additionMonster;

    TextView progressText;
    ProgressBar progressBar;
int currentQuestion = 0;
int currentposition = 0;
TextView questionDisplay;
int correct = 0;

    ArrayList<Question> questions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_subtract);
        Database database = new Database(Addition.this);
        questions = database.getQuestions();

       questionDisplay = findViewById(R.id.questionDisplay);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
        choice4 = findViewById(R.id.choice4);
        progressText = findViewById(R.id.progressText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        additionMonster = (ImageButton)findViewById(R.id.additionMonster);

        SharedPreferences settings = getApplicationContext().getSharedPreferences("badges", 0);
        String monster = settings.getString("monster", String.valueOf(0));
        System.out.println(monster);
        Resources resources = getResources();
        final int resourceId = resources.getIdentifier(monster,
                "drawable", getPackageName());

        additionMonster.setImageResource(resourceId);
        nextQuestion();

    }


public void answer(View v){
    final MediaPlayer mp = MediaPlayer.create(this, R.raw.correct);
           if (v.getId() == R.id.choice1) {

               int chose1 = Integer.parseInt(choice1.getText().toString());

               if (chose1 == (questions.get(currentQuestion - 1).getCorrectAnswer())) {

                   System.out.println("correct answer!");
                   correct++;
                   mp.start();
               } else {

                   System.out.println("wrong answer!");
               }
               nextQuestion();
           }else if (v.getId() == R.id.choice2) {

               int chose2 = Integer.parseInt(choice2.getText().toString());

               if (chose2 == (questions.get(currentQuestion - 1).getCorrectAnswer())) {

                   System.out.println("correct answer!");
                   correct++;
                   mp.start();
                   }else{
                       System.out.println("wrong answer!");
                   }
                   nextQuestion();
           }else if (v.getId() == R.id.choice3) {

               int chose3 = Integer.parseInt(choice3.getText().toString());

               if (chose3 == (questions.get(currentQuestion - 1).getCorrectAnswer())) {

                   System.out.println("correct answer!");
                   correct++;
                   mp.start();
               }else{
                   System.out.println("wrong answer!");
               }
               nextQuestion();
           }else if (v.getId() == R.id.choice4) {

               int chose4 = Integer.parseInt(choice4.getText().toString());

               if (chose4 == (questions.get(currentQuestion - 1).getCorrectAnswer())) {

                   System.out.println("correct answer!");
                   correct++;
                   mp.start();
               }else {
                   System.out.println("wrong answer!");
               }
               nextQuestion();

       }

}
    private void nextQuestion()  {
        currentposition++;
        System.out.println(currentposition);
        progressBar.setProgress(currentposition);
        progressText.setText(currentposition + "/10");
//        if ((correct)== (5)){
//            System.out.println("Halfway to new monster!");
//        }
       // System.out.println(correct);
        if (currentQuestion < 10) {
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


            choice1.setText(Integer.toString(answers[(int) num.get(0)]));
            choice2.setText(Integer.toString(answers[(int) num.get(1)]));
            choice3.setText(Integer.toString(answers[(int) num.get(2)]));
            choice4.setText(Integer.toString(answers[(int) num.get(3)]));
            currentQuestion++;


        } else {
            System.out.println("Questions have all done!");
            //endQuiz();
        }
    }
    private static int getRandom(int max) {
        int rnd = (int) (Math.random() * max);
        return rnd;
    }

    public void backHome(View v){
        Intent intent = new Intent(Addition.this, Activity2.class);
        startActivity(intent);
    }
}