package ru.chernysh.cookbook;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import ru.chernysh.cookbook.database.AppDatabase;

public class App extends Application {

    private static final String LOG_TAG = App.class.getName() + ": ";

    private static final int DB_FILES_COPY_BUFFER_SIZE = 8192;

    private static App instance;

    private static String language;

    private static Context context;

    private static AppDatabase appDatabase;

    public static App getInstance() {
        if(instance == null)  instance = new App();
        return instance;
    }

    public void setContext(Context cntxt) {
        context = cntxt;
    }

    public String getResourceString(int id){
        return instance.getString(id);
    }

    public String getLanguaget() {
        return language;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void  onCreate(){
        super.onCreate();

        instance = this;
        context = this.getApplicationContext();
        language = Locale.getDefault().getLanguage();
        appDatabase = getDatabase();
    }

    private static AppDatabase getDatabase(){
        if(!databaseFileExist())  copyInitialDBFromAssets();
        return openExistingDatabase();
    }

    private static AppDatabase openExistingDatabase() {
        return Room.databaseBuilder(context, AppDatabase.class, getDBPath()).build();
    }

    private static boolean databaseFileExist() {
        final File dbPath = context.getDatabasePath(getDBName());
        return dbPath.exists();
    }

    /**
     * Копирует файл базы данных из Assets в директорию для баз данных этого
     * приложения
     */
    private static void copyInitialDBFromAssets() {

        InputStream inStream = null;
        OutputStream outStream = null;

        try {
            inStream = new BufferedInputStream(context.getAssets().open(getDBName()), DB_FILES_COPY_BUFFER_SIZE);
            String db_folder = getDBDir();
            File dbDir = new File(db_folder);
            if (!dbDir.exists())
                if(!dbDir.mkdir())
                    throw new IOException("Can't create database dir");
            String dbPath = getDBPath();
            outStream = new BufferedOutputStream(new FileOutputStream(dbPath), DB_FILES_COPY_BUFFER_SIZE);

            byte[] buffer = new byte[DB_FILES_COPY_BUFFER_SIZE];
            int length;
            while ((length = inStream.read(buffer)) > 0)
                outStream.write(buffer, 0, length);

            outStream.flush();
            outStream.close();
            inStream.close();

        } catch (IOException ex) {
            // Что-то пошло не так
            Log.e(LOG_TAG, ex.getMessage());
        }
    }

    private static String getDBDir(){
        return    App.getInstance().getString(R.string.sd_card)
                + App.getInstance().getPackageName();
    }

    private static String getDBPath(){
        return    getDBDir()
                + "/databases/"
                + getDBName();
    }

    private static String getDBName() {
        return App.getInstance().getString(R.string.db_name);
    }

}