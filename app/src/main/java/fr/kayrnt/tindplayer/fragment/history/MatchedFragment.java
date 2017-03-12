package fr.kayrnt.tindplayer.fragment.history;

import android.util.Log;

import java.util.LinkedList;

import fr.kayrnt.tindplayer.api.match.MatchesFetchTask;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.Profile;
import fr.kayrnt.tindplayer.model.ProfileHistory;

public class MatchedFragment extends HistoryBasedFragment {

    @Override
    String getType() {
        return "matched";
    }

    @Override
    ProfileHistory getHistory() {
        return TinderAPI.getInstance().matchedProfiles;
    }

    public void updateUI() {
        final LinkedList<Profile> matchedProfiles = TinderAPI.getInstance().matchedProfiles.profiles;
        synchronized (TinderAPI.getInstance().matchedProfiles.profiles) {
            Log.i(this.getClass().getSimpleName(), "Loading "+ matchedProfiles.size() + " profiles");
            profileAdapter.updateWith(matchedProfiles);
        }
    }


    @Override
    protected void refreshItems() {
        new MatchesFetchTask(TinderAPI.getInstance(),this).execute();
        updateUI();
    }
}