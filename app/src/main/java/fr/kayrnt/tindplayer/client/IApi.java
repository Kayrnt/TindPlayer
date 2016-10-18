package fr.kayrnt.tindplayer.client;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import fr.kayrnt.tindplayer.activity.FriendListActivity;
import fr.kayrnt.tindplayer.fragment.ProfileDetailFragment;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;
import fr.kayrnt.tindplayer.fragment.history.MatchedFragment;
import fr.kayrnt.tindplayer.model.Profile;

/**
 * Created by Kayrnt on 21/12/14.
 */
public interface IApi {

    public void requestUsers();
    public void auth(Activity activity);
    public void auth(Activity activity, int retryRemaining);
    public void setupHistory();
    public void likeProfile(Profile profile, boolean shouldLike);
    public void saveProfileHistory();
    public void getProfiles(ProfileListFragment fragment);
    public void addLikedProfile(Profile profile);
    public void getFriends(FriendListActivity activity);
    public void getMatches(MatchedFragment fragment);
    void getProfile(ProfileDetailFragment fragment, String userId);
}
