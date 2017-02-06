package ejtang.sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SizeBookActivity extends AppCompatActivity {
    private ListView peopleList;
    private ArrayList<Person> people;
    private ArrayAdapter<Person> adapter;

    private static final String FILENAME = "SizeBook.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size_book);
        //loadFile();
        peopleList = (ListView) findViewById(R.id.peopleList);

    }

    protected void onStart() {
        super.onStart();
        loadFile();
        adapter = new PeopleAdapter(people,this);
        peopleList.setAdapter(adapter);
        TextView textView = (TextView) findViewById(R.id.numberOfEntries);
        textView.setText("Number of Entries: " + Integer.toString(people.size()));
        peopleList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> peopleAdapterView, View personView,
                                    int position, long id) {
                ExpandPerson(position);
            }
        });
    }

    protected void loadFile(){
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-01-24 18:19
            Type listType = new TypeToken<ArrayList<Person>>(){}.getType();
            people = gson.fromJson(in, listType);


        } catch (FileNotFoundException e) {
            people = new ArrayList<Person>();
        }

    }

    public void AddPerson (View view) {
        Intent intent = new Intent(this, AddPerson.class);
        startActivity(intent);
        //adapter.notifyDataSetChanged();
    }

    public void ExpandPerson (int editPosition) {
        Intent intent = new Intent(this, ExpandPerson.class);
        intent.putExtra("position", editPosition);
        startActivity(intent);
        //adapter.notifyDataSetChanged();
    }
}
