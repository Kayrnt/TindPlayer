package fr.kayrnt.tindplayer.fragment.history;

import fr.kayrnt.tindplayer.client.TinderAPI;
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
        synchronized (TinderAPI.getInstance().passedProfiles.profiles) {
            profileAdapter.updateWith(TinderAPI.getInstance().passedProfiles.profiles);
        }
    }
}