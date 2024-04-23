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

public class TrenActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textTask;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long taskId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tren);

        getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_actbar);

        findViewById(R.id.treningButton).setOnClickListener(this);
        findViewById(R.id.trening2Button).setOnClickListener(this);
        findViewById(R.id.truefalseButton).setOnClickListener(this);
        findViewById(R.id.controlButton).setOnClickListener(this);
        findViewById(R.id.earlylaterButton).setOnClickListener(this);
        findViewById(R.id.sootvButton).setOnClickListener(this);
        findViewById(R.id.type7Button).setOnClickListener(this);

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
            } catch (Exception e) {
                textTask.setText("Ошибка!");
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.treningButton: {
                Intent intent = new Intent(this, TestActivity.class);
                intent.putExtra("id", taskId);
                intent.putExtra("trenType", 1);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.trening2Button: {
                Intent intent = new Intent(this, TestActivity.class);
                intent.putExtra("id", taskId);
                intent.putExtra("trenType", 2);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.truefalseButton: {
                Intent intent = new Intent(this, TestActivity.class);
                intent.putExtra("id", taskId);
                intent.putExtra("trenType", 3);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.sootvButton: {
                Intent intent = new Intent(this, TestActivity.class);
                intent.putExtra("id", taskId);
                intent.putExtra("trenType", 4);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.earlylaterButton: {
                Intent intent = new Intent(this, TestActivity.class);
                intent.putExtra("id", taskId);
                intent.putExtra("trenType", 5);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.controlButton: {
                Intent intent = new Intent(this, TestActivity.class);
                intent.putExtra("id", taskId);
                intent.putExtra("trenType", 300);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.type7Button: {
                Intent intent = new Intent(this, TestActivity.class);
                intent.putExtra("id", taskId);
                intent.putExtra("trenType", 7);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
        }
    }

    private void goHome() {
        db.close();
        Intent intent = new Intent(getApplicationContext(), ThemeActivity.class);
        intent.putExtra("id", taskId);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    public void onBackPressed() {
        super.onBackPressed();
        goHome();
    }
}
