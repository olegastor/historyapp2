package by.znaj.rogachev2.historyapptest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import static android.widget.Toast.LENGTH_SHORT;

public class TestActivity extends AppCompatActivity /*implements View.OnClickListener*/{

    TextView nameBox;
    TextView textQuestion;
    ImageView imageQ;
    Button banswer1;
    Button banswer2;
    Button banswer3;
    Button banswer4;



    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor cursorTask;
    Cursor cursorQuestions;
    Cursor cursorAnswers;
    
    long taskId = 0;
    long questionId = 0;

    AlertDialog.Builder ad;

    int countQuestions = 0;
    int totalQuestions = 10;
    int correctAnswers = 0;

    int flag1 = 0;
    int flag2 = 0;
    int flag3 = 0;
    int flag4 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        nameBox = (TextView) findViewById(R.id.name);
        textQuestion = (TextView) findViewById(R.id.textQuestion);
        imageQ = (ImageView) findViewById(R.id.imageQ);
        banswer1 = (Button) findViewById(R.id.answer1);
        banswer2 = (Button) findViewById(R.id.answer2);
        banswer3 = (Button) findViewById(R.id.answer3);
        banswer4 = (Button) findViewById(R.id.answer4);

        
        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            taskId = extras.getLong("id");
        }

        if (taskId > 0) {
            // получаем элемент по id из бд
            cursorTask = db.rawQuery("select * from " + DatabaseHelper.TABLE_TASKS + " where " + DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(taskId)});
            cursorTask.moveToFirst();
            nameBox.setText(cursorTask.getString(1));

            cursorQuestions = db.rawQuery("select * from " + DatabaseHelper.TABLE_QUESTIONS + " where " + DatabaseHelper.COLUMN_ID_TASK + "=?", new String[]{String.valueOf(taskId)});
            //textQuestion.setText(String.valueOf(userCursor.getCount()));
            if (cursorQuestions.getCount() != 0){
                cursorQuestions.moveToFirst();

                textQuestion.setText(cursorQuestions.getString(2));
                questionId = cursorQuestions.getLong(0);
                if (cursorQuestions.getBlob(3) != null) {
                    byte[] image = cursorQuestions.getBlob(3);
                    Bitmap bmp = BitmapFactory.decodeByteArray(image, 0 , image.length);
                    imageQ.setImageBitmap(bmp);
                } else{
                    imageQ.setVisibility(View.GONE);
                }

                cursorAnswers = db.rawQuery("select * from " + DatabaseHelper.TABLE_ANSWERS + " where " + DatabaseHelper.COLUMN_ID_QUESTION + "=?", new String[]{String.valueOf(questionId)});
                //cursorAnswers = db.rawQuery("select * from answers where id_question"+ "=?", new String[]{String.valueOf(questionId)});
                //cursorAnswers.moveToFirst();
                cursorAnswers.moveToPosition(0);
                banswer1.setText(cursorAnswers.getString(2) + cursorAnswers.getString(3));
                if (cursorAnswers.getInt(3) == 1){
                    flag1 = 1;
                } else{
                    flag1 = 0;
                }
                cursorAnswers.moveToPosition(1);
                banswer2.setText(cursorAnswers.getString(2)+ cursorAnswers.getString(3));
                if (cursorAnswers.getInt(3) == 1){
                    flag1 = 1;
                } else{
                    flag1 = 0;
                }
                cursorAnswers.moveToPosition(2);
                banswer3.setText(cursorAnswers.getString(2)+ cursorAnswers.getString(3));
                if (cursorAnswers.getInt(3) == 1){
                    flag1 = 1;
                } else{
                    flag1 = 0;
                }
                cursorAnswers.moveToPosition(3);
                banswer4.setText(cursorAnswers.getString(2)+ cursorAnswers.getString(3));
                if (cursorAnswers.getInt(3) == 1){
                    flag1 = 1;
                } else{
                    flag1 = 0;
                }
                countQuestions++;


            }
            else {
                textQuestion.setText("Нет вопросов пока");
            }

            //cursorQuestions.close();
        }
        ////////////////
        //showDialog();
        ////////////////
        banswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countQuestions < totalQuestions) {
                    if (flag1 == 1) {
                        banswer1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        /*try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                        correctAnswers++;
                        nextQuestion();
                    } else {
                        banswer1.setBackgroundColor(getResources().getColor(R.color.colorRed));
                        nextQuestion();
                    }
                } else{
                    //TODO results
                    nameBox.setText("Pravilnix: " +  Integer.toString(correctAnswers));
                }
            }
        });
        banswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countQuestions < totalQuestions) {
                    if (flag1 == 1) {
                        banswer1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        /*try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                        correctAnswers++;
                        nextQuestion();
                    } else {
                        banswer1.setBackgroundColor(getResources().getColor(R.color.colorRed));
                        nextQuestion();
                    }
                } else{
                    //TODO results
                    nameBox.setText("Pravilnix: " +  Integer.toString(correctAnswers));
                }
            }
        });
        banswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countQuestions < totalQuestions) {
                    if (flag1 == 1) {
                        banswer1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        /*try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                        correctAnswers++;
                        nextQuestion();
                    } else {
                        banswer1.setBackgroundColor(getResources().getColor(R.color.colorRed));
                        nextQuestion();
                    }
                } else{
                    //TODO results
                    nameBox.setText("Pravilnix: " +  Integer.toString(correctAnswers));
                }
            }
        });
        banswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countQuestions < totalQuestions) {
                    if (flag1 == 1) {
                        banswer1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        /*try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                        correctAnswers++;
                        nextQuestion();
                    } else {
                        banswer1.setBackgroundColor(getResources().getColor(R.color.colorRed));
                        nextQuestion();
                    }
                } else{
                    //TODO results
                    nameBox.setText("Pravilnix: " +  Integer.toString(correctAnswers));
                }
            }
        });
    }

    private void showDialog() {
        final String[] counts = {"10", "20", "30"};
        ad = new AlertDialog.Builder(this);
        ad.setTitle("Выберите количество вопросов");
        ad.setItems(counts, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //yearBox.setText(counts[i]);
            }
        });
        ad.setCancelable(false);
        ad.create();
        ad.show();
    }

    private void goHome(){
        db.close();
        Intent intent = new Intent(this, TasksListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in,R.anim.right_out);
    }

    public void onBackPressed() {
        goHome();
    }

    public void onDestroy(){
        super.onDestroy();
        db.close();
        cursorQuestions.close();
        cursorAnswers.close();
        cursorTask.close();
    }

    public void nextQuestion(){
        cursorQuestions.moveToNext();

        textQuestion.setText(cursorQuestions.getString(2));
        questionId = cursorQuestions.getLong(0);
        if (cursorQuestions.getBlob(3) != null) {
            byte[] image = cursorQuestions.getBlob(3);
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0 , image.length);
            imageQ.setImageBitmap(bmp);
        } else{
            imageQ.setVisibility(View.GONE);
        }

        cursorAnswers = db.rawQuery("select * from " + DatabaseHelper.TABLE_ANSWERS + " where " + DatabaseHelper.COLUMN_ID_QUESTION + "=?", new String[]{String.valueOf(questionId)});
        //cursorAnswers = db.rawQuery("select * from answers where id_question"+ "=?", new String[]{String.valueOf(questionId)});
        //cursorAnswers.moveToFirst();
        cursorAnswers.moveToPosition(0);
        banswer1.setText(cursorAnswers.getString(2) + cursorAnswers.getString(3));
        if (cursorAnswers.getInt(3) == 1){
            flag1 = 1;
        } else{
            flag1 = 0;
        }
        cursorAnswers.moveToPosition(1);
        banswer2.setText(cursorAnswers.getString(2)+ cursorAnswers.getString(3));
        if (cursorAnswers.getInt(3) == 1){
            flag1 = 1;
        } else{
            flag1 = 0;
        }
        cursorAnswers.moveToPosition(2);
        banswer3.setText(cursorAnswers.getString(2)+ cursorAnswers.getString(3));
        if (cursorAnswers.getInt(3) == 1){
            flag1 = 1;
        } else{
            flag1 = 0;
        }
        cursorAnswers.moveToPosition(3);
        banswer4.setText(cursorAnswers.getString(2)+ cursorAnswers.getString(3));
        if (cursorAnswers.getInt(3) == 1){
            flag1 = 1;
        } else{
            flag1 = 0;
        }
        countQuestions++;
    }

    /*@Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.answer1: {
                if (flag1 == 1){
                    banswer1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    //banswer1.setHighlightColor(getResources().getColor(R.color.colorGreen));
                    //banswer1.setTextColor(getResources().getColor(R.color.colorGreen));
                }else{
                    banswer1.setBackgroundColor(getResources().getColor(R.color.colorRed));
                }
                break;
            }
            case R.id.answer2: {
                if (flag2 == 1){
                    banswer2.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                }else{
                    banswer2.setBackgroundColor(getResources().getColor(R.color.colorRed));
                }
                break;
            }
            case R.id.answer3: {
                if (flag3 == 1){
                    banswer3.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                }else{
                    banswer3.setBackgroundColor(getResources().getColor(R.color.colorRed));
                }
                break;
            }
            case R.id.answer4: {
                if (flag4 == 1){
                    banswer4.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                }else{
                    banswer4.setBackgroundColor(getResources().getColor(R.color.colorRed));
                }
                break;
            }
        }
    }*/
}
