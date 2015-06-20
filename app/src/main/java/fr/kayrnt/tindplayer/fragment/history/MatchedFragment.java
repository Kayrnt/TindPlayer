package fr.kayrnt.tindplayer.fragment.history;

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

    @Override
    protected void refreshItems() {
        synchronized (TinderAPI.getInstance().matchedProfiles.profiles) {
            profileAdapter.updateWith(TinderAPI.getInstance().matchedProfiles.profiles);
        }
    }
}