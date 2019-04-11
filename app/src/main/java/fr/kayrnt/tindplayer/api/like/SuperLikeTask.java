package fr.kayrnt.tindplayer.api.like;

import android.os.AsyncTask;
import android.widget.Toast;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileDetailFragment;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;
import fr.kayrnt.tindplayer.model.Profile;


public class SuperLikeTask extends AsyncTask<Void, Void, Void> {

    private TinderAPI tinderAPI;
    private ProfileDetailFragment fragment;
    private final Profile profile;

    public SuperLikeTask(TinderAPI tinderAPI, ProfileDetailFragment fragment, Profile profile) {
        super();
        this.tinderAPI = tinderAPI;
        this.fragment = fragment;
        this.profile = profile;
    }

    @Override
    protected Void doInBackground(Void... params) {

        synchronized (tinderAPI.profiles) {
            tinderAPI.superLikeProfile(fragment, profile);
        }
        return null;
    }

}
