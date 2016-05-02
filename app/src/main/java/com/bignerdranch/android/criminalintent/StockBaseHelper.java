package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.criminalintent.StockDbSchema.StockTable;

/**
 * Created by nbens_000 on 3/14/2016.
 */
public class StockBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "stockBase.db";

    public StockBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + StockTable.NAME + "(" +
        " _id integer primary key autoincrement, " +
        StockTable.Cols.UUID + ", " +
        StockTable.Cols.TITLE + ", " +
        //StockTable.Cols.WEIGHT + ", " +
        StockTable.Cols.OVERWEIGHT + ", " +
        StockTable.Cols.UNDERWEIGHT + "," +
        StockTable.Cols.NEUTRAL + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
