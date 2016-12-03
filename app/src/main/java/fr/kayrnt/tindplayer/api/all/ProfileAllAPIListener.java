package fr.kayrnt.tindplayer.api.all;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;

import java.util.ArrayList;
import java.util.Random;

import fr.kayrnt.tindplayer.R;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;
import fr.kayrnt.tindplayer.model.Profile;
import fr.kayrnt.tindplayer.model.RecResponse;
import fr.kayrnt.tindplayer.utils.PrefUtils;


/**
 * Created by Kayrnt on 06/12/14.
 */
public class ProfileAllAPIListener
        implements Response.Listener<RecResponse> {
    private final int likeDelay;
    private final int likeJitter;
    TinderAPI tinderAPI;
    private ProfileListFragment fragment;
    private Random random = new Random();

    ProfileAllAPIListener(TinderAPI tinderAPI, final ProfileListFragment fragment) {
        this.tinderAPI = tinderAPI;
        this.fragment = fragment;
        this.likeDelay = PrefUtils.safeGetInt(tinderAPI.mPrefs, fragment.getString(R.string.pref_liker_time), 1000);
        this.likeJitter = Math.min(PrefUtils.safeGetInt(tinderAPI.mPrefs, fragment.getString(R.string.pref_liker_jitter), 100), 1);
    }

    void likeAll() {
        while (!tinderAPI.profiles.isEmpty()) {
            synchronized (tinderAPI.profiles) {
                Profile profile = tinderAPI.profiles.poll();
                if (profile != null) {
                    //sleep to avoid too many requests
                    try {
                        Thread.sleep(likeDelay + random.nextInt(likeJitter));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //stop process when we are done
                    tinderAPI.likeProfile(profile, true);
                    fragment.updateLikeAllCount();
                }
            }
            try {
                Thread.sleep(likeDelay + random.nextInt(likeJitter));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Log.i("ProfileAll", "Finished batch...");
        fragment.updateLikeCount();

        if (!fragment.stopLikeAll) new ProfileLikeAllTask(tinderAPI, fragment).execute();
        else {
            tinderAPI.saveProfileHistory();
            Activity activity = fragment.getActivity();
            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stop();
                    }
                });
            }
        }
    }

    private void stop() {
        fragment.getMoreProfileAndUpdateUI();
        fragment.updateListUI();
    }

    @Override
    public void onResponse(final RecResponse recResponse) {
        Log.i("Profile All Listener", "response : " + recResponse);
        ArrayList<Profile> filteredProfiles = new ArrayList<>();
        if ((recResponse != null) && (recResponse.profiles != null)) {
            for (Profile profile : recResponse.profiles) {
                String id = profile.getId();
                if (id != null && !id.contains("tinder_rate_limited_id"))
                    filteredProfiles.add(profile);
            }
        }
        if (!filteredProfiles.isEmpty()) {
            synchronized (tinderAPI.profiles) {
                tinderAPI.profiles.addAll(filteredProfiles);
            }
            likeAll();
        } else {
            //it's over !
            toast("We exhausted the target pool ! Try again later :)");
            Log.i("ProfileAll", "We exhausted the target pool ! Try again later :)");
            fragment.stopLikeAllListener.onClick(null);
            Activity activity = fragment.getActivity();
            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fragment.updateListUI();
                    }
                });
                fragment.disableLikeAllAlertDialog();
            }
        }
    }

    private void toast(final String newMessage) {
        Activity activity = fragment.getActivity();
        try {
            if (!activity.isFinishing()) {
                Toast.makeText(activity, newMessage, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("ProfileAllAPIListener", "Can't test", e);
        }
    }

}