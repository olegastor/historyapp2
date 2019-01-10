package by.znaj.rogachev2.historyapptest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddQActivity extends AppCompatActivity {

    EditText editTextQuestion;
    TextView textTask;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    Spinner typesSpinner;

    long taskId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_q);

        textTask = (TextView) findViewById(R.id.textTask);
        editTextQuestion = (EditText) findViewById(R.id.editTextQuestion);
        typesSpinner = (Spinner) findViewById(R.id.types_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.types_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typesSpinner.setAdapter(adapter);

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
    }
}
