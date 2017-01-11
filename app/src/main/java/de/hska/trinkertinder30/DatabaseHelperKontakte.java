package de.hska.trinkertinder30;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidiwertowski on 16.12.16.
 */

public class DatabaseHelperKontakte extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contacts.db";
    public static final String TABLE_NAME = "contacts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_VORNAME = "vorname";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_UNAME = "unname";
    public static final String COLUMN_PASS = "pass";



    SQLiteDatabase db;

    private static final String TABLE_CREATE = "CREATE TABLE "
            +TABLE_NAME
            +" ("
            +COLUMN_ID
            +" INTEGER PRIMARY KEY NOT NULL , "
            +COLUMN_NAME
            +" TEXT NOT NULL , "
            +COLUMN_VORNAME
            +" TEXT NOT NULL , "
            +COLUMN_EMAIL
            +" TEXT NOT NULL , "
            +COLUMN_UNAME
            +" TEXT NOT NULL , "
            +COLUMN_PASS
            +" TEXT NOT NULL);";



    public DatabaseHelperKontakte(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<Contact> getKontaktToShow(String user){

        List<Contact> contacts = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "+ COLUMN_UNAME + " ='" + user + "'", null);

        if (cursor != null){
            if (cursor.moveToFirst()){
                do {
                    String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                    String vorname = cursor.getString(cursor.getColumnIndex(COLUMN_VORNAME));
                    String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                    String pword = cursor.getString(cursor.getColumnIndex(COLUMN_PASS));

                    contacts.add(new Contact(name, vorname, email,  pword));

                } while (cursor.moveToNext());
            }
        }
        return contacts;
    }

    public void insertContact(Contact c){

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_VORNAME, c.getVorname());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_UNAME, c.getUname());
        values.put(COLUMN_PASS, c.getPass());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPass(String uname){

        db = this.getReadableDatabase();
        String query = "SELECT  " + COLUMN_UNAME + ", " + COLUMN_PASS + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String a, b;
        b = "not found";

        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);

                if(a.equals(uname)){

                    b = cursor.getString(1);
                    break;
                }

            }
            while (cursor.moveToNext());
        }

        return b;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }


}
