package com.dbinterface;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBManager {

    private SQLiteOpenHelper dbHelper;
    private SupportSQLiteOpenHelper dbRoomHelper;
    private static DBManager _instance;

    private DBManager(SQLiteOpenHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    private DBManager(SupportSQLiteOpenHelper dbHelper) {
        this.dbRoomHelper = dbHelper;
    }

    public static synchronized DBManager with(SQLiteOpenHelper dbHelper){
        if(_instance == null){
            _instance = new DBManager(dbHelper);
        }
        return _instance;
    }


    public static synchronized DBManager with(SupportSQLiteOpenHelper dbHelper){
        if(_instance == null){
            _instance = new DBManager(dbHelper);
        }
        return _instance;
    }


    public void openDatabaseInterface(Context context){
        context.startActivity(new Intent(context, DBManagerActivity.class));
    }


    static DBManager getInstance(){
        return _instance;
    }


    ArrayList<Cursor> getData(String Query){
        //get writable database
        SupportSQLiteDatabase sqlRoomDB = null;
        SQLiteDatabase sqlDB = null;
        if(dbHelper!=null) {
            sqlDB = dbHelper.getWritableDatabase();
        }
        if(dbRoomHelper!=null) {
            sqlRoomDB = dbRoomHelper.getWritableDatabase();
        }
        String[] columns = new String[] { "message" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = null;
            if(sqlDB!=null) {
                c = sqlDB.rawQuery(maxQuery, null);
            }
            if(sqlRoomDB!=null) {
                c = sqlRoomDB.query(maxQuery, null);
            }
            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }
}
