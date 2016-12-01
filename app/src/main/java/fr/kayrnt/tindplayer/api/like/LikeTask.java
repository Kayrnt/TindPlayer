package fr.kayrnt.tindplayer.api.like;

import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Random;

import fr.kayrnt.tindplayer.R;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;
import fr.kayrnt.tindplayer.model.Profile;


/**
 * Created by Kayrnt on 07/12/14.
 */
public class LikeTask extends AsyncTask<Void, Void, Void> {

    private final int likeDelay;
    private final int likeJitter;
    private TinderAPI tinderAPI;
    private ProfileListFragment fragment;
    private Random random = new Random();

    public LikeTask(TinderAPI tinderAPI, ProfileListFragment fragment) {
        super();
        this.tinderAPI = tinderAPI;
        this.fragment = fragment;
        this.likeDelay = tinderAPI.mPrefs.getInt(fragment.getString(R.string.pref_liker_time), 1000);
        this.likeJitter = tinderAPI.mPrefs.getInt(fragment.getString(R.string.pref_liker_jitter), 100);
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

        while (!tinderAPI.profiles.isEmpty()) {
            Profile profile;
            synchronized (tinderAPI.profiles) {
                profile = tinderAPI.profiles.poll();

                if (profile != null) {
                    //sleep to avoid too many requests
                    try {
                        Thread.sleep(likeDelay + random.nextInt(likeJitter));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tinderAPI.likeProfile(profile, profile.shouldLike);
                }
            }
        }

        tinderAPI.saveProfileHistory();

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        fragment.updateListUI();
        fragment.getMoreProfileAndUpdateUI();

    }
}
