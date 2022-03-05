package com.example.honours;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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


Question question1 = new  Question(1,"what is 2+2", 4,3,2,1);
    Question question2 = new  Question(2,"what is 4+5", 9,8,6,10);
    ArrayList<Question> questions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_subtract);
        questions.add(question1);
        questions.add(question2);
       questionDisplay = findViewById(R.id.questionDisplay);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
        choice4 = findViewById(R.id.choice4);
        nextQuestion();

    }

public void answer(View v){

           if (v.getId() == R.id.choice1) {
               if (choice1.getText().equals(questions.get(currentQuestion - 1).getCorrectAnswer())) {
                   System.out.println("correct answer!");
               } else {
                   System.out.println("wrong answer!");
               }
               nextQuestion();
           }else if (v.getId() == R.id.choice2) {
                   if (choice2.getText().equals(questions.get(currentQuestion - 1).getCorrectAnswer())) {
                       System.out.println("correct answer!");
                   }else{
                       System.out.println("wrong answer!");
                   }
                   nextQuestion();
           }else if (v.getId() == R.id.choice3) {
               if (choice3.getText().equals(questions.get(currentQuestion - 1).getCorrectAnswer())) {
                   System.out.println("correct answer!");
               }else{
                   System.out.println("wrong answer!");
               }
               nextQuestion();
           }else if (v.getId() == R.id.choice4) {
               if (choice4.getText().equals(questions.get(currentQuestion - 1).getCorrectAnswer())) {
                   System.out.println("correct answer!");
               }else{
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