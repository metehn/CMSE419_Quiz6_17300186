package com.metehanersoy.cmse419_quiz6_17300186;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextEmail;
    Button buttonSave, buttonList;

    private PersonDB v1;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Quiz6");

        editTextEmail = findViewById((R.id.editTextEmail));

        buttonSave = findViewById(R.id.buttonSave);
        buttonList = findViewById(R.id.buttonList);

        v1 = new PersonDB(this);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addStudent(editTextEmail.getText().toString());
                }finally {
                    v1.close();
                }
            }
        });

        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAll();

            }
        });

    }


    private String[] columns = {"email"};

    private void showAll() {

        list = new ArrayList<>();
        SQLiteDatabase db = v1.getReadableDatabase();
        Cursor reading = db.query("MyTable", columns, null, null, null, null, null);

        while (reading.moveToNext()) {

            String na1 = reading.getString(reading.getColumnIndex("email"));
            list.add(na1);



        }

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("array_list",list);
        startActivity(intent);

    }

    private void addStudent (String s){

        if(s.trim().length() == 0){

            Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
            return;
        }
        SQLiteDatabase db = v1.getReadableDatabase();
        db.execSQL("INSERT INTO MyTable Values('"+s+"')");
        clearText();


    }

    private void clearText(){
        editTextEmail.setText("");

    }


}