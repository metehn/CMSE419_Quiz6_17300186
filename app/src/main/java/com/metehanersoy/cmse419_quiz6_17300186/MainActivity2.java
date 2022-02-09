package com.metehanersoy.cmse419_quiz6_17300186;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ListView lv;
    ArrayAdapter<String> adapter;
    private PersonDB v2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Quiz6");

        v2 = new PersonDB(this);

        lv  = findViewById(R.id.lv);

        Bundle bundle = getIntent().getExtras();

        ArrayList<String> array = bundle.getStringArrayList("array_list");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String person = array.get(position);
                deleteStudent(person);
                array.remove(position);
                adapter.notifyDataSetChanged();


            }
        });


    }
    private void deleteStudent(String s){

        if(s.trim().length() == 0){

            Toast.makeText(this, "Please enter valid email!", Toast.LENGTH_SHORT).show();
            return;
        }
        SQLiteDatabase db = v2.getReadableDatabase();
        Cursor c =  db.rawQuery("SELECT * FROM MyTable WHERE email = '" +s+"'",null);

        if (c.moveToFirst()){
            
            db.execSQL("DELETE FROM MyTable WHERE email = '"+s+"' ");
            Toast.makeText(this, "Record Deleted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
        }



    }
}