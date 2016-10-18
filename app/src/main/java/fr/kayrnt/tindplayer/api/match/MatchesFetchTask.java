package fr.kayrnt.tindplayer.api.match;

import android.os.AsyncTask;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileDetailFragment;
import fr.kayrnt.tindplayer.fragment.history.MatchedFragment;


/**
 * Created by Kayrnt on 07/12/14.
 */
public class MatchesFetchTask extends AsyncTask<Void, Void, Void> {

    private TinderAPI tinderAPI;
    private MatchedFragment fragment;

    public MatchesFetchTask(TinderAPI tinderAPI, MatchedFragment fragment) {
        super();
        this.tinderAPI = tinderAPI;
        this.fragment = fragment;
    }

    @Override
    protected Void doInBackground(Void... params) {

        if (tinderAPI.sessionManager.getTinderToken() != null)
            tinderAPI.getMatches(fragment);

        return null;
    }

}
