package by.znaj.rogachev2.historyapptest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MultiTasksListActivity extends AppCompatActivity {

    ListView userList;
    TextView header;
    EditText userFilter;
    Button testButton;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_tasks_list);
        getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_new);

        header = (TextView)findViewById(R.id.header);
        userList = (ListView)findViewById(R.id.list);
        userFilter = (EditText)findViewById(R.id.userFilter);
        testButton = (Button)findViewById(R.id.testbutton);

        userList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.createDatabase();

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray sp = userList.getCheckedItemPositions();

                String str="";
                for(int i=0; i < sp.size(); i++)
                {
                    int position = sp.keyAt(i);
                    if (sp.valueAt(i))
                        str+=userAdapter.getItemId(position);
                }
                Toast.makeText(getApplicationContext(), ""+str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        db = databaseHelper.open();

        userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE_TASKS, null);

        String[] headers = new String[] {DatabaseHelper.COLUMN_NAME};

        userAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_multiple_choice, userCursor, headers, new int[]{android.R.id.text1}, 0);

        //--------------------------------------------------------------------------------------------------------------------------------------------------------
        // если в текстовом поле есть текст, выполняем фильтрацию
        // данная проверка нужна при переходе от одной ориентации экрана к другой
        if(!userFilter.getText().toString().isEmpty())
            userAdapter.getFilter().filter(userFilter.getText().toString());
        // установка слушателя изменения текста
        userFilter.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userAdapter.getFilter().filter(s.toString());
            }
        });
        // устанавливаем провайдер фильтрации
        userAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                if (constraint == null || constraint.length() == 0) {
                    return db.rawQuery("select * from " + DatabaseHelper.TABLE_TASKS, null);
                }
                else {
                    return db.rawQuery("select * from " + DatabaseHelper.TABLE_TASKS + " where " +
                            DatabaseHelper.COLUMN_NAME + " like ?", new String[]{"%" + constraint.toString() + "%"});
                }
            }
        });
        //-------------------------------------------------------------------------------------------------------------------------------------------------------
        userList.setAdapter(userAdapter);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        db.close();
        userCursor.close();
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in,R.anim.right_out);
    }
}
