package com.shaer.dietdatabase;

/**
 * Created by mohammad on 11/29/17.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;


public class dataBase extends SQLiteOpenHelper{
    public dataBase(Context context, String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create table foo(id integer primary key,name text)");
        db.execSQL("create table Food(id integer primary key,name text,calories text,sugar text,fat text, carbs text,date text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL("create table food(id integer primary key AUTO_INCREMENT,name text)");

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }


    public void Insert(String [] n){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        Calendar d = Calendar.getInstance();
        long time=0;
        d.setTimeInMillis( time );
        String date = d.get(Calendar.MONTH)+"/"+d.get( Calendar.DAY_OF_WEEK )+"/"+d.get(Calendar.DATE);



        c.put("name", n[0]);
        c.put("calories",n[1]);
        c.put("sugar",n[2]);
        c.put("fat",n[3]);
        c.put("carbs",n[4]);
        c.put("date", date );
        long id = db.insert("Food", null, c);


    }

    public LinkedList getData(Context c){
        LinkedList list = new LinkedList();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor= db.query("Food",null, null,null,null,null,null);

        while(cursor.moveToNext()){
            Node n = new Node();
            n.setName(cursor.getString(cursor.getColumnIndex("name")));
            n.setEnergy(cursor.getString(cursor.getColumnIndex("calories")));
            n.setSugar(cursor.getString(cursor.getColumnIndex("sugar")));
            n.setFats(cursor.getString(cursor.getColumnIndex("fat")));
            n.setCarbohydrates(cursor.getString(cursor.getColumnIndex("carbs")));
            n.setDate(cursor.getString(cursor.getColumnIndex("carbs")));
            list.add(n);

        }
        return list;
    }

}