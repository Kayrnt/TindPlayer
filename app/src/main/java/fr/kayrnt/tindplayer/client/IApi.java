package fr.kayrnt.tindplayer.client;

import android.app.Activity;

import fr.kayrnt.tindplayer.fragment.ProfileListFragment;
import fr.kayrnt.tindplayer.model.Profile;

/**
 * Created by Kayrnt on 21/12/14.
 */
public interface IApi {

    public void requestUsers();
    public void auth(Activity activity);
    public void setupHistory();
    public void likeProfile(Profile profile, boolean shouldLike);
    public void saveProfileHistory();
    public void getProfiles(ProfileListFragment fragment);
    public void addLikedProfile(Profile profile);

}
