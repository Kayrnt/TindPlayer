package fr.kayrnt.tindplayer.activity;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import fr.kayrnt.tindplayer.MyApplication;
import fr.kayrnt.tindplayer.R;

/**
 * by Kayrnt
 * 10/07/16 18:02
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new GeneralPrefFragment()).commit();
    }


    /**
     * This fragment shows the preferences for the first header.
     */
    public static class GeneralPrefFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            getPreferenceManager().setSharedPreferencesName(MyApplication.sharedAndroidPrefKey);

            // Make sure default values are applied.  In a real app, you would
            // want this in a shared function that is used to retrieve the
            // SharedPreferences wherever they are needed.
            PreferenceManager.setDefaultValues(getActivity(),
                    R.xml.preference_general, false);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preference_general);
        }

    }

}
