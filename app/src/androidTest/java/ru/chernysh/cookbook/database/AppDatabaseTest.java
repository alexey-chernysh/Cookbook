package ru.chernysh.cookbook.database;

import android.util.Log;

import org.junit.Test;

import java.util.List;

import ru.chernysh.cookbook.App;

import static org.junit.Assert.*;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;


public class AppDatabaseTest {

    private static final String LOG_TAG = AppDatabaseTest.class.getName() + ": ";

    @Test
    public void materialDao() {

        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        App.getInstance().setContext(appContext);

        AppDatabase db = App.getInstance().getAppDatabase();

        int[] ids = {0,1,2};
        List<Material> listOfMaterial =  db.materialDao().loadAllByIds(ids);
        for(int i = 0; i<ids.length; i++)
            Log.d(LOG_TAG, listOfMaterial.get(i).toString());

    }
}