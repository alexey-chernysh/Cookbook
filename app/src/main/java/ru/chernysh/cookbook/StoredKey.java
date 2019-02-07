/*
 * Copyright (c) 2015. Alexey Chernysh? Krasnoyarsk, Russia
 * e-mail: ALEXEY DOT CHERNYSH AT GMAIL DOT COM.
 */

package ru.chernysh.cookbook;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.util.Log;

public class StoredKey {

    private final static String LOG_TAG = "Selection field";

    private String requestTag;

    public StoredKey(@NonNull String name){
        if(name.length() <= 0) throw new SQLiteException("Call SelectionField constructor with NULL field name. Real table name needed.");;
        final String prefix = App.getInstance().getResourceString(R.string.preference_prefix);
        requestTag = prefix + ' ' + name;
    }

    public void set(int newKey){
        SharedPreferences sPref = App.getInstance().getContext().getSharedPreferences(null, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(requestTag, Integer.toString(newKey)).apply();
        Log.d(LOG_TAG, "Value " + newKey + " is saved for " + requestTag);
    }

    public int get(){
        SharedPreferences sPref = App.getInstance().getContext().getSharedPreferences(null, Context.MODE_PRIVATE);
        String savedText = sPref.getString(requestTag, "");
        Log.d(LOG_TAG, "Value " + savedText + " is loaded for " + requestTag);
        if(savedText.length() <= 0) return 0;
        return Integer.valueOf(savedText);
    }

}
