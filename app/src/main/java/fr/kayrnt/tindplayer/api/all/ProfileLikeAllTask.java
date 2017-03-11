package fr.kayrnt.tindplayer.api.all;

import android.os.AsyncTask;

import fr.kayrnt.tindplayer.api.profile.ProfileAPIErrorListener;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;


/**
 * Created by Kayrnt on 07/12/14.
 */
public class ProfileLikeAllTask extends AsyncTask<Void, Void, Void> {

    private TinderAPI tinderAPI;
    private ProfileListFragment fragment;

    public ProfileLikeAllTask(TinderAPI tinderAPI, ProfileListFragment fragment) {
        super();
        this.tinderAPI = tinderAPI;
        this.fragment = fragment;
    }

    @Override
    protected Void doInBackground(Void... params) {

        if (tinderAPI.sessionManager.getTinderToken() != null) {
            //lets get some profiles if none
            if (tinderAPI.profiles.isEmpty())
                tinderAPI.getProfiles(fragment,
                        new ProfileAllAPIListener(tinderAPI, fragment),
                        new APIAllErrorListener(tinderAPI, fragment));
            else {
                // else like the one we have first
                new ProfileAllAPIListener(tinderAPI, fragment).likeAll();
            }

        }
        return null;
    }
}
