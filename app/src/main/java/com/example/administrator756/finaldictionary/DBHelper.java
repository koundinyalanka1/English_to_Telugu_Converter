package com.example.administrator756.finaldictionary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Administrator756 on 12/3/2016.
 */

public class DBHelper extends SQLiteAssetHelper {
    private Context mycontext ;
    private static String DB_NAME = "SampleDict.sqlite";
    public DBHelper(Context context){
        super(context, DB_NAME, null, 1) ;
        this.mycontext  = context;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
       if(oldVersion<newVersion){

       }

    }

}

