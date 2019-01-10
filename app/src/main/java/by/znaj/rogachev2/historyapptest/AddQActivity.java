package by.znaj.rogachev2.historyapptest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class AddQActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    LinearLayout addType1;
    LinearLayout addType2;
    LinearLayout addType3;

    EditText editTextQuestion;
    TextView textTask;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    Spinner typesSpinner;
    RadioButton radioType1_1;
    RadioButton radioType1_2;
    RadioButton radioType1_3;
    RadioButton radioType1_4;

    long taskId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_q);

        getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_new);

        textTask = (TextView) findViewById(R.id.textTask);
        editTextQuestion = (EditText) findViewById(R.id.editTextQuestion);
        typesSpinner = (Spinner) findViewById(R.id.types_spinner);

        addType1 = (LinearLayout) findViewById(R.id.addType1);
        addType2 = (LinearLayout) findViewById(R.id.addType2);
        addType3 = (LinearLayout) findViewById(R.id.addType3);

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
            } catch (Exception e){
                textTask.setText("Ошибка!");
            }
        }

        typesSpinner.setSelection(0);
        addType1.setVisibility(View.VISIBLE);
        addType2.setVisibility(View.GONE);
        addType3.setVisibility(View.GONE);

        radioType1_1 = (RadioButton) findViewById(R.id.radioType1_1);
        radioType1_2 = (RadioButton) findViewById(R.id.radioType1_2);
        radioType1_3 = (RadioButton) findViewById(R.id.radioType1_3);
        radioType1_4 = (RadioButton) findViewById(R.id.radioType1_4);

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
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //typesSpinner.getSelectedItemPosition();
        if (position == 0){
            addType1.setVisibility(View.VISIBLE);
            addType2.setVisibility(View.GONE);
            addType3.setVisibility(View.GONE);
        }
        if (position == 1){
            addType1.setVisibility(View.GONE);
            addType2.setVisibility(View.VISIBLE);
            addType3.setVisibility(View.GONE);
        }
        if (position == 2){
            addType1.setVisibility(View.GONE);
            addType2.setVisibility(View.GONE);
            addType3.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
