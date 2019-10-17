package by.znaj.rogachev2.historyapptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Fill2Activity extends AppCompatActivity implements View.OnClickListener{
    Button but1, but2, but3, but4, but5, but6, buttonClear, buttonGo;
    TextView textQuestion, textDrop;
    ImageView imageQ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill2);

        but1 = (Button) findViewById(R.id.but1);
        but2 = (Button) findViewById(R.id.but2);
        but3 = (Button) findViewById(R.id.but3);
        but4 = (Button) findViewById(R.id.but4);
        but5 = (Button) findViewById(R.id.but5);
        but6 = (Button) findViewById(R.id.but6);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonGo = (Button) findViewById(R.id.buttonGo);

        textQuestion = (TextView) findViewById(R.id.textQuestion);
        textDrop = (TextView) findViewById(R.id.textDrop);
        imageQ = (ImageView) findViewById(R.id.imageQ);

        but1.setOnClickListener(this);
        but2.setOnClickListener(this);
        but3.setOnClickListener(this);
        but4.setOnClickListener(this);
        but5.setOnClickListener(this);
        but6.setOnClickListener(this);

        textQuestion.setVisibility(View.VISIBLE);
        textQuestion.setText("Полоцкий мастер-ювелир. По заказу Е.Полоцкой для Спасской церкви Евфросиниевского монастыря сделал шестиконечный крест. Утрачен в годы ВОВ");

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                but1.setVisibility(View.VISIBLE);
                but2.setVisibility(View.VISIBLE);
                but3.setVisibility(View.VISIBLE);
                but4.setVisibility(View.VISIBLE);
                but5.setVisibility(View.VISIBLE);
                but6.setVisibility(View.VISIBLE);
                textDrop.setBackground(getResources().getDrawable(R.drawable.rounded));
                textDrop.setText("");
            }
        });
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String st = String.valueOf(textDrop.getText());
                if (st.equals("БОГША")){
                    textDrop.setBackground(getResources().getDrawable(R.drawable.r_green));
                } else{
                    textDrop.setBackground(getResources().getDrawable(R.drawable.r_red));
                }


            }
        });
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        String s = String.valueOf(textDrop.getText());
        textDrop.setText(s+b.getText());
        v.setVisibility(View.INVISIBLE);
    }
}
