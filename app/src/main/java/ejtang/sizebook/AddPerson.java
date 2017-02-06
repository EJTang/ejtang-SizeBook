package ejtang.sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertFalse;

public class AddPerson extends AppCompatActivity {
    EditText name;
    EditText date;
    EditText neck;
    EditText bust;
    EditText chest;
    EditText waist;
    EditText hip;
    EditText inseam;
    EditText comments;
    private static final String FILENAME = "SizeBook.sav";
    private ArrayList<Person> people;
    private Person person;
    Boolean errorFree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        loadFile();
    }

    public void savePerson(View view) {
        errorFree = true;
        name = (EditText) findViewById(R.id.nameText);
        date = (EditText) findViewById(R.id.dateText);
        neck = (EditText) findViewById(R.id.neckText);
        bust = (EditText) findViewById(R.id.bustText);
        chest = (EditText) findViewById(R.id.chestText);
        waist = (EditText) findViewById(R.id.waistText);
        hip = (EditText) findViewById(R.id.hipText);
        inseam = (EditText) findViewById(R.id.inseamText);
        comments = (EditText) findViewById(R.id.commentsText);

        if (name.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please Enter a Name",
                    Toast.LENGTH_SHORT).show();
            return;
        } else {
            person = new Person(name.getText().toString().trim());
        }

        // Taken from https://www.mkyong.com/java/how-to-check-if-date-is-valid-in-java/
        // on 2017-02-05
        // Alex Czeto showed me this URL
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            if (date.getText().toString().isEmpty()) {
                person.setDate(new Date());
            } else {
                //if not valid, it will throw ParseException
                Date newDate = sdf.parse(date.getText().toString());
                person.setDate(newDate);
            }
        } catch (ParseException e) {
            errorFree = false;
            Toast.makeText(this, "Please enter a valid format for date",
                    Toast.LENGTH_SHORT).show();
        }


        try{
            if (neck.getText().toString().isEmpty()) {
                person.setNeck(Float.parseFloat("0"));
            } else {
                person.setNeck(Float.parseFloat(neck.getText().toString()));
            }
        } catch (NumberFormatException e) {
            errorFree = false;
            Toast.makeText(this, "Please enter a valid format for neck",
                    Toast.LENGTH_SHORT).show();
        }
        try{
            if (bust.getText().toString().isEmpty()) {
                person.setBust(Float.parseFloat("0"));
            } else {
                person.setBust(Float.parseFloat(bust.getText().toString()));
            }

        } catch (NumberFormatException e) {
            errorFree = false;
            Toast.makeText(this, "Please enter a valid format for bust",
                    Toast.LENGTH_SHORT).show();
        }
        try{
            if (chest.getText().toString().isEmpty()) {
                person.setChest(Float.parseFloat("0"));
            } else {
                person.setChest(Float.parseFloat(chest.getText().toString()));
            }
        } catch (NumberFormatException e) {
            errorFree = false;
            Toast.makeText(this, "Please enter a valid format for chest",
                    Toast.LENGTH_SHORT).show();
        }
        try{
            if (waist.getText().toString().isEmpty()) {
                person.setWaist(Float.parseFloat("0"));
            } else {
                person.setWaist(Float.parseFloat(waist.getText().toString()));
            }
        } catch (NumberFormatException e) {
            errorFree = false;
            Toast.makeText(this, "Please enter a valid format for waist",
                    Toast.LENGTH_SHORT).show();
        }
        try{
            if (hip.getText().toString().isEmpty()) {
                person.setHip(Float.parseFloat("0"));
            } else {
                person.setHip(Float.parseFloat(hip.getText().toString()));
            }
        } catch (NumberFormatException e) {
            errorFree = false;
            Toast.makeText(this, "Please enter a valid format for hip",
                    Toast.LENGTH_SHORT).show();
        }
        try{
            if (inseam.getText().toString().isEmpty()) {
                person.setInseam(Float.parseFloat("0"));
            } else {
                person.setInseam(Float.parseFloat(inseam.getText().toString()));
            }
        } catch (NumberFormatException e) {
            errorFree = false;
            Toast.makeText(this, "Please enter a valid format for inseam",
                    Toast.LENGTH_SHORT).show();
        }

        person.setComment(comments.getText().toString());


        if (errorFree) {
            finish(view);
        }

    }

    public void finish (View view) {
        people.add(person);
        saveInFile();
        finish();
    }

    protected void loadFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-01-24 18:19
            Type listType = new TypeToken<ArrayList<Person>>() {
            }.getType();
            people = gson.fromJson(in, listType);


        } catch (FileNotFoundException e) {
            people = new ArrayList<Person>();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(people, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO: Handle the Exception later
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
