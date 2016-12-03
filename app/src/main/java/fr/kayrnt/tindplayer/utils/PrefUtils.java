package fr.kayrnt.tindplayer.utils;

import android.content.SharedPreferences;

/**
 * Created by Kayrnt on 02/12/2016.
 */

public class PrefUtils {

    public static int safeGetInt(SharedPreferences prefs, String key, int defaultValue){
        try {
            return prefs.getInt(key, defaultValue);
        } catch (ClassCastException e) {
            return Integer.valueOf(prefs.getString(key, null));
        }
    }

}
