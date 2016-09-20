package goodkovapps.cleanapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sillybird on 20.09.2016.
 */
public class DBHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CleanApp";
    public static final String TABLE_USER = "user";

    /**
     * Константы наименований столбцов таблицы USER
     */
    public static final String KEY_ID = "_id";
    public static final String KEY_PHONE = "phone";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * При создании базы, создастся таблица юзверя с полями ID и PHONE
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_USER +"(" + KEY_ID +" integer primary key," + KEY_PHONE + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
