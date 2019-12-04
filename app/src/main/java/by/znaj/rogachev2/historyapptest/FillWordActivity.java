package by.znaj.rogachev2.historyapptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class FillWordActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    GridLayout grid;
    TextView task;
    TextView temp;

    ArrayList<TextView> tvs;
    ArrayList<String> chars;
    ArrayList<Integer> flags;
    ArrayList<Integer> checked;
    Button clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_word);

        getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_actbar);

        task = findViewById(R.id.name);
        clear = findViewById(R.id.clear);
        task.setVisibility(View.VISIBLE);

        grid = findViewById(R.id.grid1);
        //grid.setOnTouchListener(this);

        String word = "ЛЕДКРОМАНЬНЦНАКЕЧНИОЕЕИКННВСКНАЛТОЯООТМЕНАСРОКЛВАЦДТСТРБООМРЕРОЁОЫЛЕОЕКОПЬДРПМНЗСКРЕБКАЯТЦРАВЕНСТВОЫ";

        chars = new ArrayList<>();
        tvs = new ArrayList<>();

        flags = new ArrayList<>();
        checked = new ArrayList<>();
        flags.clear();
        checked.clear();

        for (int i = 0; i < 100; i++){
            flags.add(i);
            checked.add(i);

        }

        tvs.clear();
        tvs.add((TextView) findViewById(R.id.tv0));
        tvs.add((TextView) findViewById(R.id.tv1));
        tvs.add((TextView) findViewById(R.id.tv2));
        tvs.add((TextView) findViewById(R.id.tv3));
        tvs.add((TextView) findViewById(R.id.tv4));
        tvs.add((TextView) findViewById(R.id.tv5));
        tvs.add((TextView) findViewById(R.id.tv6));
        tvs.add((TextView) findViewById(R.id.tv7));
        tvs.add((TextView) findViewById(R.id.tv8));
        tvs.add((TextView) findViewById(R.id.tv9));
        tvs.add((TextView) findViewById(R.id.tv10));
        tvs.add((TextView) findViewById(R.id.tv11));
        tvs.add((TextView) findViewById(R.id.tv12));
        tvs.add((TextView) findViewById(R.id.tv13));
        tvs.add((TextView) findViewById(R.id.tv14));
        tvs.add((TextView) findViewById(R.id.tv15));
        tvs.add((TextView) findViewById(R.id.tv16));
        tvs.add((TextView) findViewById(R.id.tv17));
        tvs.add((TextView) findViewById(R.id.tv18));
        tvs.add((TextView) findViewById(R.id.tv19));
        tvs.add((TextView) findViewById(R.id.tv20));
        tvs.add((TextView) findViewById(R.id.tv21));
        tvs.add((TextView) findViewById(R.id.tv22));
        tvs.add((TextView) findViewById(R.id.tv23));
        tvs.add((TextView) findViewById(R.id.tv24));
        tvs.add((TextView) findViewById(R.id.tv25));
        tvs.add((TextView) findViewById(R.id.tv26));
        tvs.add((TextView) findViewById(R.id.tv27));
        tvs.add((TextView) findViewById(R.id.tv28));
        tvs.add((TextView) findViewById(R.id.tv29));
        tvs.add((TextView) findViewById(R.id.tv30));
        tvs.add((TextView) findViewById(R.id.tv31));
        tvs.add((TextView) findViewById(R.id.tv32));
        tvs.add((TextView) findViewById(R.id.tv33));
        tvs.add((TextView) findViewById(R.id.tv34));
        tvs.add((TextView) findViewById(R.id.tv35));
        tvs.add((TextView) findViewById(R.id.tv36));
        tvs.add((TextView) findViewById(R.id.tv37));
        tvs.add((TextView) findViewById(R.id.tv38));
        tvs.add((TextView) findViewById(R.id.tv39));
        tvs.add((TextView) findViewById(R.id.tv40));
        tvs.add((TextView) findViewById(R.id.tv41));
        tvs.add((TextView) findViewById(R.id.tv42));
        tvs.add((TextView) findViewById(R.id.tv43));
        tvs.add((TextView) findViewById(R.id.tv44));
        tvs.add((TextView) findViewById(R.id.tv45));
        tvs.add((TextView) findViewById(R.id.tv46));
        tvs.add((TextView) findViewById(R.id.tv47));
        tvs.add((TextView) findViewById(R.id.tv48));
        tvs.add((TextView) findViewById(R.id.tv49));
        tvs.add((TextView) findViewById(R.id.tv50));
        tvs.add((TextView) findViewById(R.id.tv51));
        tvs.add((TextView) findViewById(R.id.tv52));
        tvs.add((TextView) findViewById(R.id.tv53));
        tvs.add((TextView) findViewById(R.id.tv54));
        tvs.add((TextView) findViewById(R.id.tv55));
        tvs.add((TextView) findViewById(R.id.tv56));
        tvs.add((TextView) findViewById(R.id.tv57));
        tvs.add((TextView) findViewById(R.id.tv58));
        tvs.add((TextView) findViewById(R.id.tv59));
        tvs.add((TextView) findViewById(R.id.tv60));
        tvs.add((TextView) findViewById(R.id.tv61));
        tvs.add((TextView) findViewById(R.id.tv62));
        tvs.add((TextView) findViewById(R.id.tv63));
        tvs.add((TextView) findViewById(R.id.tv64));
        tvs.add((TextView) findViewById(R.id.tv65));
        tvs.add((TextView) findViewById(R.id.tv66));
        tvs.add((TextView) findViewById(R.id.tv67));
        tvs.add((TextView) findViewById(R.id.tv68));
        tvs.add((TextView) findViewById(R.id.tv69));
        tvs.add((TextView) findViewById(R.id.tv70));
        tvs.add((TextView) findViewById(R.id.tv71));
        tvs.add((TextView) findViewById(R.id.tv72));
        tvs.add((TextView) findViewById(R.id.tv73));
        tvs.add((TextView) findViewById(R.id.tv74));
        tvs.add((TextView) findViewById(R.id.tv75));
        tvs.add((TextView) findViewById(R.id.tv76));
        tvs.add((TextView) findViewById(R.id.tv77));
        tvs.add((TextView) findViewById(R.id.tv78));
        tvs.add((TextView) findViewById(R.id.tv79));
        tvs.add((TextView) findViewById(R.id.tv80));
        tvs.add((TextView) findViewById(R.id.tv81));
        tvs.add((TextView) findViewById(R.id.tv82));
        tvs.add((TextView) findViewById(R.id.tv83));
        tvs.add((TextView) findViewById(R.id.tv84));
        tvs.add((TextView) findViewById(R.id.tv85));
        tvs.add((TextView) findViewById(R.id.tv86));
        tvs.add((TextView) findViewById(R.id.tv87));
        tvs.add((TextView) findViewById(R.id.tv88));
        tvs.add((TextView) findViewById(R.id.tv89));
        tvs.add((TextView) findViewById(R.id.tv90));
        tvs.add((TextView) findViewById(R.id.tv91));
        tvs.add((TextView) findViewById(R.id.tv92));
        tvs.add((TextView) findViewById(R.id.tv93));
        tvs.add((TextView) findViewById(R.id.tv94));
        tvs.add((TextView) findViewById(R.id.tv95));
        tvs.add((TextView) findViewById(R.id.tv96));
        tvs.add((TextView) findViewById(R.id.tv97));
        tvs.add((TextView) findViewById(R.id.tv98));
        tvs.add((TextView) findViewById(R.id.tv99));

        char[] strToArray = word.toCharArray();
        for(int i = 0; i < strToArray.length; i++) {
            chars.add(String.valueOf(strToArray[i]));
            tvs.get(i).setText(chars.get(i));
            tvs.get(i).setOnTouchListener(this);

            //chars.add(String.valueOf(strToArray[i]));
        }

        /*for (int i = 0; i < 100; i++) {

            temp = (TextView) grid.getChildAt(i);
            temp.setMaxWidth(10);
            temp.setText(String.valueOf(i));
            temp.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
            temp.setOnTouchListener(this);
                /*temp = new TextView(getApplicationContext());
                temp.setWidth(R.dimen.grid_size);
                temp.setHeight(R.dimen.grid_size);
                temp.setText("F");
                temp.setGravity(TextView.TEXT_ALIGNMENT_CENTER);
                temp.setLayoutParams(new LinearLayout.LayoutParams(5, R.dimen.fab_margin, R.dimen.fab_margin));
                GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                param.setGravity(Gravity.CENTER);
                param.columnSpec = GridLayout.spec(i);
                param.rowSpec = GridLayout.spec(j);
                temp.setLayoutParams(param);
                temp.setText("F");
                grid.addView(temp, i,j );

            }*/
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    private void checkedClear(){
        for (int i = 0;  i< checked.size(); i++){
            checked.set(i, 0);
        }

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int[] w1 = {0,1,2,12,22,23};
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            checked.set(tvs.indexOf(view), 1);
            Toast.makeText(getApplicationContext(),String.valueOf(tvs.indexOf(view)),Toast.LENGTH_SHORT).show();

            int f1 = 1;

            /*for (int i = 0; i < w1.length; i++){
                if (!Arrays.asList(checked).contains(w1[i])){
                    f1 = 1;
                }
            }*/

            for (int i = 0; i < 100; i++){
                for (int j = 0; j < w1.length; j++){
                    if (i == w1[j] && checked.get(i) != 1)
                        f1 = 0;
                    if (i != w1[j] && checked.get(i) == 1)
                        f1 = 0;
                }
            }

            if (f1 == 1){
                Toast.makeText(getApplicationContext(),"slovo",Toast.LENGTH_SHORT).show();
                for (int i = 0; i < checked.size(); i++){
                    if (checked.get(i) == 1){
                        tvs.get(i).setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        tvs.get(i).setOnTouchListener(null);
                    }
                }
            }

        }


        /*if (motionEvent.getAction() == MotionEvent.ACTION_MOVE){
            Toast.makeText(this, "move", Toast.LENGTH_SHORT).show();
        }
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            for (int i = 0; i < 100; i++) {
                temp = (Button) grid.getChildAt(i);
                temp.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                Toast.makeText(this, "up", Toast.LENGTH_SHORT).show();
            }
        }*/

        return false;
    }
}
