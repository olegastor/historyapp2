package by.znaj.rogachev2.historyapptest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class VocabularyActivity extends AppCompatActivity {

    ListView userList;
    TextView header;
    EditText userFilter;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    Cursor cursorTask;
    Cursor defineCursor;
    SimpleCursorAdapter userAdapter;
    Context context;

    AlertDialog.Builder ad;
    AlertDialog alert;

    long taskId = 0;
    String word = "";
    String define = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            taskId = extras.getLong("id");
        }

        context = getBaseContext();
        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.createDatabase();
        db = databaseHelper.open();

        header = (TextView) findViewById(R.id.header);
        userList = (ListView) findViewById(R.id.list);
        userFilter = (EditText) findViewById(R.id.userFilter);

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                defineCursor = db.rawQuery("select * from "+ DatabaseHelper.TABLE_VOCABULARY + " where " + DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(id)});
                defineCursor.moveToFirst();
                word = defineCursor.getString(2);
                define = defineCursor.getString(3);
                ad = new AlertDialog.Builder(VocabularyActivity.this);
                ad.setTitle(word).setMessage(define).setCancelable(false).setNegativeButton("ОК", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert = ad.create();
                alert.show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        cursorTask = db.rawQuery("select * from " + DatabaseHelper.TABLE_TASKS + " where " + DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(taskId)});
        cursorTask.moveToFirst();
        header.setText(cursorTask.getString(1));

        userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE_VOCABULARY + " where " + DatabaseHelper.COLUMN_ID_TASK + "=?", new String[]{String.valueOf(taskId)});

        String[] headers = new String[] {DatabaseHelper.COLUMN_WORD};

        userAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, userCursor, headers, new int[]{android.R.id.text1}, 0);

        //--------------------------------------------------------------------------------------------------------------------------------------------------------
        // если в текстовом поле есть текст, выполняем фильтрацию
        // данная проверка нужна при переходе от одной ориентации экрана к другой
        if(!userFilter.getText().toString().isEmpty())
            userAdapter.getFilter().filter(userFilter.getText().toString());
        // установка слушателя изменения текста
        userFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) { }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            // при изменении текста выполняем фильтрацию
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userAdapter.getFilter().filter(s.toString());
            }
        });
        // устанавливаем провайдер фильтрации
        userAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                if (constraint == null || constraint.length() == 0) {
                    return db.rawQuery("select * from "+ DatabaseHelper.TABLE_VOCABULARY + " where " + DatabaseHelper.COLUMN_ID_TASK + "=?", new String[]{String.valueOf(taskId)});
                }
                else {
                    return db.rawQuery("select * from "+ DatabaseHelper.TABLE_VOCABULARY + " where " + DatabaseHelper.COLUMN_ID_TASK + "=? and " + DatabaseHelper.COLUMN_WORD + " like ?", new String[]{String.valueOf(taskId), "%" + constraint.toString() + "%"});
                }
            }
        });
        //-------------------------------------------------------------------------------------------------------------------------------------------------------

        userList.setAdapter(userAdapter);
    }

    /*@Override
    public void onDestroy(){
        super.onDestroy();
        db.close();
        userCursor.close();
        cursorTask.close();
        defineCursor.close();
    }*/

    /*public void onBackPressed() {
        overridePendingTransition(R.anim.left_in,R.anim.right_out);
    }*/
}
