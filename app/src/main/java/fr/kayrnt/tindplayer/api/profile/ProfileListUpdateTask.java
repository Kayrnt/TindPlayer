package fr.kayrnt.tindplayer.api.profile;

import android.os.AsyncTask;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;


/**
 * Created by Kayrnt on 07/12/14.
 */
public class ProfileListUpdateTask extends AsyncTask<Void, Void, Void> {

    private TinderAPI tinderAPI;
    private ProfileListFragment fragment;

    public ProfileListUpdateTask(TinderAPI tinderAPI, ProfileListFragment fragment) {
        super();
        this.tinderAPI = tinderAPI;
        this.fragment = fragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        fragment.likedCount.setText("Updating profile list...");
    }

    @Override
    protected Void doInBackground(Void... params) {

        if (tinderAPI.sessionManager.getTinderToken() != null)
            tinderAPI.getProfiles(fragment);

        return null;
    }

}
