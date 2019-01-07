package by.znaj.rogachev2.historyapptest;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static android.widget.Toast.LENGTH_SHORT;
import static by.znaj.rogachev2.historyapptest.R.color.colorGrey;

public class TestActivity extends AppCompatActivity /*implements View.OnClickListener*/{

    TextView nameBox;
    TextView textQuestion;
    ImageView imageQ;
    Button banswer1;
    Button banswer2;
    Button banswer3;
    Button banswer4;
    Button bhint;
    Button buttonCheck1;
    Button buttonCheck2;
    Button buttonCheck3;
    Button buttonCheck4;
    Button buttonType2Go;
    Button byes;
    Button bno;
    LinearLayout type1;
    LinearLayout type2;
    LinearLayout type3;
    LinearLayout header;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;


    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor cursorTask;
    Cursor cursorQuestions;
    Cursor cursorAnswers;
    
    long taskId = 0;
    long questionId = 0;
    int trenType = 0;

    AlertDialog.Builder ad;

    int countQuestions = 0;
    int totalQuestions = 10;
    int correctAnswers = 0;
    int typeQuestion = 0;

    int flag1 = 0;
    int flag2 = 0;
    int flag3 = 0;
    int flag4 = 0;

    String shint = "";
    String defaultHint = "Подсказки нет";
    Context context;
    String task = "";
    String stType = "";

