
package com.example.nilesh.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int db_version = 1;

    private static final String db_name = "Sample";

    private static final String table_name = "Demo";


    private static final String key_id = "id";
    private static final String key_name = "name";
    private static final String key_mob = "mob";




    public DatabaseHandler(Context context) {
        super(context, db_name, null, db_version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Crate table" + table_name + "(" + key_id + "Integer" + key_name + "Text" + key_mob + "Text" + ")");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exist" + table_name);

        onCreate(db);
    }

    // Adding new contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(key_name, contact.getName()); // Contact Name
        values.put(key_mob, contact.getmob()); // Contact Phone Number

        // Inserting Row
        db.insert(table_name, null, values);
        db.close(); // Closing database connection
    }


}
