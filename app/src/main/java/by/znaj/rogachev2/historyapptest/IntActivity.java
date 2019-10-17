package by.znaj.rogachev2.historyapptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class IntActivity extends AppCompatActivity implements View.OnClickListener{


    long taskId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_int);

        getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_new);

        findViewById(R.id.mapButton).setOnClickListener(this);
        //findViewById(R.id.imgButton).setOnClickListener(this);
        findViewById(R.id.fillButton).setOnClickListener(this);

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
                Intent intent = new Intent(this, Fill2Activity.class);
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
}
