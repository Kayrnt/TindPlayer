package fr.kayrnt.tindplayer.fragment.history;

import fr.kayrnt.tindplayer.api.match.MatchesFetchTask;
import fr.kayrnt.tindplayer.client.TinderAPI;
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
        synchronized (TinderAPI.getInstance().matchedProfiles.profiles) {
            profileAdapter.updateWith(TinderAPI.getInstance().matchedProfiles.profiles);
        }
    }


    @Override
    protected void refreshItems() {
        new MatchesFetchTask(TinderAPI.getInstance(),this).execute();
        updateUI();
    }
}