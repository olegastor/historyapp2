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
                "Древнейшие люди на белорусской земле",
                "Перемены в новом каменном веке",
                "Начало использования металлов на белорусских землях",
                "Население белорусских земель бронзового и железного веков",
                "Расселение славян",
                "На пути к государству",
                "Государственность восточных славян",
                "Полоцкое княжество в X—XI вв.",
                "Полоцкая земля в XII — первой половине XIII в.",
                "Туровское княжество",
                "Белорусские земли в условиях политической раздробленности",
                "Внешняя опасность в первой половине XIII в.",
                "Хозяйственное развитие белорусских земель и возникновение городов",
                "Христианизация белорусских земель",
                "Культура белорусских земель",
                "Повседневная жизнь наших предков",
                "Образование Великого Княжества Литовского (ВКЛ)",
                "Укрепление великокняжеской власти. Борьба с крестоносцами",
                "Княжение Ягайло. Кревская уния и ее значение",
                "Государственный строй ВКЛ",
                "Хозяйственное развитие и культура",
                "Население и религии в ВКЛ",
                "Княжение Витовта",
                "«Великая война» и Грюнвальдская битва",
                "Гражданская война 1432—1439 гг.  Изменения в государственном строе",
                "Войско и военное дело. Внешняя политика ВКЛ во второй половине XV в.",
                "Развитие феодального общества",
                "Развитие городов",
                "Этническое развитие белорусских земель. Церковь и религия",
                "Культура белорусских земель в конце XIV—XV в."
        };
        listItems = new ArrayList<String>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, listItems);
        userList.setAdapter(adapter);
    }
}
