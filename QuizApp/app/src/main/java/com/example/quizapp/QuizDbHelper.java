package com.example.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.quizapp.QuizContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_quiz";
    private static final int DATABASE_VERSION = 2;

    private SQLiteDatabase db;

    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NUMBER + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);

        onCreate(db);
    }


    private void fillQuestionsTable() {
        Question q1 = new Question("A is correct","A","B","C",1,Question.DIFFICULTY_EASY);
        addQuestion(q1);
        Question q2 = new Question("B is correct","A","B","C",2,Question.DIFFICULTY_EASY);
        addQuestion(q2);
        Question q3 = new Question("C is correct","A","B","C",3,Question.DIFFICULTY_EASY);
        addQuestion(q3);
        Question q4 = new Question("A is correct","A","B","C",1,Question.DIFFICULTY_EASY);
        addQuestion(q4);
        Question q5 = new Question("B is correct","A","B","C",2,Question.DIFFICULTY_EASY);
        addQuestion(q5);


        Question q6 = new Question("B is correct","A","B","C",2,Question.DIFFICULTY_MEDIUM);
        addQuestion(q6);
        Question q7 = new Question("A is correct","A","B","C",1,Question.DIFFICULTY_MEDIUM);
        addQuestion(q7);
        Question q8 = new Question("C is correct","A","B","C",3,Question.DIFFICULTY_MEDIUM);
        addQuestion(q8);
        Question q9 = new Question("B is correct","A","B","C",2,Question.DIFFICULTY_MEDIUM);
        addQuestion(q9);
        Question q10 = new Question("C is correct","A","B","C",3,Question.DIFFICULTY_MEDIUM);
        addQuestion(q10);


        Question q11 = new Question("C is correct","A","B","C",3,Question.DIFFICULTY_HARD);
        addQuestion(q11);
        Question q12 = new Question("B is correct","A","B","C",2,Question.DIFFICULTY_HARD);
        addQuestion(q12);
        Question q13 = new Question("A is correct","A","B","C",1,Question.DIFFICULTY_HARD);
        addQuestion(q13);
        Question q14 = new Question("C is correct","A","B","C",3,Question.DIFFICULTY_HARD);
        addQuestion(q14);
        Question q15 = new Question("B is correct","A","B","C",2,Question.DIFFICULTY_HARD);
        addQuestion(q15);


    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NUMBER,question.getAnswerNumber());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY,question.getDifficulty());
        db.insert(QuestionsTable.TABLE_NAME,null,cv);
    }

    public ArrayList<Question> getAllQuestions(){
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME,null);

        if(cursor.moveToFirst()){
            do {
                Question question = new Question();
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNumber(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NUMBER)));
                question.setDifficulty(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                questionList.add(question);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(String difficulty){
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        String[] selectionArgs = new String[]{difficulty};
        Cursor cursor = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME +
                " WHERE " + QuestionsTable.COLUMN_DIFFICULTY + " = ?",selectionArgs);

        if(cursor.moveToFirst()){
            do {
                Question question = new Question();
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNumber(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NUMBER)));
                question.setDifficulty(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                questionList.add(question);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionList;
    }
}
