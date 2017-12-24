package test.blowhorn.com.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Suhas on 12/13/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public SQLiteDatabase db=this.getWritableDatabase();
    public DatabaseHelper(Context context)
    {
        super(context, "logindetails",null,2);
    }



    @Override
    public void onCreate(SQLiteDatabase db)
    {
            String query="CREATE TABLE login (loginid TEXT,password text);";
            db.execSQL(query);

//
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
    public boolean checkLogin(String loginid,String pass)
    {
        //String queryInsert="Insert into login values('blowhorn','catbus');";
        //db.rawQuery(queryInsert,null);
        //queryInsert="Insert into login values('blowhorn123','catbus123');";
        //db.rawQuery(queryInsert,null);
        ContentValues content=new ContentValues();
        content.put("loginid","blowhorn");
        content.put("password","catbus");
        long inserted;
        inserted=db.insert("login",null,content);
        Log.v("test123",""+inserted);

        content.put("loginid","blowhorn123");
        content.put("password","catbus123");

        inserted=db.insert("login",null,content);
        Log.v("test123",""+inserted);

        String query="select * from login where loginid='"+loginid+"' and password='"+pass
                +"';";
        String queryExec="Select * from login";
        Log.v("test123",query);
        Cursor res=db.rawQuery(query,null);
        Log.v("test123",""+res.getCount());
        if(res.getCount()>0)
            return true;
        else
            return false;
    }
}
