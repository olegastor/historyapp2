package by.znaj.rogachev2.historyapptest;

import android.content.Intent;
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
    Button testButton;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    int count = 0;

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
        testButton = (Button)findViewById(R.id.testbutton);

        userList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.createDatabase();

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                SparseBooleanArray sp = userList.getCheckedItemPositions();
                ArrayList<String> selectedItems = new ArrayList<>();
                for(int i=0; i < sp.size(); i++)
                {
                    int position = sp.keyAt(i);
                    if (sp.valueAt(i)){
                        selectedItems.add(String.valueOf(userAdapter.getItemId(position)));
                        count++;
                    }
                }
                if (count < 1){
                    Toast.makeText(getApplicationContext(), "Выберите хотя бы одну тему", Toast.LENGTH_SHORT).show();
                } else{
                    String tasks = "-1";
                    for (int i = 0; i < selectedItems.size(); i++) {
                        tasks += ", ";
                        tasks += selectedItems.get(i);
                    }
                    Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                    intent.putExtra("tasks", tasks);
                    intent.putExtra("trenType", 4);
                    startActivity(intent);
//                    Toast.makeText(getApplicationContext(), tasks, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        db = databaseHelper.open();

        userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE_TASKS, null);

        String[] headers = new String[] {DatabaseHelper.COLUMN_NAME};

        userAdapter = new SimpleCursorAdapter(this, R.layout.list_item_checkbox, userCursor, headers, new int[]{android.R.id.text1}, 0);

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
