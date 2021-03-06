package com.darakeon.hsh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.darakeon.hsh.db.DBConnection;
import com.darakeon.hsh.db.Place;

public class AddActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
    }


    public void savePlaceAndBack(View view)
    {
        Place place = new Place(this);
        DBConnection db = new DBConnection(this);
        db.Save(place);

        Toast toast = Toast.makeText(this, R.string.save_success, Toast.LENGTH_SHORT);
        toast.show();

        this.finish();
    }

    public String getStringField(int r)
    {
        EditText view = (EditText)findViewById(r);
        return view.getText().toString();
    }

    public double getDoubleField(int r)
    {
        String text = getStringField(r);

        if (text.isEmpty())
            return 0;
        else
            return Double.parseDouble(text);
    }

    public Boolean getBooleanField(int r)
    {
        CheckBox view = (CheckBox)findViewById(r);
        return view.isChecked();
    }


}
