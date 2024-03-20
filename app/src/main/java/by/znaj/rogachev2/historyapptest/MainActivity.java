package by.znaj.rogachev2.historyapptest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.main);
        getSupportActionBar().setIcon(R.drawable.ic_actbar);

        findViewById(R.id.cl4Button).setOnClickListener(this);
        findViewById(R.id.cl4MRButton).setOnClickListener(this);

        findViewById(R.id.cl6Button).setOnClickListener(this);
        findViewById(R.id.cl6ISTBELButton).setOnClickListener(this);

        findViewById(R.id.cl7Button).setOnClickListener(this);
        findViewById(R.id.cl8Button).setOnClickListener(this);
        findViewById(R.id.cl9Button).setOnClickListener(this);



        int size_coef;
        final float start_value = 0.7f; //начальное значение размера шрифта
        final float step = 0.15f; //шаг увеличения коэффициента
        Resources res = getResources();
        SharedPreferences settings = getSharedPreferences("MyAppSett", MODE_PRIVATE);
        try {
            //считываем коэффициент размера шрифта
            size_coef = settings.getInt("size_coef", 2);
        }
        catch (Exception e) {
            size_coef = 2; //коэффициент по умолчанию
        }

        //новый коэффициент увеличения шрифта
        float new_value = start_value + size_coef * step;
        //устанавливаем размер шрифта в приложении
        Configuration configuration = new Configuration(res.getConfiguration());
        configuration.fontScale = new_value;
        res.updateConfiguration(configuration, res.getDisplayMetrics());

        /*Intent intent = getIntent();
        finish();
        startActivity(intent);*/

    }

    @Override
    public void onClick(View view) {
        LinearLayout lr4 = findViewById(R.id.lr4);
        LinearLayout lr6 = findViewById(R.id.lr6);

        switch (view.getId()) {
            case R.id.cl4Button: {
                if (lr4.getVisibility() == View.GONE) {
                    lr4.setVisibility(View.VISIBLE);
                } else {
                    lr4.setVisibility(View.GONE);
                }
                /*Intent intent = new Intent(getApplicationContext(), HistActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);*/
                break;
            }
            case R.id.cl6Button: {
                if (lr6.getVisibility() == View.GONE) {
                    lr6.setVisibility(View.VISIBLE);
                } else {
                    lr6.setVisibility(View.GONE);
                }
                /*Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);*/
                break;
            }
            case R.id.cl6ISTBELButton: {
                Intent intent = new Intent(this, TasksListActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            /*case R.id.addqButton: {
                Intent intent = new Intent(getApplicationContext(), TasksListAddQActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.aboutButton: {
                /**Toast aboutMessage = Toast.makeText(getApplicationContext(), "Тестовое приложение", Toast.LENGTH_SHORT);
                aboutMessage.setGravity(Gravity.CENTER, 0, 0);
                aboutMessage.show();*/
                /*Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.settingsButton: {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.intButton: {
                Intent intent = new Intent(getApplicationContext(), IntActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }*/
        }
    }

    public void onBackPressed() {
        return;
    }

}
