package com.bignerdranch.android.criminalintent;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

/**
 * Created by nbens_000 on 3/15/2016.
 */
public class StockCursorWrapper extends CursorWrapper {
    public StockCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Stock getStock() {
        //String uuidString = getString(getColumnIndex(StockDbSchema.StockTable.Cols.UUID));
        /*String title = getString(getColumnIndex(StockDbSchema.StockTable.Cols.TITLE));
        long date = getLong(getColumnIndex(StockDbSchema.StockTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(StockDbSchema.StockTable.Cols.SOLVED));
        String suspect = getString(getColumnIndex(StockDbSchema.StockTable.Cols.SUSPECT));*/
        String uuidString = getString(getColumnIndex(StockDbSchema.StockTable.Cols.UUID));
        String title = getString(getColumnIndex(StockDbSchema.StockTable.Cols.TITLE));
        //int weight = getInt(getColumnIndex(StockDbSchema.StockTable.Cols.WEIGHT));
        int overWeight = getInt(getColumnIndex(StockDbSchema.StockTable.Cols.OVERWEIGHT));
        int underWeight = getInt(getColumnIndex(StockDbSchema.StockTable.Cols.UNDERWEIGHT));
        int neutral = getInt(getColumnIndex(StockDbSchema.StockTable.Cols.NEUTRAL));

        Stock stock = new Stock(UUID.fromString(uuidString));
        stock.setTitle(title);
        stock.setNeutral(true);

        /*Stock crime = new Stock(UUID.fromString(uuidString));

        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);
        crime.setSuspect(suspect);*/
        return stock;

    }
}
