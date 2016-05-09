package fr.kayrnt.tindplayer.api.all;

import android.os.AsyncTask;

import fr.kayrnt.tindplayer.R;
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
        fragment.setupLikeAllAlertDialog();
    }

    @Override
    protected Void doInBackground(Void... params) {

        if (tinderAPI.session.getTinderToken() != null) {
            //lets get some profiles
            if (tinderAPI.profiles.isEmpty())
                tinderAPI.getProfiles(fragment,
                        new ProfileAllAPIListener(tinderAPI, fragment),
                        new APIAllErrorListener(tinderAPI));
                // ok like the one we have first
            else {
                new ProfileAllAPIListener(tinderAPI, fragment).likeAll();
            }

        }
        return null;
    }
}
