package de.hska.trinkertinder30;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidiwertowski on 19.12.16.
 */

public class DatabaseHelperKategorien extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "kategorien.db";
    public static final String TABLE_NAME_KATEGORIE ="categories";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_KAT = "kategorie";

    SQLiteDatabase db;

    public static final String TABLE_CREATE_KATEGROIE = "CREATE TABLE "
            +TABLE_NAME_KATEGORIE
            +" ("
            +COLUMN_ID
            +" INTEGER PRIMARY KEY AUTOINCREMENT , "
            +COLUMN_KAT
            +" TEXT NOT NULL);";

    public static final String INSERT_SPORT = "INSERT INTO " + TABLE_NAME_KATEGORIE + " (" + COLUMN_KAT + ") Values ('Sport')";
    public static final String INSERT_ESSEN = "INSERT INTO " + TABLE_NAME_KATEGORIE + " (" + COLUMN_KAT + ") Values ('Essen')";
    public static final String INSERT_DATING = "INSERT INTO " + TABLE_NAME_KATEGORIE + " (" + COLUMN_KAT + ") Values ('Dating')";
    public static final String INSERT_KFZ = "INSERT INTO " + TABLE_NAME_KATEGORIE + " (" + COLUMN_KAT + ") Values ('KFZ')";
    public static final String INSERT_NIGHTLIFE = "INSERT INTO " + TABLE_NAME_KATEGORIE + " (" + COLUMN_KAT + ") Values ('Nightlife')";
    public static final String INSERT_ELEKTRONIK = "INSERT INTO " + TABLE_NAME_KATEGORIE + " (" + COLUMN_KAT + ") Values ('Elektronik')";
    public static final String INSERT_NACHHILFE = "INSERT INTO " + TABLE_NAME_KATEGORIE + " (" + COLUMN_KAT + ") Values ('Nachhilfe')";
    public static final String INSERT_IMMOBILIEN = "INSERT INTO " + TABLE_NAME_KATEGORIE + " (" + COLUMN_KAT + ") Values ('Immobilien')";
    public static final String INSERT_JOBS = "INSERT INTO " + TABLE_NAME_KATEGORIE + " (" + COLUMN_KAT + ") Values ('Jobs')";
    public static final String INSERT_TICKETS = "INSERT INTO " + TABLE_NAME_KATEGORIE + " (" + COLUMN_KAT + ") Values ('Tickets & Eintrittkarten')";
    public static final String INSERT_VERSCHENKEN = "INSERT INTO " + TABLE_NAME_KATEGORIE + " (" + COLUMN_KAT + ") Values ('Zu Verschenken & Tauschen')";

    public DatabaseHelperKategorien(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Holt alle Kategorienamen
     * @return
     */
    public ArrayList<String> getAllKategorien(){
        ArrayList<String> kategorien = new ArrayList<String>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME_KATEGORIE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                kategorien.add(cursor.getString(1));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return kategorien;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE_KATEGROIE);
        db.execSQL(INSERT_DATING);
        db.execSQL(INSERT_ESSEN);
        db.execSQL(INSERT_SPORT);
        db.execSQL(INSERT_KFZ);
        db.execSQL(INSERT_NIGHTLIFE);
        db.execSQL(INSERT_ELEKTRONIK);
        db.execSQL(INSERT_NACHHILFE);
        db.execSQL(INSERT_IMMOBILIEN);
        db.execSQL(INSERT_JOBS);
        db.execSQL(INSERT_TICKETS);
        db.execSQL(INSERT_VERSCHENKEN);
        this.db = db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS " + TABLE_NAME_KATEGORIE;

        db.execSQL(query);
        this.onCreate(db);

    }















}
