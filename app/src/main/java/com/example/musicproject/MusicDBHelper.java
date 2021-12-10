package com.example.musicproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MusicDBHelper {

    private static String DATABASE_NAME = "mycontacts.db";
    private static int DATABASE_VERSION = 1;

    public MusicDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCommand = "create table contact (_id integer primary key autoincrement, "
                + "songname text not null, "
                + "yearreleased text, "
                + "artsistname text, "
                + "phonenumber text, cellnumber text, "
                +"email text, birthday text);";
        sqLiteDatabase.execSQL(sqlCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(sqLiteDatabase);
    }
}

