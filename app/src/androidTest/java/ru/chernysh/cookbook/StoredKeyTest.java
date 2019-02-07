package ru.chernysh.cookbook;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class StoredKeyTest {

    @Test
    public void testStoredKey() {

        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        App.getInstance().setContext(appContext);

        StoredKey storedKey = new StoredKey("test");

        final Random random = new Random();
        int storedValue = random.nextInt();
        storedKey.set(storedValue);
        int restoredValue = storedKey.get();

        assertEquals(storedValue, restoredValue);
    }

}