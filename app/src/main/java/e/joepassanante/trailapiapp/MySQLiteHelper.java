package e.joepassanante.trailapiapp;

import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Joe Passanante on 4/2/2018.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_COMMENTS = "site";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_STATE = "state";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_NAME = "name";

    public static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "(" + COLUMN_ID + " integer primary key autoincrement, " // we use default so that if null/empty strings get added, they automatically change to something easily readable.
            + COLUMN_CITY + " text not null default ' ', "
            + COLUMN_STATE + " text not null default ' ', "
            + COLUMN_COUNTRY + " text not null default ' ',"
            + COLUMN_NAME + " text not null default ' ');";
    //ID, CITY,STATE, COUNTRY
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

}