package de.hska.trinkertinder30;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.Timestamp;
import java.util.ArrayList;

/**
 * Created by davidiwertowski on 21.12.16.
 */

public class DatabaseHelperVeranstaltung extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "veranstaltung.db";
    public static final String TABLE_NAME = "veranstaltung";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_BESCHREIBUNG = "beschreibung";
    public static final String COLUMN_KATEGORIE = "kategorie";
    public static final String COLUMN_DETAIL = "detail";
    public static final String COLUMN_VERANSTALTER = "veranstalter";
    public static final String COLUMN_TIMESTAMP = "sqltime";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "CREATE TABLE "
            +TABLE_NAME
            +" ("
            +COLUMN_ID
            +" INTEGER PRIMARY KEY AUTOINCREMENT , "
            +COLUMN_BESCHREIBUNG
            +" TEXT NOT NULL , "
            +COLUMN_DETAIL
            +" TEXT NOT NULL , "
            +COLUMN_VERANSTALTER
            +" TEXT NOT NULL , "
            + COLUMN_KATEGORIE
            +" TEXT NOT NULL , "
            + COLUMN_TIMESTAMP
            +" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL);";

    public void insertVeranstaltung(Veranstaltung veranstaltung){

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_BESCHREIBUNG, veranstaltung.getBeschreibung());
        values.put(COLUMN_DETAIL, veranstaltung.getDetail());
        values.put(COLUMN_VERANSTALTER, veranstaltung.getVeranstalter());
        values.put(COLUMN_KATEGORIE, veranstaltung.getKategorie());


        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Holt die Beschreibung zum passenden Kategorienamen
     * @param kategoriename
     * @return
     */
    public ArrayList<String> getAllBeschreibungen(String kategoriename){
        ArrayList<String> beschreibungen = new ArrayList<String>();

        // Select Query
        String selectQuery = "SELECT "+ COLUMN_BESCHREIBUNG + ", " + COLUMN_KATEGORIE  +" FROM " + TABLE_NAME + " WHERE " + COLUMN_KATEGORIE + " =  '" + kategoriename +"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null){
            if (cursor.moveToFirst()){
                do {
                    String beschreibung = cursor.getString(cursor.getColumnIndex(COLUMN_BESCHREIBUNG));
                    beschreibungen.add(beschreibung);

                } while (cursor.moveToNext());
            }
        }
        return beschreibungen;
    }


    public ArrayList<String> getDetailsAndUser(String beschreibungstext, String kategoriename){
        ArrayList<String> detailsAndUser = new ArrayList<String>();

        // Select Query
        String selectQuery = "SELECT "+ COLUMN_DETAIL + " , " + COLUMN_VERANSTALTER +" FROM " + TABLE_NAME + " WHERE " + COLUMN_BESCHREIBUNG + " =  '" + beschreibungstext +"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null){
            if (cursor.moveToFirst()){
                do {
                    String detail = cursor.getString(cursor.getColumnIndex(COLUMN_DETAIL));
                    String username = cursor.getString(cursor.getColumnIndex(COLUMN_VERANSTALTER));
                    detailsAndUser.add(detail);
                    detailsAndUser.add(username);
                } while (cursor.moveToNext());
            }
        }
        return detailsAndUser;
    }


    public ArrayList<Veranstaltung> getAllVeranstaltungen(){
        ArrayList<Veranstaltung> detailsAndUser = new ArrayList<Veranstaltung>();

        // Select Query
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " DESC LIMIT 4  ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null){
            if (cursor.moveToFirst()){
                do {
                    String beschreibung = cursor.getString(cursor.getColumnIndex(COLUMN_BESCHREIBUNG));
                    String kategorie = cursor.getString(cursor.getColumnIndex(COLUMN_KATEGORIE));
                    String username = cursor.getString(cursor.getColumnIndex(COLUMN_VERANSTALTER));
                    String timestamp = cursor.getString(cursor.getColumnIndex(COLUMN_TIMESTAMP));
                    detailsAndUser.add(new Veranstaltung(beschreibung, "",username, kategorie, timestamp));
                } while (cursor.moveToNext());
            }
        }
        return detailsAndUser;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

        this.db = db;
    }

    public DatabaseHelperVeranstaltung(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
