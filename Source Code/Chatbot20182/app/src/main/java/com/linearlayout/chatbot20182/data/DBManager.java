package com.linearlayout.chatbot20182.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import com.linearlayout.chatbot20182.model.Law;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    private final String TAG = "dbManager";
    private static final String DB_NAME = "chatbotdb";
    private static final String DB_TABLE = "Law";
    private static final String COL_ID = "Id";
    private static final String COL_NAME = "Name";
    private static final String COL_DES = "Description";
   // private static final String COL_IMAGE = "Image";
    private static final String COL_ACTIVATE = "Activate";
    private static int VERSION = 1;
    private Context context;
    private String SQLiteQuery = "CREATE TABLE " + DB_TABLE + " (" +
            COL_ID + " integer primary key, " +
            COL_NAME + " TEXT, " +
            COL_DES + " TEXT, " +
           // COL_IMAGE + " BLOB, " +
            COL_ACTIVATE + " TEXT )";


    public DBManager(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
        Log.d(TAG, "DBManager: ");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLiteQuery);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: ");
    }

    public void helo() {
        Toast.makeText(context, "helo", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "helo: ");
    }

    public void addLaw(Law law) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.clear();
        values.put(COL_NAME, law.getmName());
        values.put(COL_DES, law.getmDescription());
        values.put(COL_ACTIVATE, law.getmActivate());
      //  values.put(COL_IMAGE, law.getmImage());
        db.insert(DB_TABLE, null, values);
        db.close();
        Log.d(TAG, "Add Law successfully ");
       /* String sqlite= "INSERT INTO law VALUES(null, ?, ?, ?, ?)";
        SQLiteStatement statement= db.compileStatement(sqlite);
        statement.clearBindings();
        statement.bindString(1,law.getmName() );
        statement.bindString(2, law.getmDescription());
        statement.bindBlob(3, law.getmImage());
        statement.bindString(4, "1");*/


    }

    public List<Law> getAllLaw() {
        List<Law> listLaw = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DB_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Law law = new Law();
                law.setmId(cursor.getInt(0));
                law.setmName(cursor.getString(1)+"");
                law.setmDescription(cursor.getString(2)+"");
               // law.setmImage(cursor.getBlob(3));
                law.setmActivate(cursor.getString(3)+"");
                listLaw.add(law);
            } while (cursor.moveToNext());
        }
        db.close();
        return listLaw;
    }

    public List<Law> getAllLawByName(String name) {
        List<Law> listLaw = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DB_TABLE +" WHERE name LIKE '%"+ name+"%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Law law = new Law();
                law.setmId(cursor.getInt(0));
                law.setmName(cursor.getString(1)+"");
                law.setmDescription(cursor.getString(2)+"");
               // law.setmImage(cursor.getBlob(3));
                law.setmActivate(cursor.getString(3)+"");

                listLaw.add(law);
            } while (cursor.moveToNext());
        }
        db.close();
        return listLaw;
    }
    public void updateLaw(Law law){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.clear();
        values.put(COL_NAME, law.getmName());
        values.put(COL_DES, law.getmDescription());
        values.put(COL_ACTIVATE, law.getmActivate());

        db.update(DB_TABLE,values,"Id= "+ law.getmId(),null);
        db.close();
        Log.d(TAG, "Update Law successfully ");
    }
    public int deleteLaw(int Id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DB_TABLE, COL_ID +"=?", new String[]{String.valueOf(Id)});
    }
}
