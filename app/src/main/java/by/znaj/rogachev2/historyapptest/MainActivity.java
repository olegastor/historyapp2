package by.znaj.rogachev2.historyapptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_new);

        findViewById(R.id.beginButton).setOnClickListener(this);
        findViewById(R.id.resultsButton).setOnClickListener(this);
        findViewById(R.id.controlButton).setOnClickListener(this);
        findViewById(R.id.aboutButton).setOnClickListener(this);
        findViewById(R.id.addqButton).setOnClickListener(this);
        findViewById(R.id.settingsButton).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.beginButton: {
                Intent intent = new Intent(getApplicationContext(), TasksListActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.resultsButton: {
                Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.controlButton: {
                Intent intent = new Intent(this, MultiTasksListActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.addqButton: {
                Intent intent = new Intent(getApplicationContext(), TasksListAddQActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.aboutButton: {
                /*Toast aboutMessage = Toast.makeText(getApplicationContext(), "Тестовое приложение", Toast.LENGTH_SHORT);
                aboutMessage.setGravity(Gravity.CENTER, 0, 0);
                aboutMessage.show();*/
                break;
            }
            case R.id.settingsButton: {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
        }
    }

    public void onBackPressed() {
        return;
    }

}
