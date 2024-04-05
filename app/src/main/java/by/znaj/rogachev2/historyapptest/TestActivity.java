package by.znaj.rogachev2.historyapptest;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    TextView nameBox;
    TextView maptext;
    TextView textQuestion;
    TextView textres1;
    TextView textres2;
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
    Button el1Button;
    Button el2Button;
    Button closeTest;
    LinearLayout type1;
    LinearLayout type2;
    LinearLayout type3;
    LinearLayout type4;
    LinearLayout type5;
    LinearLayout header;
    LinearLayout hintlayout;
    LinearLayout reslayout;

    Button t4t1Button;
    Button t4t2Button;
    Button t4t3Button;
    Button t4t4Button;
    Button t4t5Button;
    Button t4t6Button;
    Button t4t7Button;
    Button t4t8Button;
    ArrayList<TextView> t4Letters = new ArrayList<>();
    ArrayList<TextView> t4Numbers = new ArrayList<>();
    ArrayList<Button> t4Buttons = new ArrayList<>();
    int t4Count = 6;
    Button buttonType4Clear;
    Button buttonType4Go;
    int t4Iterator = 0;
    String t4Answer;
    int t4AnswerLength;


    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor cursorTask;
    Cursor cursorQuestions;
    Cursor cursorAnswers;
    Cursor cursorCong;

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
    int check1 = 0;
    int check2 = 0;
    int check3 = 0;
    int check4 = 0;

    String shint = "";
    String defaultHint = "Подсказки нет";
    Context context;
    String task = "";
    String stType = "";
    String tasks;

    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_actbar);

        nameBox = (TextView) findViewById(R.id.name);
        textQuestion = (TextView) findViewById(R.id.textQuestion);
        textres1 = (TextView) findViewById(R.id.textres1);
        textres2 = (TextView) findViewById(R.id.textres2);
        maptext = (TextView) findViewById(R.id.maptext);
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
        closeTest = (Button) findViewById(R.id.closeTest);
        context = getApplication().getBaseContext();
        type1 = (LinearLayout) findViewById(R.id.type1);
        type2 = (LinearLayout) findViewById(R.id.type2);
        type3 = (LinearLayout) findViewById(R.id.type3);
        header = (LinearLayout) findViewById(R.id.header);
        hintlayout = (LinearLayout) findViewById(R.id.hintlayout);

        //type5
        type5 = (LinearLayout) findViewById(R.id.type5);
        el1Button = (Button) findViewById(R.id.el1Button);
        el2Button = (Button) findViewById(R.id.el2Button);

        //type4
        type4 = (LinearLayout) findViewById(R.id.type4);
        buttonType4Clear = (Button) findViewById(R.id.buttonType4Clear);
        buttonType4Go = (Button) findViewById(R.id.buttonType4Go);
        t4Buttons.clear();
        t4Buttons.add((Button) findViewById(R.id.t4t1Button));
        t4Buttons.add((Button) findViewById(R.id.t4t2Button));
        t4Buttons.add((Button) findViewById(R.id.t4t3Button));
        t4Buttons.add((Button) findViewById(R.id.t4t4Button));
        t4Buttons.add((Button) findViewById(R.id.t4t5Button));
        t4Buttons.add((Button) findViewById(R.id.t4t6Button));
        t4Buttons.add((Button) findViewById(R.id.t4t7Button));
        t4Buttons.add((Button) findViewById(R.id.t4t8Button));
        t4t1Button = (Button) findViewById(R.id.t4t1Button);
        t4t2Button = (Button) findViewById(R.id.t4t2Button);
        t4t3Button = (Button) findViewById(R.id.t4t3Button);
        t4t4Button = (Button) findViewById(R.id.t4t4Button);
        t4t5Button = (Button) findViewById(R.id.t4t5Button);
        t4t6Button = (Button) findViewById(R.id.t4t6Button);
        t4t7Button = (Button) findViewById(R.id.t4t7Button);
        t4t8Button = (Button) findViewById(R.id.t4t8Button);
        t4Numbers.clear();
        t4Numbers.add((TextView) findViewById(R.id.t4o1));
        t4Numbers.add((TextView) findViewById(R.id.t4o2));
        t4Numbers.add((TextView) findViewById(R.id.t4o3));
        t4Numbers.add((TextView) findViewById(R.id.t4o4));
        t4Numbers.add((TextView) findViewById(R.id.t4o5));
        t4Numbers.add((TextView) findViewById(R.id.t4o6));
        t4Numbers.add((TextView) findViewById(R.id.t4o7));
        t4Numbers.add((TextView) findViewById(R.id.t4o8));
        t4Letters.clear();
        t4Letters.add((TextView) findViewById(R.id.t4b1));
        t4Letters.add((TextView) findViewById(R.id.t4b2));
        t4Letters.add((TextView) findViewById(R.id.t4b3));
        t4Letters.add((TextView) findViewById(R.id.t4b4));
        t4Letters.add((TextView) findViewById(R.id.t4b5));
        t4Letters.add((TextView) findViewById(R.id.t4b6));
        t4Letters.add((TextView) findViewById(R.id.t4b7));
        t4Letters.add((TextView) findViewById(R.id.t4b8));




        reslayout = (LinearLayout) findViewById(R.id.reslayout);
        reslayout.setVisibility(View.GONE);
        maptext.setVisibility(View.GONE);

        handler = new Handler();

        sqlHelper = new DatabaseHelper(this);
        sqlHelper.createDatabase();
        db = sqlHelper.open();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            trenType = extras.getInt("trenType");
            if (trenType != 400) {
                taskId = extras.getLong("id");
            } else {
                tasks = extras.getString("tasks");
            }
        }

        try {
            switch (trenType) {
                case 1: {
                    cursorTask = db.rawQuery("select * from " + DatabaseHelper.TABLE_TASKS + " where " + DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(taskId)});
                    cursorTask.moveToFirst();
                    nameBox.setText(cursorTask.getString(1));
                    task = cursorTask.getString(1);
                    bhint.setVisibility(View.GONE);
                    cursorQuestions = db.rawQuery("select * from " + DatabaseHelper.TABLE_QUESTIONS + " where " + DatabaseHelper.COLUMN_ID_TASK + "=? and type IN (1)  ORDER BY RANDOM() LIMIT 10", new String[]{String.valueOf(taskId)});
                    totalQuestions = cursorQuestions.getCount();
                    stType = "Тренинг";
                    break;
                }
                case 2: {
                    cursorTask = db.rawQuery("select * from " + DatabaseHelper.TABLE_TASKS + " where " + DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(taskId)});
                    cursorTask.moveToFirst();
                    nameBox.setText(cursorTask.getString(1));
                    task = cursorTask.getString(1);
                    bhint.setVisibility(View.GONE);
                    cursorQuestions = db.rawQuery("select * from " + DatabaseHelper.TABLE_QUESTIONS + " where " + DatabaseHelper.COLUMN_ID_TASK + "=? and type IN (2)  ORDER BY RANDOM() LIMIT 10", new String[]{String.valueOf(taskId)});
                    totalQuestions = cursorQuestions.getCount();
                    stType = "Тренинг";
                    break;
                }

                case 3: {
                    cursorTask = db.rawQuery("select * from " + DatabaseHelper.TABLE_TASKS + " where " + DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(taskId)});
                    cursorTask.moveToFirst();
                    nameBox.setText(cursorTask.getString(1));
                    task = cursorTask.getString(1);
                    bhint.setVisibility(View.GONE);
                    cursorQuestions = db.rawQuery("select * from " + DatabaseHelper.TABLE_QUESTIONS + " where " + DatabaseHelper.COLUMN_ID_TASK + "=? and type=3 ORDER BY RANDOM() LIMIT 10", new String[]{String.valueOf(taskId)});
                    totalQuestions = cursorQuestions.getCount();
                    stType = "Верю - Не верю";
                    break;
                }

                case 4: {
                    cursorTask = db.rawQuery("select * from " + DatabaseHelper.TABLE_TASKS + " where " + DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(taskId)});
                    cursorTask.moveToFirst();
                    nameBox.setText(cursorTask.getString(1));
                    task = cursorTask.getString(1);
                    bhint.setVisibility(View.GONE);
                    cursorQuestions = db.rawQuery("select * from " + DatabaseHelper.TABLE_QUESTIONS + " where " + DatabaseHelper.COLUMN_ID_TASK + "=? and type IN (4,6)  ORDER BY RANDOM() LIMIT 10", new String[]{String.valueOf(taskId)});
                    totalQuestions = cursorQuestions.getCount();
                    stType = "Соответствие";
                    break;
                }
                case 5: {
                    cursorTask = db.rawQuery("select * from " + DatabaseHelper.TABLE_TASKS + " where " + DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(taskId)});
                    cursorTask.moveToFirst();
                    nameBox.setText(cursorTask.getString(1));
                    task = cursorTask.getString(1);
                    bhint.setVisibility(View.GONE);
                    cursorQuestions = db.rawQuery("select * from " + DatabaseHelper.TABLE_QUESTIONS + " where " + DatabaseHelper.COLUMN_ID_TASK + "=? and type=5 ORDER BY RANDOM() LIMIT 10", new String[]{String.valueOf(taskId)});
                    totalQuestions = cursorQuestions.getCount();
                    stType = "Раньше - позже";

                    break;

                }
                case 300: {
                    cursorTask = db.rawQuery("select * from " + DatabaseHelper.TABLE_TASKS + " where " + DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(taskId)});
                    cursorTask.moveToFirst();
                    nameBox.setText(cursorTask.getString(1));
                    task = cursorTask.getString(1);
                    bhint.setVisibility(View.GONE);
                    cursorQuestions = db.rawQuery("select * from " + DatabaseHelper.TABLE_QUESTIONS + " where " + DatabaseHelper.COLUMN_ID_TASK + "=? ORDER BY RANDOM() LIMIT 10", new String[]{String.valueOf(taskId)});
                    stType = "Контроль";
                    break;
                }
                case 400: {
                    nameBox.setText("Итоговый контроль по выбранным темам");
                    //TODO заполнить строку темами контроля
                    //task = cursorTask.getString(1);
                    bhint.setVisibility(View.GONE);
                    cursorQuestions = db.rawQuery("select * from " + DatabaseHelper.TABLE_QUESTIONS + " where " + DatabaseHelper.COLUMN_ID_TASK + " IN (" + tasks + ") ORDER BY RANDOM() LIMIT 10", null);
                    stType = "Итоговый контроль";
                    break;
                }
                case 700: {
                    nameBox.setText("Интерактив с картой");
                    //TODO карта
                    //task = cursorTask.getString(1);
                    bhint.setVisibility(View.VISIBLE);
                    cursorQuestions = db.rawQuery("select * from " + DatabaseHelper.TABLE_QUESTIONS + " where type=7 ORDER BY RANDOM() LIMIT 10", null);
                    stType = "Карта";
                    //trenType = 1;
                    break;

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        /*if (cursorQuestions.getCount() != 0 && cursorQuestions.getCount() == 10) {
            cursorQuestions.moveToFirst();
            nextQuestion();
        } else {
            textQuestion.setText("Error");
        }*/

        if (cursorQuestions.getCount() != 0) {
            cursorQuestions.moveToFirst();
            nextQuestion();
        } else {
            textQuestion.setText("Error");
        }
        ////////////////
        //showDialog();
        ////////////////
        bhint.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast aboutMessage = Toast.makeText(context, shint, Toast.LENGTH_LONG);
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
                if (trenType == 3 || trenType == 4){
                    banswer1.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                }
                if (flag1 == 1) {
                    if (trenType != 3 && trenType != 4)
                        banswer1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    if (typeQuestion == 7){
                        maptext.setVisibility(View.VISIBLE);
                        maptext.setText("Правильно: " + shint);
                    }
                    disableButtons();
                    correctAnswers++;
                } else {
                    if (trenType != 3 && trenType != 4) {
                        banswer1.setBackgroundColor(getResources().getColor(R.color.colorRed));
                        colorButtonGreen();
                        if (typeQuestion == 7){
                            maptext.setVisibility(View.VISIBLE);
                            maptext.setText(shint);
                        }
                    }
                    disableButtons();
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (countQuestions < totalQuestions) {
                            cursorQuestions.moveToNext();
                            nextQuestion();
                        } else {
                            showResults();
                            insertResults();
                        }
                    }
                }, 2000);
            }
        });
        banswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trenType == 3 || trenType == 4){
                    banswer2.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                }
                if (flag2 == 1) {
                    if (trenType != 3 && trenType != 4)
                        banswer2.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    if (typeQuestion == 7){
                        maptext.setVisibility(View.VISIBLE);
                        maptext.setText("Правильно: " + shint);
                    }
                    disableButtons();
                    correctAnswers++;
                } else {
                    if (trenType != 3 && trenType != 4) {
                        banswer2.setBackgroundColor(getResources().getColor(R.color.colorRed));
                        colorButtonGreen();
                        if (typeQuestion == 7){
                            maptext.setVisibility(View.VISIBLE);
                            maptext.setText(shint);
                        }
                    }
                    disableButtons();
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (countQuestions < totalQuestions) {
                            cursorQuestions.moveToNext();
                            nextQuestion();
                        } else {
                            showResults();
                            insertResults();
                        }
                    }
                }, 2000);
            }
        });
        banswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trenType == 3 || trenType == 4){
                    banswer3.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                }
                if (flag3 == 1) {
                    if (trenType != 3 && trenType != 4)
                        banswer3.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    if (typeQuestion == 7){
                        maptext.setVisibility(View.VISIBLE);
                        maptext.setText("Правильно: " + shint);
                    }
                    disableButtons();
                    correctAnswers++;
                } else {
                    if (trenType != 3 && trenType != 4) {
                        banswer3.setBackgroundColor(getResources().getColor(R.color.colorRed));
                        colorButtonGreen();
                        if (typeQuestion == 7){
                            maptext.setVisibility(View.VISIBLE);
                            maptext.setText(shint);
                        }
                    }
                    disableButtons();
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (countQuestions < totalQuestions) {
                            cursorQuestions.moveToNext();
                            nextQuestion();
                        } else {
                            showResults();
                            insertResults();
                        }
                    }
                }, 2000);
            }
        });
        banswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trenType == 3 || trenType == 4){
                    banswer4.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                }
                if (flag4 == 1) {
                    if (trenType != 3 && trenType != 4)
                        banswer4.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    if (typeQuestion == 7){
                        maptext.setVisibility(View.VISIBLE);
                        maptext.setText("Правильно: " + shint);
                    }
                    disableButtons();
                    correctAnswers++;
                } else {
                    if (trenType != 3 && trenType != 4) {
                        banswer4.setBackgroundColor(getResources().getColor(R.color.colorRed));
                        colorButtonGreen();
                        if (typeQuestion == 7){
                            maptext.setVisibility(View.VISIBLE);
                            maptext.setText(shint);
                        }
                    }
                    disableButtons();
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (countQuestions < totalQuestions) {
                            cursorQuestions.moveToNext();
                            nextQuestion();
                        } else {
                            showResults();
                            insertResults();
                        }
                    }
                }, 2000);
            }
        });

        buttonCheck1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check1 == 0) {
                    check1 = 1;
                    buttonCheck1.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                } else {
                    check1 = 0;
                    buttonCheck1.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                }
            }
        });
        buttonCheck2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check2 == 0) {
                    check2 = 1;
                    buttonCheck2.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                } else {
                    check2 = 0;
                    buttonCheck2.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                }
            }
        });
        buttonCheck3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check3 == 0) {
                    check3 = 1;
                    buttonCheck3.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                } else {
                    check3 = 0;
                    buttonCheck3.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                }
            }
        });
        buttonCheck4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check4 == 0) {
                    check4 = 1;
                    buttonCheck4.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                } else {
                    check4 = 0;
                    buttonCheck4.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                }
            }
        });


        buttonType2Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableButtons();
                if (flag1 == check1 && flag2 == check2 && flag3 == check3 && flag4 == check4) {
                    correctAnswers++;
                }

                if (trenType != 3 && trenType != 4) {
                    if (flag1 == 1 && check1 == 0) {
                        buttonCheck1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        buttonCheck1.setTextColor(getResources().getColor(R.color.colorRed));
                    }
                    if (flag1 == 1 && check1 == 1) {
                        buttonCheck1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    }
                    if (flag1 == 0 && check1 == 1) {
                        buttonCheck1.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    }

                    if (flag2 == 1 && check2 == 0) {
                        buttonCheck2.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        buttonCheck2.setTextColor(getResources().getColor(R.color.colorRed));
                    }
                    if (flag2 == 1 && check2 == 1) {
                        buttonCheck2.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    }
                    if (flag2 == 0 && check2 == 1) {
                        buttonCheck2.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    }

                    if (flag3 == 1 && check3 == 0) {
                        buttonCheck3.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        buttonCheck3.setTextColor(getResources().getColor(R.color.colorRed));
                    }
                    if (flag3 == 1 && check3 == 1) {
                        buttonCheck3.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    }
                    if (flag3 == 0 && check3 == 1) {
                        buttonCheck3.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    }

                    if (flag4 == 1 && check4 == 0) {
                        buttonCheck4.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        buttonCheck4.setTextColor(getResources().getColor(R.color.colorRed));
                    }
                    if (flag4 == 1 && check4 == 1) {
                        buttonCheck4.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    }
                    if (flag4 == 0 && check4 == 1) {
                        buttonCheck4.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    }
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (countQuestions < totalQuestions) {
                            cursorQuestions.moveToNext();
                            nextQuestion();
                        } else {
                            showResults();
                            insertResults();
                        }
                    }
                }, 2000);
            }
        });

        byes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trenType == 3 || trenType == 4){
                    byes.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                }
                if (flag1 == 1) {
                    disableButtons();
                    if (trenType != 3 && trenType != 4)
                        byes.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    correctAnswers++;
                } else {
                    disableButtons();
                    if (trenType != 3 && trenType != 4)
                        byes.setBackgroundColor(getResources().getColor(R.color.colorRed));
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (countQuestions < totalQuestions) {
                            cursorQuestions.moveToNext();
                            nextQuestion();
                        } else {
                            showResults();
                            insertResults();
                        }
                    }
                }, 2000);
            }
        });
        bno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trenType == 3 || trenType == 4){
                    bno.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                }
                if (flag1 == 0) {
                    disableButtons();
                    if (trenType != 3 && trenType != 4)
                        bno.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    correctAnswers++;
                } else {
                    disableButtons();
                    if (trenType != 3 && trenType != 4)
                        bno.setBackgroundColor(getResources().getColor(R.color.colorRed));
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (countQuestions < totalQuestions) {
                            cursorQuestions.moveToNext();
                            nextQuestion();
                        } else {
                            showResults();
                            insertResults();
                        }
                    }
                }, 2000);
            }
        });

        el1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trenType == 300 || trenType == 400){
                    el1Button.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                }
                if (flag1 == 1) {
                    if (trenType != 300 && trenType != 400)
                        el1Button.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    disableButtons();
                    correctAnswers++;
                } else {
                    if (trenType != 300 && trenType != 400) {
                        el1Button.setBackgroundColor(getResources().getColor(R.color.colorRed));
                        colorButtonGreen();
                    }
                    disableButtons();
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (countQuestions < totalQuestions) {
                            cursorQuestions.moveToNext();
                            nextQuestion();
                        } else {
                            showResults();
                            insertResults();
                        }
                    }
                }, 2000);
            }
        });

        el2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trenType == 300 || trenType == 400){
                    el2Button.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                }
                if (flag2 == 1) {
                    if (trenType != 300 && trenType != 400)
                        el2Button.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    disableButtons();
                    correctAnswers++;
                } else {
                    if (trenType != 300 && trenType != 400) {
                        el2Button.setBackgroundColor(getResources().getColor(R.color.colorRed));
                        colorButtonGreen();
                    }
                    disableButtons();
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (countQuestions < totalQuestions) {
                            cursorQuestions.moveToNext();
                            nextQuestion();
                        } else {
                            showResults();
                            insertResults();
                        }
                    }
                }, 2000);
            }
        });

        buttonType4Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t4Iterator = 0;
                for (TextView i : t4Numbers){
                    i.setText(" ");
                }
            }
        });

        buttonType4Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t4Result = "";
                for (int i = 0; i < t4AnswerLength; i++){
                    t4Result = t4Result + (String) t4Letters.get(i).getText();
                    t4Result = t4Result + String.valueOf(t4Numbers.get(i).getText());

                }
                Toast aboutMessage = Toast.makeText(context, t4Result, Toast.LENGTH_LONG);
                aboutMessage.setGravity(Gravity.BOTTOM, 0, 0);
                aboutMessage.show();
                //t4Iterator = 0;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (countQuestions < totalQuestions) {
                            cursorQuestions.moveToNext();
                            nextQuestion();
                        } else {
                            showResults();
                            insertResults();
                        }
                    }
                }, 2000);
            }
        });

        t4t1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t4Iterator < 8) {
                    t4Numbers.get(t4Iterator).setText("1");
                    t4Iterator++;
                }
            }
        });
        t4t2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t4Iterator < 8) {
                    t4Numbers.get(t4Iterator).setText("2");
                    t4Iterator++;
                }
            }
        });
        t4t3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t4Iterator < 8) {
                    t4Numbers.get(t4Iterator).setText("3");
                    t4Iterator++;
                }
            }
        });
        t4t4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t4Iterator < 8) {
                    t4Numbers.get(t4Iterator).setText("4");
                    t4Iterator++;
                }
            }
        });
        t4t5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t4Iterator < 8) {
                    t4Numbers.get(t4Iterator).setText("5");
                    t4Iterator++;
                }
            }
        });
        t4t6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t4Iterator < 8) {
                    t4Numbers.get(t4Iterator).setText("6");
                    t4Iterator++;
                }
            }
        });
        t4t7Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t4Iterator < 8) {
                    t4Numbers.get(t4Iterator).setText("7");
                    t4Iterator++;
                }
            }
        });
        t4t8Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t4Iterator < 8) {
                    t4Numbers.get(t4Iterator).setText("8");
                    t4Iterator++;
                }
            }
        });

        closeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
    }

    /*private void showDialog() {
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
    }*/

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.t4t1Button: {
                t4Numbers.get(t4Iterator).setText("1");
                t4Iterator++;
                break;
            }
            case R.id.t4t2Button: {
                t4Numbers.get(t4Iterator).setText("2");
                t4Iterator++;
                break;
            }
            case R.id.t4t3Button: {
                t4Numbers.get(t4Iterator).setText("3");
                t4Iterator++;
                break;
            }
            case R.id.t4t4Button: {
                t4Numbers.get(t4Iterator).setText("4");
                t4Iterator++;
                break;
            }
            case R.id.t4t5Button: {
                t4Numbers.get(t4Iterator).setText("5");
                t4Iterator++;
                break;
            }
            case R.id.t4t6Button: {
                t4Numbers.get(t4Iterator).setText("6");
                t4Iterator++;
                break;
            }
        }
    }


    public void colorButtonGreen() {
        if (flag1 == 1) {
            banswer1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            el2Button.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        }
        if (flag2 == 1) {
            banswer2.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            el2Button.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        }
        if (flag3 == 1) {
            banswer3.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        }
        if (flag4 == 1) {
            banswer4.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        }
    }

    private void goHome() {
        try {
            db.close();
            /*cursorQuestions.close();
            cursorAnswers.close();
            cursorTask.close();
            cursorCong.close();*/
            Intent intent = new Intent(getApplicationContext(), ThemeActivity.class);
            if (trenType == 7) {
                intent = new Intent(getApplicationContext(), IntActivity.class);
            }
            if (trenType == 4) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
            }
            intent.putExtra("id", taskId);
            startActivity(intent);
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        goHome();
    }

    public void nextQuestion() {
        try {
            flag1 = 0;
            flag2 = 0;
            flag3 = 0;
            flag4 = 0;
            check1 = 0;
            check2 = 0;
            check3 = 0;
            check4 = 0;
            typeQuestion = cursorQuestions.getInt(5);
            textQuestion.setText(cursorQuestions.getString(2));
            shint = cursorQuestions.getString(4);
            questionId = cursorQuestions.getLong(0);
            if (cursorQuestions.getBlob(3) != null) {
                byte[] image = cursorQuestions.getBlob(3);
                Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageQ.setImageBitmap(bmp);
                imageQ.setVisibility(View.VISIBLE);
            } else {
                imageQ.setVisibility(View.GONE);
            }
            cursorAnswers = db.rawQuery("select * from " + DatabaseHelper.TABLE_ANSWERS + " where " + DatabaseHelper.COLUMN_ID_QUESTION + "=?", new String[]{String.valueOf(questionId)});
            //cursorAnswers = db.rawQuery("select * from answers where id_question"+ "=?", new String[]{String.valueOf(questionId)});
            //cursorAnswers.moveToFirst();
            maptext.setVisibility(View.GONE);
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
            buttonCheck1.setTextColor(getResources().getColor(R.color.colorBlack));
            buttonCheck2.setTextColor(getResources().getColor(R.color.colorBlack));
            buttonCheck3.setTextColor(getResources().getColor(R.color.colorBlack));
            buttonCheck4.setTextColor(getResources().getColor(R.color.colorBlack));

            el1Button.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            el2Button.setBackgroundColor(getResources().getColor(R.color.colorGrey));

            switch (typeQuestion) {
                case 1: {
                    banswer4.setVisibility(View.VISIBLE);

                    type1.setVisibility(View.VISIBLE);
                    type2.setVisibility(View.GONE);
                    type3.setVisibility(View.GONE);
                    type4.setVisibility(View.GONE);
                    type5.setVisibility(View.GONE);
                    cursorAnswers.moveToPosition(0);
                    if (cursorAnswers.getInt(3) == 1) {
                        flag1 = 1;
                    } else {
                        flag1 = 0;
                    }
                    banswer1.setText(cursorAnswers.getString(2));

                    cursorAnswers.moveToPosition(1);
                    if (cursorAnswers.getInt(3) == 1) {
                        flag2 = 1;
                    } else {
                        flag2 = 0;
                    }
                    banswer2.setText(cursorAnswers.getString(2));

                    cursorAnswers.moveToPosition(2);
                    if (cursorAnswers.getInt(3) == 1) {
                        flag3 = 1;
                    } else {
                        flag3 = 0;
                    }
                    banswer3.setText(cursorAnswers.getString(2));

                    try {
                        cursorAnswers.moveToPosition(3);
                        if (cursorAnswers.getInt(3) == 1) {
                            flag4 = 1;
                        } else {
                            flag4 = 0;
                        }
                        banswer4.setText(cursorAnswers.getString(2));
                    } catch (Exception e) {
                        banswer4.setVisibility(View.GONE);
                    }
                    countQuestions++;
                    break;
                }
                case 2: {
                    type2.setVisibility(View.VISIBLE);
                    type1.setVisibility(View.GONE);
                    type3.setVisibility(View.GONE);
                    type4.setVisibility(View.GONE);
                    type5.setVisibility(View.GONE);
                    cursorAnswers.moveToPosition(0);
                    if (cursorAnswers.getInt(3) == 1) {
                        flag1 = 1;
                    } else {
                        flag1 = 0;
                    }
                    buttonCheck1.setText(cursorAnswers.getString(2));

                    cursorAnswers.moveToPosition(1);
                    if (cursorAnswers.getInt(3) == 1) {
                        flag2 = 1;
                    } else {
                        flag2 = 0;
                    }
                    buttonCheck2.setText(cursorAnswers.getString(2));

                    cursorAnswers.moveToPosition(2);
                    if (cursorAnswers.getInt(3) == 1) {
                        flag3 = 1;
                    } else {
                        flag3 = 0;
                    }
                    buttonCheck3.setText(cursorAnswers.getString(2));

                    cursorAnswers.moveToPosition(3);
                    if (cursorAnswers.getInt(3) == 1) {
                        flag4 = 1;
                    } else {
                        flag4 = 0;
                    }
                    buttonCheck4.setText(cursorAnswers.getString(2));
                    countQuestions++;
                    break;
                }
                case 3: {
                    type3.setVisibility(View.VISIBLE);
                    type1.setVisibility(View.GONE);
                    type2.setVisibility(View.GONE);
                    type4.setVisibility(View.GONE);
                    type5.setVisibility(View.GONE);
                    cursorAnswers.moveToPosition(0);
                    if (cursorAnswers.getInt(3) == 1) {
                        flag1 = 1;
                    } else {
                        flag1 = 0;
                    }
                    countQuestions++;
                    break;
                }
                case 4: {
                    type4.setVisibility(View.VISIBLE);
                    type1.setVisibility(View.GONE);
                    type2.setVisibility(View.GONE);
                    type3.setVisibility(View.GONE);
                    type5.setVisibility(View.GONE);
                    cursorAnswers.moveToPosition(0);
                    t4Answer = cursorAnswers.getString(2);
                    t4AnswerLength = cursorAnswers.getInt(3);
                    /*Toast aboutMessage = Toast.makeText(context,t4Answer +  " " + String.valueOf(t4AnswerLength), Toast.LENGTH_LONG);
                    aboutMessage.setGravity(Gravity.BOTTOM, 0, 0);
                    aboutMessage.show();*/
                    t4Iterator = 0;
                    for (int i = 0; i < t4Letters.size(); i++){
                        t4Letters.get(i).setVisibility(View.GONE);
                        t4Numbers.get(i).setVisibility(View.GONE);
                        t4Buttons.get(i).setVisibility(View.GONE);
                        t4Numbers.get(i).setText(" ");
                    }
                    for (int i = 0; i < t4AnswerLength; i++){
                        t4Letters.get(i).setVisibility(View.VISIBLE);
                        t4Numbers.get(i).setVisibility(View.VISIBLE);
                        t4Buttons.get(i).setVisibility(View.VISIBLE);
                    }
                    countQuestions++;
                    break;
                }
                case 5: {
                    type5.setVisibility(View.VISIBLE);
                    type1.setVisibility(View.GONE);
                    type2.setVisibility(View.GONE);
                    type4.setVisibility(View.GONE);
                    type3.setVisibility(View.GONE);
                    cursorAnswers.moveToPosition(0);
                    if (cursorAnswers.getInt(3) == 1) {
                        flag1 = 1;
                    } else {
                        flag1 = 0;
                    }
                    el1Button.setText(cursorAnswers.getString(2));

                    cursorAnswers.moveToPosition(1);
                    if (cursorAnswers.getInt(3) == 1) {
                        flag2 = 1;
                    } else {
                        flag2 = 0;
                    }
                    el2Button.setText(cursorAnswers.getString(2));

                    countQuestions++;
                    break;
                }

                case 7:
                    type1.setVisibility(View.VISIBLE);
                    type2.setVisibility(View.GONE);
                    type3.setVisibility(View.GONE);
                    type4.setVisibility(View.GONE);
                    cursorAnswers.moveToPosition(0);
                    if (cursorAnswers.getInt(3) == 1) {
                        flag1 = 1;
                    } else {
                        flag1 = 0;
                    }
                    banswer1.setText(cursorAnswers.getString(2));

                    cursorAnswers.moveToPosition(1);
                    if (cursorAnswers.getInt(3) == 1) {
                        flag2 = 1;
                    } else {
                        flag2 = 0;
                    }
                    banswer2.setText(cursorAnswers.getString(2));

                    cursorAnswers.moveToPosition(2);
                    if (cursorAnswers.getInt(3) == 1) {
                        flag3 = 1;
                    } else {
                        flag3 = 0;
                    }
                    banswer3.setText(cursorAnswers.getString(2));

                    cursorAnswers.moveToPosition(3);
                    if (cursorAnswers.getInt(3) == 1) {
                        flag4 = 1;
                    } else {
                        flag4 = 0;
                    }
                    banswer4.setText(cursorAnswers.getString(2));
                    countQuestions++;
                    break;
            }
            enableButtons();
        } catch (Exception e){
            e.printStackTrace();
        }
        enableButtons();
    }

    public void insertResults() {
        try {
            String datetime = "";

            Date c = Calendar.getInstance().getTime();
            //System.out.println("Current time => " + c);

            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            datetime = df.format(c);

            String res = task + "\n" + stType + "\n" + "Результат: " + String.valueOf(correctAnswers) + " из " + String.valueOf(totalQuestions)  +  " + \n" + datetime;
            //db.rawQuery("INSERT INTO results(result) values('?')",  new String[]{String.valueOf(res)});
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_RESULT, res);
            db.insert(DatabaseHelper.TABLE_RESULTS, null, values);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void disableButtons() {
        banswer1.setClickable(false);
        banswer2.setClickable(false);
        banswer3.setClickable(false);
        banswer4.setClickable(false);
        bhint.setClickable(false);
        buttonCheck1.setClickable(false);
        buttonCheck2.setClickable(false);
        buttonCheck3.setClickable(false);
        buttonCheck4.setClickable(false);
        buttonType2Go.setClickable(false);
        byes.setClickable(false);
        bno.setClickable(false);
        el1Button.setClickable(false);
        el2Button.setClickable(false);
    }

    public void enableButtons() {
        banswer1.setClickable(true);
        banswer2.setClickable(true);
        banswer3.setClickable(true);
        banswer4.setClickable(true);
        bhint.setClickable(true);
        buttonCheck1.setClickable(true);
        buttonCheck2.setClickable(true);
        buttonCheck3.setClickable(true);
        buttonCheck4.setClickable(true);
        buttonType2Go.setClickable(true);
        byes.setClickable(true);
        bno.setClickable(true);
        el1Button.setClickable(true);
        el2Button.setClickable(true);
    }


    public void showResults() {
        try {
            reslayout.setVisibility(View.VISIBLE);
            type1.setVisibility(View.GONE);
            type2.setVisibility(View.GONE);
            type3.setVisibility(View.GONE);
            type4.setVisibility(View.GONE);
            type5.setVisibility(View.GONE);
            header.setVisibility(View.GONE);
            hintlayout.setVisibility(View.GONE);
            textres1.setText("Результат: " + Integer.toString(correctAnswers) + " из " + Integer.toString(totalQuestions));
            cursorCong = db.rawQuery("select * from " + DatabaseHelper.TABLE_CONGTATS + " where " + DatabaseHelper.COLUMN_MARK + "=?", new String[]{String.valueOf(correctAnswers)});
            //cursorCong = db.rawQuery("select * from congrats where mark=? order by random()", new String[]{String.valueOf(correctAnswers)});
            if (cursorCong.getCount() > 0) {
                cursorCong.moveToFirst();
                textres2.setText(cursorCong.getString(1));
            } else {
                textres2.setText("Поздравляю!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        try {
            db.close();
            /*cursorQuestions.close();
            cursorAnswers.close();
            cursorTask.close();
            cursorCong.close();*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
