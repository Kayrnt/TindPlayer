package fr.kayrnt.tindplayer.api.like;

import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Random;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;
import fr.kayrnt.tindplayer.model.Profile;


/**
 * Created by Kayrnt on 07/12/14.
 */
public class LikeTask extends AsyncTask<Void, Void, Void> {

    private TinderAPI tinderAPI;
    private ProfileListFragment fragment;
    private Random random = new Random();
    private boolean updateHistory = false;

    public LikeTask(TinderAPI tinderAPI, ProfileListFragment fragment) {
        super();
        this.tinderAPI = tinderAPI;
        this.fragment = fragment;
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
                        Thread.sleep(100L + random.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tinderAPI.likeProfile(profile, profile.shouldLike);
                }
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        fragment.updateListUI();
        fragment.updateProfileList();

    }
}
