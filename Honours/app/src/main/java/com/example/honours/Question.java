package com.example.honours;

import java.util.ArrayList;

public class Question {

    private final int QuestionID;
    private final String question;
    private final int answer;
    private final int wrongAns1;
    private final int wrongAns2;
    private final int wrongAns3;





    public Question(int questID, String quest, int correct, int wrong1, int wrong2, int wrong3) {

        this.QuestionID = questID;
        this.question = quest;
        this.answer = correct;
        this.wrongAns1 = wrong1;
        this.wrongAns2 = wrong2;
        this.wrongAns3 = wrong3;


    }


    public int getQuestionId() {
        return this.QuestionID;
    }


    public String getUserQuestion() {
        return this.question;
    }

    public int getCorrectAnswer() {
        return this.answer;
    }

    public int getWrongAnswer1() {
        return this.wrongAns1;
    }

    public int getWrongAnswer2() {
        return this.wrongAns2;
    }

    public int getWrongAnswer3() {
        return this.wrongAns3;
    }

    public int[] getAnswers(Question q){
        int[] answers = {q.getCorrectAnswer(), q.getWrongAnswer1(), q.getWrongAnswer2(), q.getWrongAnswer3()};
        return answers;
    }


    public static ArrayList<Question> createQuestions(){

        Question question1 = new  Question(1,"what is 2+2", 4,3,2,1);
        Question question2 = new  Question(2,"what is 4+5", 9,8,6,10);
        Question question3 = new  Question(2,"what is 632-34",598 ,567,589,601);
        Question question4 = new  Question(1,"what is 2+2", 4,3,2,1);
        Question question5 = new  Question(2,"what is 4+5", 9,8,6,10);
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
        return questions;
    }

}