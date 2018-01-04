package com.hfad.starbuzz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Eric on 1/2/2018.
 */

public class StarbuzzDatabasehelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "starbuzz";
    private static final String TABLE_NAME = "DRINK";
    private static final String CREATE_TABLE = "CREATE TABLE DRINK ("
            +"_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            +"NAME TEXT, "
            +"DESCRIPTION TEXT, "
            +"IMAGE_RESOURCE_ID INTEGER);";
    private static final String ACTIVITY_NAME = "StarbuzzDatabaseHelper";

    public StarbuzzDatabasehelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db,0,DB_VERSION);
        Log.i(ACTIVITY_NAME,"onCreate Called!");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void insertDrink(SQLiteDatabase db, String name, String description, int resourceId){
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert(TABLE_NAME,null,drinkValues);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion < 1){
            db.execSQL(CREATE_TABLE);
            Log.i(ACTIVITY_NAME,"updateMyDatabase Called!");
            insertDrink(db,"Latte","Espresso and steamed milk",R.drawable.latte);
            insertDrink(db,"Cappuccino","Espresso, hot milk and steamed milk foam",R.drawable.cappuccino);
            insertDrink(db,"Filter","Our best drip coffee",R.drawable.filter);
        }
        if(oldVersion < 2){
            db.execSQL("ALTER TABLE "+ TABLE_NAME +" ADD COLUMN FAVORITE NUMERIC;");
        }

    }
}
