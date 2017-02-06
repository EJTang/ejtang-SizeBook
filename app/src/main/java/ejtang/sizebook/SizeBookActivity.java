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

/**
 * The type Size book activity.
 * This is the main activity that the application will start in.
 */
public class SizeBookActivity extends AppCompatActivity {
    private ListView peopleList;
    private ArrayList<Person> people;
    private ArrayAdapter<Person> adapter;

    private static final String FILENAME = "SizeBook.sav";

    /**
     * runs specific things that we need at the start of the app
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size_book);
        peopleList = (ListView) findViewById(R.id.peopleList);

    }

    /**
     * runs specific things that will be needed
     * each time this class is called.
     * Things such as loading files and setting the adapter
     */
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

    /**
     * Load saved data
     */
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

    /**
     * calls a new activity which allows the user to add a new person to file
     * @param view
     */
    public void AddPerson (View view) {
        Intent intent = new Intent(this, AddPerson.class);
        startActivity(intent);
    }

    /**
     * calls a new activity which expands the person that the user tapped on
     * and displays the information on a new activity
     * @param editPosition
     */
    public void ExpandPerson (int editPosition) {
        Intent intent = new Intent(this, ExpandPerson.class);
        intent.putExtra("position", editPosition);
        startActivity(intent);
    }
}
