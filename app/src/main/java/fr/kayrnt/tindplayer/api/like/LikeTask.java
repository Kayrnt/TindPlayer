package fr.kayrnt.tindplayer.api.like;

import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Random;

import fr.kayrnt.tindplayer.R;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;
import fr.kayrnt.tindplayer.model.Profile;
import fr.kayrnt.tindplayer.utils.PrefUtils;


/**
 * Created by Kayrnt on 07/12/14.
 */
public class LikeTask extends AsyncTask<Void, Void, Void> {

//    private final int likeDelay;
//    private final int likeJitter;
    private TinderAPI tinderAPI;
    private ProfileListFragment fragment;
//    private Random random = new Random();

    public LikeTask(TinderAPI tinderAPI, ProfileListFragment fragment) {
        super();
        this.tinderAPI = tinderAPI;
        this.fragment = fragment;
//        this.likeDelay = Math.min(PrefUtils.safeGetInt(tinderAPI.mPrefs, "liker_ms_between_likes", 1000), 1);
//        this.likeJitter = Math.min(PrefUtils.safeGetInt(tinderAPI.mPrefs, "liker_ms_jitter", 100), 1);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        fragment.likedCount.setText("Liking selected targets (Sending requests)");
        fragment.likeButton.setClickable(false);
        if (tinderAPI.profiles.size() == 0) {
            fragment.updateListUI();
            Toast toast = Toast.makeText(fragment.getActivity(), "Trying to refresh ... but you may be " +
                    "out of targets !", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected Void doInBackground(Void... params) {

        synchronized (tinderAPI.profiles) {
            tinderAPI.likeProfiles(tinderAPI.profiles, fragment, false);
        }
        return null;
    }

}
