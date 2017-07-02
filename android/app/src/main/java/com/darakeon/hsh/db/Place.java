package com.darakeon.hsh.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.darakeon.hsh.AddActivity;
import com.darakeon.hsh.R;

import java.util.Calendar;

public class Place
{
    public long ID;
    public Calendar Creation;

    public String Code;
    public String EstateAgency;

    public String Description;

    public String Address;
    public Double Value;

    public Boolean AcceptPets;
    public Boolean AcceptKids;
    public Boolean HasBackyard;

    public Boolean BackyardApart;
    public Boolean LightApart;
    public Boolean WaterApart;

    public Boolean Discarded;

    public Place(AddActivity activity)
    {
        Creation = Calendar.getInstance();

        Code = activity.getStringField(R.id.field_code);
        EstateAgency = activity.getStringField(R.id.field_estate_agency);

        Description = activity.getStringField(R.id.field_description);

        Address = activity.getStringField(R.id.field_address);
        Value = activity.getDoubleField(R.id.field_value);

        AcceptPets = activity.getBooleanField(R.id.field_accept_pets);
        AcceptKids = activity.getBooleanField(R.id.field_accept_kids);
        HasBackyard = activity.getBooleanField(R.id.field_has_backyard);

        BackyardApart = activity.getBooleanField(R.id.field_backyard_apart);
        LightApart = activity.getBooleanField(R.id.field_light_apart);
        WaterApart = activity.getBooleanField(R.id.field_water_apart);

        Discarded = false;
    }



    public ContentValues GetDBWithID()
    {
        ContentValues values = GetDB();
        values.put(C._ID, ID);
        return values;
    }

    public ContentValues GetDB()
    {
        ContentValues values = new ContentValues();

        long unixTime = Creation.getTimeInMillis() / 1000;
        values.put(C.COLUMN_NAME_CREATION, unixTime);

        values.put(C.COLUMN_NAME_DESCRIPTION, Code);
        values.put(C.COLUMN_NAME_ESTATE_AGENCY, EstateAgency);

        values.put(C.COLUMN_NAME_DESCRIPTION, Description);

        values.put(C.COLUMN_NAME_ADDRESS, Address);
        values.put(C.COLUMN_NAME_VALUE, Value * 100);

        values.put(C.COLUMN_NAME_ACCEPT_PETS, AcceptPets ? 1 : 0);
        values.put(C.COLUMN_NAME_ACCEPT_KIDS, AcceptKids ? 1 : 0);
        values.put(C.COLUMN_NAME_HAS_BACKYARD, HasBackyard ? 1 : 0);

        values.put(C.COLUMN_NAME_BACKYARD_APART, BackyardApart ? 1 : 0);
        values.put(C.COLUMN_NAME_LIGHT_APART, LightApart ? 1 : 0);
        values.put(C.COLUMN_NAME_WATER_APART, WaterApart ? 1 : 0);

        values.put(C.COLUMN_NAME_DISCARDED, Discarded ? 1 : 0);

        return values;
    }

    public Place(Cursor cursor)
    {
        ID = cursor.getLong(cursor.getColumnIndexOrThrow(C._ID));

        long unixTime = cursor.getLong(cursor.getColumnIndexOrThrow(C.COLUMN_NAME_CREATION));
        Creation = Calendar.getInstance();
        Creation.setTimeInMillis(unixTime * 1000);

        Code = cursor.getString(cursor.getColumnIndexOrThrow(C.COLUMN_NAME_CODE));
        EstateAgency = cursor.getString(cursor.getColumnIndexOrThrow(C.COLUMN_NAME_ESTATE_AGENCY));

        Description = cursor.getString(cursor.getColumnIndexOrThrow(C.COLUMN_NAME_DESCRIPTION));

        Address = cursor.getString(cursor.getColumnIndexOrThrow(C.COLUMN_NAME_ADDRESS));
        Value = cursor.getDouble(cursor.getColumnIndexOrThrow(C.COLUMN_NAME_VALUE));

        AcceptPets = cursor.getInt(cursor.getColumnIndexOrThrow(C.COLUMN_NAME_ACCEPT_PETS)) == 1;
        AcceptKids = cursor.getInt(cursor.getColumnIndexOrThrow(C.COLUMN_NAME_ACCEPT_KIDS)) == 1;
        HasBackyard = cursor.getInt(cursor.getColumnIndexOrThrow(C.COLUMN_NAME_HAS_BACKYARD)) == 1;

        BackyardApart = cursor.getInt(cursor.getColumnIndexOrThrow(C.COLUMN_NAME_BACKYARD_APART)) == 1;
        LightApart = cursor.getInt(cursor.getColumnIndexOrThrow(C.COLUMN_NAME_LIGHT_APART)) == 1;
        WaterApart = cursor.getInt(cursor.getColumnIndexOrThrow(C.COLUMN_NAME_WATER_APART)) == 1;

        Discarded = cursor.getInt(cursor.getColumnIndexOrThrow(C.COLUMN_NAME_DISCARDED)) == 1;
    }


