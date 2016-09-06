package fr.kayrnt.tindplayer.api.detail;

import android.os.AsyncTask;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileDetailFragment;


/**
 * Created by Kayrnt on 07/12/14.
 */
public class ProfileDetailFetchTask extends AsyncTask<Void, Void, Void> {

    private TinderAPI tinderAPI;
    private ProfileDetailFragment fragment;
    private final String userId;

    public ProfileDetailFetchTask(TinderAPI tinderAPI, ProfileDetailFragment fragment, String
            userId) {
        super();
        this.tinderAPI = tinderAPI;
        this.fragment = fragment;
        this.userId = userId;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {

        if (tinderAPI.sessionManager.getTinderToken() != null)
            tinderAPI.getProfile(fragment, userId);

        return null;
    }

}
