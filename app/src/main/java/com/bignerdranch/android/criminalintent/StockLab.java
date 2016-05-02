package com.bignerdranch.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by nbens_000 on 3/1/2016.
 */
public class StockLab {
    private static StockLab sStockLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static StockLab get(Context context) {
        if (sStockLab == null) {
            sStockLab = new StockLab(context);
        }
        return sStockLab;
    }

    public void addStock(Stock c){
        ContentValues values = getContentValues(c);

        mDatabase.insert(StockDbSchema.StockTable.NAME, null, values);
    }

    public void deleteStock(Stock c) {
        mDatabase.delete(StockDbSchema.StockTable.NAME, null, null);
    }

    public List<Stock> getStocks(){
        List<Stock> stocks = new ArrayList<>();

        StockCursorWrapper cursor = queryStocks(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                stocks.add(cursor.getStock());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return stocks;
    }

    private StockLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new StockBaseHelper(mContext)
                .getWritableDatabase();
    }

    public Stock getStock(UUID id){
        StockCursorWrapper cursor = queryStocks(
                StockDbSchema.StockTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getStock();
        } finally {
            cursor.close();
        }
    }


    public void updateStock(Stock stock){
        String uuidString = stock.getId().toString();
        ContentValues values = getContentValues(stock);

        mDatabase.update(StockDbSchema.StockTable.NAME, values,
                StockDbSchema.StockTable.Cols.UUID + " = ?",
                new String[] { uuidString});
    }

    private static ContentValues getContentValues(Stock stock){
        ContentValues values = new ContentValues();
        values.put(StockDbSchema.StockTable.Cols.UUID, stock.getId().toString());
        values.put(StockDbSchema.StockTable.Cols.TITLE, stock.getTitle());
        values.put(StockDbSchema.StockTable.Cols.WEIGHT, stock.getWeight());
        values.put(StockDbSchema.StockTable.Cols.OVERWEIGHT, stock.isOverWeight() ? 1 : 0);
        values.put(StockDbSchema.StockTable.Cols.UNDERWEIGHT, stock.isUnderWeight() ? 1 : 0);
        values.put(StockDbSchema.StockTable.Cols.NEUTRAL, stock.isNeutral() ? 1 : 0);

        return values;
    }

    private StockCursorWrapper queryStocks(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                StockDbSchema.StockTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );

        return new StockCursorWrapper(cursor);
    }
}
