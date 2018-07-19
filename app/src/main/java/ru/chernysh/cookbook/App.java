package ru.chernysh.cookbook;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import java.io.File;
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

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

    @Override
    public void  onCreate(){
        super.onCreate();

        instance = this;
        context = this.getApplicationContext();
        language = Locale.getDefault().getLanguage();
        appDatabase = openDatabase();
    }

    private static AppDatabase openDatabase(){
        final File dbPath = context.getDatabasePath(context.getString(R.string.db_name));
        if(!dbPath.exists())  copyInitialDBFromAssets();
        RoomDatabase.Builder<AppDatabase> dbBuilder = Room.databaseBuilder(context, AppDatabase.class, dbPath.getName());
        AppDatabase db = dbBuilder.build();
        return db;
    }

    /**
     * Копирует файл базы данных из Assets в директорию для баз данных этого
     * приложения
     */
    private static void copyInitialDBFromAssets() {

        try {
            final InputStream inStream = context.getAssets().open(context.getString(R.string.db_name));
            String dbPath = getDBPath();
            File file = new File(dbPath);
            file.getParentFile().mkdirs(); // Will create parent directories if not exists
            file.createNewFile();
            final OutputStream outStream  = new FileOutputStream(file, false);

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

    private static String getDBPath(){
        return context.getString(R.string.sd_card)
                 + context.getPackageName()
                 + "/databases/"
                 + context.getString(R.string.db_name);
    }

}