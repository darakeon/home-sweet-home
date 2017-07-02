package com.darakeon.hsh.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBConnection extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "Place.db";
    private static final int DATABASE_VERSION = 1;

    public DBConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(Place.C.SQL_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }



    public long Save(Place place)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = place.GetDB();

        return db.insert(Place.C.TABLE_NAME, null, values);
    }

    public ArrayList<Place> GetAll()
    {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = Place.C.GetAll();

        //String selection = Place.C.COLUMN_NAME_DISCARDED + " = ?";
        //String[] selectionArgs = { "0" };

        //Cursor c = db.query(Place.C.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        Cursor cursor = db.query(Place.C.TABLE_NAME, projection, null, null, null, null, null);

        cursor.moveToFirst();
        ArrayList<Place> placeList = new ArrayList<>();

        if (cursor.getCount() > 0)
        {
            do
            {
                Place place = new Place(cursor);
                placeList.add(place);
            } while ((cursor.moveToNext()));
        }

        return placeList;
    }

    // How you want the results sorted in the resulting Cursor
    //String sortOrder = COLUMN_NAME_ + " DESC";

}
