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

        if (place.ID == 0)
        {
            return db.insert(Place.C.TABLE_NAME, null, values);
        }
        else
        {
            String selection = Place.C._ID + " = ?";
            String[] selectionArgs = { String.valueOf(place.ID) };

            return db.update(Place.C.TABLE_NAME, values, selection, selectionArgs);
        }
    }

    public void Delete(Place place)
    {
        SQLiteDatabase db = getWritableDatabase();

        String selection = Place.C._ID + " = ?";
        String[] selectionArgs = { String.valueOf(place.ID) };

        db.delete(Place.C.TABLE_NAME, selection, selectionArgs);
    }

    public void Update(Place place)
    {
        String selection = Place.C._ID + " = ?";
        String[] selectionArgs = { String.valueOf(place.ID) };

        SQLiteDatabase db = getWritableDatabase();
        db.delete(Place.C.TABLE_NAME, selection, selectionArgs);
    }

    public ArrayList<Place> GetAll()
    {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = Place.C.GetAll();

        String selection = Place.C.COLUMN_NAME_DISCARDED + " = ?";
        String[] selectionArgs = { "0" };

        Cursor cursor = db.query(Place.C.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

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
