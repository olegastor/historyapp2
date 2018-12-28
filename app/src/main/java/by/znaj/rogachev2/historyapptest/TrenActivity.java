package by.znaj.rogachev2.historyapptest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TrenActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textTask;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long taskId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tren);

        findViewById(R.id.treningButton).setOnClickListener(this);
        findViewById(R.id.truefalseButton).setOnClickListener(this);
        findViewById(R.id.controlButton).setOnClickListener(this);

        textTask = (TextView) findViewById(R.id.textTask);

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

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.treningButton: {
                Intent intent = new Intent(this, TestActivity.class);
                intent.putExtra("id", taskId);
                intent.putExtra("trenType", 1);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in,R.anim.left_out);
                break;
            }
            case R.id.truefalseButton: {
                Intent intent = new Intent(this, TestActivity.class);
                intent.putExtra("id", taskId);
                intent.putExtra("trenType", 2);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in,R.anim.left_out);
                break;
            }
            case R.id.controlButton: {
                Intent intent = new Intent(this, TestActivity.class);
                intent.putExtra("id", taskId);
                intent.putExtra("trenType", 3);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in,R.anim.left_out);
                break;
            }
        }
    }
}
