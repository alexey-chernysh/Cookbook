package ru.chernysh.cookbook;

import android.app.Application;
import android.content.Context;

import java.util.Locale;

public class App extends Application {

    private static App instance;

    private static String language;

    private static Context context;

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
    }
}