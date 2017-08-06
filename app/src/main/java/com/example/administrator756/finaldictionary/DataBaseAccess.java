package com.example.administrator756.finaldictionary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator756 on 12/9/2016.
 */

public class DataBaseAccess {
    private SQLiteOpenHelper openHelper ;
    private SQLiteDatabase database ;
    private static DataBaseAccess instance ;

    private DataBaseAccess(Context context){
        this.openHelper = new DBHelper(context) ;
    }

    public static DataBaseAccess getInstance(Context context){
        if(instance == null){
            instance = new DataBaseAccess(context) ;
        }
        return instance ;
    }

    public void open(){
        this.database = openHelper.getWritableDatabase();
    }

    public void close(){
        if(database != null){
            this.database.close();
        }
    }

    public String[] getQuery(String inp, int pos) {
        String s[] = new String[2] ;
        Cursor cursor = null;
        if(pos == 0)
            cursor = database.rawQuery("SELECT TELUGU, HINDI FROM SAMPLE where ENGLISH LIKE ' " + inp + "' ", null) ;
        else if(pos == 1)
            cursor = database.rawQuery("SELECT ENGLISH, HINDI FROM SAMPLE where TELUGU LIKE ' " + inp + "' ", null) ;
        else if(pos == 2)
            cursor = database.rawQuery("SELECT ENGLISH, TELUGU FROM SAMPLE where HINDI like ' " + inp + "' ", null) ;

        cursor.moveToFirst();
        s[0] = cursor.getString(0) ;
        s[1] = cursor.getString(1) ;
        cursor.close();
        return  s ;
    }

    public void insert_recent(String inp){
        try{
            database.execSQL("DELETE FROM RECENT WHERE NAME LIKE ' " + inp + "' ");
        }catch(Exception e){

        }
        database.execSQL("INSERT INTO RECENT VALUES ( ' " + inp.trim() + "') ");
    }
    public void del_recent(String inp){
        try{
            database.execSQL("DELETE FROM RECENT WHERE NAME LIKE '" + inp + "' ");
        }catch(Exception e){

        }

    }

    public boolean get_fav(String inp, int pos){
        Cursor cursor = null;
        if(pos == 0)
            cursor = database.rawQuery("SELECT NAME FROM FAVOURITE WHERE NAME LIKE ' " + inp + "' ", null) ;
        else if(pos == 1)
            cursor = database.rawQuery("SELECT NAME FROM FAVOURITE WHERE NAME LIKE ' " + inp + "' ", null) ;
        else if(pos == 2)
            cursor = database.rawQuery("SELECT NAME FROM FAVOURITE WHERE NAME LIKE ' " + inp + "' ", null) ;

        cursor.moveToFirst();
        if(cursor.getCount()>0)
            return true ;
        else
            return false ;
    }

    public void insert_fav(String inp){
        try{
            database.execSQL("DELETE FROM FAVOURITE WHERE NAME LIKE ' " + inp + "' ");
        }catch (Exception e){

        }
        database.execSQL("INSERT INTO FAVOURITE VALUES ( ' " + inp.trim() + "') ");

    }
    public void del_fav(String inp){
        try{
            database.execSQL("DELETE FROM FAVOURITE WHERE NAME LIKE '" + inp + "' ");
        }catch(Exception e){

        }
    }

    public void rec_trunc(){
        try{
            database.execSQL("DELETE FROM RECENT");
            database.execSQL("VACUUM");
        }catch(Exception e){

        }
    }

    public void fav_trunc(){
        try{
            database.execSQL("DELETE FROM FAVOURITE");
            database.execSQL("VACUUM");
        }catch(Exception e){

        }
    }


    public String[] getList(int l)
    {
        Cursor cursor ;
        if(l == 0)
          cursor = database.rawQuery("SELECT ENGLISH FROM SAMPLE", null);
        else if(l == 1)
            cursor = database.rawQuery("SELECT TELUGU FROM SAMPLE", null);
        else
            cursor = database.rawQuery("SELECT HINDI FROM SAMPLE", null);
        if(cursor.getCount() >0)
        {
            String[] str = new String[cursor.getCount()];
            int i = 0;
            while (cursor.moveToNext())
            {
                str[i] = cursor.getString(0);
                i++;
            }
            return str;
        }
        else
        {
            return new String[] {};
        }
    }

}
