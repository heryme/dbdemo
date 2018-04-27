package com.jsonparserdemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.jsonparserdemo.DataModel;
import com.jsonparserdemo.constant.DbInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.jsonparserdemo.constant.DbInfo.TABLE_NAME;

/**
 * Created by Rid's Patel on 21-04-2018.
 */

public class DemoDatabase extends DatabaseService {
    private static final int VERSION = 1;
    private static Context _ctx = null;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");


    public DemoDatabase(Context context) {

        super(context, DbInfo.DATABASE_NAME, VERSION);
        this._ctx = context;

    }

    @Override
    protected String getTableName() {

        return TABLE_NAME;
    }

    @Override
    protected List<DatabaseColumn> getColumns() {

        List<DatabaseColumn> databaseColumns = new ArrayList<>();

        //0) generInfoKeyId
        databaseColumns.add(new DatabaseColumn(DbInfo.TABLE_COLUMN[0], ColumnDataType.INTEGER, true));
        //1)Created_latLng
        databaseColumns.add(new DatabaseColumn(DbInfo.TABLE_COLUMN[1], ColumnDataType.TEXT,true,true));
        //2)Created_dateTime
        databaseColumns.add(new DatabaseColumn(DbInfo.TABLE_COLUMN[2], ColumnDataType.DATETIME));
        //3)Updated_latLng
        databaseColumns.add(new DatabaseColumn(DbInfo.TABLE_COLUMN[3], ColumnDataType.TEXT));
        //4)Updated_dateTime
        return databaseColumns;
    }


    /**
     * Insert Singal data
     * @return
     */
    public long insertUpdateData() {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        //3)Updated_latLng
        values.put(DbInfo.TABLE_COLUMN[1], "JSN");
        //4)Updated_dateTime
        values.put(DbInfo.TABLE_COLUMN[2], sdf.format(new Date()));
        //5)Project_Id
        values.put(DbInfo.TABLE_COLUMN[3], "Test");
        /**
         * Insert rack information
         * 0)Key_Id
         */
        // if(achcGeneralInfoItem.getKey_Id().length() == 0) {

        //1)Created_latLng
        return db.insert(TABLE_NAME, null, values);

        /*} else { /*//**Update row *//**//*

            String where = Achc.ACHCGeneralInfo.TABLE_COLUMN[0] +"="+achcGeneralInfoItem.getKey_Id();
            return db.update(Achc.ACHCGeneralInfo.TABLE_NAME, values, where, null);
        }*/
    }


    /**
     * Insert Bulck Data
     * @param list
     */
    public void addBulckData(List<DataModel.Choices> list) {
        SQLiteDatabase database = this.getWritableDatabase();
        String in = "INSERT OR REPLACE INTO" + " " + TABLE_NAME  +"(" + DbInfo.TABLE_COLUMN[1] + "," + DbInfo.TABLE_COLUMN[2] + "," + DbInfo.TABLE_COLUMN[3] + ")VALUES(?,?,?);";
        Log.d("TAG","In-->" + in);

        SQLiteDatabase db = this.getWritableDatabase();
            db.beginTransaction();
            try {
                ContentValues values = new ContentValues();
                for (DataModel.Choices c : list) {
                    values.put(DbInfo.TABLE_COLUMN[1], c.getUrl());
                    values.put(DbInfo.TABLE_COLUMN[2], sdf.format(new Date()));
                    values.put(DbInfo.TABLE_COLUMN[3], c.getUrl());
                    db.insert(DbInfo.TABLE_NAME, null, values);
                }
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }


        /*SQLiteStatement statement = database.compileStatement(in);
        database.beginTransaction();
        try {
            for (DataModel.Choices c : list) {
                statement.clearBindings();
                statement.bindString(1, c.getUrl());
                statement.bindString(2, sdf.format(new Date()));
                statement.bindString(3, "jsn");
               // statement.bindLong(2, c.getCityName());
                statement.execute();

            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
*/


    public void getAllACHCList() {

        // List<AchcGeneralInfoItem> achcGeneralInfoItemList = new ArrayList<AchcGeneralInfoItem>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        Log.d("TAG","Cursor Size--->" + c.getCount());

        /**
         * looping through all rows and adding to list
         */
        if (c.moveToFirst()) {

            do {

                //AchcGeneralInfoItem achcGeneralInfoItem = new AchcGeneralInfoItem();
                //0)Key_Id
                //achcGeneralInfoItem.setKey_Id(c.getString((c.getColumnIndex(Achc.ACHCGeneralInfo.TABLE_COLUMN[0]))));
                String id = c.getString(0);
                String latLog = c.getString(1);
                String date = c.getString(2);

                Log.d("TAG","ID--->" + id);
                Log.d("TAG","latLog--->" + latLog);
                ///Log.d("TAG","date--->" + date);

            } while (c.moveToNext());
        }
        c.close();
    }

    /***
     * Delete Record
     */
    public int updateRecord() {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbInfo.TABLE_COLUMN[1], "JSN");

        //String where = Achc.ACHCGeneralInfo.TABLE_COLUMN[0] +"="+achcGeneralInfoItem.getKey_Id();
        String where = DbInfo.TABLE_COLUMN[0] +"="+"8";
        return db.update(DbInfo.TABLE_NAME, values, where, null);

    }

    /***
     * Delete Record
     */
    public void deleteRecord() {
        /*SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from "+ Achc.ACHCGeneralInfo.TABLE_NAME +" where "+ Achc.ACHCGeneralInfo.TABLE_COLUMN[0]+"='"+ keyId +"'");
        db.close();
        */
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from "+ DbInfo.TABLE_NAME +" where "+ DbInfo.TABLE_COLUMN[0]+"='"+ "3" +"'");
        db.close();
    }
}
