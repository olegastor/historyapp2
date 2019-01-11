package by.znaj.rogachev2.historyapptest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ThemeActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textTask;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long taskId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_new);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        findViewById(R.id.beginButton).setOnClickListener(this);
        findViewById(R.id.vocabularyButton).setOnClickListener(this);
        findViewById(R.id.memButton).setOnClickListener(this);
        findViewById(R.id.videoButton).setOnClickListener(this);

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
            case R.id.beginButton: {
                //Toast aboutMessage = Toast.makeText(this,"Тестовое приложение",Toast.LENGTH_SHORT);
                Intent intent = new Intent(this, TrenActivity.class);
                intent.putExtra("id", taskId);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in,R.anim.left_out);
                break;
            }
            case R.id.memButton: {
                Intent intent = new Intent(this, MemoActivity.class);
                intent.putExtra("id", taskId);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in,R.anim.left_out);
                break;
            }
            case R.id.videoButton: {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=bSMZknDI6bg")));
                break;
            }
            case R.id.vocabularyButton: {
                Intent intent = new Intent(this, VocabularyActivity.class);
                intent.putExtra("id", taskId);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in,R.anim.left_out);
                break;
            }
        }
    }

    private void goHome() {
        db.close();
        Intent intent = new Intent(getApplicationContext(), TasksListActivity.class);
        intent.putExtra("id", taskId);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    public void onBackPressed() {
        super.onBackPressed();
        goHome();
    }
}
