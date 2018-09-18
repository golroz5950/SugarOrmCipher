package ir.pearly.sugarormcipher;

import android.content.Context;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import ir.pearly.sugarormcipher.SugarDb;

public class SugarContext {

    private static SugarContext instance = null;
    private ir.pearly.sugarormcipher.SugarDb sugarDb;
    private Map<Object, Long> entitiesMap;
    private static String dbName;
    private static int version;
    private static String domainPakageName;
    private static boolean queryLog;
    private static String encryptKey;

    public static String getDbName() {
        return dbName;
    }
    public static void setDbName(String dbName_) {
        dbName = dbName_;
    }

    public static int getVersion() {
        return version;
    }

    public static void setVersion(int version_) {
        version = version_;
    }

    public static String getDomainPakageName() {
        return domainPakageName;
    }

    public static void setDomainPakageName(String domainPakageName_) {
        domainPakageName = domainPakageName_;
    }

    public static boolean getQueryLog() {
        return queryLog;
    }

    public static void setQueryLog(boolean queryLog_) {
        queryLog = queryLog_;
    }

    public static String getEncryptKey() {
        return encryptKey;
    }

    public static void setEncryptKey(String encryptKey_) {
        encryptKey = encryptKey_;
    }

    private SugarContext(Context context) {
        this.sugarDb = new ir.pearly.sugarormcipher.SugarDb(context);
        this.entitiesMap = Collections.synchronizedMap(new WeakHashMap<Object, Long>());
    }
    
    public static SugarContext getSugarContext() {
        if (instance == null) {
            throw new NullPointerException("SugarContext has not been initialized properly. Call SugarContext.init(Context) in your Application.onCreate() method and SugarContext.terminate() in your Application.onTerminate() method.");
        }
        return instance;
    }

    public static void init(Context context) {
        instance = new SugarContext(context);
    }

    public static void terminate() {
        if (instance == null) {
            return;
        }
        instance.doTerminate();
    }

    /*
     * Per issue #106 on Github, this method won't be called in
     * any real Android device. This method is used purely in
     * emulated process environments such as an emulator or
     * Robolectric Android mock.
     */
    private void doTerminate() {
        if (this.sugarDb != null) {
            this.sugarDb.getDB().close();
        }
    }

    protected SugarDb getSugarDb() {
        return sugarDb;
    }

    Map<Object, Long> getEntitiesMap() {
        return entitiesMap;
    }
}
