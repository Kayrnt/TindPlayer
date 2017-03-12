package fr.kayrnt.tindplayer;

import android.content.SharedPreferences;
import android.support.multidex.MultiDexApplication;


import fr.kayrnt.tindplayer.utils.SessionManager;

import com.crashlytics.android.Crashlytics;

import java.util.concurrent.TimeUnit;

import io.fabric.sdk.android.Fabric;


public class MyApplication extends MultiDexApplication {
    private static MyApplication myApplication;
    private static SessionManager sessionManager;
    private static SharedPreferences preferences;

    public static String sharedAndroidPrefKey = "AndroidPref";

    public static MyApplication getInstance() {
        if (myApplication == null)
            myApplication = new MyApplication();
        return myApplication;
    }

    public static SessionManager session() {
        if (sessionManager == null)
            sessionManager = new SessionManager(myApplication);
        return sessionManager;
    }

    public static SharedPreferences getSharedPreferences() {
        if (preferences == null)
            preferences = myApplication.getSharedPreferences(sharedAndroidPrefKey, MODE_PRIVATE);
        return preferences;
    }

    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        myApplication = this;
        preferences = getSharedPreferences("AndroidPref", MODE_PRIVATE);
        sessionManager = new SessionManager(this);
    }
}
