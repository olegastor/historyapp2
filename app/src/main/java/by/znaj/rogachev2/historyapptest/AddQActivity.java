package by.znaj.rogachev2.historyapptest;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddQActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    LinearLayout addType1;
    LinearLayout addType2;
    LinearLayout addType3;


    EditText editTextQuestion;
    EditText editType1A1;
    EditText editType1A2;
    EditText editType1A3;
    EditText editType1A4;
    EditText editType2A1;
    EditText editType2A2;
    EditText editType2A3;
    EditText editType2A4;
    TextView textTask;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    Cursor cursorQuestions;
    Cursor cursorAnswers;

    Spinner typesSpinner;
    RadioButton radioType1_1;
    RadioButton radioType1_2;
    RadioButton radioType1_3;
    RadioButton radioType1_4;
    RadioButton radioYes;
    CheckBox checkType2A1;
    CheckBox checkType2A2;
    CheckBox checkType2A3;
    CheckBox checkType2A4;

    Button addbutton;
    Button exportbutton;
    Button importbutton;

    long taskId = 0;
    int type = 0;

    private static final int PERMISSION_REQUEST_CODE = 100;

    File dir;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_q);

        final Context context = this;

        getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_actbar);

        textTask = (TextView) findViewById(R.id.textTask);
        editTextQuestion = (EditText) findViewById(R.id.editTextQuestion);
        typesSpinner = (Spinner) findViewById(R.id.types_spinner);

        editType1A1 = (EditText) findViewById(R.id.editType1A1);
        editType1A2 = (EditText) findViewById(R.id.editType1A2);
        editType1A3 = (EditText) findViewById(R.id.editType1A3);
        editType1A4 = (EditText) findViewById(R.id.editType1A4);
        editType2A1 = (EditText) findViewById(R.id.editType2A1);
        editType2A2 = (EditText) findViewById(R.id.editType2A2);
        editType2A3 = (EditText) findViewById(R.id.editType2A3);
        editType2A4 = (EditText) findViewById(R.id.editType2A4);
        radioType1_1 = (RadioButton) findViewById(R.id.radioType1_1);
        radioType1_2 = (RadioButton) findViewById(R.id.radioType1_2);
        radioType1_3 = (RadioButton) findViewById(R.id.radioType1_3);
        radioType1_4 = (RadioButton) findViewById(R.id.radioType1_4);
        checkType2A1 = (CheckBox) findViewById(R.id.checkType2A1);
        checkType2A2 = (CheckBox) findViewById(R.id.checkType2A2);
        checkType2A3 = (CheckBox) findViewById(R.id.checkType2A3);
        checkType2A4 = (CheckBox) findViewById(R.id.checkType2A4);
        radioYes = (RadioButton) findViewById(R.id.radio_yes);

        addType1 = (LinearLayout) findViewById(R.id.addType1);
        addType2 = (LinearLayout) findViewById(R.id.addType2);
        addType3 = (LinearLayout) findViewById(R.id.addType3);
        addbutton = (Button) findViewById(R.id.addbutton);
        exportbutton = (Button) findViewById(R.id.exportbutton);
        importbutton = findViewById(R.id.importbutton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.types_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typesSpinner.setAdapter(adapter);
        typesSpinner.setOnItemSelectedListener(this);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            taskId = extras.getLong("id");
        }
        if (taskId > 0) {
            // получаем элемент по id из бд
            try {
                userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_TASKS + " where " + DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(taskId)});
                userCursor.moveToFirst();
                textTask.setText(userCursor.getString(1));
                userCursor.close();
            } catch (Exception e) {
                textTask.setText("Ошибка!");
            }
        }

        typesSpinner.setSelection(0);
        type = 1;
        addType1.setVisibility(View.VISIBLE);
        addType2.setVisibility(View.GONE);
        addType3.setVisibility(View.GONE);

        radioType1_1.setChecked(true);
        radioType1_2.setChecked(false);
        radioType1_3.setChecked(false);
        radioType1_4.setChecked(false);

        radioType1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioType1_1.setChecked(true);
                radioType1_2.setChecked(false);
                radioType1_3.setChecked(false);
                radioType1_4.setChecked(false);
            }
        });
        radioType1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioType1_1.setChecked(false);
                radioType1_2.setChecked(true);
                radioType1_3.setChecked(false);
                radioType1_4.setChecked(false);
            }
        });
        radioType1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioType1_1.setChecked(false);
                radioType1_2.setChecked(false);
                radioType1_3.setChecked(true);
                radioType1_4.setChecked(false);
            }
        });
        radioType1_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioType1_1.setChecked(false);
                radioType1_2.setChecked(false);
                radioType1_3.setChecked(false);
                radioType1_4.setChecked(true);
            }
        });

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag1 = 0;
                int flag2 = 0;
                int flag3 = 0;
                int flag4 = 0;
                long idQ = -1;
                int count = 0;

                if (type == 1) {
                    if (editTextQuestion.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Заполните вопрос!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (editType1A1.getText().toString().matches("") || editType1A2.getText().toString().matches("") || editType1A3.getText().toString().matches("") || editType1A4.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Заполните ответы!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (radioType1_1.isChecked()) {
                        flag1 = 1;
                    }
                    if (radioType1_2.isChecked()) {
                        flag2 = 1;
                    }
                    if (radioType1_3.isChecked()) {
                        flag3 = 1;
                    }
                    if (radioType1_4.isChecked()) {
                        flag4 = 1;
                    }
                    ContentValues question = new ContentValues();
                    ContentValues answer1 = new ContentValues();
                    ContentValues answer2 = new ContentValues();
                    ContentValues answer3 = new ContentValues();
                    ContentValues answer4 = new ContentValues();
                    question.put("id_task", taskId);
                    question.put("question", editTextQuestion.getText().toString());
                    question.put("hint", "");
                    question.put("type", 1);
                    question.put("own", 1);
                    idQ = db.insert(DatabaseHelper.TABLE_QUESTIONS, null, question);
                    if (idQ != -1) {
                        answer1.put("id_question",idQ);
                        answer1.put("answer",editType1A1.getText().toString());
                        answer1.put("isCorrect",flag1);
                        answer2.put("id_question",idQ);
                        answer2.put("answer",editType1A2.getText().toString());
                        answer2.put("isCorrect",flag2);
                        answer3.put("id_question",idQ);
                        answer3.put("answer",editType1A3.getText().toString());
                        answer3.put("isCorrect",flag3);
                        answer4.put("id_question",idQ);
                        answer4.put("answer",editType1A4.getText().toString());
                        answer4.put("isCorrect",flag4);
                        if (db.insert(DatabaseHelper.TABLE_ANSWERS, null, answer1) != -1 && db.insert(DatabaseHelper.TABLE_ANSWERS, null, answer2) != -1 && db.insert(DatabaseHelper.TABLE_ANSWERS, null, answer3) != -1 && db.insert(DatabaseHelper.TABLE_ANSWERS, null, answer4) != -1){
                            Toast.makeText(getApplicationContext(), "Вопрос добавлен", Toast.LENGTH_SHORT).show();
                            clearAll();
                            return;
                        } else{
                            Toast.makeText(getApplicationContext(), "Ошибка, попробуйте позже", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Ошибка, попробуйте позже", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (type == 2) {
                    if (editTextQuestion.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Заполните вопрос!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (editType2A1.getText().toString().matches("") || editType2A2.getText().toString().matches("") || editType2A3.getText().toString().matches("") || editType2A4.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Заполните ответы!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (checkType2A1.isChecked()) {
                        flag1 = 1;
                        count++;
                    }
                    if (checkType2A2.isChecked()) {
                        flag2 = 1;
                        count++;
                    }
                    if (checkType2A3.isChecked()) {
                        flag3 = 1;
                        count++;
                    }
                    if (checkType2A4.isChecked()) {
                        flag4 = 1;
                        count++;
                    }
                    if (count < 2){
                        Toast.makeText(getApplicationContext(), "Отметьте хотя бы 2 правильных ответа", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ContentValues question = new ContentValues();
                    ContentValues answer1 = new ContentValues();
                    ContentValues answer2 = new ContentValues();
                    ContentValues answer3 = new ContentValues();
                    ContentValues answer4 = new ContentValues();
                    question.put("id_task", taskId);
                    question.put("question", editTextQuestion.getText().toString());
                    question.put("hint", "");
                    question.put("type", 2);
                    question.put("own", 1);
                    idQ = db.insert(DatabaseHelper.TABLE_QUESTIONS, null, question);
                    if (idQ != -1) {
                        answer1.put("id_question",idQ);
                        answer1.put("answer",editType2A1.getText().toString());
                        answer1.put("isCorrect",flag1);
                        answer2.put("id_question",idQ);
                        answer2.put("answer",editType2A2.getText().toString());
                        answer2.put("isCorrect",flag2);
                        answer3.put("id_question",idQ);
                        answer3.put("answer",editType2A3.getText().toString());
                        answer3.put("isCorrect",flag3);
                        answer4.put("id_question",idQ);
                        answer4.put("answer",editType2A4.getText().toString());
                        answer4.put("isCorrect",flag4);
                        if (db.insert(DatabaseHelper.TABLE_ANSWERS, null, answer1) != -1 && db.insert(DatabaseHelper.TABLE_ANSWERS, null, answer2) != -1 && db.insert(DatabaseHelper.TABLE_ANSWERS, null, answer3) != -1 && db.insert(DatabaseHelper.TABLE_ANSWERS, null, answer4) != -1){
                            Toast.makeText(getApplicationContext(), "Вопрос добавлен", Toast.LENGTH_SHORT).show();
                            clearAll();
                            return;
                        } else{
                            Toast.makeText(getApplicationContext(), "Ошибка, попробуйте позже", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Ошибка, попробуйте позже", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (type == 3) {
                    if (editTextQuestion.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Заполните вопрос!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (radioYes.isChecked()){
                        flag1 = 1;
                    }
                    ContentValues question = new ContentValues();
                    ContentValues answer1 = new ContentValues();
                    question.put("id_task", taskId);
                    question.put("question", editTextQuestion.getText().toString());
                    question.put("hint", "");
                    question.put("type", 3);
                    question.put("own", 1);
                    idQ = db.insert(DatabaseHelper.TABLE_QUESTIONS, null, question);
                    if (idQ != -1) {
                        answer1.put("id_question",idQ);
                        answer1.put("answer"," ");
                        answer1.put("isCorrect",flag1);
                        if (db.insert(DatabaseHelper.TABLE_ANSWERS, null, answer1) != -1){
                            Toast.makeText(getApplicationContext(), "Вопрос добавлен", Toast.LENGTH_SHORT).show();
                            clearAll();
                            return;
                        } else{
                            Toast.makeText(getApplicationContext(), "Ошибка, попробуйте позже, a", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Ошибка, попробуйте позже, q", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });

        importbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String state = Environment.getExternalStorageState();
                if (Environment.MEDIA_MOUNTED.equals(state)) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        if (checkPermission()) {
                            OpenFileDialog fileDialog = new OpenFileDialog(context)
                                    .setFilter(".*\\.txt")
                                    .setOpenDialogListener(new OpenFileDialog.OpenDialogListener() {
                                        @Override
                                        public void OnSelectedFile(String fileName) {
                                            //TODO import
                                            //Toast.makeText(getApplicationContext(), fileName, Toast.LENGTH_LONG).show();
                                            Toast.makeText(getApplicationContext(), "Готово", Toast.LENGTH_LONG).show();
                                        }
                                    });
                            fileDialog.show();
                        } else {
                            requestPermission(); // Code for permission
                        }
                    } else {
                        OpenFileDialog fileDialog = new OpenFileDialog(context)
                                .setFilter(".*\\.txt")
                                .setOpenDialogListener(new OpenFileDialog.OpenDialogListener() {
                                    @Override
                                    public void OnSelectedFile(String fileName) {
                                        //TODO import
                                        //Toast.makeText(getApplicationContext(), fileName, Toast.LENGTH_LONG).show();
                                        Toast.makeText(getApplicationContext(), "Готово", Toast.LENGTH_LONG).show();
                                    }
                                });
                        fileDialog.show();
                    }
                }


            }
        });

        exportbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String filepath = "";
                String datetime = "";

                Date c = Calendar.getInstance().getTime();
                //System.out.println("Current time => " + c);

                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
                datetime = df.format(c);
                String filename = "history" + datetime + ".txt";

                String state = Environment.getExternalStorageState();
                if (Environment.MEDIA_MOUNTED.equals(state)) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        if (checkPermission()) {
                            File sdcard = Environment.getExternalStorageDirectory();
                            File dir = new File(sdcard.getAbsolutePath() + "/history/");
                            dir.mkdir();
                            file = new File(dir, filename);
                            filepath = sdcard.getAbsolutePath() + "/history/" + filename;
                        } else {
                            requestPermission(); // Code for permission
                        }
                    } else {
                        File sdcard = Environment.getExternalStorageDirectory();
                        File dir = new File(sdcard.getAbsolutePath() + "/history/");
                        dir.mkdir();
                        file = new File(dir, filename);
                        filepath = sdcard.getAbsolutePath() + "/history/" + filename;
                    }
                }

                cursorQuestions = db.rawQuery("select * from " + DatabaseHelper.TABLE_QUESTIONS + " where own IN (1,2)", null);
                cursorQuestions.moveToFirst();

                int curscount = cursorQuestions.getCount();

                try
                {
                    file.createNewFile();
                    CSVWriter csvWrite = new CSVWriter(new FileWriter(file));

                    for (int i = 0; i < curscount; i++) {
                        int questionId = cursorQuestions.getInt(0);
                        //int idtask = cursorQuestions.getInt(1);
                        //String textQuestion = cursorQuestions.getString(2);
                        //String shint = cursorQuestions.getString(4);
                        //int typeQuestion = cursorQuestions.getInt(5);
                        String arrStr[] ={cursorQuestions.getString(1), cursorQuestions.getString(2), cursorQuestions.getString(3), cursorQuestions.getString(4), cursorQuestions.getString(5)};
                        csvWrite.writeNext(arrStr);
                        cursorAnswers = db.rawQuery("select * from " + DatabaseHelper.TABLE_ANSWERS + " where " + DatabaseHelper.COLUMN_ID_QUESTION + "=?", new String[]{String.valueOf(questionId)});
                        cursorAnswers.moveToFirst();
                        for (int j = 0; j < cursorAnswers.getCount(); j++){
                            String arrStr2[] = {cursorAnswers.getString(2), cursorAnswers.getString(3)};
                            //Toast.makeText(context, cursorAnswers.getString(2) + " " + cursorAnswers.getString(3),Toast.LENGTH_SHORT).show();
                            csvWrite.writeNext(arrStr2);
                            cursorAnswers.moveToNext();
                        }
                        Log.i("exportq", cursorQuestions.toString());
                        cursorQuestions.moveToNext();
                    }
                    csvWrite.close();
                    Toast.makeText(context,"Вопросы экспортированы в файл: \n" + filepath,Toast.LENGTH_SHORT).show();
                }
                catch(Exception sqlEx)
                {
                    Log.e("MainActivity1", sqlEx.getMessage(), sqlEx);
                }
                /**--------------------------------------------*/

            }
        });

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!checkPermission()) {
                    requestPermission(); // Code for permission
                }
            }
        }


    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Разрешение на запись внешнего хранилища позволяет нам создавать файлы. Пожалуйста, подтвердите это разрешение в настройках приложения.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e("value", "Permission Granted, Now you can use local drive .");
            } else {
                Log.e("value", "Permission Denied, You cannot use local drive .");
            }
            break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //typesSpinner.getSelectedItemPosition();
        if (position == 0) {
            type = 1;
            addType1.setVisibility(View.VISIBLE);
            addType2.setVisibility(View.GONE);
            addType3.setVisibility(View.GONE);
            clearAll();
        }
        if (position == 1) {
            type = 2;
            addType1.setVisibility(View.GONE);
            addType2.setVisibility(View.VISIBLE);
            addType3.setVisibility(View.GONE);
            clearAll();
        }
        if (position == 2) {
            type = 3;
            addType1.setVisibility(View.GONE);
            addType2.setVisibility(View.GONE);
            addType3.setVisibility(View.VISIBLE);
            clearAll();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void clearAll() {
        editTextQuestion.setText("");
        editType1A1.setText("");
        editType1A2.setText("");
        editType1A3.setText("");
        editType1A4.setText("");
        editType2A1.setText("");
        editType2A2.setText("");
        editType2A3.setText("");
        editType2A4.setText("");
        radioType1_1.setChecked(true);
        radioType1_2.setChecked(false);
        radioType1_3.setChecked(false);
        radioType1_4.setChecked(false);
        checkType2A1.setChecked(false);
        checkType2A2.setChecked(false);
        checkType2A3.setChecked(false);
        checkType2A4.setChecked(false);
    }

    public void onBackPressed() {
        super.onBackPressed();
        try {
            db.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            db.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
