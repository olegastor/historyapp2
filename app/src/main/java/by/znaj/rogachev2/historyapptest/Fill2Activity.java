package by.znaj.rogachev2.historyapptest;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Fill2Activity extends AppCompatActivity implements View.OnClickListener{
    Button but1, but2, but3, but4, but5, but6, but7, but8, but9, but10, but11, but12, but13, but14, but15, but16, buttonClear, buttonGo;
    TextView textQuestion, textDrop;
    ImageView imageQ;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor cursorQuestions;

    Handler handler;

    String stType = "";
    String word = "";

    int countQuestions = 0;
    int totalQuestions = 10;

    ArrayList<Button> buttons;
    ArrayList<String> symbols;
    ArrayList<String> chars;

    Random random;

    private final int IDD_LIST = 1;

    AlertDialog.Builder builder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill2);



        getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_actbar);

        /**------------------------------------------------------*/
        showDialog(IDD_LIST);

        handler = new Handler();

        sqlHelper = new DatabaseHelper(this);
        sqlHelper.createDatabase();
        db = sqlHelper.open();

        random = new Random();

        chars = new ArrayList<>();

        but1 = (Button) findViewById(R.id.but1);
        but2 = (Button) findViewById(R.id.but2);
        but3 = (Button) findViewById(R.id.but3);
        but4 = (Button) findViewById(R.id.but4);
        but5 = (Button) findViewById(R.id.but5);
        but6 = (Button) findViewById(R.id.but6);
        but7 = (Button) findViewById(R.id.but7);
        but8 = (Button) findViewById(R.id.but8);
        but9 = (Button) findViewById(R.id.but9);
        but10 = (Button) findViewById(R.id.but10);
        but11 = (Button) findViewById(R.id.but11);
        but12 = (Button) findViewById(R.id.but12);
        but13 = (Button) findViewById(R.id.but13);
        but14 = (Button) findViewById(R.id.but14);
        but15 = (Button) findViewById(R.id.but15);
        //but16 = (Button) findViewById(R.id.but16);


        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonGo = (Button) findViewById(R.id.buttonGo);

        textQuestion = (TextView) findViewById(R.id.textQuestion);
        textDrop = (TextView) findViewById(R.id.textDrop);
        imageQ = (ImageView) findViewById(R.id.imageQ);

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


        //cursorTask = db.rawQuery("select * from " + DatabaseHelper.TABLE_TASKS + " where " + DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(taskId)});
        //cursorTask.moveToFirst();
        //nameBox.setText(cursorTask.getString(1));
        //task = cursorTask.getString(1);
        cursorQuestions = db.rawQuery("select * from " + DatabaseHelper.TABLE_FILL2 + " ORDER BY RANDOM() LIMIT 10", null);
        stType = "Тренинг";
        cursorQuestions.moveToFirst();
        nextQuestion();

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
                if (st.equals(word)){
                    disableButtons();
                    textDrop.setBackground(getResources().getDrawable(R.drawable.r_green));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (countQuestions < totalQuestions) {
                                cursorQuestions.moveToNext();
                                nextQuestion();
                            } else {
                                showResults();
                            }
                        }
                    }, 2000);
                } else{
                    textDrop.setBackground(getResources().getDrawable(R.drawable.r_red));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        String s = String.valueOf(textDrop.getText());
        textDrop.setText(s+b.getText());
        v.setVisibility(View.INVISIBLE);
        textDrop.setBackground(getResources().getDrawable(R.drawable.rounded));
    }

    private void disableButtons(){
        buttonClear.setClickable(false);
        buttonGo.setClickable(false);
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).setClickable(false);;
        }
    }

    private void enableButtons(){
        buttonClear.setClickable(true);
        buttonGo.setClickable(true);
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).setClickable(true);;
        }
    }

    private void nextQuestion(){
        imageQ.setVisibility(View.VISIBLE);

        textQuestion.setText(cursorQuestions.getString(3));
        word = cursorQuestions.getString(2);
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
        for(int i = 0; i < strToArray.length; i++) {
            chars.add(String.valueOf(strToArray[i]));
        }

        for(int i = 0; i < symbols.size(); i++) {
            chars.add(symbols.get(i));
        }

        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).setText(chars.get(i));
        }

        clearAll();
        enableButtons();
        countQuestions++;
    }

    private void clearAll(){
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

    private void showResults(){
        Intent intent = new Intent(this, IntActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }

    private void goHome() {
        db.close();
        Intent intent = new Intent(getApplicationContext(), IntActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    public void onBackPressed() {
        super.onBackPressed();
        goHome();
    }

    @Override
    protected Dialog onCreateDialog(int id) {

                final String[] mCatsName ={"Васька", "Рыжик", "Мурзик"};

                builder = new AlertDialog.Builder(this);
                builder.setTitle("Выбираем кота"); // заголовок для диалога

                builder.setItems(mCatsName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        // TODO выбор режима
                        Toast.makeText(getApplicationContext(),
                                "Выбранный кот: " + mCatsName[item],
                                Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setCancelable(false);
                return builder.create();
        }


}
