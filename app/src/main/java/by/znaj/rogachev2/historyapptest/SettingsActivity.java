package by.znaj.rogachev2.historyapptest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_actbar);

        final float start_value = 0.7f; //начальное значение размера шрифта
        final float step = 0.15f; //шаг увеличения коэффициента
        int size_coef; //выбранный коэффициент

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        Resources res = getResources();
        SharedPreferences settings = getSharedPreferences("MyAppSett", MODE_PRIVATE);
        try {
            //считываем коэффициент размера шрифта
            size_coef = settings.getInt("size_coef", 2);
        }
        catch (Exception e) {
            size_coef = 2; //коэффициент по умолчанию
        }

        if (seekBar != null) {
            seekBar.setProgress(size_coef);
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    int size_coef = progress; //выбранный коэффициент
                    //активируем параметры
                    SharedPreferences settings = getSharedPreferences("MyAppSett", MODE_PRIVATE);
                    SharedPreferences.Editor value_add = settings.edit();
                    //заносим новый коэффициент увеличения шрифта
                    value_add.putInt("size_coef", size_coef);
                    value_add.apply();
                    //устанавливаем размер шрифта в приложении
                    Resources res = getResources();
                    float  сoef = start_value + size_coef * step; //новый коэффициент увеличения шрифта
                    Configuration configuration = new Configuration(res.getConfiguration());
                    configuration.fontScale = сoef;
                    res.updateConfiguration(configuration, res.getDisplayMetrics());
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }
}
