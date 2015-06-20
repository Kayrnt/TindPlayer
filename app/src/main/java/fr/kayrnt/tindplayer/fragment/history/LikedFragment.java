package fr.kayrnt.tindplayer.fragment.history;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.ProfileHistory;

public class LikedFragment extends HistoryBasedFragment {

    @Override
    String getType() {
        return "liked";
    }

    @Override
    ProfileHistory getHistory() {
        return TinderAPI.getInstance().likedProfiles;
    }

    @Override
    protected void refreshItems() {
        synchronized (TinderAPI.getInstance().likedProfiles.profiles) {
            profileAdapter.updateWith(TinderAPI.getInstance().likedProfiles.profiles);
        }
    }
}