package com.example.honours;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class Addition extends Activity {
    Button choice1;
    Button choice2;
    Button choice3;
    Button choice4;
int currentQuestion = 0;
TextView questionDisplay;

    ArrayList<Question> questions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_subtract);
        questions = Question.createQuestions();

       questionDisplay = findViewById(R.id.questionDisplay);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
        choice4 = findViewById(R.id.choice4);
        nextQuestion();

    }

public void answer(View v){
    final MediaPlayer mp = MediaPlayer.create(this, R.raw.correct);
           if (v.getId() == R.id.choice1) {

               int chose1 = Integer.parseInt(choice1.getText().toString());

               if (chose1 == (questions.get(currentQuestion - 1).getCorrectAnswer())) {

                   System.out.println("correct answer!");
                   mp.start();
               } else {

                   System.out.println("wrong answer!");
               }
               nextQuestion();
           }else if (v.getId() == R.id.choice2) {

               int chose2 = Integer.parseInt(choice2.getText().toString());

               if (chose2 == (questions.get(currentQuestion - 1).getCorrectAnswer())) {

                   System.out.println("correct answer!");
                   mp.start();
                   }else{
                       System.out.println("wrong answer!");
                   }
                   nextQuestion();
           }else if (v.getId() == R.id.choice3) {

               int chose3 = Integer.parseInt(choice3.getText().toString());

               if (chose3 == (questions.get(currentQuestion - 1).getCorrectAnswer())) {

                   System.out.println("correct answer!");
                   mp.start();
               }else{
                   System.out.println("wrong answer!");
               }
               nextQuestion();
           }else if (v.getId() == R.id.choice4) {

               int chose4 = Integer.parseInt(choice4.getText().toString());

               if (chose4 == (questions.get(currentQuestion - 1).getCorrectAnswer())) {

                   System.out.println("correct answer!");
                   mp.start();
               }else {
                   System.out.println("wrong answer!");
               }
               nextQuestion();

       }

}
    private void nextQuestion()  {
        if (currentQuestion < questions.size()) {
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
            //endQuiz();
        }
    }
    private static int getRandom(int max) {
        int rnd = (int) (Math.random() * max);
        return rnd;
    }
//    public void answer(View view) {
//        if (view.getId().equals(choice1)) {
//            if (choice1.getText().equals(questions.get(qNo - 1).getCorrectAnswer())) {
//              correct.setText("");
//            }
////            nextQuestion();
//        } else if (event.getSource().equals(option2)) {
//            if (choice2.getText().equals(questions.get(qNo - 1).getCorrectAnswer())) {
//
//                correct.setText("" );
//            }
//            nextQuestion();
//        } else if (event.getSource().equals(option3)) {
//            if (choice3.getText().equals(questions.get(qNo - 1).getCorrectAnswer())) {
//                score++;
//                answQuestions.add(questions.get(qNo - 1));
//                correct.setText("" + score);
//            }
//            nextQuestion();
//        } else if (event.getSource().equals(option4)) {
//            if (choice4.getText().equals(questions.get(qNo - 1).getCorrectAnswer())) {
//
//                correct.setText("" + score);
//            }
//            nextQuestion();
//        }
//    }

}