package fr.kayrnt.tindplayer.utils;

import android.content.Context;
import android.preference.EditTextPreference;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Kayrnt on 01/12/2016.
 */

public class IntEditTextPreference extends EditTextPreference {

    public IntEditTextPreference(Context context) {
        super(context);
    }

    public IntEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IntEditTextPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected String getPersistedString(String defaultReturnValue) {
        try {
            if(defaultReturnValue == null) defaultReturnValue = "100";
            int defaultValue = Integer.parseInt(defaultReturnValue);
            return String.valueOf(getPersistedInt(defaultValue));
        } catch (Exception e){
            Log.e("Int edit", "get persisted string"+ defaultReturnValue, e);
            return "1";
        }

    }

    @Override
    protected boolean persistString(String value) {
        try {
            return persistInt(Integer.valueOf(value));
        } catch (Exception e){
            Log.e("Int edit", "persist from "+value, e);
            return false;
        }
    }
}