package com.example.musicproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MusicDataSource {
    private SQLiteDatabase database;
    private MusicDBHelper dbHelper;

    public MusicDataSource(Context context) {
        dbHelper = new MusicDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertMusic(Music c) {
        boolean didSucceed = false;
        try {
            ContentValues values = new ContentValues();
            values.put("songname", c.getSongName());
            values.put("yearreleased", c.getYearRealsed());
            values.put("artist", c.getArtistName());
            Long id = (long) c.getMusicID();
            didSucceed = database.insert("contact", null, values) > 0;
        } catch (Exception e) {

        }
        return didSucceed;
    }

    public boolean updateContact(Music c) {
        boolean didSucceed = false;
        try {
            ContentValues values = new ContentValues();
            values.put("songname", c.getSongName());
            values.put("yearrealsed", c.getYearRealsed());
            values.put("artistname", c.getArtistName());
            Long id = (long) c.getMusicID();
            didSucceed = database.update("contact", values, "_id="+id, null) > 0;
        } catch (Exception e) {

        }
        return didSucceed;
    }

    public int getLastContactID() {
        int lastID = -1;
        try {
            String query = "Select MAX(_id) from contact";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            lastID = cursor.getInt(0);
            cursor.close();
        } catch (Exception e) {

        }
        return lastID;
    }

    public ArrayList<String> getContactNames() {
        ArrayList<String> names = new ArrayList<String>();
        try {
            String query = "Select contactname from contact";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                names.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {

        }
        return names;
    }


    public ArrayList<Music> getMusic() {
        ArrayList<Music> contacts = new ArrayList<Music>();
        try {
            String query = "Select * from contact";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                Music c = new Music();
                c.set(cursor.getString(1));
                c.setAddress(cursor.getString(2));
                c.setCity(cursor.getString(3));
                contacts.add(c);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {

        }
        return contacts;
    }

    public Music getContacts(int id) {
        Music new Music();
        try {
            String query = "Select * from contact where _id=+id";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            c.setContactID(id);
            c.setName(cursor.getString(1));
            c.setAddress(cursor.getString(2));
            c.setCity(cursor.getString(3));
            cursor.close();
        } catch (Exception e) {

        }
        return c;
}
