package com.example.honours;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public static final String COLUMN_QUESTION = "QUESTION";
    public static final String QUESTIONS_TABLE = "QUESTIONS_TABLE";
    public static final String COLUMN_ANSWER = "ANSWER";
    public static final String COLUMN_WRONGANS_1 = "WRONGANS1";
    public static final String COLUMN_WRONGANS_2 = "WRONGANS2";
    public static final String COLUMN_WRONGANS_3 = "WRONGANS3";

    public static final String MONSTERS_TABLE = "MONSTERS_TABLE";
    public static final String COLUMN_FILEPATH = "FILEPATH";
    public static final String COLUMN_SOUND = "SOUND";
    public static final String COLUMN_MONSTERID = "MONSTID";


    public Database(@Nullable Context context) {
        super(context, "users.db", null, 1);
    }

    @Override
    //Called first time database accessed. Code in here to create database
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + QUESTIONS_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_QUESTION + " TEXT, " + COLUMN_ANSWER + " INT, " + COLUMN_WRONGANS_1 + " INT, " + COLUMN_WRONGANS_2 + " INT, " + COLUMN_WRONGANS_3 + " INT)";
        db.execSQL(createTableStatement);
        String createMonster = "CREATE TABLE " + MONSTERS_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_MONSTERID + " TEXT, " + COLUMN_FILEPATH + " TEXT, " + COLUMN_SOUND + " TEXT)";
        db.execSQL(createMonster);
    }

    //called if database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_QUESTION, question.getUserQuestion());
        cv.put(COLUMN_ANSWER, question.getCorrectAnswer());
        cv.put(COLUMN_WRONGANS_1, question.getWrongAnswer1());
        cv.put(COLUMN_WRONGANS_2, question.getWrongAnswer2());
        cv.put(COLUMN_WRONGANS_3, question.getWrongAnswer3());

        long insert = db.insert(QUESTIONS_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Question> getQuestions() {

        ArrayList<Question> questions = new ArrayList<>();
        String queryString = "SELECT * FROM " + QUESTIONS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
//loop through result set and create new question objects and insert into arraylist
            do {
                int questionID = cursor.getInt(0);
                String quest = cursor.getString(1);
                int answer = cursor.getInt(2);
                int wrongans1 = cursor.getInt(3);
                int wrongans2 = cursor.getInt(4);
                int wrongans3 = cursor.getInt(5);

                Question question = new Question(questionID, quest, answer, wrongans1, wrongans2, wrongans3);
                questions.add(question);
            } while (cursor.moveToNext());
        } else {
//failed, dont add anything to list
        }
        //close cursor and db
        cursor.close();
        db.close();
        return questions;
    }

    public boolean addMonster(Monster monster) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_MONSTERID, monster.getMonsterID());
        cv.put(COLUMN_FILEPATH, monster.getFilePath());
        cv.put(COLUMN_SOUND, monster.getSound());


        long insert = db.insert(MONSTERS_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Monster> getMonsters() {

        ArrayList<Monster> monsters = new ArrayList<>();
        String queryString = "SELECT * FROM " + MONSTERS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
//loop through result set and create new question objects and insert into arraylist
            do {
                int id = cursor.getInt(0);
                String monsterID = cursor.getString(1);
                String filepath = cursor.getString(2);
                String sound  = cursor.getString(3);


                Monster monster = new Monster(id, monsterID, filepath, sound);
                monsters.add(monster);
            } while (cursor.moveToNext());
        } else {
//failed, dont add anything to list
        }
        //close cursor and db
        cursor.close();
        db.close();
        return monsters;
    }
}

