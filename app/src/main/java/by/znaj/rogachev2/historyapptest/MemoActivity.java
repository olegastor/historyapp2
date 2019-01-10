package by.znaj.rogachev2.historyapptest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class MemoActivity extends AppCompatActivity {

    ImageView imageView;
    long taskId;

    /*DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor cursorImage;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_new);

        imageView = (ImageView) findViewById(R.id.imageView);

        /*sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();*/

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            taskId = extras.getLong("id");
        }

        /*Drawable myDrawable = getResources().getDrawable(R.drawable.t6);*/
        /*imageView.setImageDrawable(R.drawable.t1);*/

        /*cursorImage = db.rawQuery("select * from " + DatabaseHelper.TABLE_MEMO + " where " + DatabaseHelper.COLUMN_ID_TASK + "=?", new String[]{String.valueOf(taskId)});
        if (cursorImage.getBlob(1) != null) {
            byte[] image = cursorImage.getBlob(1);
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0 , image.length);
            imageView.setImageBitmap(bmp);
        } else{
            imageView.setVisibility(View.GONE);
        }*/

        int id = Integer.valueOf(Long.toString(taskId));

        switch (id) {
            case 1: {
                /*Glide.with(this).load(R.drawable.t1).into(imageView);*/
                imageView.setImageResource(R.drawable.t1);
                /*Picasso.get().load(R.drawable.t1).into(imageView);*/
                break;
            }
            case 2: {
                imageView.setImageResource(R.drawable.t2);
                break;
            }
            case 3: {
                imageView.setImageResource(R.drawable.t3);
                break;
            }
            case 4: {
                imageView.setImageResource(R.drawable.t4);
                break;
            }
            case 5: {
                imageView.setImageResource(R.drawable.t5);
                break;
            }
            case 6: {
                imageView.setImageResource(R.drawable.t6);
                break;
            }
            case 7: {
                imageView.setImageResource(R.drawable.t7);
                break;
            }
            case 8: {
                imageView.setImageResource(R.drawable.t8);
                break;
            }
            case 9: {
                imageView.setImageResource(R.drawable.t9);
                break;
            }case 10: {
                imageView.setImageResource(R.drawable.t10);
                break;
            }
        }


    }

    public void onDestroy(){
        super.onDestroy();
        /*db.close();
        cursorImage.close();*/
    }
}