    public static class C implements BaseColumns
    {
        public static final String TABLE_NAME = "Place";

        public static final String COLUMN_NAME_CREATION = "Creation";

        public static final String COLUMN_NAME_CODE = "Code";
        public static final String COLUMN_NAME_ESTATE_AGENCY = "EstateAgency";

        public static final String COLUMN_NAME_DESCRIPTION = "Description";

        public static final String COLUMN_NAME_ADDRESS = "Address";
        public static final String COLUMN_NAME_VALUE = "Value";

        public static final String COLUMN_NAME_ACCEPT_PETS = "AcceptPets";
        public static final String COLUMN_NAME_ACCEPT_KIDS = "AcceptKids";
        public static final String COLUMN_NAME_HAS_BACKYARD = "HasBackyard";

        public static final String COLUMN_NAME_BACKYARD_APART = "BackyardApart";
        public static final String COLUMN_NAME_LIGHT_APART = "LightApart";
        public static final String COLUMN_NAME_WATER_APART = "WaterApart";

        public static final String COLUMN_NAME_DISCARDED = "Discarded";

        private static final String TYPE_STRING = " TEXT";
        private static final String TYPE_INT = " INTEGER";
        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                _ID + TYPE_INT + " PRIMARY KEY" + COMMA_SEP +

                COLUMN_NAME_CREATION + TYPE_INT + COMMA_SEP +

                COLUMN_NAME_CODE + TYPE_STRING + COMMA_SEP +
                COLUMN_NAME_ESTATE_AGENCY + TYPE_STRING + COMMA_SEP +

                COLUMN_NAME_DESCRIPTION + TYPE_STRING + COMMA_SEP +

                COLUMN_NAME_ADDRESS + TYPE_STRING + COMMA_SEP +
                COLUMN_NAME_VALUE + TYPE_INT + COMMA_SEP +

                COLUMN_NAME_ACCEPT_PETS + TYPE_INT + COMMA_SEP +
                COLUMN_NAME_ACCEPT_KIDS + TYPE_INT + COMMA_SEP +
                COLUMN_NAME_HAS_BACKYARD + TYPE_INT + COMMA_SEP +

                COLUMN_NAME_BACKYARD_APART + TYPE_INT + COMMA_SEP +
                COLUMN_NAME_LIGHT_APART + TYPE_INT + COMMA_SEP +
                COLUMN_NAME_WATER_APART + TYPE_INT + COMMA_SEP +
                COLUMN_NAME_DISCARDED + TYPE_INT +
            " )";

        public static String[] GetAll()
        {
            return new String[]
            {
                _ID,
                COLUMN_NAME_CREATION,
                COLUMN_NAME_CODE,
                COLUMN_NAME_ESTATE_AGENCY,
                COLUMN_NAME_DESCRIPTION,
                COLUMN_NAME_ADDRESS,
                COLUMN_NAME_VALUE,
                COLUMN_NAME_ACCEPT_PETS,
                COLUMN_NAME_ACCEPT_KIDS,
                COLUMN_NAME_HAS_BACKYARD,
                COLUMN_NAME_BACKYARD_APART,
                COLUMN_NAME_LIGHT_APART,
                COLUMN_NAME_WATER_APART,
                COLUMN_NAME_DISCARDED
            };
        }
    }
}
