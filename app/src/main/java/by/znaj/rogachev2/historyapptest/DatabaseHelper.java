package by.znaj.rogachev2.historyapptest;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH;
    private static final String DATABASE_NAME = "history7.db";
    private static final int SCHEMA = 1;
    public static final String TABLE_TASKS = "tasks";
    public static final String TABLE_QUESTIONS = "questions";
    public static final String TABLE_ANSWERS = "answers";
    public static final String TABLE_RESULTS = "results";
    public static final String TABLE_VOCABULARY = "vocabulary";
    public static final String TABLE_CONGTATS = "congrats";
    public static final String TABLE_MEMO = "memo";
    public static final String TABLE_FILL2 = "fillword";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ID_TASK = "id_task";
    public static final String COLUMN_ID_QUESTION = "id_question";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_HINT = "hint";
    public static final String COLUMN_RESULT = "result";
    public static final String COLUMN_WORD = "word";
    public static final String COLUMN_MARK = "mark";
    public static final String COLUMN_CLASS = "class";
    private Context myContext;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
        this.myContext = context;
        DB_PATH = context.getFilesDir().getPath() + DATABASE_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void createDatabase() {
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
                this.getReadableDatabase();
                myInput = myContext.getAssets().open(DATABASE_NAME);
                String outFileName = DB_PATH;
                myOutput = new FileOutputStream(outFileName);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        } catch (FileNotFoundException e) {
            Log.d("DatabaseHelper", e.getMessage());
        } catch (IOException e) {
            Log.d("DatabaseHelper", e.getMessage());
        }
    }

    public SQLiteDatabase open() throws SQLException {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
}
