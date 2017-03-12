package fr.kayrnt.tindplayer.fragment.history;

import android.util.Log;

import java.util.LinkedList;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.Profile;
import fr.kayrnt.tindplayer.model.ProfileHistory;

public class PassedFragment extends HistoryBasedFragment {

    @Override
    String getType() {
        return "passed";
    }

    @Override
    ProfileHistory getHistory() {
        return TinderAPI.getInstance().passedProfiles;
    }

    @Override
    protected void refreshItems() {
        final LinkedList<Profile> passedProfiles = TinderAPI.getInstance().passedProfiles.profiles;
        synchronized (TinderAPI.getInstance().passedProfiles.profiles) {
            Log.i(this.getClass().getSimpleName(), "Loading "+ passedProfiles.size() + " profiles");
            profileAdapter.updateWith(passedProfiles);
        }
    }
}