    Handler handler;


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
        bhint = (Button) findViewById(R.id.hint);
        buttonCheck1 = (Button) findViewById(R.id.buttonCheck1);
        buttonCheck2 = (Button) findViewById(R.id.buttonCheck2);
        buttonCheck3 = (Button) findViewById(R.id.buttonCheck3);
        buttonCheck4 = (Button) findViewById(R.id.buttonCheck4);
        buttonType2Go = (Button) findViewById(R.id.buttonType2Go);
        byes = (Button) findViewById(R.id.byes);
        bno = (Button) findViewById(R.id.bno);
        context = getApplication().getBaseContext();
        type1 = (LinearLayout) findViewById(R.id.type1);
        type2 = (LinearLayout) findViewById(R.id.type2);
        type3 = (LinearLayout) findViewById(R.id.type3);
        header = (LinearLayout) findViewById(R.id.header);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);

        handler = new Handler();

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            taskId = extras.getLong("id");
            trenType = extras.getInt("trenType");
        }



        if (taskId > 0) {
            // получаем элемент по id из бд
            cursorTask = db.rawQuery("select * from " + DatabaseHelper.TABLE_TASKS + " where " + DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(taskId)});
            cursorTask.moveToFirst();
            nameBox.setText(cursorTask.getString(1));
            task = cursorTask.getString(1);
            //cursorQuestions = db.rawQuery("select * from " + DatabaseHelper.TABLE_QUESTIONS + " where " + DatabaseHelper.COLUMN_ID_TASK + "=? and type=3 ORDER BY RANDOM() LIMIT 10", new String[]{String.valueOf(taskId)});
            if (trenType == 1) {
                cursorQuestions = db.rawQuery("select * from " + DatabaseHelper.TABLE_QUESTIONS + " where " + DatabaseHelper.COLUMN_ID_TASK + "=? and type IN (1,2)  ORDER BY RANDOM() LIMIT 10", new String[]{String.valueOf(taskId)});
                stType = "Тренинг";
            }
            if (trenType == 2) {
                cursorQuestions = db.rawQuery("select * from " + DatabaseHelper.TABLE_QUESTIONS + " where " + DatabaseHelper.COLUMN_ID_TASK + "=? and type=3 ORDER BY RANDOM() LIMIT 10", new String[]{String.valueOf(taskId)});
                stType = "Верю-Неверю";
            }
            if (trenType == 3) {
                bhint.setVisibility(View.GONE);
                cursorQuestions = db.rawQuery("select * from " + DatabaseHelper.TABLE_QUESTIONS + " where " + DatabaseHelper.COLUMN_ID_TASK + "=? ORDER BY RANDOM() LIMIT 10", new String[]{String.valueOf(taskId)});
                stType = "Контроль";
            }
            //textQuestion.setText(String.valueOf(userCursor.getCount()));
            if (cursorQuestions.getCount() != 0 && cursorQuestions.getCount() == 10){
                cursorQuestions.moveToFirst();
                nextQuestion();
            }
            else {
                textQuestion.setText("Нет вопросов или их недостаточно");
            }
            //cursorQuestions.close();
        }
        ////////////////
        //showDialog();
        ////////////////
        bhint.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast aboutMessage = Toast.makeText(context,shint,Toast.LENGTH_LONG);
                aboutMessage.setGravity(Gravity.BOTTOM, 0, 0);
                aboutMessage.show();
                return false;
            }
        });
        /*bhint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast aboutMessage = Toast.makeText(context,shint,Toast.LENGTH_LONG);
                aboutMessage.setGravity(Gravity.BOTTOM, 0, 0);
                aboutMessage.show();
            }
        });*/

        banswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1 == 1) {
                    banswer1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    correctAnswers++;
                } else {
                    banswer1.setBackgroundColor(getResources().getColor(R.color.colorRed));
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (countQuestions < totalQuestions) {
                            cursorQuestions.moveToNext();
                            nextQuestion();
                        } else{
                            //TODO results
                            type1.setVisibility(View.GONE);
                            type2.setVisibility(View.GONE);
                            type3.setVisibility(View.GONE);
                            header.setVisibility(View.GONE);
                            nameBox.setText("Pravilnix: " +  Integer.toString(correctAnswers));
                            insertResults();
                        }
                    }
                }, 2000);
            }
        });
        banswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag2 == 1) {
                    banswer2.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    correctAnswers++;
                } else {
                    banswer2.setBackgroundColor(getResources().getColor(R.color.colorRed));
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (countQuestions < totalQuestions) {
                            cursorQuestions.moveToNext();
                            nextQuestion();
                        } else{
                            //TODO results
                            type1.setVisibility(View.GONE);
                            type2.setVisibility(View.GONE);
                            type3.setVisibility(View.GONE);
                            header.setVisibility(View.GONE);
                            nameBox.setText("Pravilnix: " +  Integer.toString(correctAnswers));
                            insertResults();
                        }
                    }
                }, 2000);
            }
        });
        banswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag3 == 1) {
                    banswer3.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    correctAnswers++;
                } else {
                    banswer3.setBackgroundColor(getResources().getColor(R.color.colorRed));
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (countQuestions < totalQuestions) {
                            cursorQuestions.moveToNext();
                            nextQuestion();
                        } else{
                            //TODO results
                            type1.setVisibility(View.GONE);
                            type2.setVisibility(View.GONE);
                            type3.setVisibility(View.GONE);
                            header.setVisibility(View.GONE);
                            nameBox.setText("Pravilnix: " +  Integer.toString(correctAnswers));
                            insertResults();
                        }
                    }
                }, 2000);
            }
        });
        banswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag4 == 1) {
                    banswer4.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    correctAnswers++;
                } else {
                    banswer4.setBackgroundColor(getResources().getColor(R.color.colorRed));
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (countQuestions < totalQuestions) {
                            cursorQuestions.moveToNext();
                            nextQuestion();
                        } else{
                            //TODO results
                            type1.setVisibility(View.GONE);
                            type2.setVisibility(View.GONE);
                            type3.setVisibility(View.GONE);
                            header.setVisibility(View.GONE);
                            nameBox.setText("Pravilnix: " +  Integer.toString(correctAnswers));
                            insertResults();
                        }
                    }
                }, 2000);
            }
        });
        buttonType2Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check1 = 0;
                int check2 = 0;
                int check3 = 0;
                int check4 = 0;
                if (checkBox1.isChecked()){
                    check1 = 1;
                }
                if (checkBox2.isChecked()){
                    check2 = 1;
                }
                if (checkBox3.isChecked()){
                    check3 = 1;
                }
                if (checkBox4.isChecked()){
                    check4 = 1;
                }
                if (flag1==check1 && flag2==check2 && flag3==check3 && flag4==check4){
                    correctAnswers++;
                }
                if (countQuestions < totalQuestions) {
                    cursorQuestions.moveToNext();
                    nextQuestion();
                } else{
                    //TODO results
                    type1.setVisibility(View.GONE);
                    type2.setVisibility(View.GONE);
                    type3.setVisibility(View.GONE);
                    header.setVisibility(View.GONE);
                    nameBox.setText("Pravilnix: " +  Integer.toString(correctAnswers));
                    insertResults();
                }
            }
        });

        byes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1 == 1) {
                    //banswer1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        /*try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    correctAnswers++;
                } else {
                    //banswer1.setBackgroundColor(getResources().getColor(R.color.colorRed));
                }
                if (countQuestions < totalQuestions) {
                    cursorQuestions.moveToNext();
                    nextQuestion();
                } else{
                    //TODO results
                    type1.setVisibility(View.GONE);
                    type2.setVisibility(View.GONE);
                    type3.setVisibility(View.GONE);
                    header.setVisibility(View.GONE);
                    nameBox.setText("Pravilnix: " +  Integer.toString(correctAnswers));
                    insertResults();
                }
            }
        });
        bno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1 == 0) {
                    //banswer1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        /*try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    correctAnswers++;
                } else {
                    //banswer1.setBackgroundColor(getResources().getColor(R.color.colorRed));
                }
                if (countQuestions < totalQuestions) {
                    cursorQuestions.moveToNext();
                    nextQuestion();
                } else{
                    //TODO results
                    type1.setVisibility(View.GONE);
                    type2.setVisibility(View.GONE);
                    type3.setVisibility(View.GONE);
                    header.setVisibility(View.GONE);
                    nameBox.setText("Pravilnix: " +  Integer.toString(correctAnswers));
                    insertResults();
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
        typeQuestion = cursorQuestions.getInt(5);
        textQuestion.setText(cursorQuestions.getString(2));
        shint = cursorQuestions.getString(4);
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
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);
        banswer1.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        banswer2.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        banswer3.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        banswer4.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        bhint.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        buttonCheck1.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        buttonCheck2.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        buttonCheck3.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        buttonCheck4.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        buttonType2Go.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        byes.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        bno.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        switch (typeQuestion){
            case 1: {
                type1.setVisibility(View.VISIBLE);
                type2.setVisibility(View.GONE);
                type3.setVisibility(View.GONE);
                cursorAnswers.moveToPosition(0);
                if (cursorAnswers.getInt(3) == 1){
                    flag1 = 1;
                } else{
                    flag1 = 0;
                }
                banswer1.setText(cursorAnswers.getString(2) + " " + cursorAnswers.getString(3) + " " + String.valueOf(flag1));

                cursorAnswers.moveToPosition(1);
                if (cursorAnswers.getInt(3) == 1){
                    flag2 = 1;
                } else{
                    flag2 = 0;
                }
                banswer2.setText(cursorAnswers.getString(2) + " " + cursorAnswers.getString(3) + " " + String.valueOf(flag2));

                cursorAnswers.moveToPosition(2);
                if (cursorAnswers.getInt(3) == 1){
                    flag3 = 1;
                } else{
                    flag3 = 0;
                }
                banswer3.setText(cursorAnswers.getString(2) + " " + cursorAnswers.getString(3) + " " + String.valueOf(flag3));

                cursorAnswers.moveToPosition(3);
                if (cursorAnswers.getInt(3) == 1){
                    flag4 = 1;
                } else{
                    flag4 = 0;
                }
                banswer4.setText(cursorAnswers.getString(2) + " " + cursorAnswers.getString(3) + " " + String.valueOf(flag4));
                countQuestions++;
                break;
            }
            case 2:{
                type2.setVisibility(View.VISIBLE);
                type1.setVisibility(View.GONE);
                type3.setVisibility(View.GONE);
                cursorAnswers.moveToPosition(0);
                if (cursorAnswers.getInt(3) == 1){
                    flag1 = 1;
                } else{
                    flag1 = 0;
                }
                buttonCheck1.setText(cursorAnswers.getString(2) + " " + cursorAnswers.getString(3) + " " + String.valueOf(flag1));

                cursorAnswers.moveToPosition(1);
                if (cursorAnswers.getInt(3) == 1){
                    flag2 = 1;
                } else{
                    flag2 = 0;
                }
                buttonCheck2.setText(cursorAnswers.getString(2) + " " + cursorAnswers.getString(3) + " " + String.valueOf(flag2));

                cursorAnswers.moveToPosition(2);
                if (cursorAnswers.getInt(3) == 1){
                    flag3 = 1;
                } else{
                    flag3 = 0;
                }
                buttonCheck3.setText(cursorAnswers.getString(2) + " " + cursorAnswers.getString(3) + " " + String.valueOf(flag3));

                cursorAnswers.moveToPosition(3);
                if (cursorAnswers.getInt(3) == 1){
                    flag4 = 1;
                } else{
                    flag4 = 0;
                }
                buttonCheck4.setText(cursorAnswers.getString(2) + " " + cursorAnswers.getString(3) + " " + String.valueOf(flag4));
                countQuestions++;
                break;
            }
            case 3:{
                type3.setVisibility(View.VISIBLE);
                type1.setVisibility(View.GONE);
                type2.setVisibility(View.GONE);
                cursorAnswers.moveToPosition(0);
                if (cursorAnswers.getInt(3) == 1){
                    flag1 = 1;
                    byes.setText("Да +");
                    bno.setText("Нет");
                } else{
                    flag1 = 0;
                    byes.setText("Да");
                    bno.setText("Нет +");
                }
                countQuestions++;
                break;
            }
        }
    }
    public void insertResults(){
        String res = task + "\n" + stType + "\n" + "Результат: " +  String.valueOf(correctAnswers) + " из 10" + "\n" + String.valueOf(Calendar.getInstance().getTime());
        //db.rawQuery("INSERT INTO results(result) values('?')",  new String[]{String.valueOf(res)});
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_RESULT, res);
        db.insert(DatabaseHelper.TABLE_RESULTS, null, values);
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
