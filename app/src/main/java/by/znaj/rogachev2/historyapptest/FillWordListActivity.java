package by.znaj.rogachev2.historyapptest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class FillWordListActivity extends AppCompatActivity implements View.OnClickListener {

    ListView userList;
    TextView header;
    EditText userFilter;

    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_word_list);
        getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_actbar);


        header = (TextView) findViewById(R.id.header);
        userList = (ListView) findViewById(R.id.list);
        userFilter = (EditText) findViewById(R.id.userFilter);

        userList = (ListView) findViewById(R.id.list);

        initList();

        header.setText("Филворды");

        userFilter.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    // reset listview
                    initList();
                } else {
                    // perform search
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), FillWordActivity.class);
                intent.putExtra("id", (int) (long) id);
                intent.putExtra("theme", items[(int) (long) id]);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void goHome() {
        Intent intent = new Intent(getApplicationContext(), IntActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    public void onBackPressed() {
        super.onBackPressed();
        goHome();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void searchItem(String textToSearch) {
        for (String item : items) {
            if (!item.contains(textToSearch)) {
                listItems.remove(item);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void initList() {
        items = new String[]{
                "1. Древнейшие люди на белорусской земле",
                "2. Перемены в новом каменном веке",
                "3. Начало использования металлов на белорусских землях",
                "4. Население белорусских земель бронзового и железного веков",
                "5. Расселение славян",
                "6. На пути к государству",
                "7. Государственность восточных славян",
                "8. Полоцкое княжество в X—XI вв.",
                "9. Полоцкая земля в XII — первой половине XIII в.",
                "10. Туровское княжество",
                "11. Белорусские земли в условиях политической раздробленности",
                "12. Внешняя опасность в первой половине XIII в.",
                "13. Хозяйственное развитие белорусских земель и возникновение городов",
                "14. Христианизация белорусских земель",
                "15. Культура белорусских земель",
                "16. Повседневная жизнь наших предков",
                "17. Образование Великого Княжества Литовского (ВКЛ)",
                "18. Укрепление великокняжеской власти. Борьба с крестоносцами",
                "19. Княжение Ягайло. Кревская уния и ее значение",
                "20. Государственный строй ВКЛ",
                "21. Хозяйственное развитие и культура",
                "22. Население и религии в ВКЛ",
                "23. Княжение Витовта",
                "24. «Великая война» и Грюнвальдская битва",
                "25. Гражданская война 1432—1439 гг.  Изменения в государственном строе",
                "26. Войско и военное дело. Внешняя политика ВКЛ во второй половине XV в.",
                "27. Развитие феодального общества",
                "28. Развитие городов",
                "29. Этническое развитие белорусских земель. Церковь и религия",
                "30. Культура белорусских земель в конце XIV—XV в."
        };
        listItems = new ArrayList<String>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, listItems);
        userList.setAdapter(adapter);
    }
}
