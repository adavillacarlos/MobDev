package com.example.villacarlos.mydemodb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String ID = "ID";
    public static final String CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //table creation
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CUSTOMER_NAME + " TEXT, " + CUSTOMER_AGE + " INT, " + ACTIVE_CUSTOMER + " BOOL)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //none
    }

    public boolean addCustomer(CustomerModel cm){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CUSTOMER_NAME,cm.getName());
        cv.put(CUSTOMER_AGE,cm.getAge());
        cv.put(ACTIVE_CUSTOMER,cm.isActive());

        long insert = db.insert(CUSTOMER_TABLE, null, cv);

        if(insert !=-1){
            return true;
        } else
            return false;
    }

    public List<CustomerModel> getList(){
        List <CustomerModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do{
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                int customerAge = cursor.getInt(2);
                boolean customerIsActive = cursor.getInt(3)==1 ? true:false;

                CustomerModel cm = new CustomerModel(customerID,customerName,customerAge,customerIsActive);

                returnList.add(cm);

            }while(cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return returnList;
    }


    public boolean deleteCustomer(CustomerModel cm){
        SQLiteDatabase db = this.getWritableDatabase();
        String qs = "DELETE FROM " + CUSTOMER_TABLE + " WHERE " + ID + " = " + cm.getId();
        Cursor c = db.rawQuery(qs, null);
        if(c.moveToFirst())
            return true;
        else
            return false;

    }

    public boolean updateCustomer(CustomerModel cm){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CUSTOMER_NAME,cm.getName());
        cv.put(CUSTOMER_AGE,cm.getAge());
        cv.put(ACTIVE_CUSTOMER,cm.isActive());
        long update = db.update(CUSTOMER_TABLE,cv,"ID =" + cm.getId(), null);
        if(update==-1)
            return false;
        else
            return true;
    }


    // or implement the update
    public boolean updateModel(CustomerModel cm){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CUSTOMER_NAME,cm.getName());
        cv.put(CUSTOMER_AGE,cm.getAge());
        cv.put(ACTIVE_CUSTOMER,cm.isActive());
        db.update("CUSTOMER_TABLE",cv,"ACTIVE_CUSTOMER=?",new String[]{String.valueOf(ACTIVE_CUSTOMER)});
        return true;
    }

}
