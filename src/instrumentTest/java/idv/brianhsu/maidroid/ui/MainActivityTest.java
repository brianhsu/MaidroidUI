package idv.brianhsu.maidroid.ui;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class idv.brianhsu.maidroid.ui.MainActivityTest \
 * idv.brianhsu.maidroid.ui.tests/android.test.InstrumentationTestRunner
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super("idv.brianhsu.maidroid.ui", MainActivity.class);
    }

}
