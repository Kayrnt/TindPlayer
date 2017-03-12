package fr.kayrnt.tindplayer.fragment.history;

import android.util.Log;

import java.util.LinkedList;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.Profile;
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
        final LinkedList<Profile> likedProfiles = TinderAPI.getInstance().likedProfiles.profiles;
        synchronized (TinderAPI.getInstance().likedProfiles.profiles) {
            Log.i(this.getClass().getSimpleName(), "Loading "+ likedProfiles.size() + " profiles");
            profileAdapter.updateWith(likedProfiles);
        }
    }
}