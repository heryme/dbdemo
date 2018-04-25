package com.jsonparserdemo.constant;

/**
 * Created by Rid's Patel on 21-04-2018.
 */

public class DbInfo {

    public static final String DATABASE_NAME = "ACHC_AntiSweatTestAtCase.db";
    public static final String TABLE_NAME = "ACHC_AntiSweatTestAtCase";
    public static final String ISSUE_TYPE = "ACHC_AntiSweatTestAtCase_Issue";
    public static final String PHOTO_DIR = "/AmperagesTest";

    public static final String[] TABLE_COLUMN = new String[]{
            //0)
            "Key_Id",
            //1)
            "Created_latLng",
            //2)
            "Created_dateTime",
            //3)
            "Updated_latLng",
            //4)
            "Updated_dateTime",
            //5)
            "Project_Id",
            //6)
            "achc_key_id",
            //7)
            "Number_of_Doors_Controlled",
            //8)
            "Number_of_Doors_Not_Controlled",
            //9)
            "Amperage_Per_Door",
            //10)
            "Notes",
            //11)
            "RelayAndAmps",


    };
}
