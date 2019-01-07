package by.znaj.rogachev2.historyapptest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MemoActivity extends AppCompatActivity {

    ImageView imageView = findViewById(R.id.imageView);
    long taskId;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor cursorImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            taskId = extras.getLong("id");
        }
        /*cursorImage = db.rawQuery("select * from memo where _id=?",new String[]{String.valueOf(taskId)});*/
        cursorImage = db.rawQuery("select * from " + DatabaseHelper.TABLE_MEMO + " where " + DatabaseHelper.COLUMN_ID_TASK + "=?", new String[]{String.valueOf(taskId)});
        /*if (cursorImage.getBlob(1) != null) {
            byte[] image = cursorImage.getBlob(1);
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0 , image.length);
            imageView.setImageBitmap(bmp);
        } else{
            imageView.setVisibility(View.GONE);
        }*/
    }

    public void onDestroy(){
        super.onDestroy();
        db.close();
        cursorImage.close();
    }
}
