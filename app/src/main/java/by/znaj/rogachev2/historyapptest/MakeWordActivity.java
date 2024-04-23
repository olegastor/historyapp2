package by.znaj.rogachev2.historyapptest;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MakeWordActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    final private int TIME = 60000 * 5;
    Button but1, but2, but3, but4, but5, but6, but7, but8, but9, but10, but11, but12, but13, but14, but15, but16, buttonClear, buttonGo, buttonHint, buttonEnd, buttonContinue;
    TextView textQuestion, textDrop, textResult1, textResult2, textTimer;
    ImageView imageQ;
    LinearLayout mainLayout, resultLayout;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor cursorQuestions;
    Handler handler;
    String word = "";
    String hint = "";
    int count10q = 0;
    int total10q = 10;
    int countQuestions = 0;
    int totalQuestions = 10;
    int correctQuestions = 0;
    int hintFlag = 0;
    int mode = 0;
    ArrayList<Button> buttons;
    ArrayList<String> symbols;
    ArrayList<String> chars;
    Random random;
    Context context;
    long minutes;
    long seconds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_make_word);

        getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_actbar);

        context = this;

        handler = new Handler();

        sqlHelper = new DatabaseHelper(this);
        sqlHelper.createDatabase();
        db = sqlHelper.open();

        random = new Random();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mode = extras.getInt("mode");
        }

        chars = new ArrayList<>();

        but1 = findViewById(R.id.but1);
        but2 = findViewById(R.id.but2);
        but3 = findViewById(R.id.but3);
        but4 = findViewById(R.id.but4);
        but5 = findViewById(R.id.but5);
        but6 = findViewById(R.id.but6);
        but7 = findViewById(R.id.but7);
        but8 = findViewById(R.id.but8);
        but9 = findViewById(R.id.but9);
        but10 = findViewById(R.id.but10);
        but11 = findViewById(R.id.but11);
        but12 = findViewById(R.id.but12);
        but13 = findViewById(R.id.but13);
        but14 = findViewById(R.id.but14);
        but15 = findViewById(R.id.but15);
        //but16 = (Button) findViewById(R.id.but16);


        mainLayout = findViewById(R.id.mainLayout);
        resultLayout = findViewById(R.id.resultLayout);
        mainLayout.setVisibility(View.VISIBLE);
        resultLayout.setVisibility(View.GONE);

        buttonClear = findViewById(R.id.buttonClear);
        buttonGo = findViewById(R.id.buttonGo);
        buttonHint = findViewById(R.id.buttonHint);
        buttonContinue = findViewById(R.id.buttonContinue);
        buttonEnd = findViewById(R.id.buttonEnd);

        textQuestion = findViewById(R.id.textQuestion);
        textDrop = findViewById(R.id.textDrop);
        imageQ = findViewById(R.id.imageQ);
        textResult1 = findViewById(R.id.textResult1);
        textResult2 = findViewById(R.id.textResult2);
        textTimer = findViewById(R.id.textTimer);
        textTimer.setVisibility(View.GONE);

        buttonHint.setOnLongClickListener(this);

        but1.setOnClickListener(this);
        but2.setOnClickListener(this);
        but3.setOnClickListener(this);
        but4.setOnClickListener(this);
        but5.setOnClickListener(this);
        but6.setOnClickListener(this);
        but7.setOnClickListener(this);
        but8.setOnClickListener(this);
        but9.setOnClickListener(this);
        but10.setOnClickListener(this);
        but11.setOnClickListener(this);
        but12.setOnClickListener(this);
        but13.setOnClickListener(this);
        but14.setOnClickListener(this);
        but15.setOnClickListener(this);
        //but16.setOnClickListener(this);

        buttons = new ArrayList<>();
        buttons.clear();
        buttons.add(but1);
        buttons.add(but2);
        buttons.add(but3);
        buttons.add(but4);
        buttons.add(but5);
        buttons.add(but6);
        buttons.add(but7);
        buttons.add(but8);
        buttons.add(but9);
        buttons.add(but10);
        buttons.add(but11);
        buttons.add(but12);
        buttons.add(but13);
        buttons.add(but14);
        buttons.add(but15);
        //buttons.add(but16);

        symbols = new ArrayList<>();
        symbols.add("А");
        symbols.add("Б");
        symbols.add("В");
        symbols.add("Г");
        symbols.add("Д");
        symbols.add("Е");
        symbols.add("Ж");
        symbols.add("З");
        symbols.add("И");
        symbols.add("К");
        symbols.add("Л");
        symbols.add("М");
        symbols.add("Н");
        symbols.add("Ц");
        symbols.add("У");
        symbols.add("Ч");
        symbols.add("Т");
        symbols.add("Ш");
        symbols.add("Щ");
        symbols.add("О");
        symbols.add("П");
        symbols.add("Р");


        //cursorQuestions = db.rawQuery("select * from " + DatabaseHelper.TABLE_FILL2 + " ORDER BY RANDOM() LIMIT 10", null);
        cursorQuestions = db.rawQuery("select * from " + DatabaseHelper.TABLE_FILL2 + " ORDER BY RANDOM()", null);
        cursorQuestions.moveToFirst();
        nextQuestion();
        if (mode == 0) {
            totalQuestions = 10;
        } else if (mode == 1) {
            totalQuestions = cursorQuestions.getCount();
            new CountDownTimer(TIME, 1000) {

                public void onTick(long millisUntilFinished) {
                    minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                    seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - 60 * minutes;
                    //textTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
                    if (seconds < 10) {
                        textTimer.setText(minutes + " : 0" + seconds);
                    } else
                        textTimer.setText(minutes + " : " + seconds);
                }

                public void onFinish() {
                    //textTimer.setText("done!");
                    showResults();
                }
            }.start();
            textTimer.setVisibility(View.VISIBLE);

        } else if (mode == 2) {
            totalQuestions = cursorQuestions.getCount();
        }


        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
            }
        });

        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String st = String.valueOf(textDrop.getText());
                if (st.equals(word)) {
                    disableButtons();
                    textDrop.setBackground(getResources().getDrawable(R.drawable.r_green));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (hintFlag == 0) {
                                correctQuestions++;
                            }
                            switch (mode) {
                                case 0:
                                case 1: {
                                    if (countQuestions < totalQuestions) {
                                        cursorQuestions.moveToNext();
                                        nextQuestion();
                                    } else {
                                        showResults();
                                    }
                                    break;
                                }
                                case 2: {
                                    if (countQuestions < totalQuestions && count10q < total10q) {
                                        cursorQuestions.moveToNext();
                                        nextQuestion();
                                    } else {
                                        showResults();
                                    }
                                    break;
                                }
                            }
                        }
                    }, 2000);
                } else {
                    textDrop.setBackground(getResources().getDrawable(R.drawable.r_red));
                }
            }
        });

        buttonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, IntActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                if (mode == 2) {
                    insertResults();
                }
            }
        });

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count10q = 0;
                mainLayout.setVisibility(View.VISIBLE);
                resultLayout.setVisibility(View.GONE);
                nextQuestion();
            }
        });

    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        String s = String.valueOf(textDrop.getText());
        textDrop.setText(s + b.getText());
        v.setVisibility(View.INVISIBLE);
        textDrop.setBackground(getResources().getDrawable(R.drawable.rounded));
    }

    private void disableButtons() {
        buttonClear.setClickable(false);
        buttonGo.setClickable(false);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setClickable(false);
        }
    }

    private void enableButtons() {
        buttonClear.setClickable(true);
        buttonGo.setClickable(true);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setClickable(true);
        }
    }

    private void nextQuestion() {

        countQuestions++;
        count10q++;
        hintFlag = 0;
        imageQ.setVisibility(View.VISIBLE);
        textQuestion.setText(cursorQuestions.getString(3));
        word = cursorQuestions.getString(2);
        hint = cursorQuestions.getString(2);
        if (cursorQuestions.getBlob(4) != null) {
            byte[] image = cursorQuestions.getBlob(4);
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            imageQ.setImageBitmap(bmp);
        } else {
            imageQ.setVisibility(View.GONE);
        }

        Collections.shuffle(buttons);
        Collections.shuffle(symbols);

        chars.clear();

        char[] strToArray = word.toCharArray();
        for (char c : strToArray) {
            chars.add(String.valueOf(c));
        }

        for (int i = 0; i < symbols.size(); i++) {
            chars.add(symbols.get(i));
        }

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setText(chars.get(i));
        }

        clearAll();
        enableButtons();
    }

    private void clearAll() {
        but1.setVisibility(View.VISIBLE);
        but2.setVisibility(View.VISIBLE);
        but3.setVisibility(View.VISIBLE);
        but4.setVisibility(View.VISIBLE);
        but5.setVisibility(View.VISIBLE);
        but6.setVisibility(View.VISIBLE);
        but7.setVisibility(View.VISIBLE);
        but8.setVisibility(View.VISIBLE);
        but9.setVisibility(View.VISIBLE);
        but10.setVisibility(View.VISIBLE);
        but11.setVisibility(View.VISIBLE);
        but12.setVisibility(View.VISIBLE);
        but13.setVisibility(View.VISIBLE);
        but14.setVisibility(View.VISIBLE);
        but15.setVisibility(View.VISIBLE);
        //but16.setVisibility(View.VISIBLE);

        textDrop.setBackground(getResources().getDrawable(R.drawable.rounded));
        textDrop.setText("");
    }

    private void showResults() {
        /*Intent intent = new Intent(this, IntActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);*/
        mainLayout.setVisibility(View.GONE);
        resultLayout.setVisibility(View.VISIBLE);
        textResult2.setVisibility(View.GONE);
        switch (mode) {
            case 0: {
                textResult1.setText("Ваш результат: " + correctQuestions + " из " + totalQuestions);
                buttonContinue.setVisibility(View.GONE);
                insertResults();
                break;
            }
            case 1: {
                countQuestions--;
                textResult1.setText("Ваш результат: " + correctQuestions + " из " + countQuestions);
                buttonContinue.setVisibility(View.GONE);
                insertResults();
                break;
            }
            case 2: {
                textResult1.setText("Ваш результат: " + correctQuestions + " из " + countQuestions);
                textResult2.setText("Желаете продолжить?");
                textResult2.setVisibility(View.VISIBLE);
                break;
            }
        }

    }

    private void goHome() {
        try {
            db.close();
            //cursorQuestions.close();
        } catch (Exception ex) {

        }
        Intent intent = new Intent(getApplicationContext(), IntActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    public void onBackPressed() {
        super.onBackPressed();
        goHome();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            db.close();
            //cursorQuestions.close();
        } catch (Exception ex) {

        }
    }

    @Override
    public boolean onLongClick(View view) {
        //Toast.makeText(this, hint, Toast.LENGTH_SHORT).show();
        hintFlag = 1;
        textDrop.setText(word);
        buttonGo.callOnClick();
        return false;
    }

    public void insertResults() {
        try {
            String datetime = "";
            String res_ = "";

            Date c = Calendar.getInstance().getTime();
            //System.out.println("Current time => " + c);

            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            datetime = df.format(c);

            String stType = "";
            switch (mode) {
                case 0: {
                    stType = "10 слов";
                    res_ = "Результат: " + correctQuestions + " из " + totalQuestions;
                    break;
                }
                case 1: {
                    stType = "На время";
                    res_ = "Результат: " + correctQuestions + " из " + countQuestions;
                    break;
                }
                case 2: {
                    stType = "Без ограничений";
                    res_ = "Результат: " + correctQuestions + " из " + countQuestions;
                    break;
                }
            }

            String res = "Выбери слово" + "\n" + stType + "\n" + res_;
            //db.rawQuery("INSERT INTO results(result) values('?')",  new String[]{String.valueOf(res)});
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_RESULT, res);
            db.insert(DatabaseHelper.TABLE_RESULTS, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
