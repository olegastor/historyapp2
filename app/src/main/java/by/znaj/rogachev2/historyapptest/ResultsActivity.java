package by.znaj.rogachev2.historyapptest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    ListView userList;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_actbar);

        userList = (ListView) findViewById(R.id.list);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.createDatabase();
    }

    @Override
    public void onResume() {
        super.onResume();
        db = databaseHelper.open();

        userCursor =  db.rawQuery("select * from " + DatabaseHelper.TABLE_RESULTS, null);

        String[] headers = new String[] {DatabaseHelper.COLUMN_RESULT};

        userAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, userCursor, headers, new int[]{android.R.id.text1}, 0);

        userList.setAdapter(userAdapter);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        db.close();
        userCursor.close();
    }

    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.left_in,R.anim.right_out);
    }
}
