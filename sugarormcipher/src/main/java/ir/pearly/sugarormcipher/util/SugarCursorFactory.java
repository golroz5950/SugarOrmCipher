package ir.pearly.sugarormcipher.util;

//import android.database.Cursor;
//import android.database.sqlite.SQLiteCursor;
//import android.database.sqlite.SQLiteCursorDriver;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteQuery;
import android.util.Log;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteCursor;
import net.sqlcipher.database.SQLiteCursorDriver;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteQuery;

public class SugarCursorFactory implements SQLiteDatabase.CursorFactory {

    private boolean debugEnabled;

    public SugarCursorFactory() {
        this.debugEnabled = false;
    }

    public SugarCursorFactory(boolean debugEnabled) {

        this.debugEnabled = debugEnabled;
    }

    @SuppressWarnings("deprecation")
    public Cursor newCursor(SQLiteDatabase sqLiteDatabase,
                            SQLiteCursorDriver sqLiteCursorDriver,
                            String editTable,
                            SQLiteQuery sqLiteQuery) {

        if (debugEnabled) {
            Log.d("SQL Log", sqLiteQuery.toString());
        }

        return new SQLiteCursor(sqLiteDatabase, sqLiteCursorDriver, editTable, sqLiteQuery);
    }

}
