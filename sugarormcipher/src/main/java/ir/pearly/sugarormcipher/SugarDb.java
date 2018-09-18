package ir.pearly.sugarormcipher;

import android.content.Context;
import android.util.Log;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import ir.pearly.sugarormcipher.util.ManifestHelper;
import ir.pearly.sugarormcipher.util.SugarCursorFactory;

import static ir.pearly.sugarormcipher.util.ManifestHelper.getDatabaseVersion;
import static ir.pearly.sugarormcipher.util.ManifestHelper.getDebugEnabled;

//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;


public class SugarDb extends SQLiteOpenHelper {

    private final SchemaGenerator schemaGenerator;
    private SQLiteDatabase sqLiteDatabase;
    private int openedConnections = 0;
    private Context context;

    public SugarDb(Context context) {

        super(context, ManifestHelper.getDatabaseName(context),
                new SugarCursorFactory(getDebugEnabled(context)), getDatabaseVersion(context));
        this.context=context;
        schemaGenerator = new SchemaGenerator(context);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        schemaGenerator.createDatabase(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        schemaGenerator.doUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }

//    public synchronized SQLiteDatabase getDB() {
//        if (this.sqLiteDatabase == null) {
//            this.sqLiteDatabase = getWritableDatabase();
//
//        }
//
//        return this.sqLiteDatabase;
//    }
//
//    @Override
//    public synchronized SQLiteDatabase getReadableDatabase() {
//        Log.d("SUGAR", "getReadableDatabase");
//        openedConnections++;
//        return super.getReadableDatabase();
//
//    }


    public synchronized SQLiteDatabase getDB() {
        if (this.sqLiteDatabase == null) {

            SQLiteDatabase.loadLibs(context);
            this.sqLiteDatabase = getWritableDatabase(SugarContext.getEncryptKey());
        }

        return this.sqLiteDatabase;
    }


    public synchronized SQLiteDatabase getReadableDatabase() {
        Log.d("SUGAR", "getReadableDatabase");
        openedConnections++;
        return super.getReadableDatabase(SugarContext.getEncryptKey());
    }

    @Override
    public synchronized void close() {
        Log.d("SUGAR", "getReadableDatabase");
        openedConnections--;
        if(openedConnections == 0) {
            Log.d("SUGAR", "closing");
            super.close();
        }
    }
}
