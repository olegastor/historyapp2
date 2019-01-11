package by.znaj.rogachev2.historyapptest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {

    private int splashTime = 2000;
    private Thread thread;
    private ProgressBar mSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
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
        mSpinner = (ProgressBar) findViewById(R.id.Splash_ProgressBar);
        mSpinner.setIndeterminate(true);
        thread = new Thread(runable);
        thread.start();
    }
    public Runnable runable = new Runnable() {
        public void run() {
            try {
                Thread.sleep(splashTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    };
}
