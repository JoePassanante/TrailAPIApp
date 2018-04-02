package e.joepassanante.trailapiapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Joe Passanante on 4/2/2018.
 */

public class SearchDatabaseSource {
    private MySQLiteHelper dbhelper;
    private SQLiteDatabase database;
    private String[] allColumns = {MySQLiteHelper.COLUMN_ID,MySQLiteHelper.COLUMN_CITY,MySQLiteHelper.COLUMN_STATE,MySQLiteHelper.COLUMN_COUNTRY,MySQLiteHelper.COLUMN_NAME,};
    public SearchDatabaseSource(Context context) {
        this.dbhelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        this.database = dbhelper.getWritableDatabase();
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void close() {
        this.database.close();
    }
    public void addSearch(String country, String state, String city, String name){
        country = (country!=null)? country : "";
        state = (state!=null)? state : "";
        city = (city!=null)? city : "";
        name = (name!=null)? name : (country+"/"+state+"/"+city);
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COUNTRY,country);
        values.put(MySQLiteHelper.COLUMN_STATE,state);
        values.put(MySQLiteHelper.COLUMN_CITY,city);
        values.put(MySQLiteHelper.COLUMN_NAME,name);
        if(this.database==null){
            Log.e("No database","THERE IS NO DATABASE");
        }
        long id = database.insert(MySQLiteHelper.TABLE_COMMENTS,null,values);
      //  Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,allColumns,MySQLiteHelper.COLUMN_ID + " = ", new String[]{String.valueOf(id)},null,null,null);
        Log.i("ID",String.valueOf(id));
        //cursor.moveToFirst();
    }

    public ArrayList<Search> getAllSearchs() {
        ArrayList<Search> searches= new ArrayList<Search>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,allColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Search search = cursorToSearch(cursor);
            searches.add(search);
            cursor.moveToNext();
        }
        return searches;
    }
    //ID, CITY,STATE, COUNTRY
    private Search cursorToSearch(Cursor cursor) {
        Search s = new Search();
        Log.i("Database",""+cursor.getColumnCount());
        s.setName(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_NAME)));
        s.setCountry(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_COUNTRY)));
        s.setState(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_STATE)));
        s.setCity(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_CITY)));
        return s;
    }
}
