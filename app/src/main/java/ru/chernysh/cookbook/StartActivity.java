package ru.chernysh.cookbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import ru.chernysh.cookbook.database.AppDatabase;
import ru.chernysh.cookbook.database.Material;

public class StartActivity extends AppCompatActivity {

    private static final String LOG_TAG = StartActivity.class.getName() + ": ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

    }
}
