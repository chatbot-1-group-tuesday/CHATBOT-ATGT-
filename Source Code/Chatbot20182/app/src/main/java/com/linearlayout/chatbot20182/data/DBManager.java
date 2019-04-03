package com.linearlayout.chatbot20182.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.linearlayout.chatbot20182.Model.Law;

public class DBManager extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME ="law";
    private static final String TABLE_NAME="lawlist";
    private static final String ID = "id";
    private static final String NAME ="name";
    private static final String DESCRIPTION ="description";
    private static int VERSION =1;

    private Context context;
    //Câu truy vấn tạo thêm bảng

    String sqlQuery = "CREATE TABLE "+TABLE_NAME +" (" +
            ID +" integer primary key, "+
            NAME + " TEXT, "+
            DESCRIPTION +" TEXT) ";


    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {




    }
    public void addLaw(Law law)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,law.getmName());
        values.put(DESCRIPTION,law.getmDescription());

        db.insert(TABLE_NAME,null,values);

    }


}
