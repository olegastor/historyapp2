package by.znaj.rogachev2.historyapptest;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class IntActivity extends AppCompatActivity implements View.OnClickListener{


    long taskId = 0;

    private final int IDD_LIST = 1;

    AlertDialog.Builder builder;

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_int);

        getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_actbar);

        findViewById(R.id.mapButton).setOnClickListener(this);
        //findViewById(R.id.imgButton).setOnClickListener(this);
        findViewById(R.id.fillButton).setOnClickListener(this);
        findViewById(R.id.fillwordButton).setOnClickListener(this);

        context = this;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mapButton: {
                taskId = 1;
                Intent intent = new Intent(this, TestActivity.class);
                intent.putExtra("id", taskId);
                intent.putExtra("trenType", 7);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            /*case R.id.imgButton: {
                Intent intent = new Intent(this, TestActivity.class);
                intent.putExtra("id", taskId);
                intent.putExtra("trenType", 8);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }*/
            case R.id.fillButton: {
                showDialog(IDD_LIST);
                /*Intent intent = new Intent(this, MakeWordActivity.class);
                intent.putExtra("id", taskId);
                intent.putExtra("trenType", 9);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);*/
                break;
            }
            case R.id.fillwordButton: {
                Intent intent = new Intent(this, FillWordActivity.class);
                intent.putExtra("id", taskId);
                intent.putExtra("trenType", 9);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
        }
    }

    private void goHome() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("id", taskId);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    public void onBackPressed() {
        super.onBackPressed();
        goHome();
    }

    @Override
    protected Dialog onCreateDialog(int id) {

        final String[] modes ={"10 слов", "На время", "Без ограничений"};

        builder = new AlertDialog.Builder(this);
        builder.setTitle("Заполни слово \n Выберите режим:"); // заголовок для диалога

        builder.setItems(modes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                //mode = item;
                Intent intent = new Intent(context, MakeWordActivity.class);
                intent.putExtra("id", taskId);
                intent.putExtra("mode", item);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        builder.setCancelable(false);
        return builder.create();
    }
}
