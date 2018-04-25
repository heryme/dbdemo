package com.jsonparserdemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jsonparserdemo.constant.DbInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        return DbInfo.TABLE_NAME;
    }

    @Override
    protected List<DatabaseColumn> getColumns() {

        List<DatabaseColumn> databaseColumns = new ArrayList<>();

        //0) generInfoKeyId
        databaseColumns.add(new DatabaseColumn(DbInfo.TABLE_COLUMN[0], ColumnDataType.INTEGER, true));
        //1)Created_latLng
        databaseColumns.add(new DatabaseColumn(DbInfo.TABLE_COLUMN[1], ColumnDataType.TEXT));
        //2)Created_dateTime
        databaseColumns.add(new DatabaseColumn(DbInfo.TABLE_COLUMN[2], ColumnDataType.DATETIME));
        //3)Updated_latLng
        databaseColumns.add(new DatabaseColumn(DbInfo.TABLE_COLUMN[3], ColumnDataType.TEXT));
        //4)Updated_dateTime
        return databaseColumns;
    }

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
        return db.insert(DbInfo.TABLE_NAME, null, values);

        /*} else { /*//**Update row *//**//*

            String where = Achc.ACHCGeneralInfo.TABLE_COLUMN[0] +"="+achcGeneralInfoItem.getKey_Id();
            return db.update(Achc.ACHCGeneralInfo.TABLE_NAME, values, where, null);
        }*/
    }


    public void getAllACHCList() {

        // List<AchcGeneralInfoItem> achcGeneralInfoItemList = new ArrayList<AchcGeneralInfoItem>();
        String selectQuery = "SELECT * FROM " + DbInfo.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

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
                Log.d("TAG","date--->" + date);

            } while (c.moveToNext());
        }
        c.close();
    }
}
