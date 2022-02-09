package com.metehanersoy.cmse419_quiz6_17300186;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonDB extends SQLiteOpenHelper {
    private static final String person_db="person";
    private static final int Version =1;


    public PersonDB(Context c ){

        super(c,person_db,null,Version);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  MyTable(email TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS MyTable");
        onCreate(db);

    }
}

