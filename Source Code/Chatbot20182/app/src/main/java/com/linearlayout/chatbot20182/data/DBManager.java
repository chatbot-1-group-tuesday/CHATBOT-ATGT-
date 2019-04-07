package com.linearlayout.chatbot20182.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import com.linearlayout.chatbot20182.Model.Law;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper
{

    private final String Tag = "DBManager";
    private static final String DATABASE_NAME ="law";
    private static final String TABLE_NAME="lawlist";
    private static final String ID = "id";
    private static final String NAME ="name";
    private static final String DESCRIPTION ="description";
    private static int VERSION = 60;

    private Context context;
    //Câu truy vấn tạo thêm bảng

    String sqlQuery = "CREATE TABLE "+TABLE_NAME +" (" +
            ID +" integer primary key, "+
            NAME + " TEXT, "+
            DESCRIPTION +" TEXT) ";


    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context=context;
        Log.d(Tag,"DB Manager :");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlQuery);
        Log.d(Tag,"OnCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d(Tag,"OnUpgrade");


    }
    public void addLaw(Law law)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        //de them doi tuong vao CSDL thi khong the them truc tiep ma phai
        //dung ContentValues
        ContentValues values = new ContentValues();

        //Sau đó put các giá trị vào value
        values.put(NAME,law.getmName());
        values.put(DESCRIPTION,law.getmDescription());

        //Insert ra bảng
        db.insert(TABLE_NAME,null,values);
        db.close();
        Log.d(Tag,"Add Law Sucessfully");
    }

    public List<Law> getAllLaw()
    {
        List<Law> listLaw = new ArrayList<>();

        String selectQuery = "SELECT * FROM "+ TABLE_NAME;

        //giúp database có thể đọc và sửa CSDL
        SQLiteDatabase db=this.getWritableDatabase();

        //selection args : dieu kien de select , Vd :Lay luat theo id
        //cursor có mục đích lấy kết quả trả về
        //cung cấp 1 số phương thức lấy kết quả trả về
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst())
        {
            do {
                Law law = new Law();
                law.setmID(cursor.getInt(0));
                law.setmName(cursor.getString(1)+"");
                law.setmDescription(cursor.getString(2)+"");
                listLaw.add(law);
            }
            //check xem sau ket qua dau tien con ket qua nao khong
            //Nếu kết quả là false thì sẽ không tiếp tục thực hiện do
            while (cursor.moveToNext());

        }
          db.close();
          return listLaw;
    }


}
